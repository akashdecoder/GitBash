import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Project1 extends JFrame{
    JFrame f;
    JLabel l1,l2,l3;
    JTextField t1;
    JButton b1,b2;
    final JPasswordField p1;
    
    Connection con = null;
    Statement st = null;
    ResultSet res = null;
    PreparedStatement pst = null;
    
    String name, pass;
    String sql;
    
    public Project1(){
        f = new JFrame("Signup/Login Form");
        f.setLayout(null);
        f.setSize(500,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("Name: ");
        l1.setBounds(20,30,50,30);

        t1 = new JTextField();
        t1.setBounds(150,30,250,30);

        l2 = new JLabel("Password: ");
        l2.setBounds(20,60,90,30);

        p1 = new JPasswordField();
        p1.setBounds(150,60,250,30);

        b1 = new JButton("Sign Up");
        b1.setBounds(20,120,150,30);

        b2 = new JButton("Login");
        
        b2.setBounds(250,120,150,30);
        
        l3 = new JLabel("Dynamics@Siddaganga Institute of Technology,Tumkur");
        l3.setBounds(40,230,400,30);
        
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(p1);
        f.add(b1);
        f.add(b2);
        f.add(l3);
        f.setVisible(true);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project1?useSSL=false", "root", "");
            st = con.createStatement();
            
            b1.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		try {
            			boolean flag1 = false;
            			name = t1.getText();
            			pass = new String(p1.getPassword());
            			res = st.executeQuery("select * from login");
                        while(res.next()) {
                        	if(name.compareTo(res.getString(1)) == 0 && pass.compareTo(res.getString(2)) == 0) {
            					flag1=true;
            					break;
            				}
            				else {
            					flag1=false;
            				}
                        }
                        if(flag1==true)
                        	JOptionPane.showMessageDialog(null,"Already Exists");
                        else {
                        	sql = "INSERT INTO login (Name, Password) VALUES ("+"\""+name+"\""+","+"\""+pass+"\""+");"; 
                        	st.executeUpdate(sql);
                        	JOptionPane.showMessageDialog(null,"Signed Up");
                        }
                        
            		}
            		catch(Exception e1) {
            			JOptionPane.showMessageDialog(null,e1.getMessage());
            		}
            	}
            });
            
            b2.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		try {
            			boolean flag = false;
            			name = t1.getText();
            			pass = new String(p1.getPassword());
            			res = st.executeQuery("select * from login");
            			while(res.next()) {
            				if(name.compareTo(res.getString(1)) == 0 && pass.compareTo(res.getString(2)) == 0) {
            					flag = true;
            					break;
            				}
            			}
            			if(flag == true)
            				JOptionPane.showMessageDialog(null,"Login Successfull");
            			else
            				JOptionPane.showMessageDialog(null,"User name doesn't exists or username or password is invalid!");
            		}
            		catch(Exception e1) {
            			JOptionPane.showMessageDialog(null,e1.getMessage());
            		}
            	}
            });
            
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
        }
        
    }
    public static void main(String...s){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Project1();
            }
        });
    }
}