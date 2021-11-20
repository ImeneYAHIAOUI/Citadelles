```mermaid

stateDiagram-v2
    [*] --> Initialisation_de_4_district
    Initialisation_de_4_district --> Moins_de_8_quartiers?
    Moins_de_8_quartiers? --> Distribution_des_heros
    Distribution_des_heros --> Pour_chaque_joueur
    Pour_chaque_joueur --> Choix_entre_or_ou_district
    Choix_entre_or_ou_district --> Construction_de_district
    Construction_de_district --> Action_du_hero
    Action_du_hero --> Choix_entre_or_ou_district
    Action_du_hero --> Affichage
    Affichage --> Moins_de_8_quartiers?
    Moins_de_8_quartiers? --> Fin
    Fin --> [*]

```