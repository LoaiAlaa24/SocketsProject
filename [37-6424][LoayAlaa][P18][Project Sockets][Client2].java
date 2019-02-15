import java.io.*; 
import java.net.*;
import java.util.Scanner;

 
public class Client2 
{
 
     public static void main(String args[]) throws Exception 
    {
    	Socket s;
    	int portID = 0;
    	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("1 -> server 1 or 2-> server 2");
    	Scanner scn = new Scanner(System.in);
    	int c = scn.nextInt();
    	if(c==1){
    	portID = 5443;	
    	}else if(c==2){
    		portID=5445;
    	}	
    	
    	s = new Socket("Lenovo-PC", portID);
        DataInputStream x = new DataInputStream(s.getInputStream());
        DataOutputStream y = new DataOutputStream(s.getOutputStream());
        y.writeUTF("u");
        Thread sending = new Thread(new Runnable() 
        {
            public void run() {
               
                while (true) {
                    String msg = "";
					try {
						msg = inFromUser.readLine();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
                     
                    try {
                        y.writeUTF(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }); 
        sending.start();
         
     
        Thread reading = new Thread(new Runnable() 
        {   public void run() {
                while (true) {
                    try {
                        String msg = x.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        System.out.println("You logged out!");
                        break;
                    }
                }
            }
        });

        
        reading.start();
        
    }}

	