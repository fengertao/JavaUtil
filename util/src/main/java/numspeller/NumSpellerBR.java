// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerBR extends NumSpeller__ {

    NumSpellerBR() {
        NumSpeller__.periodo_singular = (new String[]{
                "kant", "mil", "ur milion", "ur miliard", "ur mil miliard", "ur milion miliard", "ur trillion", "ur trillard", "ur quatrillion", "ur quatrillard",
                "ur quintillion", "ur quintilliard"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "c'hant", "mil", "milion", "miliard", "billion", "billard", "trillion", "trillard", "quatrillion", "quatrillard",
                "quintillion", "quintilliard"
        });
        NumSpeller__.centenas = (new String[]{
                "", "kant", "daou c'hant", "tri c'hant", "pevar c'hant", "pemp kant", "c'hwec'h kant", "seizh kant", "eizh kant", "nav c'hant"
        });
        NumSpeller__.decenas = (new String[]{
                "", "dek", "ugent", "tregont", "daou-ugent", "hanter-kant", "tri-ugent", "dek ha tri-ugent", "pevar-ugent", "dek ha pevar-ugent"
        });
        NumSpeller__.unidades = (new String[]{
                "mann", "unan", "daou", "tri", "pevar", "pemp", "c'hwec'h", "seizh", "eizh", "nav",
                "dek", "unnek", "daouzek", "trizek", "pevarzek", "pemzek", "c'hwezek", "seitek", "triwec'h", "naontek",
                "ugent", "unan warn-ugent"
        });
        NumSpeller__.y_decenas = "%ha ";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = " virgulenn ";
        NumSpeller__.orden_decenas = 1;
        NumSpeller__.signo_menos = "nemet";
        NumSpeller__.contraccion1 = "daou mil|daou vil";
        NumSpeller__.contraccion2 = "ha ugent|warn-ugent";
        NumSpeller__.contraccion3 = "ha hanter|hag hanter";
    }

    public static void main(String[] args) {
        NumSpellerBR c = new NumSpellerBR();
        System.out.println(c.leer("123456789012"));
    }
}
