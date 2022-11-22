package model;

import java.util.Random;

public class ExtraLayer {
	private Integer offsetx;
	private Integer offsety;

	private Integer rowNum;// 行
	private Integer colNum;// 列
	private Integer capacity; // 当前图层最大容量

	private Integer size; // 当前图层牌数，动态变化

	private ExtraLayer parent;

	public ExtraLayer getParent() {
		return parent;
	}

	public void setParent(ExtraLayer parent) {
		this.parent = parent;
	}

	// private Cell[][] cell = new Cell[4][5];
	private Cell[][] cells = null;

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public ExtraLayer() throws Exception {
		super();
		this.rowNum = 1;
		this.colNum = 3;
		this.capacity = this.colNum * this.rowNum;
		if (this.capacity % 3 != 0) {
			throw new Exception("容量非3的倍数！！！");
		}
		this.cells = new Cell[this.rowNum][this.colNum];
		this.size = 0;
		this.offsetx = new Random().nextInt(100);
		this.offsety = new Random().nextInt(100);
	}

	public Integer getOffsety() {
		return offsety;
	}

	public void setOffsety(Integer offsety) {
		this.offsety = offsety;
	}

	public Integer getOffsetx() {
		return offsetx;
	}

	public void setOffsetx(Integer offsetx) {
		this.offsetx = offsetx;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getColNum() {
		return colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void showCells() {
		for (int col = 0; col < cells[0].length; col++) {
			if (cells[0][col] == null) {
				return;
			}
			Brand brands1 = cells[0][col].getBrand();
			System.out.print(brands1.getName() + "-");
		}
		System.out.println();
	}

	public Cell getIndex(int index) {
		int index_x = index / this.getColNum();
		int index_y = index % this.getColNum();

		return this.cells[index_x][index_y];

	}
}
