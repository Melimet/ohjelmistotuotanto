package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiNegatiivistaTilavuutta(){
        varasto = new Varasto(-3214);


        assertEquals(varasto.getTilavuus(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysJaOtto(){
        varasto.lisaaVarastoon(-5);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
        varasto.otaVarastosta(-5);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);

    }

    @Test
    public void saldoEiYlity(){

        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);

        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }


    @Test
    public void tulostusTesti(){
        varasto.lisaaVarastoon(1);
        assertEquals(varasto.toString(),("saldo = 1.0, vielä tilaa 9.0"));
    }

    @Test
    public void alustusMolemmillaOikeillaArvoillaSkulaa(){
        varasto = new Varasto(12, 6);

        assertEquals(varasto.getTilavuus(), 12, vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), 6, vertailuTarkkuus);
    }

    @Test
    public void alustaessaNegatiivisetArvotKorjataan(){

        varasto = new Varasto(-1, -2);

        assertEquals(varasto.getTilavuus(), 0, vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
 
    }

    @Test
    public void varastostaLahteeKaikkiMahdollinen(){

        varasto.lisaaVarastoon(3);

        assertEquals(varasto.otaVarastosta(4),3, vertailuTarkkuus);

    }

    @Test
    public void alustusYlijaamallaToimii(){
        varasto = new Varasto(2, 4);



        assertEquals(varasto.getTilavuus(), 2, vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), 2, vertailuTarkkuus);


    }
}