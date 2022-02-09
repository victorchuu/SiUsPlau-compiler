package ast;

import java.util.List;

import asem.TablaAmbitos;

public class Blanco extends E{
	
	  public Blanco() {  }

	  public TipoE tipo() {return TipoE.BLANCO;}   
	  public String toString() {return "";}  
	  
	  public void vincula(TablaAmbitos T) {
			
		}
	  public void chequea() throws Exception {
		  
	  }
	  
	  public String codigo(List<String> lista) {
		   return "";
	  }
}
