package umk.mat.jakuburb.QrmGame.ws.repley;

import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.game.Machine;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckRepley extends Repley{

    private State[][] statesForMachines = new State[4][8];

    private CheckRepley() {
        super(KeyForJS.CHECK);
    }

    public static CheckRepley analize(GameData gameData){
        CheckRepley checkRepley = new CheckRepley();

        List<Machine> machines = gameData.getMachines();
        Map<String, Integer> magazine = new HashMap<>(gameData.getMagazyn());

        String[][] containsMachineOne = {
                machines.get(0).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek()),
                machines.get(1).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek()),
                machines.get(2).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek()),
                machines.get(3).getPlanForMachine().get(gameData.getChosenDay().getDayOfWeek())
        };


        for(int i=0; i<8; i++){
            String resource0 = containsMachineOne[0][i];
            String resource1 = containsMachineOne[1][i];
            String resource2 = containsMachineOne[2][i];
            String resource3 = containsMachineOne[3][i];


        }


        return checkRepley;
    }

}
