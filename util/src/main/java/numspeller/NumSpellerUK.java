// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


// Referenced classes of package numspeller:
//            NumSpeller__

class NumSpellerUK extends NumSpeller__
{

    NumSpellerUK()
    {
        NumSpeller__.periodo_singular = (new String[] {
            "one hundred", "one thousand", "one million", "one millard", "one billion", "one billard", "one trillion", "one trillard", "one quatrillion", "one quatrillard", 
            "one quintillion", "one quintilliard"
        });
        NumSpeller__.periodo_plural = (new String[] {
            "hundred", "thousand", "million", "millard", "billion", "billard", "trillion", "trillard", "quatrillion", "quatrillard", 
            "quintillion", "quintilliard"
        });
        NumSpeller__.centenas = (new String[] {
            "", "one hundred", "two hundred", "three hundred", "four hundred", "five hundred", "six hundred", "seven hundred", "eight hundred", "nine hundred"
        });
        NumSpeller__.decenas = (new String[] {
            "", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"
        });
        NumSpeller__.unidades = (new String[] {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", 
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        });
        NumSpeller__.y_decenas = "-";
        NumSpeller__.y_centenas = " and ";
        NumSpeller__.separador_decimal = " and ";
        NumSpeller__.orden_decenas = 0;
        NumSpeller__.signo_menos = "minus";
    }

    public static void main(String args[])
    {
        NumSpellerUK c = new NumSpellerUK();
        System.out.println(c.leer("123456789012"));
    }
}
