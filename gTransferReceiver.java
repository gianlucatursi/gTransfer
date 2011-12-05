import java.io.*;
import java.net.*;
import java.util.*;
 
public class gTransferReceiver extends Thread {
 
  private String path = "./";
 
  private Socket socket;
 
 /**
   * Costructor for the class
   *
   * @param socket  : socket fot the connection
   * @param p : Path to save the file
   */
  public gTransferReceiver(Socket socket, String p) {
      this.socket = socket;
      path = p;
  }
 
  /**
   * Methode run is the main of the Thread, in this function
   * waiting for the incoming file and save the file recived through socket
   * 
   * @param socket  : socket fot the connection
   * @param p : Path to save the file
   */
  public void run() {
      try {
          
          /* waiting for the incoming file */
          ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
 
          File inFile = (File) objIn.readObject();
 
          /* path for the new file */
          File saveFile = new File(path + "/" + inFile.getName());
 
          /* save the file */
          save(inFile, saveFile);
 
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              socket.close();
          } catch (IOException e) { }
      }
  }
 
  /**
   * Save the file
   *
   * @param Fin  : File read
   * @param Fout : File where to write
   * @throws IOException
   */
  private void save(File Fin, File Fout) throws IOException {
	  System.out.println("gTransferClient >> I'm sending the file: :" + Fin.getName());
	   
      /* new stream for the file in input and output*/
      FileInputStream in  = new FileInputStream(Fin);
      FileOutputStream out = new FileOutputStream(Fout);
 
      byte[] file = new byte[1024];
      int i = 0;

      /* save the file */
      while((i = in.read(file))!=-1) {
          out.write(file, 0, i);
      }
      in.close();
      out.close();
 
      System.out.println("gTransferServer >> File recived correctly!");
      System.out.println("gTransferServer >> You can find the file here: " + path + "" + Fin.getName());
  }
}
