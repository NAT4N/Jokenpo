import java.util.Random;

public class OfflineGame {

    public void play(int jogador) {

        int winner = checkWinner(jogador);

        if (winner == 0) {
            System.out.println("MSG: Empate!");
        } else if (winner == 2) {
            System.out.println("MSG: Você venceu!");
        } else {
            System.out.println("MSG: Você perdeu!");
        }
    }

    public static int checkWinner(int a) {
        Random random = new Random();
        int b = random.nextInt(2);

        if (a == b) {
            return 0;
        }
        if (a == 0) {
            if (b == 1) {
                return 2;
            }
            if (b == 2) {
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
