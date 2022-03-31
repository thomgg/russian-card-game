package wenham;

import java.util.*;

public class GameLogic {

    public static int chooseCardP2(int[][] gameHistory, int stakeCard){
        /*
        The "gameHistory" is a two-dimensional array, of dimension [k][3] where k is the number of previous rounds that
        have been played so far in the game (hence k is between 0 and 12 inclusive.).  The outer array dimension
        (of size k) represents the past rounds in the order they were played - so index 0 is the first round played;
        index 1 is the second round played, etc.  The inner array (of length [3]) contains 3 ints - {the int of the
        stake card shown in round k; the int of the card the human player chose in round k; then int of the card the
        computer player chose in round k}.
        The "stakeCard" is the card shown from the third suit.
        */
        validateGameHistory(gameHistory);

        List<Integer> possible_cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) possible_cards.add(i);
        List<Integer> opponent_cards = new ArrayList<>(possible_cards);
        List<Integer> remaining_stakes = new ArrayList<>(possible_cards);
        int max = 0;
        for (int round[] : gameHistory) {
            if (round[2] == 0) break;
            if (max < round[0]) max = round[0];
            opponent_cards.remove(new Integer(round[1]));
            possible_cards.remove(new Integer(round[2]));
            remaining_stakes.remove(new Integer(round[0]));
        }
        remaining_stakes.remove(new Integer(stakeCard));


        if (max == 0) return stakeCard == 13 ? 13 : stakeCard + 1;
        else if (stakeCard == 13 && (possible_cards.contains(stakeCard))) return 13;
        else if (possible_cards.contains(stakeCard) && !opponent_cards.contains(stakeCard)) return stakeCard;
        else if (possible_cards.contains(stakeCard + 1) && !opponent_cards.contains(stakeCard + 1)) return stakeCard + 1;
        else if (possible_cards.contains(stakeCard + 2) && !opponent_cards.contains(stakeCard + 2)) return stakeCard + 2;
        else if (possible_cards.contains(stakeCard + 1))  return stakeCard + 1;
        else if (possible_cards.contains(stakeCard + 2))  return stakeCard + 2;
        else if (possible_cards.contains(stakeCard))  return stakeCard;
        else if (possible_cards.contains(stakeCard - 1) && !opponent_cards.contains(stakeCard - 1)) return stakeCard - 1;
        else {
            Random r = new Random();
            return possible_cards.get(r.nextInt(possible_cards.size()));
        }


    }

    public static int calcPlayer1Score(int[][] gameHistory){
        //gameHistory defined above

        validateGameHistory(gameHistory);
        int score = 0;
        for (int round[] : gameHistory){
            if (round[1] > round[2]) score += round[0];
        }
        return score;
    }

    public static void validateGameHistory(int [][] gameHistory){
        Set<Integer> stakecards, p1cards, p2cards;
        stakecards = new HashSet<>(); p1cards = new HashSet<>(); p2cards = new HashSet<>();
        for (int round[] : gameHistory){
            if (round[0] == round[1] && round[1] == round[2] && round[1] == 0) break; //don't try to evaluate a game that hasn't been played
            if ((round.length != 3) ||
                    !(((round[0] > 0) && (round[0] <= 13)) || ((round[1] > 0) && (round[1] <= 13)) || ((round[2] <= 13))) ||
                    (!stakecards.add(round[0]) || !p1cards.add(round[1]) || !p2cards.add(round[2]))
                    ) throw new IllegalArgumentException();
            /*  Illegality criteria
                if there are not exactly 3 values for cards in each round
                if any of the card values are not 1-13 (or 0, which means not yet played)
                if any of the lists stays the same when the card is added, then the value was already present, so the gameHistory is not valid
            */
        }
    }

    public static void validateGameWithUnfinishedRound(int [][] gameHistory) {
        Set<Integer> stakecards, p1cards, p2cards;
        stakecards = new HashSet<>();
        p1cards = new HashSet<>();
        p2cards = new HashSet<>();
        for (int round[] : gameHistory) {
            if (round[0] == 0 && round[1] == round[2] && round[1] == 0 ) break; //don't try to evaluate a game that hasn't been played
            else if (round[0] != 0 && round[1] == round[2] && round[1] == 0) { //if players not played only check stake
                if (!stakecards.add(round[0])) throw new IllegalArgumentException();
            }
            else if (round[0] != 0 && round[1] != 0 && round[2] == 0) { //if only p2 has not played check stake and player 1
                if (!stakecards.add(round[0]) || !p1cards.add(round[1])) throw new IllegalArgumentException();
            }
            else if ((!stakecards.add(round[0]) || !p1cards.add(round[1]) || !p2cards.add(round[2]))) //if all have played check all
                throw new IllegalArgumentException();
        }
    }
}
