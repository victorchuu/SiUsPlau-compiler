package ast;

import asem.TablaAmbitos;

public abstract class EIden extends E {

	protected String iden;
	protected E puntero;
	
	public void setIden(String id) {
		iden = id;
		puntero = null;
	}
	
	
	
}
