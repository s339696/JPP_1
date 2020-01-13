package jpp.gametheory.generic;


import java.util.List;

public class Player<C extends IChoice> implements IPlayer<C> {

    private String name;
    private IStrategy<C> strategy;

    public Player(String name, IStrategy<C> strategy){
        if (name == null)
            throw new NullPointerException();
        if (strategy == null)
            throw new NullPointerException();

        this.name = name;
        this.strategy = strategy;
    }


    /**
     * Gibt den Namen des Spielers aus.
     * @return Name des Spielers.
     */

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gibt die Strategie des Spielers aus.
     * @return Strategie des Spielers.
     */

    @Override
    public IStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Gibt die Wahl des Zuges für die nächste Runde für diesen Spieler aus. Dabei werden alle bisher gespielten Runden übergeben.
     * @param previousRounds Bisher gespielte Runden. Falls noch keine Runden gespielt wurden, soll eine leere Liste übergeben werden.
     * @return Wahl des Spielers für die nächste Runde.
     */

    @Override
    public C getChoice(List<IGameRound<C>> previousRounds) {
        if(previousRounds == null){
            throw new NullPointerException("previousRounds is null");
        } else {
            return strategy.getChoice(this, previousRounds);
        }
    }

    @Override
    public int compareTo(IPlayer<C> o){
        return this.name.compareTo(o.getName());
    }


    @Override
    public boolean equals (Object obj){
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        Player other = null;
        if (obj.getClass() == this.getClass())
            other = (Player) obj;
        else
            return false;
        if (this.getName().equals(other.getName()))
            return true;
        return false;
    }

    @Override
    public int hashCode(){
        return this.getName().hashCode();
    }

    @Override
    public String toString(){
        return this.name + "(" + this.strategy + ")";
    }

}

