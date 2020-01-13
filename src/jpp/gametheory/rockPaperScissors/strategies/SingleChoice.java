package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;

public class SingleChoice implements IStrategy<RPSChoice> {

    private RPSChoice choice;

    public SingleChoice(RPSChoice choice) {
        if (choice == null) {
            throw new NullPointerException("choice is null");
        }
        this.choice = choice;
    }

    @Override
    public String name() {
        String stringChoice = choice.name();
        return "Always " + stringChoice;

    }

    @Override
    public String toString() {
        String stringChoice = choice.name();
        return "Always " + stringChoice;
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        return choice;
    }
}
