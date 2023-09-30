package umk.mat.jakuburb.QrmGame.messageBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import umk.mat.jakuburb.QrmGame.game.GameData;
import umk.mat.jakuburb.QrmGame.ws.JSOrders.BricksClass;
import umk.mat.jakuburb.QrmGame.ws.JSOrders.PlanMachineOrderJs;
import umk.mat.jakuburb.QrmGame.ws.KeyForJS;
import umk.mat.jakuburb.QrmGame.ws.repley.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
public class MessageBrokerController {

    //TODO: SPRAWDZ CZY NIE BEDZIE PROBLEMU JAK SIE GRA POMIEDZY DNIEM A PO POLNOCY
    // a chyba nie musisz bo dzien dzisiejszy narazie jest tylko aktualizowany przy tworzeniu gameData
    // ale bedzie potem aktualizowany, po kliknieciu przycisku sprawdzenia :>

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

                    return getMachinePlans(
                            gameData.getDate().plusDays(Integer.parseInt(ofj.getAddInfo()))
                    );
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
                return new CheckRepley(gameData);
            }
        }

        return new Repley(KeyForJS.NULL){

        };
    }

    private InitForJs getInit(){
        InitForJs init = new InitForJs();

        init.setDay(gameData.getChosenDay().getDayOfWeek().getValue());
        init.setData(gameData.getDate().toString());
        init.setSaldo(gameData.getSaldo().intValue());

        return init;
    }

    private MachinePlans getMachinePlans(LocalDate newChosenDay){
        MachinePlans machinePlans = new MachinePlans();

        LocalDate localDate = gameData.getDate();

        //TODO: juz chyba dobrze :>
        gameData.setChosenDay(newChosenDay);

        for(int i=0; i<8; i++) {
            machinePlans.getMachine1()[i] = gameData.getMachines().get(0)
                    .getPlanForMachine()
                    .get(gameData.getChosenDay().getDayOfWeek())[i];

            machinePlans.getMachine2()[i] = gameData.getMachines().get(1)
                    .getPlanForMachine()
                    .get(gameData.getChosenDay().getDayOfWeek())[i];

            machinePlans.getMachine3()[i] = gameData.getMachines().get(2)
                    .getPlanForMachine()
                    .get(gameData.getChosenDay().getDayOfWeek())[i];

            machinePlans.getMachine4()[i] = gameData.getMachines().get(3)
                    .getPlanForMachine()
                    .get(gameData.getChosenDay().getDayOfWeek())[i];
        }



        return machinePlans;
    }

    private MachinePlans updateMachinePlan(PlanMachineOrderJs pMOJ){
        BricksClass bricks = pMOJ.getMachinePlanChange();
        int countPrzezbrojenia = 0;

        DayOfWeek dayOfWeek = DayOfWeek.of(
                (gameData.getDate().getDayOfWeek().getValue() + bricks.getHowManyDayBetweenTheDayAndChoseDay() - 1) % 7 + 1
        );

        String[] dane = gameData.getMachines()
                .get(bricks.getMachineNumber())
                .getPlanForMachine()
                .get(dayOfWeek);

        for(int i=bricks.getStart(); i<=bricks.getEnd(); i++){
            if(countPrzezbrojenia < gameData.getMachines().get(bricks.getMachineNumber()).getPrzezbronienie()){
                dane[i] = "X";
                countPrzezbrojenia++;
            }else {
                dane[i] = bricks.getLetters();
            }
        }

        gameData.getMachines().get(bricks.getMachineNumber())
                .getPlanForMachine()
                .put(dayOfWeek, dane);

        return getMachinePlans(gameData.getDate().plusDays(bricks.getHowManyDayBetweenTheDayAndChoseDay()));
    }

    private MagazineRepley getMagazineInfo(PlanMachineOrderJs pMOJ){
        MagazineRepley mr = new MagazineRepley();
        mr.setMap(gameData.getMagazyn());

        return mr;
    }
}
