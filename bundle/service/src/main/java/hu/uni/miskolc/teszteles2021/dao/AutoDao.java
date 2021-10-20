package hu.uni.miskolc.teszteles2021.dao;

import hu.uni.miskolc.teszteles2021.Auto;
import hu.uni.miskolc.teszteles2021.exception.AutoNemTalalhato;
import hu.uni.miskolc.teszteles2021.exception.RendszamMarFoglalt;

import java.util.Collection;

public interface AutoDao {
    public Collection<Auto> readAllAuto();
    public  Auto readAutoById(String rendszam) throws AutoNemTalalhato;
    public void createAuto(Auto auto) throws RendszamMarFoglalt;
    public void updateAuto(Auto auto);
    public void deleteAuto(Auto auto);
    public  Auto deleteAutoById(String rendszam);


}
