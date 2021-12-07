package fr.unice.polytech.startingpoint.cards;

public class MiracleCourt extends DistrictD implements IWonder {
    private final String description;

    public MiracleCourt() {
        this.name = DistrictName.LACOURDESMIRACLES;
        this.color = Color.PURPLE;
        this.price = 2;
        this.description = "Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.";
    }

    @Override
    public boolean isWonder() {
        return true;
    }

    @Override
    public void doAction(infoaction info) {
        int i;
        for (i = 0; i < info.getplayer().getBuiltDistricts().size()-1; i++) {
            if (info.getplayer().getBuiltDistricts().get(i).getDistrictName() == DistrictName.LACOURDESMIRACLES )   {
                this.color=info.getchoosencolor();
            }



        }
    }

        @Override
        public String getDescription () {
            return this.description;
        }
        }


