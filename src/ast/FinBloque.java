package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;

public class FinBloque extends E {

	public FinBloque() {
		
	}
	@Override
	public TipoE tipo() {
		return TipoE.FINBLOQUE;
	}
	public String toString() {
		return "}fin bloque";
	}  
	
	public void vincula(TablaAmbitos T) {
		T.cierraBloque();
	}

	public void chequea() throws Exception {
	}
	public void memoria (Memoria m) {

	}
	public String codigo(List<String> lista) {
		   return "";
	   }
}
