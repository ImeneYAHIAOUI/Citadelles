# Scenario industriel

## Réussi :

* Architure cohérente avec l’idée de base (On a fait attention à la modularité de notre logiciel).
* A avoir une certaine régularité dans les rendez-vous dans la semaine
* On estime avoir un code de bonne qualité (Testé + Mockito + Modularité + Lisibilité + Code commenté )
* Bonne arborescence dans les fichiers du projet. (Chaque type de classe correspond à un package)
* Code maintenable : Modularité, visibilité
* Logiciel répondant au cahier des charges

## Raté :

* Procrastination de la mise en place de l’architecture d’utilisation des merveilles.
* Ordre du jour des reus pas toujours respecté (Parler de plusieurs sujets en même temps)
* Communication sur messenger mal exploité. Toutes les informations utiles pour le groupe et les informations personnelles sont noyées. (Exemple il suffit d’un jour sans la lecture de messenger pour perdre le fil et avoir des difficultée à traiter les données) Solution -> Privilégier les informations de groupe en faisant une synthèse sur la convesation de groupe, et utiliser des canaux privés pour des échanges avec une personne concernée.
* Répartition des tâches -> Les classes avec beaucoup de complexité n’ont pas été bien réparties. Résultat, pour la personne n’ayant pas beaucoup touchée la classe, il est difficile de se mettre à jour et de faire évoluer le programme.

## Conserver :

Toute l'architecture fonctionne avec des contrats. Ces interface nous garantit une certaine modularité. Pour toute nouvelle fonctionnalité dans les parties existances, il suffit d'implementer
l'interface. Les stragies sont des classes, ce qui permets de créer d'autre startegie facilement, et d'instaencier l'une d'elle pour l'attribuer à un bot.

Des classes existent pour récuperer les informations des choix de l'IA pour faire une action. 
Que ce soit pour l'action du hero ou de la merveille, ce qui permet d'avoir un seul argument dans les méthodes d'action.

Il y a un objet controlleur qui permet de veiller au bon séquencement de la boucle du jeu citadelles.

## Changer :

Les merveilles sont des classes, elles possèdent une méthode d'action, mais il y a seulement quelque une de ces merveilles qui necessite l'implementation de cette méthode.
Etant donné qu'elles implement une interface, il y a donc des merveilles avec cette méthode qui n'implemente rien.
Peut être qu'il faut abordé le problematique sous en autre angle. 

# RAPPORT

## L'architecture des Player

<img src="player.svg"/>

## L'architecture des Districts et Wonders

<img src="cards.svg"/>

projet ? Et quels choix vous ont amené à la réaliser ainsi ?
