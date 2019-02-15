import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*; 
import java.net.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

 
public class ClientGUI extends JFrame implements ActionListener
{    private JComboBox members;
   private static JPanel mainPanel,chattingPanel;
	private static JFrame jf;
	private static JLabel rec;
	private static JTextField msgss;
	public static JTextField nameField,textField;
	private static JButton connectToser1,getmemberlist,sends;
	static String inFromUser , name ,message;
	static boolean send;
	static DataInputStream x;
	static DataOutputStream y;
	static Socket s;
     public static void main(String args[]) throws Exception 
    {  
    	 
    	 
    	 jf = new JFrame();
    jf.setSize(500,500);
	jf.setVisible(true);
	jf.setTitle("Chatting App");
	nameField=new JTextField("Enter your name");
	textField = new JTextField();
	mainPanel = new JPanel(new FlowLayout());
	rec = new JLabel();
	msgss = new JTextField("Chatting here");
	msgss.setSize(100,100);
	chattingPanel =  new JPanel(new FlowLayout());
	connectToser1=new JButton("connect");
	getmemberlist=new JButton("GetMemberList");
	sends=new JButton("Send");
	Socket s;
  chattingPanel.add(msgss);
  chattingPanel.add(sends);
  chattingPanel.add(rec);
	//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	mainPanel.add(nameField);
	mainPanel.add(getmemberlist);
	mainPanel.add(connectToser1);

	mainPanel.add(textField);

	jf.setContentPane(mainPanel);
	mainPanel.add(chattingPanel);
	jf.setVisible(true);
	jf.repaint();
	jf.revalidate();
	s = new Socket("Lenovo-PC", 5443);
	System.out.println("connected");;
   x = new DataInputStream(s.getInputStream());
   y = new DataOutputStream(s.getOutputStream());
  // y.writeUTF("u");
  /* Thread sending = new Thread(new Runnable() 
   {
       

		public void run() {
           //while (true) {

				String msg = "";
				msg = inFromUser;//nameField.getText();//.readLine();
              // System.out.println(msg); 
               try {
               	if( y!=null && send){
                   y.writeUTF(msg);
                   send =false;
               	}
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
		//}
   });
   */
    

   Thread reading = new Thread(new Runnable() 
   {   public void run() {
         while (true) {
               try {
               	String msg;
                   if(x !=null){
				     rec.setText(x.readUTF());
                   //rec.setText(msg);
                   }
                   //System.out.println(msg);
               } catch (IOException e) {
                   System.out.println("You logged out!");
               }
           }
   }
   });

   
   reading.start();
   
   sends.addMouseListener(new MouseAdapter() {
	   public void mouseClicked(MouseEvent arg0){		
			  send = true;
				message = msgss.getText();
			
				  if(message!= null)
					try {
						
						y.writeUTF(message);
					 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		}});
		
 
   
	connectToser1.addMouseListener(new MouseAdapter() {
		
		public void mouseClicked(MouseEvent arg0){		
		  send = true;
			name = nameField.getText();
		
			  if(name!= null)
				try {
					
					y.writeUTF(name);
				 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}});
	
	
	
	
    	
       
     
        
        
        
    }
     
     public void actionPerformed(ActionEvent a) {
 		Object x = a.getSource();}

}

	