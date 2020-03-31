package com.company;

public class Main {

    public static void main(String[] args) {
        BuscaTexto text = new BuscaTexto();
        text.cargaFicheros("cambio.html");
        String result = text.extraeCadena("<div id=\"cc-ratebox\" name=\"cc-ratebox\" aria-labelledby=\"elb\" tabindex=\"4\">", "</div>");
        System.out.println(result);
        text.reinicia();
        result = text.extraeCadenaConDelim("<div id=\"cc-ratebox\" name=\"cc-ratebox\" aria-labelledby=\"elb\" tabindex=\"4\">", "</div>");
        System.out.println(result);
    }
}
