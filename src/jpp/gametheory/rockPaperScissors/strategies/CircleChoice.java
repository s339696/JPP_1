package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;

public class CircleChoice implements IStrategy<RPSChoice> {

    public CircleChoice(){}

    @Override
    public String name() {

        return "Circle Choice";
    }

    @Override
    public String toString(){

        return "Circle Choice";
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {

        RPSChoice decision = null;

        if (previousRounds.isEmpty()) {
            decision = RPSChoice.ROCK;

        }
         else if (previousRounds.get(previousRounds.size() - 1).getChoice(player).equals(RPSChoice.ROCK)){
            decision = RPSChoice.PAPER;

        }
         else if (previousRounds.get(previousRounds.size() - 1).getChoice(player).equals(RPSChoice.PAPER)) {
            decision = RPSChoice.SCISSORS;

        }
         else if (previousRounds.get(previousRounds.size() - 1).getChoice(player).equals(RPSChoice.SCISSORS)) {
            decision = RPSChoice.ROCK;

        }
    return decision;
    }
}
