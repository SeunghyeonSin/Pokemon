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
        testlabel.setBounds(380, 400, 300, 40);
        
        // ��ư �̺�Ʈ ����
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // ����Ʈ ������ ���� �����Ѵ�.
                
                try{
                	Connection conn = null; // DB����� ����(����)�� ���� ��ü
                    PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
                    ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
                    
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
                    
                    System.out.println("����");
//                  
                    PreparedStatement pstm2 = null;  // SQL ���� ��Ÿ���� ��ü
                    ResultSet rs2 = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
                    
                    pstm2 = conn.prepareStatement(quary2);
                    rs2 = pstm2.executeQuery();
                    
                    System.out.println(rs2.next());
                    // ����� �ϳ��� ����Ѵ�.
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
                
        //�����ӿ� ������Ʈ �߰�
        frame.add(loadBtn);
        frame.add(randomList);
        frame.add(pokemonList);
        frame.add(testlabel);
        
        //������ ���̱�
        frame.setVisible(true);
    }
}
