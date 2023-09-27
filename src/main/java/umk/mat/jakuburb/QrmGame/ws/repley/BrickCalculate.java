package umk.mat.jakuburb.QrmGame.ws.repley;

public class BrickCalculate {
    private String loss;
    private int valueOfLoss;

    private String profit;
    private int valueOfProfit;

    private State state;

    public BrickCalculate() {
    }

    public BrickCalculate(String loss, int valueOfLoss, String profit, int valueOfProfit, State state) {
        this.loss = loss;
        this.valueOfLoss = valueOfLoss;
        this.profit = profit;
        this.valueOfProfit = valueOfProfit;
        this.state = state;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public int getValueOfLoss() {
        return valueOfLoss;
    }

    public void setValueOfLoss(int valueOfLoss) {
        this.valueOfLoss = valueOfLoss;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public int getValueOfProfit() {
        return valueOfProfit;
    }

    public void setValueOfProfit(int valueOfProfit) {
        this.valueOfProfit = valueOfProfit;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

enum State{
    ACCEPTANCE, OBJECTION
}