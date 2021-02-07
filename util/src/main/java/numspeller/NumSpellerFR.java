// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerFR extends NumSpeller__ {

    NumSpellerFR() {
        NumSpeller__.periodo_singular = (new String[]{
                "cent", "mille", "un million", "un milliard", "un billion", "un billiard", "un trillion", "un trilliard", "un cuatrillo", "un cuatrilliard",
                "un quintillio", "un quintilliard"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "cent", "mille", "millions", "milliards", "billions", "billiards", "trillions", "trilliards", "cuatrillions", "cuatrilliards",
                "quintillions", "quintilliards"
        });
        NumSpeller__.centenas = (new String[]{
                "", "cent", "deux cents", "trois cents", "quatre cents", "cinq cents", "six cents", "sept cents", "huit cents", "neuf cents"
        });
        NumSpeller__.decenas = (new String[]{
                "", "dix", "vingt", "trente", "quarante", "cinquente", "soixante", "", "quatre-vingt", ""
        });
        NumSpeller__.unidades = (new String[]{
                "z\351ro", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf",
                "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf",
                "vingt", "vingt et un"
        });
        NumSpeller__.y_decenas = "-";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = "et";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "moins";
    }

    public static void main(String[] args) {
        NumSpellerFR c = new NumSpellerFR();
        System.out.println(c.leer("123456789012"));
    }
}
