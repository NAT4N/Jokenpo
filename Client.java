import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------- MENU -----------------------");
        System.out.println("1 - Jogador x Computador");
        System.out.println("2 - Jogador x Jogador");
        System.out.println("0 - Exit");

        int escolha = sc.nextInt();

        try {
            if(escolha == 1)
            {
                OfflineGame offlineGame = new OfflineGame();
                System.out.println("MSG: Digite um numero de 0 a 2");
                int jogada = sc.nextInt();
                offlineGame.play(jogada);

            }else if (escolha == 2) {
                Socket socket = new Socket("localhost", 1234);
                BufferedReader in;
                PrintWriter out;
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                boolean cont = true;
                String response;

                System.out.println("Aguardando conexão com outro jogador. . . .");
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
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Jogo encerrado!");
        }
    }

}
