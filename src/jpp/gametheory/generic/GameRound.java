package jpp.gametheory.generic;

import java.util.*;

public class GameRound<C extends IChoice> implements IGameRound<C> {

    private  Map<IPlayer<C>, C> playerChoices;


    /**
     * Gibt die Zuordnung Spieler -> Entscheidung/Zug in dieser Runde zurück.
     * Achten Sie darauf dass von außerhalb keine Änderungen an Instanzen der Klasse vorgenommen werden können.
     * @return Zuordnung der Spieler auf ihre Sterategien.
     */
    @Override
    public Map<IPlayer<C>, C> getPlayerChoices() {
        Map<IPlayer<C>, C> copieMap= playerChoices;
        return copieMap;
    }

    /**
     * Gibt den Zug eines Spielers in dieser Runde zurück.
     * @param player Spieler für den der Zug in dieser Runde ausgegeben werden soll.
     * @return Zug des Spielers.
     * @throws IllegalArgumentException falls der Spieler in der Runde nicht mitgespielt hat.
     */

    @Override
    public C getChoice(IPlayer<C> player) {
        if (player == null){
            throw new NullPointerException("player is null");
        }
        if (!playerChoices.containsKey(player))
            throw new IllegalArgumentException("Spieler hat in der Runde nicht mitgespielt.");
        return playerChoices.get(player);
    }

    /**
     * Gibt ein Set aller Spieler in dieser Runde zurück.
     * @return Set der Spieler dieser Runde.
     */

    @Override
    public Set<IPlayer<C>> getPlayers() {
        Set<IPlayer<C>> copieSet = Collections.unmodifiableSet(playerChoices.keySet());
        return copieSet;
    }

    /**
     * Gibt zu einem Spieler alle anderen Spieler dieser Runde zurück.
     * @param player Spieler für den die anderen Spieler gesucht werden.
     * @return Set aller andern Spieler.
     * @throws IllegalArgumentException falls der Spieler in der Runde nicht mitgespielt hat.
     */

    @Override
    public Set<IPlayer<C>> getOtherPlayers(IPlayer<C> player) {
        if (player == null){
            throw new NullPointerException("player is null");
        }
        if (!playerChoices.containsKey(player))
            throw new IllegalArgumentException("Spieler hat in der Runde nicht mitgespielt.");
        else {
            Set<IPlayer<C>> copiedKeySet = new HashSet<>();
            for ( IPlayer<C> p: playerChoices.keySet()) {
                if (!p.equals(player)){
                    copiedKeySet.add(p);
                }
            }
            return copiedKeySet;
        }
    }

    public GameRound(Map<IPlayer<C>, C> playerChoices){
        if (playerChoices.keySet().isEmpty())
            throw new IllegalArgumentException("There are no Players");

        this.playerChoices = playerChoices;
    }

    @Override
    public String toString(){
        ArrayList<IPlayer<C>> players = new ArrayList<>(playerChoices.keySet());

        Collections.sort(players);

        int count = 0;
        String output = "";

        for (IPlayer<C> player :
                players) {

            if (count == 0){
                output += "(";
            }
            output += player.getName();
            output += " -> ";
            output += playerChoices.get(player).name();
            count++;
            if (count != players.size()) {
                output += ", ";
            } else {
                output += ")";
            }
        }

        return output;
    }
}
