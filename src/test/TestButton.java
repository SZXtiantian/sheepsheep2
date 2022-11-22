package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Brand;
import model.Cell;
import model.Layer;
import model.Map;
import util.MapUtil;


public class TestButton extends JFrame{
	private static final long serialVersionUID = 1L;
	public static Map map = MapUtil.build(6);

	private Brand background = new Brand("背景草地", true);
	private Brand eliminateBox = new Brand("消除区域", true);	
	public TestButton() {		
		init();
		List<Layer> list = map.getList();
		for (int i = 0; i < list.size(); i++) {
			renderLayer(list.get(i));
		}
		JButton btn=new JButton("上移");
		btn.setBounds(20, 690, 90, 60);
        Dimension btn1Size=new Dimension(80, 50);
        btn.setPreferredSize(btn1Size); 
        Font font1=new Font("宋体",Font.BOLD,24);
        btn.setFont(font1);
        this.add(btn);
        btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//EliminateBox eliminateBox2 = new EliminateBox();
				//Queue<Brand> threeBrand = eliminateBox2.getThreeBrand();
				MapUtil.buildExtra(map);
			}
		});
        
        JButton btn2=new JButton("撤销");
		btn2.setBounds(175, 690, 90, 60);
        Dimension btn2Size=new Dimension(80, 50);
        btn2.setPreferredSize(btn2Size); 
        Font font2=new Font("宋体",Font.BOLD,24);
        btn2.setFont(font2);
        this.add(btn2);
        
        
        JButton btn3=new JButton("打乱");
		btn3.setBounds(330, 690, 90, 60);
        Dimension btn3Size=new Dimension(80, 50);
        btn3.setPreferredSize(btn3Size);
        Font font3=new Font("宋体",Font.BOLD,24);
        btn3.setFont(font3);
        this.add(btn3);
		
		eliminateBox.setBounds(0, 575, 450, 800);
		this.getContentPane().add(eliminateBox);
		background.setBounds(0, 0, 450, 800);
		this.getContentPane().add(background);
		map.compareAll();
		autoRefresh();
	}
	private void renderLayer(Layer layer) {
		Cell[][] cells = layer.getCells();
		layer.showCells();
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				Brand brands1 = cells[row][col].getBrand();
				//System.out.print(brands1.getName()+"-");
				int x = col *50 + layer.getOffsetx();
				int y = row *50 + layer.getOffsety();
				brands1.setBounds(x, y, 50, 50);
				this.getContentPane().add(brands1);
			}
			System.out.println();
		}
	}
	/**
	 * 
	* @Description 	自动刷新,就是隔一段时间，拽一下窗口
	* @author TimTim
	* @date 2022年11月9日下午2:31:39
	 */
	private void autoRefresh() {
		JFrame start = this;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					start.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void init() {
		this.setTitle("羊了个羊");
		this.setSize(450, 1500); 
		this.getContentPane().setBackground(Color.GREEN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getLayeredPane().setLayout(null);		
		this.setBounds(0, 0, 450, 800);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new TestButton(); 
	}
}
