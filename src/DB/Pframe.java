package DB;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JLabel;
public class Pframe {
	
	int clickCount =0;
	int price=0;
	int totalMoney=0;
	int ranNo=0;
	

	// ������ ��ü ����
    Frame frame = new Frame("Pokemon�� ���� ������");
    
    // ��ư, ����Ʈ ��Ʈ�� ����
    List randomList = new List();
    Panel pokemonList = new Panel();
    Label testlabel = new Label(totalMoney+"��");
    Button loadBtn = new Button("Pokemon�� �̱�");
    ScrollPane sp = new ScrollPane();
    
    
    Panel pCenter = new Panel ();
	GridLayout gl151= new GridLayout(5,30);

	Panel pls [] =new Panel[151];
	
	JLabel lbsImage [] =new JLabel[151];
	Label lbsNo [] =new Label[151];
	Label lbsName [] =new Label[151];
	
	
	GridLayout gl3= new GridLayout(3,1,5,5);
	
	
	public int getRanNo() {
		return ranNo;
	}
	public void setRanNo(int ranNo) {
		this.ranNo = ranNo;
	}
	public static final int totalAmount =151;
	
    
    public void createFrame()
    {
        // ���̾ƿ� �Ŵ����� ������� �ʱ�
        frame.setLayout(null);
        
        // ������ ũ�� ����
        frame.setSize(1280, 720);
        
        // ���� ��ư�� ������ �Ҵ��Ѵ�.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // ������Ʈ ũ��, ��ġ ����
        loadBtn.setBounds(40,665,580,40);
        randomList.setBounds(40,80,580,580);
        pokemonList.setBounds(660, 80, 580, 580);
        testlabel.setBounds(100, 600, 300, 40); //<<<<��ġ���� �ʿ��� 7�÷�
        sp.setPreferredSize(new Dimension(200, 100));
        
        
        // ��ư �̺�Ʈ ����
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // ����Ʈ ������ ���� �����Ѵ�.
                
                try{
                	Connection conn = null; // DB����� ����(����)�� ���� ��ü
                    PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
                    ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
                    
                    PreparedStatement pstm2 = null;  // SQL ���� ��Ÿ���� ��ü
                    
                    PreparedStatement pstm3 = null;  // SQL ���� ��Ÿ���� ��ü
                    ResultSet rs3 = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
                    
                    int number = (int)(Math.random()*151) + 1;
                    String quary = "select * from pokemon where no = " + number;
                    String quary2 = "update nullpokemon set name = ?, image = ? where no = ?";
                    String quary3 = "select * from nullpokemon";
                    System.out.println(number);
                    
                    conn = DBConnection.getConnection();
                    pstm = conn.prepareStatement(quary);
                    rs = pstm.executeQuery();
                    
                    pstm2 = conn.prepareStatement(quary2);
                    
                    //System.out.println(rs.next());
                    if(rs.next()) {
                    	//System.out.println("hey");
                        int no = rs.getInt("no");
                        String name = rs.getString("name");
                        int image = rs.getInt("image");
                        String result = no + " " + name;
                        
                        pstm2.setString(1, name);
                        pstm2.setInt(2, image);
                        pstm2.setInt(3, no);
                        
                        randomList.add(result);
                        randomList.add("images\\" + number + ".webp");
                    }
                    
                    int rs2 = pstm2.executeUpdate();
                    //System.out.println(rs2);
                    
                    pstm3 = conn.prepareStatement(quary3);
                    rs3 = pstm3.executeQuery();
                    
                    
                    //System.out.println(rs3.next());
                    // ����� �ϳ��� ����Ѵ�.
                    while (rs3.next()){
                    	//System.out.println("ho");
                    	int no = rs3.getInt("no");
                        String name = rs3.getString("name");
                        int image = rs3.getInt("image");
                        
                        String result = no + " " + name + " " + image;
                        System.out.println(result);
                        pokemonList.add(result);
                        pokemonList.add("images\\" + image + ".webp");
                    }
                }catch(SQLException sqle){
                    System.out.println("SQLException: " + sqle.getMessage());
                    System.out.println("SQLState: " + sqle.getSQLState());
                }
            }
        });
                
        //�����ӿ� ������Ʈ �߰�
        frame.add(loadBtn);
        frame.add(randomList);
        frame.add(pokemonList);
        frame.add(testlabel);
        
        //������ ���̱�
        frame.setVisible(true);
    }
    
public void init () {
		
		/*
		this.setLayout(bl);
		
		
		p1.setLayout(gl1);
		
		p1.add(bt2);
		p1.add(lb_S1);
		p1.add(lb_S2);
		
		this.add("South",p1);
		*/
		
		pCenter.setLayout(gl151);
		
		
		
		for(int i=0;i<totalAmount;i++) {
			int iDisp=i+1;
			pls[i]=new Panel();
			
			lbsImage[i]=new JLabel("apple");
			lbsImage[i].setSize(100, 600);
			lbsNo[i]=new Label(""+iDisp);
			lbsName[i]=new Label("��Ȯ�θ�");
			
			//System.out.println("�� �г� ����"+i);
		
			pls[i].setLayout(gl3);
			pls[i].add(lbsImage[i]);
			pls[i].add(lbsNo[i]);
			pls[i].add(lbsName[i]);
			
			
			
			pCenter.add(pls[i]);
			System.out.println("pCenter add"+i);
		}
		sp.add(pCenter);
		
		pokemonList.add(sp);
		
	}



public void start () {   
	MouseAdapter ma =new MouseAdapter() { //������ �͸��̳�Ŭ��������
		
		public void mouseClicked(MouseEvent e ) {
			int price= 1500;
			
			
			
			clickCount++;
			totalMoney=price*clickCount;
		//	System.out.println(clickCount+" "+totalMoney);
			testlabel.setText(totalMoney+"��");
			
			
			
			/////////////////////////////////
			Random ran =new Random();
			int n=ran.nextInt(totalAmount-1)+1;

			
			setRanNo(n);
			
			
			//System.out.println(getRanNo());
			
		//System.exit(0);
		
		}
	};
	loadBtn.addMouseListener(ma);
}

}
