// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerNW extends NumSpeller__ {

    NumSpellerNW() {
        NumSpeller__.periodo_singular = (new String[]{
                "ett hundre", "ett tusen", "en million", "en milliard", "en billion", "en billiard", "en trillion", "en trilliard", "en cuatrillion", "en cuatrilliard",
                "en quintillion", "en quintilliard"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "hundre", "tusen", "millioner", "milliarder", "billioner", "billiarder", "trillioner", "trilliarder", "cuatrillioner", "cuatrilliarder",
                "quintillioner", "quintilliarder"
        });
        NumSpeller__.centenas = (new String[]{
                "", "ett hundre", "to hundre", "tre hundre", "fire hundre", "fem hundre", "seks hundre", "syv hundre", "\345tte hundre", "ni hundre"
        });
        NumSpeller__.decenas = (new String[]{
                "", "ti", "tyve", "tretti", "f\370rti", "femti", "seksti", "sytti", "\345tti", "nitti"
        });
        NumSpeller__.unidades = (new String[]{
                "null", "en", "to", "tre", "fire", "fem", "seks", "syv", "\345tte", "ni",
                "ti", "elleve", "tolv", "tretten", "fjorten", "femten", "seksten", "s\370tten", "atten", "nitten",
                "tyve", "tjueen", "tjueto", "tjuetre", "tjuefire", "tjuefem", "tjueseks", "tjuesyv", "tjue\345tte", "tjueni"
        });
        NumSpeller__.y_decenas = "";
        NumSpeller__.y_centenas = " og ";
        NumSpeller__.separador_decimal = " , ";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "minus";
    }

    public static void main(String[] args) {
        NumSpellerNW c = new NumSpellerNW();
        System.out.println(c.leer("123456789012"));
    }
}
