package jpp.gametheory.generic;

public class KeyValuePair<C extends IChoice> implements Comparable<KeyValuePair> {
    private IPlayer<C> key;
    private int value;

    public IPlayer<C> getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public KeyValuePair(IPlayer<C> key, int value){
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(KeyValuePair o) {
        if (this.value > o.value)
            return -1;
        else if (this.value < o.value)
            return 1;
        else {
            return this.key.getName().compareTo(o.key.getName());
        }
    }
}
