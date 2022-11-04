import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
       try {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader in;
        PrintWriter out;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        boolean cont = true;
        String response;
        while (cont) {
            response = in.readLine();
            if (response.startsWith("MSG: ")) {
                System.out.println(response.substring(5));
            }
            if (response.startsWith("STATUS: ")) {
                if (response.substring(8).equals("y")) {
                    int n = Integer.parseInt(System.console().readLine());
                    out.println("R: " + n);
                }
            }
        }
       } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Jogo encerrado!");
       }
    }

}
