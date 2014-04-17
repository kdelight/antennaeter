package antennaeter.mapping;

/*
 * This class is based off of the SimpleMap2 and SimpleMap classes in the com.bbn.openmap.app.example package of Openmap.
 */
import java.util.Properties;

import com.bbn.openmap.LayerHandler;
import com.bbn.openmap.MapBean;
import com.bbn.openmap.MapHandler;
import com.bbn.openmap.MouseDelegator;
import com.bbn.openmap.event.OMMouseMode;
import com.bbn.openmap.gui.EmbeddedNavPanel;
import com.bbn.openmap.gui.EmbeddedScaleDisplayPanel;
import com.bbn.openmap.gui.LayersPanel;
import com.bbn.openmap.gui.MapPanel;
import com.bbn.openmap.gui.MouseModeButtonPanel;
import com.bbn.openmap.gui.OpenMapFrame;
import com.bbn.openmap.gui.OverlayMapPanel;
import com.bbn.openmap.gui.ToolPanel;
import com.bbn.openmap.layer.GraticuleLayer;
import com.bbn.openmap.layer.shape.ShapeLayer;
import com.bbn.openmap.proj.coords.LatLonPoint;

public class MainMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * The BasicMapPanel automatically creates many default components,
		 * including the MapBean and the MapHandler. You can extend the
		 * BasicMapPanel class if you like to add different functionality or
		 * different types of objects.
		 */
		MapPanel mapPanel = new OverlayMapPanel();

		// Get the default MapHandler the BasicMapPanel created.
		MapHandler mapHandler = mapPanel.getMapHandler();

		// Get the default MapBean that the BasicMapPanel created.
		MapBean mapBean = mapPanel.getMapBean();

		// Set the map's center
		mapBean.setCenter(new LatLonPoint.Double(43.0, -95.0));

		// Set the map's scale 1:120 million
		mapBean.setScale(120000000f);

		/*
		 * Create and add a LayerHandler to the MapHandler. The LayerHandler
		 * manages Layers, whether they are part of the map or not.
		 * layer.setVisible(true) will add it to the map. The LayerHandler has
		 * methods to do this, too. The LayerHandler will find the MapBean in
		 * the MapHandler.
		 */
		mapHandler.add(new LayerHandler());
		// Add navigation tools over the map
		mapHandler.add(new EmbeddedNavPanel());
		// Add scale display widget over the map
		mapHandler.add(new EmbeddedScaleDisplayPanel());
		// Add MouseDelegator, which handles mouse modes (managing mouse
		// events)
		mapHandler.add(new MouseDelegator());
		// Add OMMouseMode, which handles how the map reacts to mouse
		// movements
		mapHandler.add(new OMMouseMode());
		// Add a ToolPanel for widgets on the north side of the map.
		mapHandler.add(new ToolPanel());
		mapHandler.add(new LayersPanel());
		mapHandler.add(new MouseModeButtonPanel());

		// Create a Swing frame. The OpenMapFrame knows how to use
		// the MapHandler to locate and place certain objects.
		OpenMapFrame frame = new OpenMapFrame("Antennaeter");
		// Size the frame appropriately
		frame.setSize(640, 480);

		mapHandler.add(frame);
		// Display the frame
		frame.setVisible(true);

		// Since this Properties object is being used just for
		// this layer, the properties do not have to be scoped
		// with marker name.
		ShapeLayer shapeLayer = new ShapeLayer();
		Properties shapeLayerProps = new Properties();
		shapeLayerProps.put("prettyName", "Political Solid");
		shapeLayerProps.put("lineColor", "000000");
		shapeLayerProps.put("fillColor", "BDDE83");
		shapeLayerProps.put("shapeFile", "data/shape/dcwpo-browse.shp");
		shapeLayerProps.put("spatialIndex", "data/shape/dcwpo-browse.ssx");
		shapeLayer.setProperties(shapeLayerProps);

		// Last on top.
		mapHandler.add(shapeLayer);
		mapHandler.add(new GraticuleLayer());
	}
}
