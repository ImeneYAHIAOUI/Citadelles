package fr.unice.polytech.startingpoint.cards;
public interface IWonder{

    /**
     * Active the action of wonder
     */
    public void doAction(infoaction info);

    /**
     * Analyze the effect of the action
     */
    public void effectOfAction();
    public String getDescription() ;
}