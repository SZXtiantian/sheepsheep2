package model;

//import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
/**
 * 
* @Description
* @author TimTim Email:754595995@qq.com
* @version
* @date 2022年11月5日下午4:06:19
*
 */
public class EliminateBox {
	private static List<Brand> slot = new LinkedList<>();
	
	public boolean addSlot(Brand brand){
		slot.add(brand);	
		slot.sort(Comparator.comparing(Brand::getName));//按牌名字排序
		
		Map<String, List<Brand>> map = slot.stream().collect(Collectors.groupingBy(Brand::getName));//获取牌的名称
		Set<String> key = map.keySet();
		for (String s : key) {
			List<Brand> brands = map.get(s);
			if (brands.size()==3) {
				
				deleteByBrandName(s);
				break;
			}
		}
		paint();
		if (over(brand)) {
			Object[] list = {"复活", "退出游戏"};
			Object objResult = JOptionPane.showOptionDialog(null, "请选择：", "在努力一下下你就成功了！",
			JOptionPane.YES_NO_CANCEL_OPTION, 2, null, list, list[0]);
			if ((int)objResult == 1) {
				System.exit(0);
			}
			else{
				/*Queue<Brand>temp = new LinkedList<Brand>();
				temp = getThreeBrand();*/
				return true;
				//ExtraLayer extraLayer = new ExtraLayer();
				//extraLayer.set
			}
		}
		return false;
	}
	
	void paint(){
		for (int i = 0; i < slot.size(); i++) {
			Brand brand = slot.get(i);
			int x = i * brand.getWidth() + 10;
			brand.setBounds(x, 600, 50, 50);
		}
	}
	
	void deleteByBrandName(String name){
		Iterator<Brand>iterator = slot.iterator();
		while(iterator.hasNext()){
			Brand next = iterator.next();
			if (next.getName().equals(name)) {
				next.getParent().remove(next);
				iterator.remove();
			}
		}
	}
	boolean over(Brand brand){
		if (slot.size() >= 8) {
			JOptionPane.showMessageDialog(brand, "You lose!Game Over");
			return true;
		}
		return false;
	}
	public Queue<Brand> getThreeBrand() {
		Queue<Brand>queue = new LinkedList<Brand>();
		Queue<String>key = new LinkedList<String>();
		if (slot.size() < 3) {
			for (int i = 0; i < slot.size(); i++) {
				Brand temp = slot.get(i);
				temp.setBounds(i * temp.getWidth() + 135, 530, 50, 50);
				queue.add(temp);
				key.add(slot.get(i).getName());
			}
		}else{
			for (int i = 0; i < 3; i++) {
				Brand temp = slot.get(i);
				temp.setBounds(i * temp.getWidth() + 135, 530, 50, 50);
				queue.add(temp);
				key.add(slot.get(i).getName());
			}
		}
		int temp = key.size();
		for (int i = 0; i <temp; i++) {
			//deleteByBrandName(key.peek());
			slot.remove(0);
			key.poll();
		}
		paint();
		return queue;		
	}
}
