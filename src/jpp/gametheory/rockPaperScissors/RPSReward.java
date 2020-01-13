package jpp.gametheory.rockPaperScissors;

import jpp.gametheory.generic.IChoice;
import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;

import java.util.ArrayList;


public class RPSReward implements IReward<RPSChoice> {


    /**
     * Gibt den Ertrag zurück, den der Spieler in der Runde gemacht hat.
     * Je höher der Zahlenwert, desto größer der Gewinn.
     *
     * @param player Spieler für den der Profit berechnet werden soll.
     * @param gameRound Runde für die der Profit berechnet werden soll.
     *
     * @return Profit für den Spieler in der Runde.
     *
     * @throws IllegalArgumentException Falls der Spieler in der Runde nicht mitgespielt hat.
     */
    @Override
    public int getReward(IPlayer<RPSChoice> player, IGameRound<RPSChoice> gameRound) {
        int myPoints = 0;
        int count = 0;
        RPSChoice decision = gameRound.getChoice(player);

        ArrayList<IPlayer<RPSChoice>> players = new ArrayList<>(gameRound.getPlayers());

        for (IPlayer<RPSChoice> p : players) {
            if (p.equals(player)) {
                count++;
            }
        }
        if( count != 1) {
            throw new IllegalArgumentException("Spieler hat in der Runde nicht mitgespielt.");
        } else {
        for(IPlayer<RPSChoice> temp : gameRound.getOtherPlayers(player)){
            if (!gameRound.getChoice(temp).equals(decision)){
                switch (decision){
                    case PAPER:
                        if (gameRound.getChoice(temp) == RPSChoice.SCISSORS)
                            myPoints--;
                        if (gameRound.getChoice(temp) ==  RPSChoice.ROCK)
                            myPoints += 2;
                        break;
                    case ROCK:
                        if (gameRound.getChoice(temp) ==  RPSChoice.SCISSORS)
                            myPoints += 2;
                        if (gameRound.getChoice(temp) ==  RPSChoice.PAPER)
                            myPoints--;
                        break;
                    case SCISSORS:
                        if (gameRound.getChoice(temp) == RPSChoice.PAPER)
                            myPoints += 2;
                        if (gameRound.getChoice(temp) == RPSChoice.ROCK)
                            myPoints--;
                    }
                }
            }
        }

        return myPoints;
    }
}
