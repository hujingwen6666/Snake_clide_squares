import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.Graphics;

public class Snake {
	public static  int speedX = 10; // ��̬ȫ�ֱ����ٶ�---------������Ϊ���������ü����ٶȿ�Ļ��Ƚ���
	public static int count = 0;
	public static final int width = 35, length = 35; //��ÿһ���ֵ�ȫ�ִ�С�����в��ɸı���
	private Direction direction = Direction.STOP; // ��ʼ��״̬Ϊ��ֹ
//	private Direction Kdirection = Direction.U; // ��ʼ������Ϊ����
	SnakeClient sc;

	private boolean good;
	private int x, y;
	private int oldX, oldY;
	public boolean live = true; // ��ʼ��Ϊ����
	public int len = 5; // ��ʼ����ֵ,̰���ߵĳ���Ϊ5
//	private snakeBlock[] sBlock;
	List<snakeBlock> sBlock = new ArrayList<snakeBlock>();

	private static Random r = new Random();

	private boolean bL = false, bU = false, bR = false, bD = false;
	

	public Snake(int x, int y) {// Snake�Ĺ��캯��1
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
	}

	public Snake(int x, int y, Direction dir, SnakeClient sc) {// Snake�Ĺ��캯��2
		this(x, y);
		this.direction = dir;
		this.sc = sc;
		this.initiate();
	}
	
	public void initiate(){           //��ʼ��
		for(int i=0;i<len;i++){
			sBlock.add(new snakeBlock(x,y+i*20,sc));
//			System.out.println(sBlock.get(i).x+" "+sBlock.get(i).y);
		}
	}

	public void draw(Graphics g) {
		g.drawString(String.valueOf(len), x+6, y);

		move(g);   //����move����
	}

	void move(Graphics g) {

		this.oldX = x;
		this.oldY = y;

		switch (direction) {  //ѡ���ƶ�����
		case L:
			x -= speedX;
			break;
		case R:
			x += speedX;
			break;
		case STOP:
			break;
		}

		if (x < 0)
			x = 0;
		if (y < 40)      //��ֹ�߳��涨����
			y = 40;
		if (x + Snake.width > SnakeClient.Fram_width)  //����������ָ����߽�
			x = SnakeClient.Fram_width - Snake.width;
		if (y + Snake.length > SnakeClient.Fram_length)
			y = SnakeClient.Fram_length - Snake.length;
		int tempx = x;
		for(int i=0;i<len;i++){                //�����sblock�õ�ǰ���sblock�ĺ����꣬ʵ�������ƶ�
			snakeBlock si = sBlock.get(i);
			si.changexy(tempx);
			tempx = si.oldx;
			si.draw(g);
		}

	}



	public void keyPressed(KeyEvent e) {  //���ܼ����¼�
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT: //�������Ҽ�
			bR = true;
			break;
			
		case KeyEvent.VK_LEFT://���������
			bL = true;
			break;
		
		}
		decideDirection();//���ú���ȷ���ƶ�����
	}

	void decideDirection() {
		if (!bL && !bU && bR && !bD)  //�����ƶ�
			direction = Direction.R;

		else if (bL && !bU && !bR && !bD)   //������
			direction = Direction.L;

		else if (!bL && !bU && !bR && !bD)
			direction = Direction.STOP;  //û�а������ͱ��ֲ���
	}

	public void keyReleased(KeyEvent e) {  //�����ͷż���
		int key = e.getKeyCode();
		switch (key) {
			
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
			

		}
		decideDirection();  //�ͷż��̺�ȷ���ƶ�����
	}


	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}