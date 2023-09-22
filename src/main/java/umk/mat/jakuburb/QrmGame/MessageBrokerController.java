package umk.mat.jakuburb.QrmGame;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.game.Machine;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;
import umk.mat.jakuburb.QrmGame.ws.MachinePlans;
import umk.mat.jakuburb.QrmGame.ws.OrderFromJs;
import umk.mat.jakuburb.QrmGame.ws.Repley;

import java.time.DayOfWeek;
import java.util.Random;

@Controller
public class MessageBrokerController {

    private GameData gameData;

    public MessageBrokerController(){
        gameData = new GameData();

        for(Machine m: gameData.getMachines()){
            m.getPlanForMachine().put(DayOfWeek.MONDAY, new String[]{
                    "X", "X", "X", "A", "A", "A", "X", "AA"
            });

            m.getPlanForMachine().put(DayOfWeek.TUESDAY, new String[]{
                    "BB", "BBB", "BBBB", "ABCD", "BBS", "DV", "FD", "FDC"
            });

            m.getPlanForMachine().put(DayOfWeek.WEDNESDAY, new String[]{
                    "FV", "DD", "WW", "BVC", "EFE", "VDV", "", ""
            });

            m.getPlanForMachine().put(DayOfWeek.THURSDAY, new String[]{
                    "", "", "", "", "", "", "", ""
            });

            m.getPlanForMachine().put(DayOfWeek.FRIDAY, new String[]{
                    "", "", "", "", "", "", "", ""
            });

            m.getPlanForMachine().put(DayOfWeek.SATURDAY, new String[]{
                    "ii", "", "", "", "", "", "", ""
            });

            m.getPlanForMachine().put(DayOfWeek.SUNDAY, new String[]{
                    "X", "X", "", "", "", "", "", ""
            });
        }
    }

    @MessageMapping("/drzwi")
    @SendTo("/mb/informacje")
    public Repley get(OrderFromJs ofj){

        Repley ri = getRI(ofj);

        return ri;
    }

    //@DestinationVariable String informacje,


    private Repley getRI(OrderFromJs ofj){
        MachinePlans machinePlans = new MachinePlans(KeyForJS.MACHINE_PLAN);

        String[] tab = {"AD", "A", "X", "ABC", "ABCD", "DA", "BCD", "DABC", "CDAB"};

        Random random = new Random();

        for(int i=0; i<8; i++) {
            machinePlans.getMachine1()[i] = tab[random.nextInt(9)];
            machinePlans.getMachine2()[i] = tab[random.nextInt(9)];
            machinePlans.getMachine3()[i] = tab[random.nextInt(9)];
            machinePlans.getMachine4()[i] = tab[random.nextInt(9)];
        }

        return machinePlans;
    }
}
