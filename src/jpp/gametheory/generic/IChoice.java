package jpp.gametheory.generic;

import jpp.gametheory.rockPaperScissors.RPSChoice;

/**
 * Eine Klasse, die dieses Interface implementiert,
 * stellt einen möglichen Zug dar, den ein Spieler in einer Runde machen kann.
 * Es empfiehlt sich, diese später als {@code enum} zu implementieren.
 */
public interface IChoice {

    /**
     * Gibt den Namen des Zuges / der Entscheidung zurück.
     * @return Name des Zuges / der Entscheidung.
     */
    String name();
}
