package umk.mat.jakuburb.QrmGame.ws;

public class MachinePlans extends Repley {
    private String[] machine1;
    private String[] machine2;
    private String[] machine3;
    private String[] machine4;

    public MachinePlans(KeyForJS key){
        super(key);

        machine1 = new String[8];
        machine2 = new String[8];
        machine3 = new String[8];
        machine4 = new String[8];
    }

    public String[] getMachine1() {
        return machine1;
    }

    public void setMachine1(String[] machine1) {
        this.machine1 = machine1;
    }

    public String[] getMachine2() {
        return machine2;
    }

    public void setMachine2(String[] machine2) {
        this.machine2 = machine2;
    }

    public String[] getMachine3() {
        return machine3;
    }

    public void setMachine3(String[] machine3) {
        this.machine3 = machine3;
    }

    public String[] getMachine4() {
        return machine4;
    }

    public void setMachine4(String[] machine4) {
        this.machine4 = machine4;
    }
}
