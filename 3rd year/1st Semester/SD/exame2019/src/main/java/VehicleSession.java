import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

public class VehicleSession implements Runnable {

   private Control c;
   private Socket s;

   public VehicleSession(final Control c, final Socket s) {
      this.c = c;
      this.s = s;
   }

   public void run() {
      try {
         BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
         String message;
         while((message = in.readLine()) != null) {
            String[] cmd = message.split(" ");
            if ("barco".equals(cmd[0])) {
               if("entrar".equals(cmd[1])) {
                  c.entra_barco();
               } else if("sair".equals(cmd[1])) {
                  c.sai_barco();
               } else { System.out.println("Fode-te"); }

            } else if ("carro".equals(cmd[0])) {

               if("entrar".equals(cmd[1])) {
                  c.entra_carro();
               } else if("sair".equals(cmd[1])) {
                  c.sai_carro();
               } else { System.out.println("Fode-te"); }

            } else {
               System.out.println("Escreve coisas em condições fáxavor");
            }
         }
      } catch(Exception ignored) {
      }

   }
}
