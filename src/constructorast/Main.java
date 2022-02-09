package constructorast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import alex.AnalizadorLexicoExp;
import asem.Memoria;
import asem.TablaAmbitos;
import ast.E;
import ast.IniBloque;
import errors.GestionErroresExp;

public class Main {	
	
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 ConstructorASTExp constructorast = new ConstructorASTExp(alex);
	 E arbol = (IniBloque) constructorast.parse().value;
	 System.out.println(arbol);
	 if (GestionErroresExp.errorSintact == 0) {
		 try {
			 TablaAmbitos tabla = new TablaAmbitos();
			 tabla.inicializa();
			 arbol.vincula(tabla);
			 arbol.chequea();
			 codigo(arbol);
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
			 //e.printStackTrace();
		 }
	 } else 
		 System.err.println("Corregir els errors SiUsPlau");
 }
   
   private static void codigo(E arbol) throws FileNotFoundException, UnsupportedEncodingException {
	   Memoria m = new Memoria();
	   arbol.memoria(m);
	   PrintWriter writer = new PrintWriter("codigo.wat", "UTF-8");
	   List<String> lista = new ArrayList<String>();
	   String nudo = arbol.codigo(lista);
	   String introduccion = "(module\r\n" + 
		 		"(type $_sig_i32ri32 (func (param i32) (result i32)))\r\n" +
				"(type $_sig_i32 (func (param i32)))\r\n" +
		 		"(type $_sig_ri32 (func (result i32)))\r\n" + 
		 		"(type $_sig_void (func ))\r\n" + 
		 		"(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\r\n" + 
		 		"(import \"runtime\" \"print\" (func $print (type $_sig_i32)))\r\n" + 
		 		"(import \"runtime\" \"read\" (func $read (type $_sig_ri32)))\r\n" + 
		 		"(memory 2000)\r\n" + 
		 		"(global $SP (mut i32) (i32.const 0)) ;; start of stack\r\n" + 
		 		"(global $MP (mut i32) (i32.const 0)) ;; mark pointer\r\n" + 
		 		"(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\r\n" + 
		 		"(start $main)\r\n" + 
		 		"(func $main  (type $_sig_void)\r\n" + 
		 		"   (local $localsStart i32)\r\n" + 
		 		"   (local $temp i32)\r\n" + 
		 		"   (local $darr i32)\r\n" + 
		 		"   (local $prod i32)\r\n" + 
		 		"   i32.const "+(m.max + 2)*4 + 
		 		"   \ncall $reserveStack \n " + 
		 		"   set_local $temp\r\n" + 
		 		"   get_global $MP\r\n" + 
		 		"   get_local $temp\r\n" + 
		 		"   i32.store\r\n" + 
		 		"   get_global $MP\r\n" + 
		 		"   get_global $SP\r\n" + 
		 		"   i32.store offset=4\r\n" + 
		 		"   get_global $MP\r\n" + 
		 		"   i32.const 8\r\n" + 
		 		"   i32.add\r\n" + 
		 		"   set_local $localsStart\r\n" + 
		 		"\r\n";
	   String desenlace = "\r\n" + 
		 		"   call $freeStack\r\n" + 
		 		")\r\n" +  
		 		"(func $reserveStack (param $size i32)\r\n" + 
		 		"   (result i32)\r\n" + 
		 		"   get_global $MP\r\n" + 
		 		"   get_global $SP\r\n" + 
		 		"   set_global $MP\r\n" + 
		 		"   get_global $SP\r\n" + 
		 		"   get_local $size\r\n" + 
		 		"   i32.add\r\n" + 
		 		"   set_global $SP\r\n" + 
		 		"   get_global $SP\r\n" + 
		 		"   get_global $NP\r\n" + 
		 		"   i32.gt_u\r\n" + 
		 		"   if\r\n" + 
		 		"   i32.const 3\r\n" + 
		 		"   call $exception\r\n" + 
		 		"   end\r\n" + 
		 		")\r\n" + 
		 		"(func $freeStack (type $_sig_void)\r\n" + 
		 		"   get_global $MP\r\n" + 
		 		"   i32.load\r\n" + 
		 		"   i32.load offset=4\r\n" + 
		 		"   set_global $SP\r\n" + 
		 		"   get_global $MP\r\n" + 
		 		"   i32.load\r\n" + 
		 		"   set_global $MP   \r\n" + 
		 		")";
	   			for (String s : lista) {
	   				desenlace += s;
	   			}
	   			desenlace += ")";
	   
	   
		 		writer.println(introduccion);
		 		writer.println(nudo);
		 		writer.println(desenlace);
	   
	   writer.close();
   }
   
   
}   
   
