package umk.mat.jakuburb.QrmGame.ws.repley;

import umk.mat.jakuburb.QrmGame.game.Machine;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

import java.util.HashMap;
import java.util.Map;

public class MagazineRepley extends Repley{

    private Map<String, Integer> map;

    public MagazineRepley(){
        super(KeyForJS.MAGAZINE);

        map = new HashMap<>();
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
