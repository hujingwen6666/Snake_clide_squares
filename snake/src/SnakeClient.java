import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;



public class SnakeClient extends Frame implements ActionListener {
	public static final int Fram_width = 500; // ��̬ȫ�ִ��ڴ�С
	public static final int Fram_length = 800; 
	public static boolean printable = true;//�Ƿ�ˢ�»���ı�־��
	public static int time = 0; 
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
			jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null;
	Snake homeSnake =  new Snake(300, 600, Direction.STOP, this);
	Image screenImage = null;
	Random rand =new Random(25);
	List<barrier> bs = new ArrayList<barrier>();
	public void update(Graphics g) {                 //����ִ��update�������ö�������

		screenImage = this.createImage(Fram_width, Fram_length);

		Graphics gps = screenImage.getGraphics();
		Color c = gps.getColor();
		gps.setColor(Color.GRAY);
		gps.fillRect(0, 0, Fram_width, Fram_length);
		gps.setColor(c);
		framPaint(gps);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void framPaint(Graphics g) {             //update�ڵĺ�����ִ��updateʱҲҪִ��
		time +=1;
		if(homeSnake.isLive()){
			//��λʱ���100ȡ�����0����5��һ�ŵ��ϰ����100ȡ�����50����������3��һ�ŵ��ϰ���
			if(time%100==0){
				for(int i=0;i<5;i++){
					bs.add(new barrier(100*i,50, rand.nextInt(5),this));
				}
			}
			if((time+50)%100==0){
				int[] pos =new int[3];
				for(int i=0;i<3;i++){
					pos[i] = rand.nextInt(5);
					for(int j=0;j<i;j++){
						if(pos[i]==pos[j]) pos[i]=-1;
					}
					if(pos[i]>=0)
					bs.add(new barrier(100*pos[i],50,rand.nextInt(100),this));
				}
			}
			
			//�������е��ϰ��Ｐ��
//			System.out.println("----------------------------------------");
			System.out.println(bs.size());
			for(int i=0;i<bs.size();i++){
				bs.get(i).draw(g);
//				System.out.println(bs.get(i).x+"  "+bs.get(i).y);
			}
			int last = bs.size();
			for(int i=0;i<last;i++){
				barrier bi = bs.get(i);
				if (!bi.live) {
					bs.remove(this);
//					System.out.println("Successfully remove");
					last -= 1;
				}
			}
			homeSnake.draw(g);
			int x = homeSnake.getX();
			int y = homeSnake.getY();
			
			//������е��ϰ�������Ƿ���ײ
			for(int i=0;i<bs.size();i++){
				barrier xi = bs.get(i);
				if(x>=xi.x&&x<xi.x+100&&y<=xi.y+100&&y>xi.y){
					hitBarrier(g,xi,homeSnake);
				}
			}
		}
		else{
			printable = false;
			g.drawString("Game over! ", 220, 300);
		}
	}
	
	public SnakeClient() {
		// printable = false;
		// �����˵����˵�ѡ��
		jmb = new MenuBar();
		jm1 = new Menu("��Ϸ");
		jm2 = new Menu("��ͣ/����");
		jm3 = new Menu("����");
		jm4 = new Menu("��Ϸ����");
		jm1.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
		jm2.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
		jm3.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
		jm4.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������

		jmi1 = new MenuItem("��ʼ����Ϸ");
		jmi2 = new MenuItem("�˳�");
		jmi3 = new MenuItem("��ͣ");
		jmi4 = new MenuItem("����");
		jmi5 = new MenuItem("��Ϸ˵��");
		jmi6 = new MenuItem("����1");
		jmi7 = new MenuItem("����2");
		jmi8 = new MenuItem("����3");
		jmi9 = new MenuItem("����4");
		jmi1.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi2.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi3.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi4.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi5.setFont(new Font("TimesRoman", Font.BOLD, 15));

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm2.add(jmi3);
		jm2.add(jmi4);
		jm3.add(jmi5);
		jm4.add(jmi6);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm4.add(jmi9);

		jmb.add(jm1);
		jmb.add(jm2);

		jmb.add(jm4);
		jmb.add(jm3);

		jmi1.addActionListener(this);
		jmi1.setActionCommand("NewGame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("Stop");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("Continue");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("help");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("level1");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("level2");
		jmi8.addActionListener(this);
		jmi8.setActionCommand("level3");
		jmi9.addActionListener(this);
		jmi9.setActionCommand("level4");

		this.setMenuBar(jmb);// �˵�Bar�ŵ�JFrame��
		this.setVisible(true);

		this.setSize(Fram_width, Fram_length); // ���ý����С
		this.setLocation(280, 50); // ���ý�����ֵ�λ��
		this.setTitle("̰����������");

		this.addWindowListener(new WindowAdapter() { // ���ڼ����ر�
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		
		for(int i=0;i<5;i++){
			bs.add(new barrier(100*i,50, rand.nextInt(10),this));
		}

		this.addKeyListener(new KeyMonitor());// ���̼���
		new Thread(new PaintThread()).start(); // �߳�����
	}
	
	//��ײ��������ײ�����ߵĳ��Ⱥͷ������ֵͬʱ���٣�����0�Ļ��ͽ��û����ߵ�������Ϊfalse
	public void hitBarrier(Graphics g,barrier b,Snake s){
		for(int i = Math.min(b.num,s.len);i>0;i--){
			b.num -= 1;
			s.len -= 1;
			s.sBlock.remove(s.len);
		}
		if(b.num==0){
			bs.remove(b);
		}
		if(s.len==0){
			s.live = false;
		}
	}
	
	//���ö��̺߳�����ÿ��50����ˢ�»�ͼ����
	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new SnakeClient(); // ʵ����
	}
	
	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) { // ���������ͷ�
			homeSnake.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) { // �������̰���
			homeSnake.keyPressed(e);
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("NewGame")) {
			time = 0;
			printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ��ʼ����Ϸ��", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				printable = true;
				this.dispose();
				new SnakeClient();
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}

		} else if (e.getActionCommand().endsWith("Stop")) {
			printable = false;
			// try {
			// Thread.sleep(10000);
			//
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
		} else if (e.getActionCommand().equals("Continue")) {

			if (!printable) {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}
			// System.out.println("����");
		} else if (e.getActionCommand().equals("Exit")) {
			printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ�˳���", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				System.out.println("�˳�");
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����

			}

		} else if (e.getActionCommand().equals("help")) {
			printable = false;
			JOptionPane.showMessageDialog(null, "�á� �� �� �����Ʒ���F���̷��䣬R���¿�ʼ��",
					"��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); // �߳�����
		} /*else if (e.getActionCommand().equals("level1")) {
			Tank.count = 12;
			Tank.speedX = 6;
			Tank.speedY = 6;
			Bullets.speedX = 10;
			Bullets.speedY = 10;
			this.dispose();
			new TankClient();
		} else if (e.getActionCommand().equals("level2")) {
			Tank.count = 12;
			Tank.speedX = 10;
			Tank.speedY = 10;
			Bullets.speedX = 12;
			Bullets.speedY = 12;
			this.dispose();
			new TankClient();

		} else if (e.getActionCommand().equals("level3")) {
			Tank.count = 20;
			Tank.speedX = 14;
			Tank.speedY = 14;
			Bullets.speedX = 16;
			Bullets.speedY = 16;
			this.dispose();
			new TankClient();
		} else if (e.getActionCommand().equals("level4")) {
			Tank.count = 20;
			Tank.speedX = 16;
			Tank.speedY = 16;
			Bullets.speedX = 18;
			Bullets.speedY = 18;
			this.dispose();
			new TankClient();
		}*/
	}
}
