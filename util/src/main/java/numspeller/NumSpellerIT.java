// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerIT extends NumSpeller__
{

    NumSpellerIT()
    {
        NumSpeller__.periodo_singular = (new String[] {
            "cento", "mille", "un milione", "un miliardo", "un billon", "un billardo", "un trillon", "un trillardo", "un cuatrillon", "un cuatrillardo", 
            "un quintillon", "un quintillardo"
        });
        NumSpeller__.periodo_plural = (new String[] {
            "cento", "mila", "milione", "miliardo", "billon", "billardo", "trillon", "trillardo", "cuatrillon", "cuatrillardo", 
            "quintillones", "quintillardo"
        });
        NumSpeller__.centenas = (new String[] {
            "", "cento", "dueccento", "treccento", "quattroccento", "cinquecento", "seiscento", "settecento", "ottocento", "novecento"
        });
        NumSpeller__.decenas = (new String[] {
            "", "diece", "venti", "trenta", "quaranta", "cinquanta", "sessanta", "settanta", "ottanta", "novanta"
        });
        NumSpeller__.unidades = (new String[] {
            "cero", "uno", "due", "tre", "cuattro", "cinque", "sei", "sette", "otto", "nove", 
            "diece", "undici", "dodici", "tredici", "quattordici", "quindici", "sedici", "diciasette", "diciotto", "dicianove", 
            "", "ventuno", "", "", "", "", "", "", "ventotto"
        });
        NumSpeller__.y_decenas = "";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = "comma";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "meno";
        NumSpeller__.contraccion1 = "tauno|tuno";
        NumSpeller__.contraccion2 = "taotto|totto";
    }

    public static void main(String args[])
    {
        NumSpellerIT c = new NumSpellerIT();
        System.out.println(c.leer("123456789012"));
    }
}
