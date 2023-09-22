package umk.mat.jakuburb.QrmGame.ws;

public class OrderFromJs {

    private String giveOrTake;

    private String order;

    public OrderFromJs() {

    }

    public OrderFromJs(String giveOrTake, String order) {
        this.giveOrTake = giveOrTake;
        this.order = order;
    }

    public String getGiveOrTake() {
        return giveOrTake;
    }

    public void setGiveOrTake(String giveOrTake) {
        this.giveOrTake = giveOrTake;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
