package fr.unice.polytech.startingpoint.player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CircularList {
    private List<IPlayer> players;
    private int kingIndex;

    public CircularList(List<IPlayer> elements) {
        this.players = elements;
    }

    /** get the element at index i % size(). */

    public IPlayer get(int i) {
        return players.get((i+kingIndex) % players.size());
    }

    public int getKingIndex() {
        return kingIndex;
    }

    /** return the first index of the object o in the circular list, -1 if the
     ** object is not present. Note that an element of the circular list may be
     ** null. */
    public int indexOf(Object o) {
        for (int i = 0; i < players.size(); i++) {
            if (Objects.equals(players.get(i), o))
                return i;
        }
        return -1;
    }

    public void findPlayerWithCrown(){
        for(int index = 0; index < this.players.size(); index++){
            if(players.get(index).getCrown()){
                this.kingIndex = index;
                break;
            }
        }
    }
    public List<IPlayer> rotate(){
        List<IPlayer> sortedPlayers = new ArrayList<>();
        for(int i = 0; i<players.size();i++){
            sortedPlayers.add(get(i));

        }
        return sortedPlayers;
    }

    /** number of elements contained in the circular list. */
    public int size() {
        return players.size();
    }
}