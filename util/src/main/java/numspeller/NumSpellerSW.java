// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerSW extends NumSpeller__ {

    NumSpellerSW() {
        NumSpeller__.periodo_singular = (new String[]{
                "ett hundra", "ett tusen", "en milljon", "en milljard", "en billjon", "en billjard", "en trilljon", "en trilljard", "en cuatrilljon", "en cuatrilljard",
                "en quintilljon", "en quintilljard"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "hundra", "tusen", "milljoner", "milljarder", "billjoner", "billjarder", "trilljoner", "trilljarder", "cuatrilljoner", "cuatrilljarder",
                "quintilljoner", "quintilljarder"
        });
        NumSpeller__.centenas = (new String[]{
                "", "ett hundra", "tv\345 hundra", "tre hundra", "fyra hundra", "fem hundra", "sex hundra", "sju hundra", "\345tta hundra", "nio hundra"
        });
        NumSpeller__.decenas = (new String[]{
                "", "tio", "tjugo", "trettio", "fyrtio", "femtio", "sextio", "sjuttio", "\345ttio", "nittio"
        });
        NumSpeller__.unidades = (new String[]{
                "null", "ett", "tv\345", "tre", "fyra", "fem", "sex", "sju", "\345tta", "nio",
                "tio", "elva", "tolv", "tretton", "fjorton", "femton", "sexton", "sjutton", "arton", "nitton",
                "tjugo"
        });
        NumSpeller__.y_decenas = "";
        NumSpeller__.y_centenas = " och";
        NumSpeller__.separador_decimal = " , ";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "minus";
        NumSpeller__.contraccion_decenas = "tio|ti";
    }

    public static void main(String args[]) {
        NumSpellerSW c = new NumSpellerSW();
        System.out.println(c.leer("123456789012"));
    }
}
