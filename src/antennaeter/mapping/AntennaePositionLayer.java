package antennaeter.mapping;

import com.bbn.openmap.layer.OMGraphicHandlerLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.OMGraphicList;

public class AntennaePositionLayer extends OMGraphicHandlerLayer {
	public AntennaePositionLayer() {
		setName("AntennaePositionLayer");
		setRenderPolicy(new BufferedImageRenderPolicy());
	}

	public synchronized OMGraphicList prepare() {
		OMGraphicList list = getList();
		list.generate(getProjection());
		return list;
	}

}
