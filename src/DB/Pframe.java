package DB;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Pframe {

	// 프레임 객체 설정
    Frame frame = new Frame("Pokemon씰 랜덤 생성기");
    
    // 버튼, 리스트 컨트롤 선언
    List randomList = new List();
    List pokemonList = new List();
    Label testlabel = new Label();
    Button loadBtn = new Button("Pokemon씰 뽑기");
    
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
        testlabel.setBounds(380, 400, 300, 40);
        
        // 버튼 이벤트 세팅
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // 리스트 내용을 전부 제거한다.
                
                try{
                	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
                    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
                    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
                    
                    int number = (int)(Math.random()*151) + 1;
                    String quary = "select * from pokemon where no = " + number;
                    String quary2 = "select * from nullpokemon";
                    System.out.println(number);
                    
                    conn = DBConnection.getConnection();
                    pstm = conn.prepareStatement(quary);
                    rs = pstm.executeQuery();
                    
                    //System.out.println(rs.next());
                    if(rs.next()) {
                    	System.out.println("hey");
                        int no = rs.getInt("no");
                        String name = rs.getString("name");
                        int image = rs.getInt("image");
                        String result = no + " " + name + " " + image;
                        randomList.add(result);
                    }
                    
                    System.out.println("여기");
//                  
                    PreparedStatement pstm2 = null;  // SQL 문을 나타내는 객체
                    ResultSet rs2 = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
                    
                    pstm2 = conn.prepareStatement(quary2);
                    rs2 = pstm2.executeQuery();
                    
                    System.out.println(rs2.next());
                    // 결과를 하나씩 출력한다.
                    while (rs2.next()){
                    	System.out.println("ho");
                    	int no2 = rs2.getInt("no");
                        String name2 = rs2.getString("name");
                        int image2 = rs2.getInt("image");
                        
                        String result2 = no2 + " " + name2 + " " + image2;
                        System.out.println(result2);
                        pokemonList.add(result2);
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
}
