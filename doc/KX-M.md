# Sommaire
1. [Scenario industriel](#etape1)
2. [RAPPORT](#etape2)
   1. [Architecture](#third-example)
   2. [Process de l'équipe]

# Scenario industriel <a name="etape1"></a>

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

# RAPPORT <a name="etape2"></a>

## Architecture

### L'architecture des Heros

<img src="heros.png"/>

Si le développeur veut ajouter un nouveau hero, il suffit ici de le faire hériter de Hero. Il n'aura plus que la méthode
doAction à implémenter.

* IHero : L'interfaces IHero est le contrat avec le reste du jeu. La classe Hero est une classe abstraite qui factorise le code
redondant de chaque hero. Il ne reste que définir la méthode doAction() pour que les hero puisse appliquer
leur pouvoir sur la partie. Cette méthode prend en parametre un objet qui ressence toutes les décision du IPlayer. 

* HeroName : Le heroName repertorie tous les nomes des heros. 

* DistrictDeck : La districtDeck comporte une liste de ces IHero. Toutes les méthodes implémentées dans cette deck permettent de gérer 
cette liste. 

* IaToHero : La classe IaToHero est une classe qui permet de récolter toutes les informations de décision de l'IA pour les actions 
du hero

### L'architecture des Districts et Wonders

<img src="cards.png"/>

Cette architecture permet d'ajouterfacilement un nouveau Wonder comme de nouveau district. 
Pour les district il suffit d'ajouter leur nom dans les énum, et pour les wonders, il suffit de les faire hérité de DistrictD.
Il n'y aura plus qu'a implémenter la méthode doAction. 

* IDistrict : Le IDistrict est le contrat avec le reste du jeu. La classe DistrcitD est une classe abstraite qui factorise le code commun.
La classe District est la classe pour instancier des objets. En paramètre, il faut mette son prix, sa couleur et son nom.
Les Wonders extend l'interface IDistrict, mais extends aussi une interface IWonder.
Grâce à un boolean, nous somme capable de savoir s'il s'agit d'un district ou d'une wonder. Il nous suffit de caster avec IWonder
pour utiliser la méthode doAction des Wonder.
Nous avons fait cela pour pouvoir créer une liste de IDistrict, qui est la classe DistrictDeck, et de pouvoir mettre des districts comme des Wonder à l'intérieur.

* DistrictName : L'énum DistrictName répertorie tous les noms possibles des districts.

* Color : L'énum Color répertorie toutes les couleurs.

* DistrictDeck : La classe DistrictDeck extend List, auquel on a défini des méthodes pour pouvoir interagir avec elle selon les règles du jeu.

* IAToWonder : La classe IAtoWonder est une classe qui recupere toutes les informations de décision de l'IA concernant ces choix d'actions pour les wonders.
Les objets de type IWonder implement une méthode appelé doAction, et elle prend en parametre un objet de ce type. L'action de la wonder
se fera selon les informations obtenues.

### L'architecture des Players

<img src="player.png"/>

Si un développeur veut intergrer une nouveau bot, il peut le faire facilement. Il suffit de faire une classe qui herite de IA pour 
qu'il soit prix en compte. Dans cette classe, il a juste besoin de définir ca strategie. 
Si jamais un joueur humain viendrait à etre implementer, il suffirait de faire hérité de la classe Player. Le développeur devra juste implémenter les méthodes de l'interface non définit dans Player.

* IPlayer : Cette l'interface qui joue le role de contrat avec le reste du logiciel. 

* Player : Cette une classe abtraite qui factorise tout le code basique des players. C'est essentillement des getter et des setter.

* IA : Cette classe comporte tout le mécanisme de fonctionnement de l'IA.

* BotX : Ces classe définisse la stratégie du bot grace à des énums.

* HeroChoice : Cette énum est utile pour lorsque l'IA choisi un hero. Ces énums représente un chemin de pensé potentiel. Suivant ou le bot 
choisit d'aller dans sa reflexion, il le marquera en rajoute l'une de ces énums dans une liste. On pourra alors afficher son 
chemin de pensé dans le Display.

* Circular list : Cette classe s'occupe de faire choisir les players un hero en fonction de la couronne. 
Celui qui à la couronne commence à choisir un hero, puis le suivant et ect ..

* HeroDecisionStandar : Cette classe permet, selon une annalyse du terrain et en fonction de la strategie du bot, de choisir un hero.

* Toutes les classes de choix pour les héros : Ces classes représente un résonnnement particulier pour chaque hero. C'est ici que son implémenté l'intelligence des bots lorsqu'il 
faut faire une action avec les heros. 

## Process de l'équipe

Nous avons utiliser GIT. Lorsque nous nous répartissons le travail, https://github.com/pns-si3-projects/projet2-ps5-21-22-ps5-21-22-projet2-m/blob/master/doc/cards.png