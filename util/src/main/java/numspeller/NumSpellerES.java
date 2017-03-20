// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerES extends NumSpeller__ {

    NumSpellerES() {
        NumSpeller__.periodo_singular = (new String[]{
                "cien", "mil", "un millon", "un millardo", "un billon", "un billardo", "un trillon", "un trillardo", "un cuatrillon", "un cuatrillardo",
                "un quintillon", "un quintillardo"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "cien", "mil", "millones", "millardos", "billones", "billardos", "trillones", "trillardos", "cuatrillones", "cuatrillardos",
                "quintillones", "quintillardos"
        });
        NumSpeller__.centenas = (new String[]{
                "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
        });
        NumSpeller__.decenas = (new String[]{
                "", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"
        });
        NumSpeller__.unidades = (new String[]{
                "cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
                "diez", "once", "doce", "trece", "catorce", "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve",
                "veinte", "veintiuno", "veintidos", "veintitres", "veinticuatro", "veinticinco", "veintiseis", "veintisiete", "veintiocho", "veintinueve"
        });
        NumSpeller__.y_decenas = " y ";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = "con";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "menos";
    }

    public static void main(String args[]) {
        NumSpellerES c = new NumSpellerES();
        System.out.println(c.leer("123456789012"));
    }
}
