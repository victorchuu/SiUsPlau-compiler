package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Bloque extends E {

	private E i, s;
	public Bloque(E inst,E bloque) {
		i=inst;
		s=bloque;
	}
	
	public TipoE tipo() {
		return TipoE.BLOQUE;
	}
	public String toString() {
		return i.toString()+"\n"+s.toString();
     }
	
	public void vincula(TablaAmbitos T) {
		i.vincula(T);
		s.vincula(T);
	}

	public void chequea() throws Exception {
		i.chequea();
		s.chequea();
	}
	public void memoria (Memoria m) {
		i.memoria(m);
		s.memoria(m);
	}
	
	public String codigo(List<String> lista) {
		   return i.codigo(lista)
				   +s.codigo(lista);
	   }
}
