import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class barrier {
//	public static final int width = 20; //�����ϰ���Ĺ̶�����
//	public static final int length = 20;
	public static  int speedY = 10; // �ϰ����ȫ�־�̬�ٶ�
	public boolean live = true;
	int x, y;
	int num;

	SnakeClient sc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bImags = null;
	static {
		bImags = new Image[] { // ����barrier��ͼƬ
		tk.getImage(barrier.class.getResource("Images/1.png")), 
		};
	}

	public barrier(int x, int y,int num, SnakeClient sc) { // ���캯��
		this.x = x;
		this.y = y;
		this.num = num;
		this.sc = sc; // ��ý������
	}


	public void draw(Graphics g) {		
		g.drawImage(bImags[0], x, y,100,100,null);   //���ϰ�������x,y,�����100        
//		g.drawRect(x,y,100,100);
		g.drawString(String.valueOf(num),x+50,y+50);  //�ϰ����м�д����
		y += speedY;
		if(y>SnakeClient.Fram_length){
			live = false;
//			System.out.println("Successfully set live false");
		}
//		move(); // �����ӵ�move()����
	}
	
/*	private void move(){
		y += speedY;
		if(y>SnakeClient.Fram_length){
			live = false;
		}
	}*/

}
