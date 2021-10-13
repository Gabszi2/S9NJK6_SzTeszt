package hu.uni.miskolc.teszteles2021.exception;

import java.time.LocalDate;

public class GyartasiIdoNemMegfelelo extends Throwable {
    public GyartasiIdoNemMegfelelo(LocalDate gyartasiIdo) {
        super(gyartasiIdo.toString());
    }
}
