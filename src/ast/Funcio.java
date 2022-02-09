package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Funcio extends E {
	protected String i;	
	private E a;
	private IniBloque b;
	private int memEstatica;
   	public Funcio (MiTipo t, String id, E argumentos,  IniBloque bloque) {
    	tipo = t;
		i=id;
		a=argumentos;
		b=bloque;
   	}     
   	public TipoE tipo() {return TipoE.FUNCIO;}
   	public String toString() {return "funcion("+i+","+tipo.toString()+","+a.toString()+")\n"+b.toString();}
   
   	public void vincula(TablaAmbitos T) {
	   	T.insertaId(i, this);
	   	T.iniFuncion(this);
	   	T.abreBloque();
	   	a.vincula(T);
	   	b.vincula(T);
	   	T.finFuncion();
   	}
   
   	public void chequea() throws Exception{
		b.chequea();
	}
	public boolean comprueba(E p){
		boolean ok = true;
		if (a.tipo() == TipoE.BLANCO) { 
			if(p.tipo() != TipoE.BLANCO){
				ok = false;
			}
		} 
		else if (p.tipo() == TipoE.BLANCO){
			ok = false;
		} else {
			ok = ((IniArgumentos) a).comprueba((IniParametro) p);
		}
		return ok;
	}
	
	public void memoria(Memoria m) {
		Memoria M = new Memoria();
		b.memoria(M);
		memEstatica = M.max;
	}
	
	public String codigo(List<String> lista) {
		String str = "\r\n(func $"+i
				   + a.codigo(lista)
				   + (tipo.tipo == TPrimitivo.BUIT ? "\r\n" : 
					   "\r\n(result " + (tipo.tipo == TPrimitivo.REAL ? 
						   		"f32 " : 
								"i32 ") 
				   + ")\r\n")
				   + "(local $darr i32)\r\n" + 
				   "(local $prod i32)\r\n"
				   + " get_global $SP\r\n"
				   + "i32.const " + (2+memEstatica)*4
				   + "\r\ncall $reserveStack\r\n" 
				   + "i32.store\r\n "
				   + b.codigo(lista)
				   + " get_global $MP\r\n" + 
				   "\r\n" + 
				   " get_global $MP\r\n" + 
				   " i32.load \r\n" + 
				   " set_global $MP\r\n" + 
				   " \r\n" + 
				   "  set_global $SP\r\n)\r\n";
			lista.add(str);	   
		  return ";; ----------------- FUNCIO ----------------------\r\n";

	}

}


/*
 get_global $SP

 get memEstatica
 call $reserveStack ;; MP = SP ;  SP += memEstatica
 
  i32.store 			;;Guardamos el MP viejo a continuacion en mem.
 
 ...
 
 get_global $MP

 get_global $MP
 i32.load 			;;Cargamos la primera posicion del bloque, que almacena el MP viejo
 set_global $MP
 
  set_global $SP    ;;Dejamos el SP donde antes estaba
 
 
 */
 