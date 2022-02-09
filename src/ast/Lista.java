package ast;

import asem.TablaAmbitos;

public class Lista extends E {

	private E elem, siguiente;
	
	public Lista(E d, E l) {
		elem = d;
		siguiente = l;
	}
	
	public TipoE tipo() {
		return TipoE.LISTA;
	}
	
	public String toString() {
		return "("+elem.toString()+","+siguiente.toString()+")";
	}
	
	public void vincula(TablaAmbitos T) {
		elem.vincula(T);
		siguiente.vincula(T);
	}

	public void chequea() throws Exception {
		elem.chequea();
		siguiente.chequea();
		this.tipo = elem.tipo;
		this.tipo.dimension++;
		if (siguiente.tipo() != TipoE.BLANCO){
			if (! this.tipo.igual(siguiente.tipo))
				throw new Exception("ERROR: Todos los elementos de una lista deben tener el mismo tipo.");
		}
		
	}

} 
