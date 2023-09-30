package umk.mat.jakuburb.QrmGame.ws.JSOrders;

public class BricksClass {
    private int machineNumber;
    private int start;
    private int end;
    private String letters;
    private int HowManyDayBetweenTheDayAndChoseDay;

    public BricksClass() {
    }

    public BricksClass(int machineNumber, int start, int end, String letters, int HowManyDayBetweenTheDayAndChoseDay) {
        this.machineNumber = machineNumber;
        this.start = start;
        this.end = end;
        this.letters = letters;
        this.HowManyDayBetweenTheDayAndChoseDay = HowManyDayBetweenTheDayAndChoseDay;
    }

    public int getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(int machineNumber) {
        this.machineNumber = machineNumber;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public int getHowManyDayBetweenTheDayAndChoseDay() {
        return HowManyDayBetweenTheDayAndChoseDay;
    }

    public void setNrOfDay(int HowManyDayBetweenTheDayAndChoseDay) {
        this.HowManyDayBetweenTheDayAndChoseDay = HowManyDayBetweenTheDayAndChoseDay;
    }
}
