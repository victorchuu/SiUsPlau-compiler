package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Var extends E {
	
	private Declaracion decl;
	
	 public Var(MiTipo t, Declaracion d) {
		tipo = t;
		decl = d;
	}    
	public TipoE tipo() {return TipoE.VAR;}
	public String toString() {return "var("+tipo.toString()+","+decl.toString()+")";}
	 
	public void vincula(TablaAmbitos T) {
		   decl.vincula(T);
	   }


	public void chequea() throws Exception {
		decl.setTipo(tipo);
		decl.chequea();
	}
	
	public void memoria (Memoria m) {
		decl.memoria(m);
	}
	
	public String codigo(List<String> lista) {
		return decl.codigo(lista);
	}
}
