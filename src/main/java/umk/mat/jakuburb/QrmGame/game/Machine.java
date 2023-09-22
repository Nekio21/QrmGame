package umk.mat.jakuburb.QrmGame.game;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class Machine {

    private String name;
    private int przezbronienie;
    private int szybkosc;
    private int wadliwosc;

    private Map<DayOfWeek, String[]> planForMachine;

    public Machine(String name){
        this.name = name;
        planForMachine = new HashMap<>();

        for(int i=1;i<8;i++){
            planForMachine.put(DayOfWeek.of(i), new String[]{
                    "","","","","","","",""
            });
        }

        przezbronienie = 1;
        szybkosc = 1;
        wadliwosc = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrzezbronienie() {
        return przezbronienie;
    }

    public void setPrzezbronienie(int przezbronienie) {
        this.przezbronienie = przezbronienie;
    }

    public int getSzybkosc() {
        return szybkosc;
    }

    public void setSzybkosc(int szybkosc) {
        this.szybkosc = szybkosc;
    }

    public int getWadliwosc() {
        return wadliwosc;
    }

    public void setWadliwosc(int wadliwosc) {
        this.wadliwosc = wadliwosc;
    }

    public Map<DayOfWeek, String[]> getPlanForMachine() {
        return planForMachine;
    }

    public void setPlanForMachine(Map<DayOfWeek, String[]> planForMachine) {
        this.planForMachine = planForMachine;
    }
}
