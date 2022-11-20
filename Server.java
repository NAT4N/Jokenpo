import java.net.ServerSocket;
import java.net.Socket;

// 0: PAPEL
// 1: PEDRA
// 2: TESOURA


public class Server {
    public static void main(String[] args) throws Exception{

        ServerSocket listener = new ServerSocket(1234);
        System.out.println("Server is running...");
        while(true){
            Socket p1 = listener.accept();
            System.out.println("Player 1 connected");
            Socket p2 = listener.accept();
            System.out.println("Player 2 connected");
            System.out.println("Partida: " + p1.hashCode() + "-" + p2.hashCode() + " iniciada !");
            GameThread game = new GameThread(p1, p2, listener);
            game.start();
        }

    }


}
