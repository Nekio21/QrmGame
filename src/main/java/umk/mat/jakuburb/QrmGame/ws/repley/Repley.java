package umk.mat.jakuburb.QrmGame.ws.repley;

import umk.mat.jakuburb.QrmGame.ws.KeyForJS;

public abstract class Repley {
    public KeyForJS kodOdpowiedzi;

    public Repley(KeyForJS kodOdpowiedzi){
        this.kodOdpowiedzi = kodOdpowiedzi;
    }

    public KeyForJS getKodOdpowiedzi() {
        return kodOdpowiedzi;
    }

    public void setKodOdpowiedzi(KeyForJS kodOdpowiedzi) {
        this.kodOdpowiedzi = kodOdpowiedzi;
    }
}
