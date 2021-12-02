import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JFrame{
	private ImageIcon icon[] = new ImageIcon[13];
	private ArrayList<Integer> dataList = new ArrayList<Integer>();
	//��������12����ť
	private JButton jbt[] = new JButton[12];
	//���ڼ�¼ʱ��
	private JPanel jpnTime = new JPanel();
	//���ڼ�¼���ƵĴ���
	private JPanel jpnTimes = new JPanel();
	private Mouse mouse = new Mouse();
	//���ڼ�¼�����ǵ�һ�λ��ǵڶ�����
	int p_num = 0;
	//���ڼ�¼��һ�Ͷ��η��ư�ť�ı��
	int anniu1 = -1, anniu2 = -1;
	//���ڼ�¼�ɹ���ԵĴ���
	int correct = 0;
	int count = 0;
	int minute = 0;
	int second = 0;
	Timer timer = new Timer(1000, new TimerListener());
	private JLabel jlbTimes = new JLabel("���� "+count+" ��");
	private JLabel jlbTime = new JLabel("��ʱ "+minute+" �� "+second+" ��");
	
	public Game(){
		timer.start();
		
		//�Ʊ���ͼƬ
		icon[0] = new ImageIcon("tupian\\0.jpg");
		//������ͼƬ
		for(int i = 1;i <= 6;i++){
			icon[i] = new ImageIcon("tupian\\"+ i +".jpg");
			icon[i+6] = new ImageIcon("tupian\\"+ i +".jpg");
		}
		
		//�������1-12
		for(int i = 1;i <= 12; i++){
			dataList.add(i);
		}
		Collections.shuffle(dataList);
	
		//��12��ͼƬ��ťȫ���óɱ���
		int k = 0;
		for(int i = 0;i <= 2;i++){
			for(int j = 0;j <= 3;j++){
				jbt[k] = new JButton(icon[0]);
				jbt[k].setBounds(10+j*160, 10+i*230, 150, 220);
				add(jbt[k]);
				k++;
			}
		}
		
		//������
		for(int i = 0;i <= 11; i++){
			jbt[i].addMouseListener(mouse);
		}
		
		jlbTime.setFont(new Font("TimesRoman",Font.BOLD,13));
		jpnTime.add(jlbTime);
		//��ʾ��Ϸʱ��
		jpnTime.setBounds(485,736,169,30);
		jpnTime.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); 
		add(jpnTime);
		//��ʾ���ƴ���
		jpnTimes.add(jlbTimes);
		jlbTimes.setFont(new Font("TimesRoman",Font.BOLD,13));
		jpnTimes.setBounds(485,703,169,30);
		jpnTimes.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); 
		add(jpnTimes);
		
		
		
		setTitle("Java��ĩ��ҵ����˥��С��Ϸ");
		setLayout(null);
		setBounds(100,100,660,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		ImageIcon img = new ImageIcon("tupian/bg2.jpg");
		//Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		//������ͼ���ڱ�ǩ�
		add(imgLabel, new Integer(Integer.MIN_VALUE));
		//��������ǩ��ӵ�frame��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		
	}
	
	//�����¼�
	public class Mouse extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			for(int i = 0;i <= 11;i++){
				//�����Ч��ť
				if(jbt[i].isEnabled()){
					if(e.getSource() == jbt[i]){
						count++;
						//ˢ�´���
						jlbTimes.setText("���ƴ��� "+count+" ��");
						p_num++;
						if(p_num > 2){
							//���ر���
							jbt[anniu1].setIcon(icon[0]);
							jbt[anniu2].setIcon(icon[0]);
							anniu1 = -1;
							anniu2 = -1;
							p_num = 1;
						}
						//ͼƬ��ť����
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
				//����ƥ��ɹ�ʱ
				if(Math.abs(dataList.get(anniu1) - dataList.get(anniu2)) == 6){
					//��ť����
					jbt[anniu1].setEnabled(false);	
					jbt[anniu2].setEnabled(false);
					p_num = 0;
					anniu1 = -1;
					anniu2 = -1;
					//ƥ��ɹ��Ĵ���+1
					correct++;
					//���ƥ��ɹ��Ĵ����ﵽ��6�Σ��رյ�ǰ���ڣ��������¿�ʼ��Ϸ����
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
			jlbTime.setText(minute+" ��  "+second+" ��");
		}
	}
		
	//���¿�ʼ��Ϸ����
	public class rePlay extends JFrame{
		private JButton jbtRePlay = new JButton("�ؿ�");
		private JButton jbtFinish = new JButton("����");
		private JLabel jlb = new JLabel("ͨ�أ�");
		private JLabel jlbFinal = new JLabel("������"+minute+"��"+second+"��,"+"  ����"+count+"��");
				
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
			//Ҫ���õı���ͼƬ
			JLabel imgLabel = new JLabel(img);
			//������ͼ���ڱ�ǩ�
			add(imgLabel, new Integer(Integer.MIN_VALUE));
			//��������ǩ��ӵ�frame��LayeredPane����
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			// ���ñ�����ǩ��λ��
			
			//���¿�ʼ��ť��������������Game.java
			jbtRePlay.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					Game game = new Game();
					game.setVisible(true);
					dispose();
				}
			});
			
			//������ť�����¹ر����еĴ���
			jbtFinish.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			
			setTitle("Java��ĩ��ҵ����˥��С��Ϸ");
			setLayout(null);
			setBounds(100,100,350,200);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
		}
	}
}
