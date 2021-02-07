// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerEH extends NumSpeller__ {

    NumSpellerEH() {
        NumSpeller__.periodo_singular = (new String[]{
                "ehun", "mila", "milioi bat", "miliardo bat", "bilioi bat", "biliardo bat", "trillioi bat", "triliardo bat", "kuatrilioi bat", "kuatriliardo bat",
                "kintilioi bat", "kintiliardo bat"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "ehun", "mila", "milioi", "miliardo", "bilioi", "biliardo", "trilioi", "triliardo", "kuatrilioi", "kuatrilardo",
                "kintilioi", "kintiliardo"
        });
        NumSpeller__.centenas = (new String[]{
                "", "ehun", "berrehun", "hirurehun", "laurehun", "bostehun", "seirehun", "zazpirehun", "zortzirehun", "bederatzirehun"
        });
        NumSpeller__.decenas = (new String[]{
                "", "hamar", "hogei", "", "berrogei", "", "hirurogei", "", "laurogei", ""
        });
        NumSpeller__.unidades = (new String[]{
                "zero", "bat", "bi", "hiru", "lau", "bost", "sei", "zazpi", "zortzi", "bederatzi",
                "hamar", "hamaika", "hamabi", "hamairu", "hamalau", "hamabost", "hamasei", "hamazazpi", "hamazortzi", "hemeretzi",
                "hogei"
        });
        NumSpeller__.y_decenas = "ta";
        NumSpeller__.y_centenas = " ta ";
        NumSpeller__.separador_decimal = "koma";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "minus";
    }

    public static void main(String[] args) {
        NumSpellerEH c = new NumSpellerEH();
        System.out.println(c.leer("123456789012"));
    }
}
