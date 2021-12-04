```mermaid
stateDiagram-v2
    [*] --> Attaque_ou_defence
    Attaque_ou_defence --> Attaque
    Attaque_ou_defence --> Defence
    Attaque --> Assassin
    Attaque --> Voleur
    Defence --> Magicien_ou_besoin_Or
    Magicien_ou_besoin_Or --> Besoin_or
    Magicien_ou_besoin_Or --> Magicien
    Besoin_or --> Roi
    Besoin_or --> Marchant
    Besoin_or --> Voleur
    Besoin_or --> Eveque
```