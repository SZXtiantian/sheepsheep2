package test;



import java.util.List;

import model.ExtraLayer;
import model.Layer;
import model.Map;
import util.ExtraLayerUtil;
import util.LayerUtil;

public class TestQueue {
	public static void main(String[] args) {
		Map map = new Map();
		map.setFloorHeight(3);
		Layer layer1 = LayerUtil.build(3, 3);
		Layer layer2 = LayerUtil.build(3, 3);
		Layer layer3 = LayerUtil.build(3, 3);
		
		map.getList().add(layer1);
		map.getList().add(layer2);
		map.getList().add(layer3);
		List<Layer>list = map.getList();
		ExtraLayer extraLayer = ExtraLayerUtil.build();
		map.getExtraList().add(extraLayer);
		List<ExtraLayer>list2 = map.getExtraList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("第"+i+"个图层");
			list.get(i).showCells();
		}
		for (int i = 0; i < list2.size(); i++) {
			System.out.println("第"+i+"个图层");
			list2.get(i).showCells();
		}
	}
}
