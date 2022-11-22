package test;
import javax.swing.JOptionPane;
public class Testkk {
	
	public static void main(String[] args) {
		Object[] list = {"复活", "退出游戏"};
		Object objResult = JOptionPane.showOptionDialog(null, "请选择：", "在努力一下下你就成功了！",
		JOptionPane.YES_NO_CANCEL_OPTION, 3, null, list, list[0]);
		System.out.println(objResult);
	}
}
