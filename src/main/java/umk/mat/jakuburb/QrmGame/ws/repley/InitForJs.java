package umk.mat.jakuburb.QrmGame.ws.repley;

import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

public class InitForJs extends Repley{

    private String data;
    private int saldo;

    public InitForJs() {
        super(KeyForJS.INIT);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
