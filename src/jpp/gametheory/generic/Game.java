package jpp.gametheory.generic;

import java.util.*;

public class Game<C extends IChoice> {


    private Set<IPlayer<C>> players;
    private IReward<C> reward;
    private List<IGameRound<C>> gameRounds;


    public Game(Set<IPlayer<C>> players, IReward<C> reward) {
        if (players == null) {
            throw new NullPointerException("We ait found shit.");
        } else {
            this.players = players;
            if (reward == null) {
                throw new NullPointerException("reward is null");
            } else {
                this.reward = reward;
            }
        }
        this.gameRounds = new ArrayList<>();
    }

    public Set<IPlayer<C>> getPlayers() {
        return players;
    }

    public IGameRound<C> playRound() {
        Map<IPlayer<C>, C> playerChoices = new HashMap<>();

        for (IPlayer<C> p : players) {
            C choice = p.getChoice(getPlayedRounds());
            playerChoices.put(p, choice);
        }
        GameRound<C> gameRound = new GameRound<C>(playerChoices);
        gameRounds.add(gameRound);
        return gameRound;
    }

    public void playNRounds(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n is 0");
        } else {
            for (int i = 0; i < n; i++) {
                playRound();
            }
        }
    }

    public Optional<IGameRound<C>> undoRound() {
        if (gameRounds.isEmpty()) {
            return Optional.empty();
        }
        IGameRound<C> deleted = gameRounds.remove(gameRounds.size() - 1);
        return Optional.ofNullable(deleted);
    }

    public void undoNRounds(int n) {
        for (int i = 0; i < n; i++) {
            undoRound();
        }

    }

    public List<IGameRound<C>> getPlayedRounds() {
        List<IGameRound<C>> copieList = Collections.unmodifiableList(gameRounds);
        return copieList;
    }

    public int getPlayerProfit(IPlayer<C> player) {
        int count = 0;
        int profit = 0;
        for (IPlayer<C> p : players) {
            if (p.equals(player)) {
                count++;
            }
        }
        if (count < 1) {
            throw new NullPointerException("player is null");
        } else {
            for (IGameRound<C> games : gameRounds) {
                profit += reward.getReward(player, games);
            }
        }

        return profit;
    }

    public Optional<IPlayer<C>> getBestPlayer() {

        ArrayList<IPlayer<C>> highestScored = new ArrayList<>();
        int profit = 0;
        for (IPlayer<C> p : players) {
            int pProfit = 0;
            for (IGameRound<C> game : gameRounds) {
                pProfit += reward.getReward(p, game);
            }
            if(pProfit > profit){
                profit = pProfit;
                highestScored.clear();
                highestScored.add(p);
            }
            else if (pProfit == profit){
                highestScored.add(p);
            }
        }

        if (highestScored.size() > 1){
            return Optional.empty();
        }
        return Optional.of((highestScored.get(0)));
    }

    public static void main(String[] args) {
        String one = "Spiel nach " + 2 + "Runden:";
        String two = "Profit : Spieler";

        System.out.println(one + two);
    }

    public String toString() {
        int profit = 0;
        SortedSet<KeyValuePair<C>> scoringSet = new TreeSet<>();

        for (IPlayer<C> p : players) {
            int pProfit = getPlayerProfit(p);
            scoringSet.add(new KeyValuePair<>(p, pProfit));
        }
        
        StringBuilder output = new StringBuilder();

        output.append("Spiel nach " + gameRounds.size() + " Runden:").append("\n").append("Profit : Spieler").append("\n");
        int count = 0;
        for (KeyValuePair<C> kvp :
                scoringSet) {
            output.append(kvp.getValue()).append(" : ").append(kvp.getKey());
            count++;
            if (count < scoringSet.size())
             output.append("\n");
            }

        return output.toString();
    }
}
