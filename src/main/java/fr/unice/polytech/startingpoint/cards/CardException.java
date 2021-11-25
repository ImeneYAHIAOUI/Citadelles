package fr.unice.polytech.startingpoint.cards;

public class CardException extends Exception{
    private static final long serialVersionUID = 1L;

    public CardException(String string) {
        System.err.println("CardException error: " + string);
    }
}
