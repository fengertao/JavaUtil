// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerDE extends NumSpeller__ {

    NumSpellerDE() {
        NumSpeller__.periodo_singular = (new String[]{
                "einhundert", "eintausend", "eine million", "eine milliarde", "eine billion", "eine billiarde", "eine trillion", "eine trilliarde", "eine quatrillion", "eine quatrilliarde",
                "eine quintillion", "eine quintilliarde"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "hundert", "tausend", "million", "milliarde", "billionen", "billiarden", "trillionen", "trilliarden", "quatrillionen", "quatrilliarden",
                "quintillionen", "quintilliarden"
        });
        NumSpeller__.centenas = (new String[]{
                "", "einhundert", "zweihundert", "dreihundert", "vierhundert", "f\374nfhundert", "sechshundert", "siebenhundert", "achthundert", "neunhundert"
        });
        NumSpeller__.decenas = (new String[]{
                "", "zehn", "zwanzig", "dreissig", "vierzig", "f\374nfzig", "sechzig", "siebzig", "achtzig", "neunzig"
        });
        NumSpeller__.unidades = (new String[]{
                "null", "ein", "zwei", "drei", "vier", "f\374nf", "sechs", "sieben", "acht", "neun",
                "zehn", "elf", "zw\366lf", "dreizehn", "vierzehn", "f\374nfzehn", "sechzehn", "siebzehn", "achtzehn", "neunzehn",
                "zwanzig"
        });
        NumSpeller__.y_decenas = "und";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = " und ";
        NumSpeller__.orden_decenas = 1;
        NumSpeller__.signo_menos = "minus";
    }

    public static void main(String[] args) {
        NumSpellerDE c = new NumSpellerDE();
        System.out.println(c.leer("123456789012"));
    }
}
