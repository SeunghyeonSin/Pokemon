package DB;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Pframe {

	// ������ ��ü ����
    Frame frame = new Frame("Pokemon�� ���� ������");
    
    // ��ư, ����Ʈ ��Ʈ�� ����
    List randomList = new List();
    List pokemonList = new List();
    Label testlabel = new Label();
    Button loadBtn = new Button("Pokemon�� �̱�");
    
    public void createFrame()
    {
        // ���̾ƿ� �Ŵ����� ������� �ʱ�
        frame.setLayout(null);
        
        // ������ ũ�� ����
        frame.setSize(720, 480);
        
        // ���� ��ư�� ������ �Ҵ��Ѵ�.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // ������Ʈ ũ��, ��ġ ����
        loadBtn.setBounds(40,400,300,40);
        randomList.setBounds(40,80,300,300);
        pokemonList.setBounds(380, 80, 300, 300);
        testlabel.setBounds(380, 400, 300, 40);
        
        // ��ư �̺�Ʈ ����
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // ����Ʈ ������ ���� �����Ѵ�.
                
                try{
                	Connection conn = null; // DB����� ����(����)�� ���� ��ü
                    PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
                    ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
                    
                    String quary = "select * from pokemon";
                    int number = (int)(Math.random()*151) + 1;
                    
                    conn = DBConnection.getConnection();
                    pstm = conn.prepareStatement(quary);
                    rs = pstm.executeQuery();
                    System.out.println(rs.next());
                    // ����� �ϳ��� ����Ѵ�.
                    while (rs.next()){
                    	System.out.println("why");
                    	int no = rs.getInt("no");
                        String name = rs.getString("name");
                        int image = rs.getInt("image");
                        
                        String result = no + " " + name + " " + image;
                        System.out.println(result);
                        pokemonList.add(result);
//                        String str = rs.getNString(1);
//                        pokemonList.add(str); // ����Ʈ�� �����͸� �߰��Ѵ�.
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
}
