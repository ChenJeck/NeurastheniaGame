import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rule extends JFrame{
	private JButton jbtSure = new JButton("��ʼ��Ϸ��");

	
	public Rule(){
		setLayout(null);
		
		//��ʼ��Ϸ��ť
		jbtSure.setBounds(190, 190, 100, 40);
		add(jbtSure);
		
		//����Game.java��Ȼ��ر�Rule.java
		jbtSure.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Game game = new Game();
				game.setVisible(true);
				dispose();
			}
		});
		
	}
	
	//main�������
	public static void main(String[] args) {
		JFrame frame = new Rule();
		frame.setTitle("Java��ĩ��ҵ����˥��С��Ϸ");
		frame.setLayout(null);
		frame.setBounds(100,100,495,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		ImageIcon img = new ImageIcon("tupian/bg1.jpg");
		//Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		//������ͼ���ڱ�ǩ�
		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//��������ǩ��ӵ�frame��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		Container contain = frame.getContentPane();
		((JPanel) contain).setOpaque(false); 
		// �����������Ϊ͸������LayeredPane����еı�����ʾ������
		

	}

}

