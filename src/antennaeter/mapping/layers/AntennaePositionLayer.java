package antennaeter.mapping.layers;

/*
 * This layer simply contains a point for each antenna that has been registered with the software.
 */
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import antennaeter.antenna.Antenna;

import com.bbn.openmap.layer.OMGraphicHandlerLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.OMGraphicList;
import com.bbn.openmap.omGraphics.OMLine;
import com.bbn.openmap.omGraphics.OMPoint;

public class AntennaePositionLayer extends OMGraphicHandlerLayer {
	List<Antenna> m_antennaList;
	private static final int POINT_RADIUS = 3;
	private static final int ARROW_LENGTH = 30;
	private static final Color ANTENNA_COLOR = Color.yellow;

	public AntennaePositionLayer(List<Antenna> antennaList) {
		m_antennaList = antennaList;
		if (m_antennaList == null) {
			m_antennaList = new LinkedList<Antenna>();
		}
		setName("AntennaePositionLayer");
		setRenderPolicy(new BufferedImageRenderPolicy());
	}

	public synchronized OMGraphicList prepare() {
		/*
		 * We only actually need to add points to the list if we haven't already
		 * added them. TODO: Keep track of which points need to be added in a
		 * separate list, and only add them to the graphicList if we need to.
		 */
		OMGraphicList graphicList = getList();
		// This is fairly wasteful. See above todo.
		graphicList = new OMGraphicList();
		for (Antenna antenna : m_antennaList) {
			double latitude = antenna.getLatitude();
			double longitude = antenna.getLongitude();
			double heading = antenna.getHeading();
			// Add a point for the position of the antenna
			OMPoint point = new OMPoint(latitude, longitude, POINT_RADIUS);
			point.setFillPaint(ANTENNA_COLOR);
			point.setOval(true);
			graphicList.add(point);
			// Add a line coming out of the point to show which direction the
			// antenna is pointing. Note that heading angle is measured from
			// north, not east.
			OMLine line = new OMLine(latitude, longitude, 0, 0,
					(int) (-ARROW_LENGTH * Math.sin(heading)),
					(int) (ARROW_LENGTH * Math.cos(heading)));
			line.addArrowHead(true);
			line.setLinePaint(ANTENNA_COLOR);
			graphicList.add(line);
		}
		graphicList.generate(getProjection());
		return graphicList;
	}
}
