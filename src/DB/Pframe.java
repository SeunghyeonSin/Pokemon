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
	

	// 프레임 객체 설정
    Frame frame = new Frame("Pokemon씰 랜덤 생성기");
    
    // 버튼, 리스트 컨트롤 선언
    List randomList = new List();
    Panel pokemonList = new Panel();
    Label testlabel = new Label(totalMoney+"원");
    Button loadBtn = new Button("Pokemon씰 뽑기");
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
        // 레이아웃 매니저를 사용하지 않기
        frame.setLayout(null);
        
        // 프레임 크기 지정
        frame.setSize(1280, 720);
        
        // 종료 버튼에 동작을 할당한다.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // 컴포넌트 크기, 위치 설정
        loadBtn.setBounds(40,665,580,40);
        randomList.setBounds(40,80,580,580);
        pokemonList.setBounds(660, 80, 580, 580);
        testlabel.setBounds(100, 600, 300, 40); //<<<<위치조정 필요함 7시로
        sp.setPreferredSize(new Dimension(200, 100));
        
        
        // 버튼 이벤트 세팅
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // 리스트 내용을 전부 제거한다.
                
                try{
                	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
                    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
                    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
                    
                    PreparedStatement pstm2 = null;  // SQL 문을 나타내는 객체
                    
                    PreparedStatement pstm3 = null;  // SQL 문을 나타내는 객체
                    ResultSet rs3 = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
                    
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
                    // 결과를 하나씩 출력한다.
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
                
        //프레임에 컴포넌트 추가
        frame.add(loadBtn);
        frame.add(randomList);
        frame.add(pokemonList);
        frame.add(testlabel);
        
        //프레임 보이기
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
			lbsName[i]=new Label("미확인명");
			
			//System.out.println("새 패널 생성"+i);
		
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
	MouseAdapter ma =new MouseAdapter() { //유사한 익명이너클래스내용
		
		public void mouseClicked(MouseEvent e ) {
			int price= 1500;
			
			
			
			clickCount++;
			totalMoney=price*clickCount;
		//	System.out.println(clickCount+" "+totalMoney);
			testlabel.setText(totalMoney+"원");
			
			
			
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
