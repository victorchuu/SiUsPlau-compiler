package errors;

import alex.UnidadLexica;
import alex.TokenValue;

public class GestionErroresExp {
    public static int errorSintact = 0;
    public void errorLexico(int fila, int columna, String lexema) {
      System.err.println("ERROR LEXICO fila "+fila+", columna "+columna+": Caracter inesperado: "+lexema); 
      System.exit(1);
    }  
    public void errorSintactico(UnidadLexica unidadLexica) {
      errorSintact++;
      System.err.println("ERROR SINTACTICO fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado "+((TokenValue)unidadLexica.value).lexema);
      //System.exit(1);
    }
}
