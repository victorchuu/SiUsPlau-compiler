package ast;

import asem.TablaAmbitos;

public class PorDefecto extends E{

	@Override
	public TipoE tipo() {
		return TipoE.DEFAULT;
	}
	public String toString() {
		return "default";}  
	
	public void vincula(TablaAmbitos T) {
		
	}
	public void chequea() throws Exception{
		
	}
	
}
