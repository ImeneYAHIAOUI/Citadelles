# projet2-ps5-21-22-ps5-21-22-projet2-m
projet2-ps5-21-22-ps5-21-22-projet2-m created by GitHub Classroom

# Sommaire

 1. [Fonctionnalités du Jeu](#fonctionnalite)
    1. [Implementation des quartiers](#fonc1)
    2. [Implemtation des  Héros](#fonc2)
    3. [Régles du jeu](#fonc3)
    4. [Les bots](#fonc4)
    5. [Objectif atteint ](#fonc5)
 

2. [LOGs](#logs)
    1. [Avant son implémentation](#logs1)
    2. [Après son implémentation](#logs2)
3. [Les Statistiques](#stat)
   

# Fonctionnalités du Jeu  <a name="fonctionnalite"></a>
## Implementation des quartiers  <a name="fonc1"></a>
- L'implémentation de tous les quartiers avec la gestion des merveilles en tenant compte de leurs effets dans le jeu.

## Implémentation des héros  <a name="fonc2"></a>
- L'implémentation des 8 héros du jeu, avec la gestion de leur actions pour chaque tour.

## Régles du jeu  <a name="fonc3"></a>
- On a une fin de partie à 8 quariers battis, avec la gestion des faces cachés et visibles pendant la  distrubition des cartes héros. 
-  La gestion des points bonus en fin de jeu, par exemple le bonus couleurs, bonus du  premier à finir....
-  La gestion de l'achat  des doublons .
-  La gestion  de l'ordre des joueur pour le choix des héros qu'on controle par une CircularList

## Les bots <a name="fonc4"></a>
- Pour une première version on  a  crée quatre bots chacun avec ses propres stratégies et chemins de pensée qui s'impliquent le choix de l'héro et les choix de son action, et pour l'extension de la dernière semaine on a crée un cinquième bot dont la stratégie  combine celle du premier commentaire de Rachel et celui d'Alphonse.

## Objectif atteint <a name="fonc5"></a>
- On peut considérer que notre  système est  un jeu citadelle complet avec tous ses fonctionnalités et régles.

# LOGs  <a name="logs"></a>



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
# Les statistiques <a name="stat"></a>
Les 2 simulations ont été faites.
La première simulation: BOT1 (notre meilleur Bot Nasty bot) **contre** BOT2 (Random Bot ).
La deuxième  simulation: BOT1( Nasty bot) **contre** BOT2(Nasty bot).
L'entête du fichier : PARTIES_GAGNEES1 ,% PARTIES_GAGNEES1,PARTIE_PERDUES1,%PARTIES_PERDUES1,PARTIES_NULLES1,%PARTIES_NULLES1,SCORE1,PARTIES_GAGNEES2 ,%PARTIES_GAGNEES2,PARTIE_PERDUES2,%PARTIES_PERDUES2,PARTIES_NULLES2,%PARTIES_NULLES2,SCORE2.

on calcule la moyenne  du nouveau résultat avec les anciens du même type de simulation.
Exemple: 1ére simulation lancée: on a 780 parties gagnées
2éme simulation lancée : on a 720 parties gagnées

3éme simulation lancée : on a 300 parties gagnées

à la fin de la 3éme simulation pour le nombre des parties gagnées on écrit (780+720+300)/3 donc 600.on fait le même calcul pour les parties perdues/parties nulle.
