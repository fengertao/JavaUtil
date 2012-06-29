// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerGA extends NumSpeller__
{

    NumSpellerGA()
    {
        NumSpeller__.periodo_singular = (new String[] {
            "cen", "un milleiro", "un millon", "un millardo", "un billon", "un billardo", "un trillon", "un trillardo", "un cuatrillon", "un catrillardo", 
            "un quintillon", "un quintillardo"
        });
        NumSpeller__.periodo_plural = (new String[] {
            "cen", "mil", "millons", "millardos", "billones", "billardos", "trillones", "trillardos", "catrillones", "catrillardos", 
            "quintillones", "quintillardos"
        });
        NumSpeller__.centenas = (new String[] {
            "", "cento", "douscentos", "trescentos", "catrocentos", "cincocentos", "seiscentos", "setecentos", "oitocentos", "novecentos"
        });
        NumSpeller__.decenas = (new String[] {
            "", "dez", "vinte", "trinta", "corenta", "cincuenta", "sesenta", "setenta", "oitenta", "noventa"
        });
        NumSpeller__.unidades = (new String[] {
            "cero", "un", "dous", "tres", "catro", "cinco", "seis", "sete", "oito", "nove", 
            "dez", "once", "doce", "trece", "catorce", "quince", "dezaseis", "dezasete", "dezaoito", "dezanove", 
            "vinte"
        });
        NumSpeller__.y_decenas = " e ";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = "con";
        NumSpeller__.orden_decenas = 0;
    }

    public static void main(String args[])
    {
        NumSpellerGA c = new NumSpellerGA();
        System.out.println(c.leer("123456789012"));
    }
}
