package umk.mat.jakuburb.QrmGame.messageBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.ws.JSOrders.BricksClass;
import umk.mat.jakuburb.QrmGame.ws.JSOrders.OrderFromJs;
import umk.mat.jakuburb.QrmGame.ws.JSOrders.PlanMachineOrderJs;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;
import umk.mat.jakuburb.QrmGame.ws.repley.InitForJs;
import umk.mat.jakuburb.QrmGame.ws.repley.MachinePlans;
import umk.mat.jakuburb.QrmGame.ws.repley.MagazineRepley;
import umk.mat.jakuburb.QrmGame.ws.repley.Repley;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
public class MessageBrokerController {

    private GameData gameData;

    @Autowired
    public MessageBrokerController(GameData gameData){
        this.gameData = gameData;
    }

    @MessageMapping("/drzwi")
    @SendTo("/mb/informacje")
    public Repley get(PlanMachineOrderJs ofj){

        Repley ri = getRI(ofj);

        return ri;
    }

    //@DestinationVariable String informacje,


    private Repley getRI(PlanMachineOrderJs ofj){
        switch (ofj.getOrder()){
            case INIT -> {
                return getInit();
            }
            case MACHINE_PLAN -> {
                if(ofj.getGiveOrTake().equals("take")) {
                    return getMachinePlans(DayOfWeek.of(Integer.parseInt(ofj.getAddInfo())));
                }
                else if(ofj.getGiveOrTake().equals("give")){
                    return updateMachinePlan(ofj);
                }
            }
            case MAGAZINE -> {
                if(ofj.getGiveOrTake().equals("give")){
                    return getMagazineInfo(ofj);
                }
            }
            case CHECK -> {
                //return getCheckReplay();
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

        LocalDate localDate = gameData.getDate();

        gameData.setChosenDay(localDate.plusDays(dayOfWeek.getValue()-1));

        return machinePlans;
    }

    private MachinePlans updateMachinePlan(PlanMachineOrderJs pMOJ){
        BricksClass bricks = pMOJ.getMachinePlanChange();
        String lastLetter;
        int countPrzezbrojenia = 0;

        String[] dane = gameData.getMachines().get(bricks.getMachineNumber()).getPlanForMachine().get(DayOfWeek.of(bricks.getNrOfDay()));

        for(int i=bricks.getStart(); i<=bricks.getEnd(); i++){
            if(countPrzezbrojenia < gameData.getMachines().get(bricks.getMachineNumber()).getPrzezbronienie()){
                dane[i] = "X";
                countPrzezbrojenia++;
            }else {
                dane[i] = bricks.getLetters();
            }
        }
        gameData.getMachines().get(bricks.getMachineNumber()).getPlanForMachine().put(DayOfWeek.of(bricks.getNrOfDay()), dane);

        return getMachinePlans(DayOfWeek.of(bricks.getNrOfDay()));
    }

    private MagazineRepley getMagazineInfo(PlanMachineOrderJs pMOJ){
        MagazineRepley mr = new MagazineRepley();
        mr.setMap(gameData.getMagazyn());

        return mr;
    }
}
