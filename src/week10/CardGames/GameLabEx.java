package week10.CardGames;

/**
 * Created by Tom on 26/03/2016.
 */
public class GameLabEx {
    Deck d;
    Hand[] hands;

    int scores[];

    final int NUMBER_OF_HANDS, CARDS_IN_A_HAND;
    int maxScore;

    String telemetry = ""; ////>>> lab exercise

    //CONSTRUCTORS
    public GameLabEx(int initNumberOfHands, int initCardsInAHand){
        NUMBER_OF_HANDS = initNumberOfHands; //input parameter required in multiple methods
        CARDS_IN_A_HAND = initCardsInAHand; //input parameter required in multiple methods

        d = new Deck();
        d.fill(); d.shuffle();

        if (NUMBER_OF_HANDS * CARDS_IN_A_HAND <= d.getCardsLeft()) {
            hands = new Hand[NUMBER_OF_HANDS];

            for (int i = 0; i < NUMBER_OF_HANDS; i++) {
                hands[i] = new Hand(CARDS_IN_A_HAND);
            }
        }
    }

    //ACCESSORS
    //not required

    //MUTATORS
    //not required

    //OTHER METHODS
    public void play(){
        scores = new int[NUMBER_OF_HANDS]; //elements are auto=init to 0

        dealCards();

        //GAME LOGIC
        //>>> START lab exercise
        for (int i = 0; i < NUMBER_OF_HANDS; i++){
            processHand(i);
        }
        //>>> END lab exercise

        //sum card values for each hand
        for (int i = 0; i < NUMBER_OF_HANDS; i++){

            for (int j = 0; j < CARDS_IN_A_HAND; j++){
                scores[i] += hands[i].getCard(j).getValue();
            }
        }

        //find max score
        maxScore = scores[0];
        for (int i = 1; i < scores.length; i++){
            if (scores[i] > maxScore) {
                maxScore = scores[i];
            }
        }
    }

    private void dealCards(){
        for (int i = 0; i < NUMBER_OF_HANDS; i++){
            for (int j = 0; j < CARDS_IN_A_HAND; j++){
                hands[i].acceptCard(d.deal());
            }
        }
    }

    //>>> START lab exercise //
    private void processHand(int i){
        Hand h = hands[i];
        Card c;
        int scoreDelta;
        for (int j = 0; j < CARDS_IN_A_HAND; j++){
            if (h.getCard(j).getValue() <= 7){
                if (Math.random() < 0.75) {
                    c = d.deal();
                    scoreDelta = c.getValue() - h.getCard(j).getValue();
                    telemetry += "hand #" + i + " card #" + j + " ";
                    telemetry += h.getCard(j).toString() + " replaced by ";
                    telemetry += c.toString() + " (score change " + scoreDelta + ")";
                    telemetry += "\n";
                    h.acceptCardAt(c, j);
                }
            }
        }
    }
    //>>> END lab exercise

    public String toString(){
        String retVal = "";

        retVal += telemetry;
        for (int i = 0; i < NUMBER_OF_HANDS; i++){
            retVal += "\nHand #" + i + ", Score:" + scores[i] + "\n";
            retVal += hands[i].toString();
        }

        retVal += "\n\n";
        //allow for ties
        for (int i = 0; i < NUMBER_OF_HANDS; i++) {
            if (scores[i] == maxScore)
                retVal += "Hand #" + i + " wins with a score of " + maxScore + "\n";
        }

        return retVal;
    }
}
