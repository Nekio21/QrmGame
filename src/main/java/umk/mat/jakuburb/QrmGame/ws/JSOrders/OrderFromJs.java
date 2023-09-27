package umk.mat.jakuburb.QrmGame.ws.JSOrders;

import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

import java.util.List;

public class OrderFromJs {

    private String giveOrTake;

    private KeyForJS order;

    private String addInfoList;

    public OrderFromJs() {

    }

    public OrderFromJs(String giveOrTake, KeyForJS order,String addInfoList) {
        this.giveOrTake = giveOrTake;
        this.order = order;
        this.addInfoList = addInfoList;
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
        return addInfoList;
    }

    public void setAddInfo(String addInfoList) {
        this.addInfoList = addInfoList;
    }
}
