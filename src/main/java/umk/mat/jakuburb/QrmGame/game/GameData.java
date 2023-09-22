package umk.mat.jakuburb.QrmGame.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameData {

    private LocalDate date;

    private List<Machine> machines;
    private Map<String, Integer> magazyn;

    public GameData(){
        date = LocalDate.now();
        magazyn = new HashMap<>();
        machines = new ArrayList<>();

        machines.add(new Machine("Maszyna A"));
        machines.add(new Machine("Maszyna B"));
        machines.add(new Machine("Maszyna C"));
        machines.add(new Machine("Maszyna D"));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public Map<String, Integer> getMagazyn() {
        return magazyn;
    }

    public void setMagazyn(Map<String, Integer> magazyn) {
        this.magazyn = magazyn;
    }
}