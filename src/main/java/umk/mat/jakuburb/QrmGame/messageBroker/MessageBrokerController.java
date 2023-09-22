package umk.mat.jakuburb.QrmGame.messageBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;
import umk.mat.jakuburb.QrmGame.ws.repley.InitForJs;
import umk.mat.jakuburb.QrmGame.ws.repley.MachinePlans;
import umk.mat.jakuburb.QrmGame.ws.OrderFromJs;
import umk.mat.jakuburb.QrmGame.ws.repley.Repley;

import java.time.DayOfWeek;
import java.util.Random;

@Controller
public class MessageBrokerController {

    private GameData gameData;

    @Autowired
    public MessageBrokerController(GameData gameData){
        this.gameData = gameData;
    }

    @MessageMapping("/drzwi")
    @SendTo("/mb/informacje")
    public Repley get(OrderFromJs ofj){

        Repley ri = getRI(ofj);

        return ri;
    }

    //@DestinationVariable String informacje,


    private Repley getRI(OrderFromJs ofj){
        switch (ofj.getOrder()){
            case INIT -> {
                return getInit();
            }
            case MACHINE_PLAN -> {
                return getMachinePlans(DayOfWeek.of(Integer.parseInt(ofj.getAddInfo())));
            }
        }

        return new Repley(KeyForJS.NULL){

        };
    }

    private InitForJs getInit(){
        InitForJs init = new InitForJs();

        init.setData(gameData.getDate().toString());
        init.setSaldo(gameData.getSaldo().intValue());

        return init;
    }

    private MachinePlans getMachinePlans(DayOfWeek dayOfWeek){
        MachinePlans machinePlans = new MachinePlans();

        for(int i=0; i<8; i++) {
            machinePlans.getMachine1()[i] = gameData.getMachines().get(0).getPlanForMachine().get(dayOfWeek)[i];
            machinePlans.getMachine2()[i] = gameData.getMachines().get(1).getPlanForMachine().get(dayOfWeek)[i];
            machinePlans.getMachine3()[i] = gameData.getMachines().get(2).getPlanForMachine().get(dayOfWeek)[i];
            machinePlans.getMachine4()[i] = gameData.getMachines().get(3).getPlanForMachine().get(dayOfWeek)[i];
        }

        return machinePlans;
    }
}
