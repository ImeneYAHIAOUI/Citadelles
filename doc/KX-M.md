# Sommaire
1. [Rétrospective](#etape1)
   1. [Réussi](#etape11)
   2. [Raté](#etape12)
   3. [A Conserver](#etape13)
   4. [A Changer](#etape14)
2. [RAPPORT](#etape2)
   1. [Architecture](#etape21)
      1. [L'architecture des Heros](#etape211)
      2. [L'architecture des Districts et Wonders](#etape212)
      3. [L'architecture des Players](#etape213)
   2. [Qui est responsable de quoi / qui a fait quoi ?](#etape22)
   3. [Process de l'équipe](#etape23)
   4. [Où trouver les infos dans la doc ?](#etape24)
      1. [L'Architecture](#etape241)
      2. [Affichage](#etape242)
      3. [IAToWonder & IAToHero ](#etape243)
   5. [Avancement sur les fonctionnalités](#etape25)
      1. [Milestone 1](#etape251)
      2. [Milestone 2](#etape252)
      3. [Milestone 3](#etape253)
      4. [Milestone 4](#etape254)
      5. [Milestone 5](#etape255)
   6. [Quelles parties sont bien faites ?](#etape26)
   7. [Quelles parties sont mal faites ?](#etape27)


# Rétrospective <a name="etape1"></a>

## Réussi : <a name="etape11"></a>
* Architure cohérente avec l’idée de base (On a fait attention à la modularité de notre logiciel).
* A avoir une certaine régularité dans les rendez-vous dans la semaine
* On estime avoir un code de bonne qualité (Testé + Mockito + Modularité + Lisibilité + Code commenté )
* Bonne arborescence dans les fichiers du projet. (Chaque type de classe correspond à un package)
* Code maintenable : Modularité, visibilité
* Logiciel répondant au cahier des charges

## Raté : <a name="etape12"></a>

* Procrastination de la mise en place de l’architecture d’utilisation des merveilles.
* Ordre du jour des reus pas toujours respecté (Parler de plusieurs sujets en même temps)
* <p style='text-align: justify;'> Communication sur messenger mal exploité. Toutes les informations utiles pour le groupe et les informations personnelles sont noyées. (Exemple il suffit d’un jour sans la lecture de messenger pour perdre le fil et avoir des difficultée à traiter les données) Solution -> Privilégier les informations de groupe en faisant une synthèse sur la convesation de groupe, et utiliser des canaux privés pour des échanges avec une personne concernée. </p>
* <p style='text-align: justify;'> Répartition des tâches -> Les classes avec beaucoup de complexité n’ont pas été bien réparties. Résultat, pour la personne n’ayant pas beaucoup touchée la classe, il est difficile de se mettre à jour et de faire évoluer le programme. </p>

## Conserver : <a name="etape13"></a>

<p style='text-align: justify;'>
Toute l'architecture fonctionne avec des contrats. Ces interface nous garantit une certaine modularité. Pour toute nouvelle fonctionnalité dans les parties existances, il suffit d'implementer
l'interface. Les stragies sont des classes, ce qui permets de créer d'autre startegie facilement, et d'instaencier l'une d'elle pour l'attribuer à un bot.

Des classes existent pour récuperer les informations des choix de l'IA pour faire une action. 
Que ce soit pour l'action du hero ou de la merveille, ce qui permet d'avoir un seul argument dans les méthodes d'action.

Il y a un objet controlleur qui permet de veiller au bon séquencement de la boucle du jeu citadelles.
</p>

## Changer : <a name="etape14"></a>

<p style='text-align: justify;'>
Les merveilles sont des classes, elles possèdent une méthode d'action, mais il y a seulement quelque une de ces merveilles qui necessite l'implementation de cette méthode.
Etant donné qu'elles implement une interface, il y a donc des merveilles avec cette méthode qui n'implemente rien.
Peut être qu'il faut abordé le problematique sous en autre angle. 
</p>

# <span style="color:blue"> RAPPORT <a name="etape2"></a> </span>

## <span style="color:blue"> Architecture <a name="etape21"></a> </span>

### <span style="color:blue"> L'architecture des Heros </span> <a name="etape211"></a>

<img src="heros.png"/>
<p style='text-align: justify;'>
Si le développeur veut ajouter un nouveau hero, il suffit ici de le faire hériter de Hero. Il n'aura plus que la méthode
doAction à implémenter.
</p>

<p style='text-align: justify;'>

* IHero : L'interfaces IHero est le contrat avec le reste du jeu. La classe Hero est une classe abstraite qui factorise le code
redondant de chaque hero. Il ne reste que définir la méthode doAction() pour que les hero puisse appliquer
leur pouvoir sur la partie. Cette méthode prend en parametre un objet qui ressence toutes les décision du IPlayer. 

* HeroName : Le heroName repertorie tous les nomes des heros. 

* DistrictDeck : La districtDeck comporte une liste de ces IHero. Toutes les méthodes implémentées dans cette deck permettent de gérer 
cette liste. 

* IaToHero : La classe IaToHero est une classe qui permet de récolter toutes les informations de décision de l'IA pour les actions 
du hero

### <span style="color:blue"> L'architecture des Districts et Wonders </span> </span> <a name="etape212"></a>

<img src="cards.png"/>
<p style='text-align: justify;'> 
Cette architecture permet d'ajouterfacilement un nouveau Wonder comme de nouveau district. 
Pour les district il suffit d'ajouter leur nom dans les énum, et pour les wonders, il suffit de les faire hérité de DistrictD.
Il n'y aura plus qu'a implémenter la méthode doAction. 
</p>

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

### <span style="color:blue"> L'architecture des Players </span> </span> <a name="etape213"></a>

<img src="player.png"/>
<p style='text-align: justify;'> 
Si un développeur veut intergrer une nouveau bot, il peut le faire facilement. Il suffit de faire une classe qui herite de IA pour 
qu'il soit prix en compte. Dans cette classe, il a juste besoin de définir ca strategie. 
Si jamais un joueur humain viendrait à etre implementer, il suffirait de faire hérité de la classe Player. Le développeur devra juste implémenter les méthodes de l'interface non définit dans Player.
</p>

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
## <span style="color:blue"> __Qui est responsable de quoi / qui a fait quoi ?__ </span> <a name="etape22"></a> 
Notre travail sur le projet était collectif. Chacun a travaillé un peu partout. On a distribué les taches en fonction  des idées proposés. Dans le but d’améliorer notre code. Il arrive parfois qu’une méthode soit modifiée plusieurs fois par plusieurs personnes


## <span style="color:blue"> __Process de l'équipe__ </span> <a name="etape23"></a> 
<p style='text-align: justify;'> 
Nous avons utilisé GIT. Lorsque nous nous répartissons le travail, nous faisons attention à bien definir les parametres d'entrées et de sorties des méthodes utilisés à l'exterieur de la classe.
Il arrive parfois que nous travaillons à plusieurs sur des parties de la classe. Fatalement, nous rencontrons des conflis lorsque l'on veux pull.
Mais nous utilisons quelques commandes pour gérer ce genre de conflit.
</p>

```
git stash
git pull
git stash pop
```
<p style='text-align: justify;'> 
Grâce à ces commandes, nous arrivons à travailler à plusieur sur une même classe. Evidemmant, si deux personnes travailles sur les même lignes de commande,
nous ne pouvons pas gérer les confits avec un stash. Dans ce cas, une discution s'opere sur le tchat du groupe pour regler le conflit. 
</p>


## <span style="color:blue"> __Où trouver les infos dans la doc ?__ </span> <a name="etape24"></a>
###  L'Architecture <a name="etape241"></a>
   * L'Architecture a dû avoir  beaucoup de travail surtout qu'il fallait faire des changements pour introduire les wonders, qui est  une démarche très critique. Alors on avait une première version base  en [Milestone 0.5](https://github.com/pns-si3-projects/projet2-ps5-21-22-ps5-21-22-projet2-m/issues/1), mais à chaque fois on avait du refactor et des factorisations ce nous a permis d'arriver  à une version définitive en [Milestone 4](https://github.com/pns-si3-projects/projet2-ps5-21-22-ps5-21-22-projet2-m/issues/53)
   * utilisation  d'une interface IPlayer est susceptible de faciliter le jeu humain en cas d'amélioration 

### Affichage <a name="etape242"></a>
   * La responsabilité d'affichage des étapes du jeu est maintenu par la classe Display qui permet d'afficher une description detaillée des étapes du jeu c'est à dire les informations de chaque Round jusqu'à la fin du jeu.
   * Le display permet d'afficher le schéma de pensée de L'IA, ainsi que des actions et leurs effets.
   * L'introduction des méthodes d'affichages se fait en classe Citadelles comme on le voit dans l'exemple d'affichage des wonders en [Milestone 5](https://github.com/pns-si3-projects/projet2-ps5-21-22-ps5-21-22-projet2-m/issues/81)

### IAToWonder & IAToHero <a name="etape243"></a>
   * La particualrité de ces deux des classes ce qu'elles contiennent que des getters et setters ou on stocke les informations pour appliquer des actions des wonders et heros. Et ces informations sont les choix faites par lien en terme de stratégies.
   * Pour faciliter l'utilisation on a essayer de regrouper les infos de chaque wonder et hero comme on le voit en [Milestone 5](https://github.com/pns-si3-projects/projet2-ps5-21-22-ps5-21-22-projet2-m/issues/73) comme version simplifiée  du IAToHero,ainsi que pour les wonders en [Mislestone 4](https://github.com/pns-si3-projects/projet2-ps5-21-22-ps5-21-22-projet2-m/issues/60) .
### Initialization  <a name="etape244"></a>
   * La classe Initialization assure l'ajout de tous nouveaux élements dans  le cas pour les wonders,district, et héros.




## Avancement sur les fonctionnalités </span> <a name="etape25"></a>

### Milestone 1 <a name="etape251"></a>

<p style='text-align: justify;'> 
Nous avons fait 2 Milestones durant cette semaine. Le premier nous a parmis d'avoir une base dans l'architecture. Nous avions de 
l'avance donc nous nous sommes décidé à faire un deuxieme milestone pour commencer à implementé des heros.
</p>

Milestone 0.5 :

* 2 Joueurs
* 1 Plateau
* un template de personnage
* IA prend un quartier et le construit directement. Pas de notion d'argent ici
* 2 quartiers à valeurs differentes , valeur 1 et 2
* 1 Tour
* Vainqueur : celui qui a le plus de point via le quartier

Milestone 1 :

* Personnage : Marchant et Roi. Leur action ne sont pas implementé
* Un nouveau personnage pour les joueurs à chaque tour
* 8 quartiers en jeu
* Fin de partie lorsqu'un joueur à 4 quartiers

### Milestone 2 <a name="etape252"></a>

<p style='text-align: justify;'> 
Duant la deuxieme semaines nous avons implémenté plusieurs autre fonctionnalité. La plus importante a été celle de 
l'action des heros. Nous avons finis en avance donc nous avons rajouté la gestion de la couronne, et l'excecution de l'action
des heros en fonction de leur rang. 
</p>

* Action des heros du premier Milestone marchant, roi
* Ajout du hero magicien + son action
* Ajout de 2 merveilles -> Laboratoire, Cours des miracles + action laboratoire
* Gestion du compte de quartier par Perso -> la main + quartiers construits
* 16 quartiers
* Celui qui a la couronne commence à choisir un hero, liste circulaire de player
* Action en fonction du rang des heros
* Ajout de l'intelligence à l'IA : </br>
au choix: </br>
soit prendre deux pièces d'or ; </br>
soit piocher deux cartes Quartiers, </br>

### Milestone 3 <a name="etape243"></a>

La semaine 3 a été charger en refactoring. Nous avons préparé l'IA pour recevoir des
fonctionnalité complexe. Nous avons essayé d'anticipé les futures ajout de stratégie. 

* Hero :Assassin
* Tresorie
* 24 quartiers
* 3 joueurs
* Fin du tour à 8 quartiers
* Gestion des quartiers : </br>
Ceux dans la main</br>
Ceux construit</br>

### Milestone 4 <a name="etape254"></a>

<p style='text-align: justify;'> 
L'IA est maintenant plus intelligente. Grace à notre préparation nous pouvons maintenant ajouter des stratégies jeux.
Pour le choix du hero, nous avons fait une mécanique lié a un choix stochastique. Nous somme capable aussi de capturer 
son chemin de pensé pour l'affiché ensuite. 
</p>

* 4 Joueurs
* Evêque , Voleur
* Donjon + Biblio + Manufacture + Observatoire
* Choix du Hero en fonction de probabilité établie par rapport à la lecture de jeu
* Affichage de la réflexion de l’IA
* Affichage des de tous les processus de jeu des IA
* Controleur pour la boucle principale
* Ajout dans l’interface IPlayer la fonction d’utilisation des merveilles

### Milestone 5 <a name="etape255"></a>

<p style='text-align: justify;'> 
Durant cette deniere semaine, nous avons ajouté les heros et les mervielles qu'il nous manqué. Cela n'a pas
était difficile car toute l'architecture nous permet de le faire facilement. 
Nous avons également implémenté des bots, qui herite de notre classe IA. Ces bots ont pour unique rôle de définir une stratégie de jeux.
Grace à des énmus, le bot à un comportement different. Notre mécanique IA éguille les appels de méthodes en fonction de ces énums.
Les IA peuvent être soit méchant ou gentil. Soit avoir pour objectif de construire rapidement pour pas beaucoup de point ou lentement et avec beaucoup de point. Le bot  peut aussi avoir
une strategie de jeux random. 
</p>

* Heros : Condottiere + architecte.
* Merveilles : Cimetiere + Ecole de magie + Université + Dracopor.
* Le premier hero deposé, et le dernier personnage recupere la carte.
* 2 Bots avec comme strategie (Achete vite pour pas cher et Achete lentement mais cher)

## Quelles parties sont bien faites ? </span> <a name="etape26"></a>

*  L’architecture des personnages a été bien fait. Pour ajouter un nouveau personnage, on a qu’implémenter la méthode DoAction qui définit son action.
* Il en va de même pour les stratégies des héros: une classe  pour chaque personnage  qui définit ses stratégies ce qui permet d’ajouter facilement de nouvelles stratégies.
* Les duplications de code ont été évitées au maximum.
* L’affichage du jeu a été bien fait.on affiche tout le déroulement de la partie :les effets des merveilles le choix des héros et leurs actions…

## Quelles parties sont mal faites ? </span> <a name="etape27"></a>

Tous les joueurs avaient les mêmes stratégies : on était à qu’ une seule IA. Son niveau d’intelligence a évolué tout au long du projet.
L’idée des bots a été proposée lors du milestone5.c’est avec les deadlines en tête que nous les  avons ajoutés on avait donc pas suffisamment de  temps pour implémenter plusieurs bots.On a fait un bot random qui effectue des choix aléatoires un bot gentil qui prévilige la défense et un bot méchant qui choisit d’attaquer les autres.


