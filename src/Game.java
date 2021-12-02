import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JFrame{
	private ImageIcon icon[] = new ImageIcon[13];
	private ArrayList<Integer> dataList = new ArrayList<Integer>();
	//用于生成12个按钮
	private JButton jbt[] = new JButton[12];
	//用于记录时间
	private JPanel jpnTime = new JPanel();
	//用于记录翻牌的次数
	private JPanel jpnTimes = new JPanel();
	private Mouse mouse = new Mouse();
	//用于记录翻的是第一次还是第二次牌
	int p_num = 0;
	//用于记录第一和二次翻牌按钮的编号
	int anniu1 = -1, anniu2 = -1;
	//用于记录成功配对的次数
	int correct = 0;
	int count = 0;
	int minute = 0;
	int second = 0;
	Timer timer = new Timer(1000, new TimerListener());
	private JLabel jlbTimes = new JLabel("翻牌 "+count+" 次");
	private JLabel jlbTime = new JLabel("用时 "+minute+" 分 "+second+" 秒");
	
	public Game(){
		timer.start();
		
		//牌背面图片
		icon[0] = new ImageIcon("tupian\\0.jpg");
		//牌正面图片
		for(int i = 1;i <= 6;i++){
			icon[i] = new ImageIcon("tupian\\"+ i +".jpg");
			icon[i+6] = new ImageIcon("tupian\\"+ i +".jpg");
		}
		
		//随机打乱1-12
		for(int i = 1;i <= 12; i++){
			dataList.add(i);
		}
		Collections.shuffle(dataList);
	
		//把12个图片按钮全设置成背面
		int k = 0;
		for(int i = 0;i <= 2;i++){
			for(int j = 0;j <= 3;j++){
				jbt[k] = new JButton(icon[0]);
				jbt[k].setBounds(10+j*160, 10+i*230, 150, 220);
				add(jbt[k]);
				k++;
			}
		}
		
		//鼠标监听
		for(int i = 0;i <= 11; i++){
			jbt[i].addMouseListener(mouse);
		}
		
		jlbTime.setFont(new Font("TimesRoman",Font.BOLD,13));
		jpnTime.add(jlbTime);
		//显示游戏时间
		jpnTime.setBounds(485,736,169,30);
		jpnTime.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); 
		add(jpnTime);
		//显示翻牌次数
		jpnTimes.add(jlbTimes);
		jlbTimes.setFont(new Font("TimesRoman",Font.BOLD,13));
		jpnTimes.setBounds(485,703,169,30);
		jpnTimes.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); 
		add(jpnTimes);
		
		
		
		setTitle("Java期末作业：神经衰弱小游戏");
		setLayout(null);
		setBounds(100,100,660,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		ImageIcon img = new ImageIcon("tupian/bg2.jpg");
		//要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		//将背景图放在标签里。
		add(imgLabel, new Integer(Integer.MIN_VALUE));
		//将背景标签添加到frame的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		
	}
	
	//翻牌事件
	public class Mouse extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			for(int i = 0;i <= 11;i++){
				//检测有效按钮
				if(jbt[i].isEnabled()){
					if(e.getSource() == jbt[i]){
						count++;
						//刷新次数
						jlbTimes.setText("翻牌次数 "+count+" 次");
						p_num++;
						if(p_num > 2){
							//翻回背面
							jbt[anniu1].setIcon(icon[0]);
							jbt[anniu2].setIcon(icon[0]);
							anniu1 = -1;
							anniu2 = -1;
							p_num = 1;
						}
						//图片按钮翻牌
						jbt[i].setIcon(icon[dataList.get(i)]);
						if(p_num == 1){
							anniu1 = i;
						}
						if(p_num == 2){
							anniu2 = i;
						}
					}
				}
			}
			
			if(anniu1 != -1 && anniu2 != -1){
				//翻牌匹配成功时
				if(Math.abs(dataList.get(anniu1) - dataList.get(anniu2)) == 6){
					//按钮禁用
					jbt[anniu1].setEnabled(false);	
					jbt[anniu2].setEnabled(false);
					p_num = 0;
					anniu1 = -1;
					anniu2 = -1;
					//匹配成功的次数+1
					correct++;
					//如果匹配成功的次数达到了6次，关闭当前窗口，运行重新开始游戏的类
					if(correct == 6){
						rePlay replay = new rePlay();
						replay.setVisible(true);
						dispose();
						timer.stop();
					}
				}
			}
		}
	}
	
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			second++;
			if(second == 60){
				minute++;
				second = 0;
			}
			jlbTime.setText(minute+" 分  "+second+" 秒");
		}
	}
		
	//重新开始游戏的类
	public class rePlay extends JFrame{
		private JButton jbtRePlay = new JButton("重开");
		private JButton jbtFinish = new JButton("结束");
		private JLabel jlb = new JLabel("通关！");
		private JLabel jlbFinal = new JLabel("您花了"+minute+"分"+second+"秒,"+"  翻了"+count+"次");
				
		public rePlay(){
			setLayout(null);
			jlb.setBounds(120, 30, 100, 40);
			jlb.setFont(new Font("TimesRoman",Font.BOLD,20));
			add(jlb);
			jlb.setForeground(Color.white);
			jlbFinal.setBounds(90, 70, 150, 30);
			jlbFinal.setForeground(Color.white);
			add(jlbFinal);
			
			jbtRePlay.setBounds(60, 120, 80, 30);
			add(jbtRePlay);
			jbtFinish.setBounds(190, 120, 80, 30);
			add(jbtFinish);
			ImageIcon img = new ImageIcon("tupian/bg.jpg");
			//要设置的背景图片
			JLabel imgLabel = new JLabel(img);
			//将背景图放在标签里。
			add(imgLabel, new Integer(Integer.MIN_VALUE));
			//将背景标签添加到frame的LayeredPane面板里。
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			// 设置背景标签的位置
			
			//重新开始按钮，按下重新运行Game.java
			jbtRePlay.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					Game game = new Game();
					game.setVisible(true);
					dispose();
				}
			});
			
			//结束按钮，按下关闭所有的窗口
			jbtFinish.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			
			setTitle("Java期末作业：神经衰弱小游戏");
			setLayout(null);
			setBounds(100,100,350,200);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
		}
	}
}
