package fr.unice.polytech.startingpoint.cards;

public interface IWonder extends IDistrict{
    /**
     * Active the action of wonder
     */
    public void doAction();

    /**
     * Analize the effect of the action
     */
    public void effectOfAction();
}
