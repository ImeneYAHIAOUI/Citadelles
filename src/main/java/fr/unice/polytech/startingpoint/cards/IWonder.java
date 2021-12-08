package fr.unice.polytech.startingpoint.cards;

/*
 * Contract to create new .
 */
public interface IWonder{

    /**
     * Active the action of wonder
     */
    void doAction(IAToWonder info);

    /**
     * Returns the description of the wonder
     * @return
     */
    String getDescription() ;
}