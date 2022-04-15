package Sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class OracleTest {
	String [] name1= new String [12];
	String targetName;
	
	
	public static void main(String[] args) {
		new JavaFrame_sub("빵빵빵");	
		
		
		Map<Integer, Integer> numbers = new HashMap<>();
        Map<Integer, String> names = new HashMap<>();
        Map<Integer, String> images = new HashMap<>();
		
		
		
		
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String quary = "SELECT * FROM pokemon";
            String quary2 = "SELECT * FROM pokemon where no=1";
            
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary2);
            rs = pstm.executeQuery();
            
            /*  EMP 테이블의 데이터 타입
             * 
                EMPNO NOT NULL NUMBER(4) -- int
                ENAME VARCHAR2(10) -- String
                JOB VARCHAR2(9) -- String
                MGR NUMBER(4) -- int
                HIREDATE DATE -- Date
                SAL NUMBER(7,2) -- float/double
                COMM NUMBER(7,2) -- float/double
                DEPTNO NUMBER(2) -- int
            */
            
            System.out.println("NO		NAME		IMAGE");
            System.out.println("============================================");
            
            while(rs.next()){
                int no = rs.getInt("no");
                //int empno = rs.getInt("empno"); 숫자 대신 컬럼 이름을 적어도 된다.
                String name = rs.getString("name");
                String image = rs.getString("image");
         
               // targetName=name;
              
                String result = no + " " + name + " " + image;
                System.out.println(result);
                
                
                numbers.put(no, no);
                names.put(no, name);
                images.put(no, image);

                
             
                
            }
            
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            
            
        
        }finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }

	}

}
