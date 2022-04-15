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
		new JavaFrame_sub("������");	
		
		
		Map<Integer, Integer> numbers = new HashMap<>();
        Map<Integer, String> names = new HashMap<>();
        Map<Integer, String> images = new HashMap<>();
		
		
		
		
		Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        
        try {
            // SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
            // �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.
            String quary = "SELECT * FROM pokemon";
            String quary2 = "SELECT * FROM pokemon where no=1";
            
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary2);
            rs = pstm.executeQuery();
            
            /*  EMP ���̺��� ������ Ÿ��
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
                //int empno = rs.getInt("empno"); ���� ��� �÷� �̸��� ��� �ȴ�.
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
            System.out.println("SELECT������ ���� �߻�");
            sqle.printStackTrace();
            
            
        
        }finally{
            // DB ������ �����Ѵ�.
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
