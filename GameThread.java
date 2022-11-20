import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class GameThread extends Thread{

    private Socket p1; 
    private Socket p2;
    private ServerSocket listener;

    public GameThread(Socket p1, Socket p2, ServerSocket listener)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.listener = listener;
    }


    public void run() { 
        sendMessage(p1, "MSG: Seja bem-vindo ao jogo!");
        sendMessage(p2, "MSG: Seja bem-vindo ao jogo!");
        sendMessage(p1, "MSG: O jogo começou!");
        sendMessage(p2, "MSG: O jogo começou!");
        sendMessage(p1, "MSG: Digite um numero de 0 a 2");
        sendMessage(p1, "STATUS: y");
        sendMessage(p2, "MSG: Esperando player 1");
        int n1 = Integer.parseInt(receiveMessage(p1).substring(3));
        sendMessage(p2, "MSG: Digite um numero de 0 a 2");
        sendMessage(p1, "MSG: Esperando player 2");
        sendMessage(p2, "STATUS: y");
        int n2 = Integer.parseInt(receiveMessage(p2).substring(3));
        int winner = checkWinner(n1, n2);
        if (winner == 0) {
            sendMessage(p1, "MSG: Empate!");
            sendMessage(p2, "MSG: Empate!");
        } else if (winner == 2) {
            sendMessage(p1, "MSG: Você venceu!");
            sendMessage(p2, "MSG: Você perdeu!");
        } else {
            sendMessage(p2, "MSG: Você venceu!");
            sendMessage(p1, "MSG: Você perdeu!");
        }

        sendMessage(p1, "MSG: Deseja uma revanche ?  || 1 - Sim  || 0 - Não");
        sendMessage(p2, "MSG: Deseja uma revanche ?  || 1 - Sim  || 0 - Não");
        sendMessage(p1, "STATUS: y");
        sendMessage(p2, "STATUS: y");

        int revp1 = Integer.parseInt(receiveMessage(p1).substring(3));
        int revp2 = Integer.parseInt(receiveMessage(p2).substring(3));

        if(revp1 == 1 && revp2 == 1)
        {
            sendMessage(p1, "MSG: Revanche aceita ! \n Iniciando um novo jogo. . . \n");
            sendMessage(p2, "MSG: Revanche aceita ! \n Iniciando um novo jogo. . . \n");
            run();
        }else if(revp1 == 1 && revp2 == 0 || revp1 == 0 && revp2 == 1 ||revp1 == 0 && revp2 == 0)
        {
            sendMessage(p1, "MSG: A revanche não foi aceita por um dos jogadores");
            sendMessage(p2, "MSG: A revanche não foi aceita por um dos jogadores");
            System.out.println("Jogo: " + p1.hashCode() + "-" + p2.hashCode() + " encerrado !");
        }
        try{
            p1.close();
            p2.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Socket socket, String msg){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String receiveMessage(Socket socket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int checkWinner(int a, int b) {
        if (a == b) {
            return 0;
        }
        if (a == 0) {
            if (b == 1) {
                //P1 GANHA
                return 2;
            }
            if (b == 2) {
                //P2 GANHA
                return 1;
            }
        }
        if (a == 1) {
            if (b == 0) {
                return 1;
            }
            if (b == 2) {
                return 2;
            }
        }
        if (a == 2) {
            if (b == 0) {
                return 2;
            }
            if (b == 1) {
                return 1;
            }
        }
        return 0;
    }
}
