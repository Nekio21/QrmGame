package umk.mat.jakuburb.QrmGame;

public class GameDataToSend {
    private String valueOrder;
    private String[] planMachineA;
    private String[] planMachineB;
    private String[] planMachineC;
    private String[] planMachineD;

    public GameDataToSend() {
        machineInit();
    }

    public GameDataToSend(String valueOrder) {
        this.valueOrder = valueOrder;
        machineInit();
    }

    private void machineInit(){
        planMachineA = new String[8];
        planMachineB = new String[8];
        planMachineC = new String[8];
        planMachineD = new String[8];
    }

    public String getValue() {
        return valueOrder;
    }

    public void setValue(String valueOrder) {
        this.valueOrder = valueOrder;
    }

    public String[] getPlanMachineA() {
        return planMachineA;
    }

    public void setPlanMachineA(String[] planMachineA) {
        this.planMachineA = planMachineA;
    }

    public String[] getPlanMachineB() {
        return planMachineB;
    }

    public void setPlanMachineB(String[] planMachineB) {
        this.planMachineB = planMachineB;
    }

    public String[] getPlanMachineC() {
        return planMachineC;
    }

    public void setPlanMachineC(String[] planMachineC) {
        this.planMachineC = planMachineC;
    }

    public String[] getPlanMachineD() {
        return planMachineD;
    }

    public void setPlanMachineD(String[] planMachineD) {
        this.planMachineD = planMachineD;
    }
}
