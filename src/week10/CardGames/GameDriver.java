package week10.CardGames;

/**
 * Created by shuxford on 26/03/2016.
 */
public class GameDriver {

    public static void main(String[] args) {
        Game aGame = new Game(4, 5);
        //GameLabEx aGame = new GameLabEx(4, 5);

        aGame.play();
        System.out.println(aGame); //implicit toString
    }

}
