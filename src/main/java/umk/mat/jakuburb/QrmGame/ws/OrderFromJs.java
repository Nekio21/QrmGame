package umk.mat.jakuburb.QrmGame.ws;

public class OrderFromJs {

    private String giveOrTake;

    private KeyForJS order;

    private String addInfo;

    public OrderFromJs() {

    }

    public OrderFromJs(String giveOrTake, KeyForJS order, String addInfo) {
        this.giveOrTake = giveOrTake;
        this.order = order;
        this.addInfo = addInfo;
    }

    public String getGiveOrTake() {
        return giveOrTake;
    }

    public void setGiveOrTake(String giveOrTake) {
        this.giveOrTake = giveOrTake;
    }

    public KeyForJS getOrder() {
        return order;
    }

    public void setOrder(KeyForJS order) {
        this.order = order;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
