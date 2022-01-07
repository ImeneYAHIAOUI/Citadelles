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

Nous avons utilisé la librairie JUL (pour java.util.logging).
Pour changer les System.out.println(...), il suffit de faire une sélection multiple sur ceci, puis de les modifier avec le LOGGER.

Il existe plusieurs niveaux pour afficher plus ou moins d'informations. Ci dessous, les différents niveaux allant du plus élevé au plus bas.

         * SEVERE (valeur la plus élevée)
         * WARNING
         * INFO
         * CONFIG
         * FINE
         * FINER
         * FINEST (valeur la plus basse)


Dans la version final, nous devons avoir le choix entre l'affichage de tous les LOGs, comprenant l'affichage de la partie plus l'affichage
du résultat des simulations, ou seulement l'affichage des simulations. Le niveau des LOGs d'affichage de la simulation est donc configuré un niveau au-dessus.
    
Dans le main :

En conclusion, pour tout afficher, on demande à afficher le niveau finest comme ci dessous.

    LOGGER.setLevel(Level.FINEST);

Pour afficher seulement la simulation, on demande à afficher le niveau finer.

    LOGGER.setLevel(Level.FINER);