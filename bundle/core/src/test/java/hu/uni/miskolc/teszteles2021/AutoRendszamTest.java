package hu.uni.miskolc.teszteles2021;

import hu.uni.miskolc.teszteles2021.exception.RendszamNemMegfelelo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class AutoRendszamTest {
    @Parameterized.Parameters
    public static Collection data(){
        List data=new ArrayList();
        data.add(new Object[]{"AAAA-123"});
        data.add(new Object[]{"AAA123"});
        data.add(new Object[]{"aaa-123"});
        data.add(new Object[]{"AAA-1234"});
        data.add(new Object[]{"AAQ-123"});
        data.add(new Object[]{"123-123"});
        data.add(new Object[]{"AAA-AAA"});
        return data;
    }

    String rendszam;
    public AutoRendszamTest(String rendszam){
        this.rendszam=rendszam;
    }
    @Test(expected = RendszamNemMegfelelo.class)
    public void testRendszam() throws RendszamNemMegfelelo {
        Auto auto=new Auto();
        System.out.println("Vizsgált rendszám"+rendszam);
        auto.setRendszam(rendszam);
    }
}
