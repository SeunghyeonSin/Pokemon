package Sample;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
//implements MouseListener ����
class JavaFrame_sub extends JFrame  {
	ImageIcon beauty = new ImageIcon("images\\apple.jpg");
	
	Moneycount mc =new Moneycount();
	
	public static final int totalAmount =151;
	
	
	private Button bt2 =new Button ("Ȯ2");
	private Label lb_S1 =new Label(mc.moneyCalculate(10));
	private Label lb_S2 =new Label("���� ��:");
	
	
	Panel p1 =new Panel();
	GridLayout gl1= new GridLayout(1,3,5,5);
	private BorderLayout bl =new BorderLayout();
	
	
	Panel pCenter = new Panel ();
	GridLayout gl151= new GridLayout(5,30);

	Panel pls [] =new Panel[151];
	
	JLabel lbsImage [] =new JLabel[151];
	Label lbsNo [] =new Label[151];
	Label lbsName [] =new Label[151];
	
	
	GridLayout gl3= new GridLayout(3,1,5,5);

	
	
	public JavaFrame_sub(String title) {
		
		super(title); //����Ŭ���� ������ ȣ��
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.init();  //ȭ���ʱ�ȭ �޼ҵ�
		this.start(); //�̺�Ʈ ó�� �޼ҵ�
		super.setSize(1600,1000);
		Dimension sc=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos=(int)(sc.getWidth()/2-frm.getWidth()/2);
		int ypos=(int)(sc.getHeight()/2-frm.getHeight()/2);
		super.setLocation(xpos,ypos);
		super.setVisible(true);
		super.setResizable(false);
	}
	public void init () {
		
		
		this.setLayout(bl);
		
		
		p1.setLayout(gl1);
		
		p1.add(bt2);
		p1.add(lb_S1);
		p1.add(lb_S2);
		
		this.add("South",p1);
		
		
		pCenter.setLayout(gl151);
		
		
		
		for(int i=0;i<totalAmount;i++) {
			int iDisp=i+1;
			pls[i]=new Panel();
			
			lbsImage[i]=new JLabel(beauty);
			lbsImage[i].setSize(100, 600);
			lbsNo[i]=new Label(""+iDisp);
			lbsName[i]=new Label("��Ȯ�θ�");
			
			//System.out.println("�� �г� ����"+i);
		
			pls[i].setLayout(gl3);
			pls[i].add(lbsImage[i]);
			pls[i].add(lbsNo[i]);
			pls[i].add(lbsName[i]);
			
			
			
			pCenter.add(pls[i]);
			//System.out.println("pCenter add"+i);
		}
		
		
		this.add("Center",pCenter);
		
	}

	public void start () {   
		MouseAdapter ma =new MouseAdapter() { //������ �͸��̳�Ŭ��������
		public void mouseClicked(MouseEvent e ) {
			
			
			System.exit(0);
			
			}
		};
		bt2.addMouseListener(ma);
	}
}

