import java.io.*;
import java.net.*;
import java.util.*;
 
public class gTransferClient {
 
	public static void main(String[] args) {
		
		File upload = new File(args[0]);
		
		try {
				
			/* address for LocalHost: 127.0.0.1 */
			String addr = InetAddress.getLocalHost().getHostName();
			Socket socket = new Socket(addr, 8189);
		
			/* send file to the gTransferServer */
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.reset();
			out.writeObject(upload);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
