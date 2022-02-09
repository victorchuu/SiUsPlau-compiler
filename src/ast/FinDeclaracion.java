package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;

public class FinDeclaracion extends Declaracion {
	public FinDeclaracion() {
		super(null,null,null);
	}
	public TipoE tipo() {return TipoE.BLANCO;} 
	public String toString() {return "";}
	public void vincula(TablaAmbitos T) {
		
	}
	public void chequea() throws Exception{
		
	}
	public void memoria(Memoria m) {}
	
	public String codigo(List<String> lista) {return "";}
}
