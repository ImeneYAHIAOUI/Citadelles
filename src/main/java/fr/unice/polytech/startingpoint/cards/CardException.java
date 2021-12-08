package fr.unice.polytech.startingpoint.cards;

/*
 * If the developer puts an incorrect value for the card, an exception is thrown
 */
public class CardException extends Exception{
    private static final long serialVersionUID = 1L;

    public CardException(String string) {
        System.err.println("CardException error: " + string);
    }
}
