import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class snakeBlock {
	public static final int width = 20; //�����ϰ���Ĺ̶�����
	public static final int length = 20;
	public int x, y;
	int oldx,oldy;

	SnakeClient sc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] sImags = null;
	static {
		sImags = new Image[] { // ����snakeBlock��ͼƬ
		tk.getImage(snakeBlock.class.getResource("Images/2.png")), };
	}

	public snakeBlock(int x, int y, SnakeClient sc) { // ���캯��
		this.x = x;
		this.y = y;
		this.sc = sc; // ��ý������
	}
	
	public void changexy(int oldx){//�ı䵱ǰsblock�ĺ����꣬����¼�¾ɵĺ�����
		this.oldx = x;
		this.x = oldx;
	}

	public void draw(Graphics g) {// ��snakeBlock
		g.drawImage(sImags[0], x, y,20,20, null);
	}

}
