package fr.unice.polytech.startingpoint.cards;
public interface IWonder{

    /**
         * Active the action of wonder
         */
        public abstract void doAction(infoaction info);

        /**
         * Analyze the effect of the action
         */
        public abstract void effectOfAction();
    public String getDescription() ;


    }