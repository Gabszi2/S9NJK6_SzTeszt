package hu.uni.miskolc.teszteles.service;


import hu.uni.miskolc.teszteles.core.Auto;
import hu.uni.miskolc.teszteles.core.enums.Kivitel;
import hu.uni.miskolc.teszteles.core.enums.Uzemanyag;
import hu.uni.miskolc.teszteles.core.enums.Valto;
import hu.uni.miskolc.teszteles.core.exceptions.AjtokSzamaNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.dao.AutoDao;

import hu.uni.miskolc.teszteles.service.exception.RendszamMarFoglalt;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AutoServiceIT {

    private static AutoService service;
    private static AutoDao dao;

    @BeforeClass
    public static void setup(){
        dao=new AutoDAOMongo("mongodb+srv://test:test@szoftverteszteles2021.bqwgi.mongodb.net/test?retryWrites=true&w=majority",
                "autok", "autok");

        service=new AutoService(dao);
        ((AutoDAOMongo)dao).deleteAll();
    }
    @Before
    public void initData() throws RendszamNemMegfelelo, GyartasiIdoNemMegfelelo, AjtokSzamaNemMegfelelo, RendszamMarFoglalt {

        Auto auto = new Auto("Opel","Astra","1.2" , "ABC-123", Uzemanyag.BENZIN, LocalDate.of(2017, 5,12),
                "#dedede",	false, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.KOMBI, 5);
        dao.createAuto(auto);
    }

    @Test(expected = RendszamMarFoglalt.class)
    public void test() throws RendszamMarFoglalt, RendszamNemMegfelelo, GyartasiIdoNemMegfelelo, AjtokSzamaNemMegfelelo {
        Auto auto = new Auto("Opel","Astra","1.2" , "ABC-123", Uzemanyag.BENZIN, LocalDate.of(2017, 5,12),
                "#dedede",	false, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.KOMBI, 5);
        service.addAuto(auto);

    }
    @After
    public void dropData(){
        ((AutoDAOMongo)dao).deleteAll();
    }
}
