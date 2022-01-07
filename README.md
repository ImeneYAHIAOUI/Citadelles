# projet2-ps5-21-22-ps5-21-22-projet2-m
projet2-ps5-21-22-ps5-21-22-projet2-m created by GitHub Classroom

# Sommaire

1. [LOGs](#logs)
    1. [Avant son implémentation](#logs1)
    2. [Après son implémentation](#logs2)

# LOGs <a name="logs"></a>


## Avant l'implémentation des LOGs <a name="logs1"></a>

L'affichage dans le terminal est géré par une classe appelée display. Elle se situe dans le package output.

## Après l'implémentation des LOGs <a name="logs2"></a>

Nous avons utilisé la librairie JUL (pour java.util.logging). Ci dessous la déclaration de l'attribut LOGGER, avec son lot de configuration.

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

Pour changer les System.out.println(...), il suffit de faire une sélection multiple sur ceci, puis de les modifier avec le LOGGER.

    System.out.println(“ … ”) -> LOGGER.finest(“ … ”)

Le “.finest” configure un niveau à l’affichage.
Il existe plusieurs niveaux pour afficher plus ou moins d'informations. Ci dessous, les différents niveaux allant du plus élevé au plus bas.

         * SEVERE (valeur la plus élevée)
         * WARNING
         * INFO
         * CONFIG
         * FINE
         * FINER
         * FINEST (valeur la plus basse)

Tout l'affichage présent dans la classe Display a été configuré avec le niveau le plus bas. Mais comment changer le niveau d'affichage? Ci dessous la configuration du niveau.
    
    LOGGER.setLevel(Level.*);

Level.* : le * doit être remplacé par le nom du niveau en majuscule. Donc pour afficher nos LOGs, on doit mettre Level.FINEST.
L'affichage concerne alors le niveau sélectionné avec tous les niveaux plus élevés que celui-ci.
</br>
</br>
Dans la version final, nous devons avoir le choix entre l'affichage de tous les LOGs, comprenant l'affichage de la partie plus l'affichage
du résultat des simulations, ou seulement l'affichage des simulations. Le niveau des LOGs d'affichage de la simulation est donc configuré un niveau au-dessus.
    
    LOGGER.finer(" ... ")

En conclusion, pour tout afficher, on demande à afficher le niveau finest comme ci dessous.

    LOGGER.setLevel(Level.FINEST);

Pour afficher seulement la simulation, on demande à afficher le niveau finer.

    LOGGER.setLevel(Level.FINER);