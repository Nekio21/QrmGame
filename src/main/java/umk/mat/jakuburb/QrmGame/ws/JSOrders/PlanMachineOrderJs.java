package umk.mat.jakuburb.QrmGame.ws.JSOrders;

import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

import java.util.ArrayList;
import java.util.List;

public class PlanMachineOrderJs extends  OrderFromJs{
    private BricksClass machinePlanChange;

    public PlanMachineOrderJs(){
        super();
    }

    public PlanMachineOrderJs(String giveOrTake, KeyForJS order, String addInfoList, BricksClass machinePlanChange){
        super(giveOrTake, order, addInfoList);
        this.machinePlanChange = machinePlanChange;
    }

    public BricksClass getMachinePlanChange() {
        return machinePlanChange;
    }

    public void setMachinePlanChange(BricksClass machinePlanChange) {
        this.machinePlanChange = machinePlanChange;
    }
}
