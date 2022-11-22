package util;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

import model.Brand;
import model.Cell;
import model.EliminateBox;
import model.ExtraLayer;

/**
 * 
 * @Description
 * @author TimTim Email:754595995@qq.com
 * @version
 * @date 2022年11月10日下午4:32:44
 *
 */
public class ExtraLayerUtil {
	public static ExtraLayer build() {
		ExtraLayer layer = null;
		try {
			layer = new ExtraLayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EliminateBox eliminateBox = new EliminateBox();
		Queue<Brand>brands = new LinkedList<Brand>();
		brands = eliminateBox.getThreeBrand();
		Cell[][] cells = layer.getCells();

		int temp = brands.size();
		for (int col = 0; col < temp; col++) {
			System.out.println(0 + "-" + col);
			Brand brands1 = brands.peek();
			brands.poll();
			Cell cell = new Cell();
			cell.setState(1);
			cell.setBrand(brands1);

			brands1.setCell(cell);

			cells[0][col] = cell;// 将之前空的图层设置值
		}
		return layer;
	}

	public static void renderLayer(ExtraLayer layer, JFrame jframe) {
		Cell[][] cells = layer.getCells();
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				if (cells[row][col] == null) {
					return;
				}
				Brand brands1 = cells[row][col].getBrand();
				// System.out.print(brands1.getName()+"-");
				int x = col * 50;
				int y = row * 50;
				brands1.setBounds(x, y, 50, 50);
				jframe.getContentPane().add(brands1);
			}
			System.out.println();
		}
	}
}
