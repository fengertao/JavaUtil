// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerCA extends NumSpeller__ {

    NumSpellerCA() {
        NumSpeller__.periodo_singular = (new String[]{
                "cent", "mil", "un milio", "un miliard", "un bilio", "un billard", "un trilio", "un triliard", "un cuatrillo", "un cuatriliard",
                "un quintilio", "un quintiliard"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "cent", "mil", "milions", "miliards", "bilions", "biliards", "trilions", "triliards", "cuatrilions", "cuatriliards",
                "quintilions", "quintiliards"
        });
        NumSpeller__.centenas = (new String[]{
                "", "cent", "dos-cent", "tres-cent", "quatre-cent", "cinc-cent", "sis-cent", "set-cent", "vuit-cent", "nou-cent"
        });
        NumSpeller__.decenas = (new String[]{
                "", "deu", "vint-i", "trenta", "quaranta", "cinquanta", "seixanta", "setanta", "vuitanta", "noranta"
        });
        NumSpeller__.unidades = (new String[]{
                "cero", "un", "dos", "tres", "quatre", "cinc", "sis", "set", "vuit", "nou",
                "deu", "onze", "dotze", "tretze", "catorze", "quinze", "setze", "disset", "divuit", "dinou",
                "vint"
        });
        NumSpeller__.y_decenas = "-";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = "con";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "minus";
    }

    public static void main(String args[]) {
        NumSpellerCA c = new NumSpellerCA();
        System.out.println(c.leer("123456789012"));
    }
}
