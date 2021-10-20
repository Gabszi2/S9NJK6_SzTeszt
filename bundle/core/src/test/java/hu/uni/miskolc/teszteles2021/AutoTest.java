package hu.uni.miskolc.teszteles2021;

import hu.uni.miskolc.teszteles2021.exception.AjtokSzamaNemMegfelelo;

import hu.uni.miskolc.teszteles2021.exception.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles2021.exception.RendszamNemMegfelelo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AutoTest{

    Auto auto;
    @Before
    public void init(){
        auto=new Auto();
    }

    @Test
    public void testAjtokSzamaMegfelelo() throws AjtokSzamaNemMegfelelo {

        auto.setAjtokSzama(3);
    }
    @Test(expected = AjtokSzamaNemMegfelelo.class)
    public void testAjtokSzamaTulAlacsony() throws AjtokSzamaNemMegfelelo {

        auto.setAjtokSzama(-1);
    }
    @Test(expected = AjtokSzamaNemMegfelelo.class)
    public void testAjtokSzamaTulMagas() throws AjtokSzamaNemMegfelelo {

        auto.setAjtokSzama(6);
    }
    @Test
    public void testJoRendszam() throws RendszamNemMegfelelo {

        String rendszam="ABC-123";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testKotojelNelkuliRendszam() throws RendszamNemMegfelelo {
        String rendszam="ABC123";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testTulHosszuBetusRendszam() throws RendszamNemMegfelelo {
        String rendszam="ABCA-123";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testTulHosszuSzamuRendszam() throws RendszamNemMegfelelo {
        String rendszam="ABC-1234";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testCsakSzamRendszam() throws RendszamNemMegfelelo {
        String rendszam="123-123";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testCsakBetuRendszam() throws RendszamNemMegfelelo {
        String rendszam="ABC-ABC";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testKisBetuRendszam() throws RendszamNemMegfelelo {
        String rendszam="asd-123";
        auto.setRendszam(rendszam);
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testQBetuvelRendszam() throws RendszamNemMegfelelo {
        String rendszam="ABQ-123";
        auto.setRendszam(rendszam);
    }
    @Test
    public void testJoGyartasiido() throws GyartasiIdoNemMegfelelo {
        String gyartasiido="2021-04-17";
        auto.setGyartasiIdo(LocalDate.parse(gyartasiido));
    }
    @Test(expected = GyartasiIdoNemMegfelelo.class)
    public void testKoraiGyartasiido() throws GyartasiIdoNemMegfelelo {
        String gyartasiido="1200-04-17";
        auto.setGyartasiIdo(LocalDate.parse(gyartasiido));
    }
    @Test(expected = GyartasiIdoNemMegfelelo.class)
    public void testKesoiGyartasiido() throws GyartasiIdoNemMegfelelo {
        String gyartasiido="2222-04-17";
        auto.setGyartasiIdo(LocalDate.parse(gyartasiido));
    }
    @Test
    public void testSzinkodSetter(){
        String szinkod="#FFFFFF";
        auto.setSzinHex(szinkod);
        assertEquals(szinkod,auto.szinHex);
    }
    @Test
    public void testSzinkodGetter(){
        auto.szinHex="#FFFFFF";
        assertEquals(auto.szinHex,auto.getSzinHex());
    }
}