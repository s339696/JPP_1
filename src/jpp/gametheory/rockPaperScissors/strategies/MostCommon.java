package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.GameRound;
import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;
import java.util.Map;

public class MostCommon implements IStrategy<RPSChoice> {

    
    private IStrategy<RPSChoice> alternate;

    public MostCommon(IStrategy<RPSChoice> alternate){
        this.alternate = alternate;
    }

    @Override
    public String name() {
        return "Most Common Choice (Alternate: " + alternate + ")";
    }

    @Override
    public String toString(){
        return "Most Common Choice (Alternate: " + alternate + ")";
    }

    /**
     * 1. PreviousRounds durchiterieren und herausfinden was am meisten gespielt wurde.
     * 2. Ist PreviousRounds die Liste von allen Spielern oder muss ich erst die Listen der anderen Spieler zusammenfügen?
     * 3. Mit if vergleichen ||
     * @param player Spieler für den der nächste Zug ausgegeben werden soll.
     * @param previousRounds Liste aller bisher gespielten Runden. Dabei steht die letzte Runde an letzter Stelle.
     * @return
     */
    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        int rock = 0;
        int paper = 0;
        int scissor = 0;
        RPSChoice decision = null;
        for (IGameRound<RPSChoice> round : previousRounds){
            for (Map.Entry<IPlayer<RPSChoice>, RPSChoice> entry :
                    round.getPlayerChoices().entrySet()) {
                if (entry.getValue().equals(RPSChoice.PAPER)){
                    paper++;
                } else if (entry.getValue().equals(RPSChoice.ROCK)){
                    rock++;
                } else if (entry.getValue().equals(RPSChoice.SCISSORS)){
                    scissor++;
                }
            }
        }

        if(rock > paper && rock > scissor){
            decision = RPSChoice.ROCK;
        } else if (paper > rock && paper > scissor){
            decision =  RPSChoice.PAPER;
        } else if (scissor > rock && scissor > paper){
            decision = RPSChoice.SCISSORS; 
        } else {
            return alternate.getChoice(player, previousRounds);
        }
        return decision;
    }
}
