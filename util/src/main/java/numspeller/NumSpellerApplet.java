// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpellerApplet.java

package numspeller;

import java.applet.Applet;
import java.awt.*;

// Referenced classes of package numspeller:
//            NumSpellerES, NumSpellerEH, NumSpellerCA, NumSpellerFR, 
//            NumSpellerDE, NumSpellerIT, NumSpellerPO, NumSpellerNW, 
//            NumSpellerBR, NumSpellerGA, NumSpellerSW, NumSpellerUK, 
//            NumSpeller__

@SuppressWarnings("serial")
public class NumSpellerApplet extends Applet {

    public String number;
    public String language;
    protected String result;
    private NumSpeller__ cifra;

    public NumSpellerApplet() {
    }

    public void init() {
        number = new String();
        number = getParameter("CIFRA");
        language = new String();
        language = getParameter("language");
        result = new String();
        result = "";
        if (language == null)
            language = "UK";
        activar_idioma(language);
    }

    public void activar_idioma(String l_language) {
        String s = l_language.substring(0, 2).toUpperCase();
        if (s.equals("ES") || s.equals("SP"))
            cifra = new NumSpellerES();
        else if (s.equals("EH") || s.equals("BA"))
            cifra = new NumSpellerEH();
        else if (s.equals("CT") || s.equals("CA"))
            cifra = new NumSpellerCA();
        else if (s.equals("FR"))
            cifra = new NumSpellerFR();
        else if (s.equals("DE") || s.equals("GE") || s.equals("AL"))
            cifra = new NumSpellerDE();
        else if (s.equals("IT"))
            cifra = new NumSpellerIT();
        else if (s.equals("PO") || s.equals("PT"))
            cifra = new NumSpellerPO();
        else if (s.equals("NW") || s.equals("NO"))
            cifra = new NumSpellerNW();
        else if (s.equals("BR") || s.equals("BZ"))
            cifra = new NumSpellerBR();
        else if (s.equals("GA"))
            cifra = new NumSpellerGA();
        else if (s.equals("SW") || s.equals("SU"))
            cifra = new NumSpellerSW();
        else
            cifra = new NumSpellerUK();
    }

    public void DoIt() {
        result = cifra.leer(number);
    }

    public String Spell(String un_number, String un_language) {
        activar_idioma(language);
        language = un_language;
        number = un_number;
        return cifra.leer(number);
    }

    public String leer(String un_number, String un_language) {
        return Spell(un_number, un_language);
    }

    public void paint(Graphics g) {
        g.drawString("Soy NumSpellerApplet y he recibido el argumento numero " + number + " y idioma " + language, 50, 25);
    }
}
