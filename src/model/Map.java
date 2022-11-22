package model;

import java.util.ArrayList;
import java.util.List;

//import javax.swing.JOptionPane;

import util.MapUtil;

/**
 * 
 * @Description 一个地图，下有多个图层， 层层覆盖
 * @author TimTim Email:754595995@qq.com
 * @version
 * @date 2022年11月4日下午9:29:54
 *
 */
public class Map {
	private Integer floorHeight;
	private Integer extraFloorHeight;

	private List<Layer> list = new ArrayList<>();
	private List<ExtraLayer> extraList = new ArrayList<>();

	public Integer getFloorHeight() {
		return floorHeight;
	}

	public void setFloorHeight(Integer floorHeight) {
		this.floorHeight = floorHeight;
	}

	public List<Layer> getList() {
		return list;
	}

	public void setList(List<Layer> list) {
		this.list = list;
	}
	public List<ExtraLayer> getExtraList() {
		return extraList;
	}

	public void setExtraList(List<ExtraLayer> extraList) {
		this.extraList = extraList;
	}
	public boolean isEmpty() {
		Layer layer = list.get(list.size() - 1);
		Cell[][] cells = layer.getCells();
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				Cell cell = cells[row][col];
				if (cell.getState() == 1) {
					return false;
				}
			}
		}
		if (extraList.size() == 1) {
			ExtraLayer extraLayer = extraList.get(extraList.size() - 1);
			Cell[][] extraCells = extraLayer.getCells();
			for (int row = 0; row < extraCells.length; row++) {
				for (int col = 0; col < extraCells[row].length; col++) {
					Cell extraCell = extraCells[row][col];
					if (extraCell.getState() == 1) {
						return false;
					}
				}
			}			
		}
		return true;
	}
	/*
	 * 性能会差， 牌多 1、开始时调用 2、牌点击后调用
	 */
	public void compareAll() {
		for (int i = 1; i < list.size(); i++) {
			Layer layer = list.get(i);
			Cell[][] cells = layer.getCells();

			for (int row = 0; row < cells.length; row++) {
				for (int col = 0; col < cells[row].length; col++) {
					Cell cell = cells[row][col];
					if (cell.getState() == 1) {
						Brand brand = cell.getBrand();
						boolean result = MapUtil.compare1(brand, layer.getParent());
						brand.setIsGray(result);
					}
				}
			}
		}
		//compareExtra();
	}
	
	public void compareExtra() {
		for (int i = 1; i < extraList.size(); i++) {
			ExtraLayer layer = extraList.get(i);
			Cell[][] cells = layer.getCells();

			for (int col = 0; col < cells[0].length; col++) {
				Cell cell = cells[0][col];
				if (cell.getState() == 1) {
					Brand brand = cell.getBrand();
					boolean result = MapUtil.compareExtra(brand, layer.getParent());
					brand.setIsGray(result);
				}
			}
		}
	}

	public Integer getExtraFloorHeight() {
		return extraFloorHeight;
	}

	public Map() {
		super();
		this.extraFloorHeight = 0;
	}

	public void setExtraFloorHeight(Integer extraFloorHeight) {
		this.extraFloorHeight = extraFloorHeight;
	}

}
