import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ServerClass {
	public static void main(String[]args){
		Server server1 =new Server();
		server1.start();

		OtherServer server2 = new OtherServer();
		server2.start();
		
	}

}
