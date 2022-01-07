# projet2-ps5-21-22-ps5-21-22-projet2-m
projet2-ps5-21-22-ps5-21-22-projet2-m created by GitHub Classroom


## LOGs

### Avant l'implentation des LOGs

L'affichage dans le terminal est géré par une class appelé display. Elle se situe dans le package output.

### Aprés l'implémentation des LOGs

Nous avons utilisé la librairie JUL (pour java.util.logging). Ci dessous la déclaration de l'attribut LOGGER, avec son lots de configuration.

    import java.util.logging.*; 
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    Logger configuration
    static{
        LOGGER.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new TerminalFormatter());
        handler.setLevel(Level.FINEST);
        LOGGER.addHandler(handler);
    }

Pour changer les System.out.println(...), il suffit de faire un selection multiple sur ceci, puis de les modifier avec le LOGGER.

    LOGGER.finest(" ... ")

Il existe plusieurs niveaux pour afficher plus ou moins d'information. Ci dessous, les differents niveaux allant du plus elevé au plus bas.

         * SEVERE (valeur la plus élevée)
         * WARNING
         * INFO
         * CONFIG
         * FINE
         * FINER
         * FINEST (valeur la plus basse)

Tout l'affichage present à configuré avec le niveau le plus bas. Mais comment changé le niveau d'affichage? Ci dessous le configuration du niveau

    LOGGER.setLevel(Level.*);

Level.* : le * doit être remplacer par le nom du niveau en majuscule. Donc pour afficher nos LOGs, on doit mettre Level.FINEST.
L'affichage concernera le niveau selectionné avec tous les niveaux plus elevé que celui ci. 
</br>
</br>
Dans la version final, nous devons avoir le choix entre l'affichage de tous les LOGs, comprenant l'affichage de la partie plus l'affichage 
du resultat des 2 parties. Ou seulement l'affichage des simulations. Le niveau des LOGs d'affichage de la simulation est donc configuré un niveau au dessus.
    
    LOGGER.finer(" ... ")

En conclusion, pour tout afficher, on demande à afficher le niveau finest comme ci dessous.

    LOGGER.setLevel(Level.FINEST);

Pour afficher seulement la simulation, on demande à afficher le niveau finer.

    LOGGER.setLevel(Level.FINER);