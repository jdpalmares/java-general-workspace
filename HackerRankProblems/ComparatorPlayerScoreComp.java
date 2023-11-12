import java.util.*;

// Write your Checker class here
class Checker implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        // sort higher score would be returned
        if (player1.score > player2.score) {
            return -1;
        } else if (player1.score == player2.score) {
            // if equal score, sort alphabetically
            if (player1.name.compareTo(player2.name) <= 0) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
        // shorter alt answer but as efficient
        // if (a.score == b.score){
        // return a.name.compareTo(b.name);//alphabetically
        // } else {
        // return b.score - a.score;//decreasing
        // }
    }
}

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class ComparatorPlayerScoreComp {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for (int i = 0; i < n; i++) {
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for (int i = 0; i < player.length; i++) {
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}