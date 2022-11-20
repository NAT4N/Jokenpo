import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        
        int escolha = 0;
        System.out.println("-------------------- MENU -----------------------");
        System.out.println("1 - Jogador x Computador");
        System.out.println("2 - Jogador x Jogador");
        System.out.println("0 - Exit");
        System.out.println("-------------------------------------------------");

        escolha = processaEntrada();

        if(escolha == 1)
            offGame();
        else if (escolha == 2) 
            onGame();
    }
    public static void offGame()
    {
        while(true){
            System.out.println("Iniciando Jogo. . .");
            OfflineGame offlineGame = new OfflineGame();
            int jogada = 0;
            System.out.println("MSG: Digite um numero de 0 a 2");
            try{
                jogada = processaEntrada();
            }catch(Exception e)
            {
                System.out.println("Digite apenas numeros !");
                offGame();
            }
            offlineGame.play(jogada);
            System.out.println("Deseja jogar novamente ? \n 1 - Sim \n 0 - Não");
            int revanche = processaEntrada();
            if(revanche == 0){
                System.out.println("Jogo encerrado!");
                break;
            }
        }
    }
    public static void onGame()
    {
        try {
                Socket socket = new Socket("localhost", 1234);
                BufferedReader in;
                PrintWriter out;
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String response;

                System.out.println("Aguardando conexão com outro jogador. . . .");
                while (true) {
                    response = in.readLine();
                    if (response.startsWith("MSG: ")) {
                        System.out.println(response.substring(5));
                    }
                    if (response.startsWith("STATUS: ")) {
                        if (response.substring(8).equals("y")) {
                            int n = processaEntrada();
                            out.println("R: " + n);
                        }
                    }
                }
        } catch (Exception e) {
                System.out.println("Jogo encerrado!");
        }
    }

    public static int processaEntrada()
    {
        int response;
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            try{
                response = sc.nextInt(); 
                if(response <= 2){
                    break;
                }
                else{
                    System.out.println("Digite um numero entre 0 - 2");
                }
            }catch(Exception e)
            {
                System.out.println("Digite apenas numeros !");
            }
        }
        return response;
    }

}
