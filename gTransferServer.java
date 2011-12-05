import java.io.*;
import java.net.*;
import java.util.*;
 
public class gTransferServer {
 
  public static void main(String[] args) {
	    
	    String path = args[0];
		
		try {
			ServerSocket s = new ServerSocket(8189);
			 
			/* Cycle to accept every connection */
			for (;;) {
				
				/* accept the connection */
				Socket mySock = s.accept();
				
				/*  starts the thread that manages the transfer so 
				 *  i can cycle for new connection without getting hung up */ 
				new gTransferReceiver(mySock, path).start();
	
				/* sleep the thread for not to overload */
				try {
					Thread.sleep(500); //0.5 sec
				} catch (InterruptedException e) { }
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
 }
