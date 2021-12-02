import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rule extends JFrame{
	private JButton jbtSure = new JButton("开始游戏！");

	
	public Rule(){
		setLayout(null);
		
		//开始游戏按钮
		jbtSure.setBounds(190, 190, 100, 40);
		add(jbtSure);
		
		//运行Game.java，然后关闭Rule.java
		jbtSure.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Game game = new Game();
				game.setVisible(true);
				dispose();
			}
		});
		
	}
	
	//main函数入口
	public static void main(String[] args) {
		JFrame frame = new Rule();
		frame.setTitle("Java期末作业：神经衰弱小游戏");
		frame.setLayout(null);
		frame.setBounds(100,100,495,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		ImageIcon img = new ImageIcon("tupian/bg1.jpg");
		//要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		//将背景图放在标签里。
		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//将背景标签添加到frame的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		Container contain = frame.getContentPane();
		((JPanel) contain).setOpaque(false); 
		// 将内容面板设为透明。将LayeredPane面板中的背景显示出来。
		

	}

}

