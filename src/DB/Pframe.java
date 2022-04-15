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
        frame.setSize(720, 480);
        
        // 종료 버튼에 동작을 할당한다.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // 컴포넌트 크기, 위치 설정
        loadBtn.setBounds(40,400,300,40);
        randomList.setBounds(40,80,300,300);
        pokemonList.setBounds(380, 80, 300, 300);
        testlabel.setBounds(380, 400, 300, 40);
        
        // 버튼 이벤트 세팅
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // 리스트 내용을 전부 제거한다.
                
                try{
                	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
                    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
                    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
                    
                    String quary = "select * from pokemon";
                    int number = (int)(Math.random()*151) + 1;
                    
                    conn = DBConnection.getConnection();
                    pstm = conn.prepareStatement(quary);
                    rs = pstm.executeQuery();
                    System.out.println(rs.next());
                    // 결과를 하나씩 출력한다.
                    while (rs.next()){
                    	System.out.println("why");
                    	int no = rs.getInt("no");
                        String name = rs.getString("name");
                        int image = rs.getInt("image");
                        
                        String result = no + " " + name + " " + image;
                        System.out.println(result);
                        pokemonList.add(result);
//                        String str = rs.getNString(1);
//                        pokemonList.add(str); // 리스트에 데이터를 추가한다.
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
