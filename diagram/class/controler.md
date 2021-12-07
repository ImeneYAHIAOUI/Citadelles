```mermaid

classDiagram
Controleur <|-- Citadelles

    class Controleur{
      -IPlayer cimetiere
      -IPlayer assassinated

      +void getInformation(List<IPlayer> players)
      +haveCimetiere(Iplayer player)
      +thief(Iplayer player)
      +boolean isAssasinated(IPlayer player)
    } 

    class Citadelles{
      -Controleur controleur
    }

``` 