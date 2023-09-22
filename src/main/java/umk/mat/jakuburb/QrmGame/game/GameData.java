package umk.mat.jakuburb.QrmGame.game;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Controller
public class GameData {

    private LocalDate date;

    private List<Machine> machines;
    private Map<String, Integer> magazyn;

    private LocalDate chosenDay;

    private BigDecimal saldo;

    private int nrTura;

    public GameData(){
        date = LocalDate.now();
        chosenDay = LocalDate.now();
        magazyn = new HashMap<>();
        machines = new ArrayList<>();

        machines.add(new Machine("Maszyna A"));
        machines.add(new Machine("Maszyna B"));
        machines.add(new Machine("Maszyna C"));
        machines.add(new Machine("Maszyna D"));

        String[] randomText = {"X", "ABC","AC", "BD", "ABCD", "DCA", "ADC", "DBAC", "DCBA"};

        Random random = new Random();

        for(Machine m: machines){
            for(int j=1;j<8;j++) {
                for (int i = 0; i < 8; i++) {
                    m.getPlanForMachine().get(DayOfWeek.of(j))[i] = randomText[random.nextInt(9)];
                }
            }
        }

        saldo = new BigDecimal(2_000_000);
        nrTura = 1;
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

    public LocalDate getChosenDay() {
        return chosenDay;
    }

    public void setChosenDay(LocalDate chosenDay) {
        this.chosenDay = chosenDay;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getNrTura() {
        return nrTura;
    }

    public void setNrTura(int nrTura) {
        this.nrTura = nrTura;
    }
}