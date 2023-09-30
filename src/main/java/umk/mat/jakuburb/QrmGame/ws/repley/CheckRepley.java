package umk.mat.jakuburb.QrmGame.ws.repley;

import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.game.Machine;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckRepley extends Repley{

    private BrickCalculate[][] statesForMachines = new BrickCalculate[4][8];

    public CheckRepley(GameData gameData) {
        super(KeyForJS.CHECK);
        analize(gameData);
    }

    public void analize(GameData gameData){
        List<Machine> machines = gameData.getMachines();

        String[][] containsMachineOne = {
                machines.get(0).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek()),
                machines.get(1).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek()),
                machines.get(2).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek()),
                machines.get(3).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek())
        };


        for(int i=0; i<8; i++){
            Map<String, Integer> countTheSameBrick = new HashMap<>();

            String resource0 = containsMachineOne[0][i];
            String resource1 = containsMachineOne[1][i];
            String resource2 = containsMachineOne[2][i];
            String resource3 = containsMachineOne[3][i];

            countTheSameBrick.put(resource0,  machines.get(0).getSzybkosc());
            countTheSameBrick.put(resource1, Optional.ofNullable(countTheSameBrick.get(resource1)).orElse(0) + machines.get(1).getSzybkosc());
            countTheSameBrick.put(resource2, Optional.ofNullable(countTheSameBrick.get(resource2)).orElse(0) + machines.get(2).getSzybkosc());
            countTheSameBrick.put(resource3, Optional.ofNullable(countTheSameBrick.get(resource3)).orElse(0) + machines.get(3).getSzybkosc());

            statesForMachines[0][i] = brickAccept(machines.get(0), resource0, countTheSameBrick, gameData.getMagazyn());
            statesForMachines[1][i] = brickAccept(machines.get(1), resource1, countTheSameBrick, gameData.getMagazyn());
            statesForMachines[2][i] = brickAccept(machines.get(2), resource2, countTheSameBrick, gameData.getMagazyn());
            statesForMachines[3][i] = brickAccept(machines.get(3), resource3, countTheSameBrick, gameData.getMagazyn());
        }
    }

    //TODO: teraz jest czwartek a zaznaczony jest poniedzialek dlatego nie dziala tak jak chcem :>

    private BrickCalculate brickAccept(Machine machine, String res, Map<String, Integer> countTheSameBrick, Map<String,Integer> magazine){
        BrickCalculate brickCalculate = new BrickCalculate();

        if(res.equals("X")){
            brickCalculate.setState(State.X);
            return brickCalculate;
        }else if(res.equals("")){
            brickCalculate.setState(State.NOTHING);
            return brickCalculate;
        }

        String resSub = res.trim().substring(1);
        int newSize = resSub.length();

        brickCalculate.setLoss(resSub);
        brickCalculate.setValueOfLoss(machine.getSzybkosc());

        brickCalculate.setProfit(res);
        brickCalculate.setValueOfProfit(machine.getSzybkosc());

        if(newSize > 0){
            int ileMaByc = countTheSameBrick.get(res);
            int ilosc = magazine.get(resSub);

            if(ilosc >= ileMaByc){
                brickCalculate.setState(State.ACCEPTANCE);
                return brickCalculate;
            }
        }
        else if(newSize == 0){
            brickCalculate.setState(State.ACCEPTANCE);
            return brickCalculate;
        }

        brickCalculate.setState(State.OBJECTION);
        return brickCalculate;
    }

    public BrickCalculate[][] getStatesForMachines() {
        return statesForMachines;
    }

    public void setStatesForMachines(BrickCalculate[][] statesForMachines) {
        this.statesForMachines = statesForMachines;
    }
}
