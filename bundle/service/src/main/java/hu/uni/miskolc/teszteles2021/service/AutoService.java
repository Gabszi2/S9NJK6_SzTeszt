package hu.uni.miskolc.teszteles2021.service;

import hu.uni.miskolc.teszteles2021.Auto;
import hu.uni.miskolc.teszteles2021.dao.AutoDao;
import hu.uni.miskolc.teszteles2021.exception.AutoNemTalalhato;
import hu.uni.miskolc.teszteles2021.exception.RendszamMarFoglalt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

public class AutoService {
    private AutoDao dao;

    private AutoService(AutoDao dao){
        this.dao=dao;
    }

    public Auto getAutoByRendszam(String rendszam) throws AutoNemTalalhato {
        return dao.readAutoById(rendszam);
    }

    public Collection<Auto> getAllAuto(){
        return dao.readAllAuto();
    }
    public Collection<Auto> getAllKorozottAuto(){
        Collection<Auto> autok=getAllAuto();
        Collection<Auto> korozott=autok.stream().filter(a ->a.isKorozott()).collect(Collectors.toList());
        return korozott;
    }
    public Collection<Auto> getAllAutoDatumKozott(LocalDate tol,LocalDate ig){
        Collection<Auto> autok=getAllAuto();
        Predicate<Auto> pred= a -> a.getGyartasiIdo().isAfter(tol)&& a.getGyartasiIdo().isBefore(ig);
        CollectionUtils.filter(autok,pred);
        return autok;
    }

    public void addAuto(Auto auto) throws RendszamMarFoglalt {
        dao.createAuto(auto);
    }
    public void deleteAuto(Auto auto){
        dao.deleteAuto(auto);
    }
    public void updateAuto(Auto auto){
        dao.updateAuto(auto);
    }
}
