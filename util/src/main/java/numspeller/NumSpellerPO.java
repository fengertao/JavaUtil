// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

final class NumSpellerPO extends NumSpeller__ {

    NumSpellerPO() {
        NumSpeller__.periodo_singular = (new String[]{
                "cem", "um mil", "um milh\343o", "um milh\343rdo", "um bilh\343o", "un bilh\343rdo", "um trilh\343o", "un trilh\343rdo", "un cuatrilh\343o", "un cuatrilh\343rdo",
                "un quintilh\343o", "un quintilh\343rdo"
        });
        NumSpeller__.periodo_plural = (new String[]{
                "cem", "mil", "milh\365es", "milh\343rdos", "bilh\365es", "bilh\343rdos", "trilh\365es", "trilh\343rdos", "quatrilh\365es", "quatrilh\343rdos",
                "quintilh\365es", "quintilh\343rdos"
        });
        NumSpeller__.centenas = (new String[]{
                "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"
        });
        NumSpeller__.decenas = (new String[]{
                "", "dez", "vinte", "trinta", "quarenta", "cinq\374enta", "sessenta", "setenta", "oitenta", "noventa"
        });
        NumSpeller__.unidades = (new String[]{
                "zero", "um", "dois", "tr\352s", "quatro", "cinco", "seis", "sete", "oito", "nove",
                "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"
        });
        NumSpeller__.y_decenas = " e ";
        NumSpeller__.y_centenas = " ";
        NumSpeller__.separador_decimal = " , ";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "menos";
    }

    public static void main(String[] args) {
        NumSpellerPO c = new NumSpellerPO();
        System.out.println(c.leer("123456789012"));
    }
}
