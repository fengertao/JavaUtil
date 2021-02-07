// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumSpeller.java

package numspeller;


class NumSpeller__ {

    static final int MAXIMO_NUMERO_DE_DIGITOS = 24;
    protected static String[] periodo_singular = {
            "one hundred", "one thousand", "one million", "one millard", "one billion", "one billard", "one trillion", "one trillard", "one quatrillion", "one quatrillard",
            "one quintillion", "one quintilliard"
    };
    protected static String[] periodo_plural = {
            "hundred", "thousand", "million", "millard", "billion", "billard", "trillion", "trillard", "quatrillion", "quatrillard",
            "quintillion", "quintilliard"
    };
    protected static String[] centenas = {
            "", "one hundred", "two hundred", "three hundred", "four hundred", "five hundred", "six hundred", "seven hundred", "eight hundred", "nine hundred"
    };
    protected static String[] decenas = {
            "", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    protected static String[] unidades = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    protected static String y_decenas = "-";
    protected static String y_centenas = " and ";

    /*
    private String formatear_cifra(String cifra)
    {
        Double n = Double.valueOf("0");
        DecimalFormat df = new DecimalFormat("#.#");
        String s;
        if(cifra.length() > 15)
        {
            n = Double.valueOf(cifra.substring(0, 14));
            s = df.format(n);
            s = s + cifra.substring(15);
        } else
        {
            n = Double.valueOf(cifra);
            s = df.format(n);
        }
        String _result = s;
        return _result;
    }
    */
    protected static String separador_decimal = " and ";
    protected static int orden_decenas = 0;
    protected static String signo_menos = "minus";
    protected static String contraccion_decenas = "";
    protected static String contraccion1 = "";
    protected static String contraccion2 = "";
    protected static String contraccion3 = "";
    public int genero;
    int idioma;

    public NumSpeller__() {
    }

    public NumSpeller__(int idioma) {
        this();
    }

    @SuppressWarnings("unused")
    public String leer(String cifra) {
        double n = 0.0D;
        int l = 0;
        String resultado = "";
        cifra.trim();
        if (cifra.length() == 0) {
            return "ERROR: no se ha espoecificado una cifra";
        }
        if (cifra.length() > 24) {
            return "ERROR: La cifra no puede tener mas de " + 24 + " digitos";
        }
        if (cifra.charAt(0) == '-')
            resultado = signo_menos + " ";
        resultado = resultado + leer_parte(parte_entera(cifra));
        String s = parte_decimal(cifra);
        if (!s.equals(""))
            resultado = resultado + " " + separador_decimal + " " + leer_parte(s);
        if (!contraccion1.equals(""))
            resultado = contraer(resultado, contraccion1);
        if (!contraccion2.equals(""))
            resultado = contraer(resultado, contraccion2);
        if (!contraccion3.equals(""))
            resultado = contraer(resultado, contraccion3);
        return resultado;
    }

    private String parte_decimal(String s) {
        int p = 0;
        p = s.indexOf(".");
        if (p < 0)
            p = s.indexOf(",");
        String _result;
        if (p >= 0) {
            _result = s.substring(p + 1);
        } else {
            _result = "";
        }
        return _result;
    }

    private String leer_999(int n) {
        int c = 0;
        String _result;
        String s;
        if (n > 99) {
            if (n == 100) {
                s = periodo_singular[0];
                _result = s;
                return _result;
            }
            c = (n - n % 100) / 100;
            s = centenas[c];
        } else {
            c = 0;
            s = "";
        }
        if (n % 100 == 0) {
            _result = s;
            return _result;
        }
        if (!s.equals(""))
            s = s + y_centenas;
        try {
            if (unidades[n % 100].length() > 0) {
                s = s + unidades[n % 100];
                _result = s;
                return _result;
            }
        } catch (ArrayIndexOutOfBoundsException _ex) {
        }
        if (!decenas[(n - c * 100 - n % 10) / 10].equals("")) {
            if (orden_decenas == 0) {
                s = s + decenas[(n - c * 100 - n % 10) / 10];
                if (n % 10 > 0) {
                    s = s + y_decenas + unidades[n % 10];
                    if (!contraccion_decenas.equals(""))
                        s = contraer(s, contraccion_decenas);
                }
            } else if (n % 10 > 0) {
                s = s + unidades[n % 10] + y_decenas + decenas[(n - c * 100 - n % 10) / 10];
                if (!contraccion_decenas.equals(""))
                    s = contraer(s, contraccion_decenas);
            } else {
                s = s + decenas[(n - c * 100 - n % 10) / 10];
            }
        } else {
            s = s + decenas[(n - c * 100 - n % 10) / 10 - 1] + y_decenas + unidades[10 + n % 10];
            if (!contraccion_decenas.equals(""))
                s = contraer(s, contraccion_decenas);
        }
        _result = s;
        return _result;
    }

    private String miles_de(int c, int n, int t) {
        String s = "";
        String _result;
        if (c == 0) {
            if (t == 1 && n == 1)
                _result = unidades[0];
            else
                _result = s;
            return _result;
        }
        s = leer_999(c);
        if (n <= 1) {
            if (s.contains("%"))
                _result = sustituir(s, "%", " ");
            else
                _result = s;
            return _result;
        }
        if (c == 1) {
            s = periodo_singular[n - 1];
            _result = s;
        } else if (s.contains("%"))
            _result = sustituir(s, "%", " " + periodo_plural[n - 1] + " ");
        else
            _result = s + " " + periodo_plural[n - 1];
        return _result;
    }

    private String leer_parte(String s) {
        int[] m = {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0
        };
        int l = 0;
        int i = 0;
        l = s.length();
        String r;
        for (i = 0; i < 10; i++) {
            if (l >= 3) {
                m[i] = Integer.parseInt(s.substring(l - 3, l));
                l -= 3;
                continue;
            }
            if (l > 0) {
                m[i] = Integer.parseInt(s.substring(0, l));
                i++;
            }
            break;
        }

        StringBuilder rBuilder = new StringBuilder();
        for (l = i; l >= 1; l--)
            rBuilder.append(miles_de(m[l - 1], l, i)).append(" ");
        r = rBuilder.toString();

        r.trim();
        return r;
    }

    private String parte_entera(String s) {
        int p = 0;
        if (s.charAt(0) == '-')
            s = s.substring(1);
        p = s.indexOf(".");
        if (p < 0)
            p = s.indexOf(",");
        String _result;
        if (p >= 0)
            _result = s.substring(0, p);
        else
            _result = s;
        return _result;
    }

    private String contraer(String s, String contraccion) {
        return sustituir(s, contraccion.substring(0, contraccion.indexOf("|")), contraccion.substring(contraccion.indexOf("|") + 1));
    }

    public String sustituir(String source, String search, String replace) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        for (int index = 0; (index = source.indexOf(search, start)) >= 0; ) {
            result.append(source, start, index);
            result.append(replace);
            start = index + search.length();
        }

        result.append(source.substring(start));
        return result.toString();
    }

}
