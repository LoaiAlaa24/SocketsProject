import java.io.*; 
import java.util.*;
import java.net.*;
 
// Server class
public class OtherServer extends Thread implements Runnable
{
  
	ArrayList<ClientProfile> cpy = new ArrayList<>();
   
    static ArrayList<ClientProfile> cp2 = new ArrayList<>();
    static ArrayList<Server> ser = new ArrayList<>();

   
    
    public void run() 
    {
    	
        ServerSocket welcomeSocket = null;
		try {
			welcomeSocket = new ServerSocket(5445);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DataInputStream dis2 = null ;
        DataOutputStream dos2 =null;
    
        

        Socket u;
		try {
			u = new Socket("Lenovo-PC",5443);
	         dis2 = new DataInputStream(u.getInputStream());
	         dos2 = new DataOutputStream(u.getOutputStream());
	         
	         dos2.writeUTF("s");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Please Connect to Server1 for the first usage");
		}

        
         
         //}
  
        Socket s = null;
        while (true) 
        {  
            try {
				s = welcomeSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DataInputStream dis = null;
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DataOutputStream dos = null;
			try {
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     
            
            try {
				if(!(dis.readUTF().equals("s"))){
				System.out.println("Enter your username:");
				 Scanner scn = new Scanner(System.in);
		            ClientProfile x = new ClientProfile(2, s,scn.nextLine(), dis, dos , dis2 , dos2);
		            System.out.println(x.name + " has joined the session");
		          //  System.out.println("You can chat with "+cp2.size() +" users from this server and " + x.Sercp.size()+" users from server1");
		            cp2.add(x);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
          
            
            
        }
    }
}
