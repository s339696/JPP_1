package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;
import java.util.Map;

public class MostSuccessful implements IStrategy<RPSChoice> {

    private IStrategy<RPSChoice> alternate;
    private IReward<RPSChoice> reward;

    public MostSuccessful(IStrategy<RPSChoice> alternate, IReward<RPSChoice> reward) {
        this.alternate = alternate;
        this.reward = reward;
    }

    @Override
    public String name() {
        return "Most Successful Choice (Alternate: " + alternate + ")";
    }

    @Override
    public String toString(){
        return "Most Successful Choice (Alternate: " + alternate + ")";
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        int rock = 0;
        int scissor = 0;
        int paper = 0;
        RPSChoice decision;

        for (IGameRound<RPSChoice> round : previousRounds) {
            for (Map.Entry<IPlayer<RPSChoice>, RPSChoice> entry :
                    round.getPlayerChoices().entrySet()) {
                if (entry.getValue().equals(RPSChoice.SCISSORS)) {
                        scissor += reward.getReward(entry.getKey(), round);
                } else if (entry.getValue().equals(RPSChoice.ROCK)) {
                        rock += reward.getReward(entry.getKey(), round);
                } else {
                        paper += reward.getReward(entry.getKey(), round);
                }
            }
        }

        if(rock > paper && rock > scissor){
            decision = RPSChoice.ROCK;
        } else if (paper > rock && paper > scissor){
            decision =  RPSChoice.PAPER;
        } else if (scissor > rock && scissor > paper){
            decision = RPSChoice.SCISSORS;
        } else{
            return alternate.getChoice(player, previousRounds);
        }

        return decision;
    }
}