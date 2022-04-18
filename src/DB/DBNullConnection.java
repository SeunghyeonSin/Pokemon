package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBNullConnection {
    
    public static String[] getConnection()
    {
        Connection conn = null;
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        String[] list = new String[151];
        
        try {
            String user = "scott"; 
            String pw = "1234";
            String url = "jdbc:oracle:thin:@localhost:1521:BTDB";
            String quary = "select * from nullpokemon";
            
            Class.forName("oracle.jdbc.driver.OracleDriver");        
            conn = DriverManager.getConnection(url, user, pw);
            pstm = conn.prepareStatement(quary);
            rs = pstm.executeQuery();
            
//            System.out.println("Database에 연결되었습니다.\n");

            //System.out.println(rs2.next());
            // 결과를 하나씩 출력한다.
            while (rs.next()){
            	for (int i=0; i<152; i++) {
            		System.out.println("ho");
                	int no2 = rs.getInt("no");
                    String name2 = rs.getString("name");
                    int image2 = rs.getInt("image");
                    
                    String result2 = no2 + " " + name2 + " " + image2;
                    System.out.println(result2);
                    list[i] = result2;
            	}
            }
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : "+sqle.toString());
        } catch (Exception e) {
            System.out.println("Unkonwn error");
            e.printStackTrace();
        }
        return list;
    }
}
