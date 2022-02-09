package ast;

import java.util.List;

import asem.TablaAmbitos;

public class Tornar extends E {

	private E op;
	private E funcion;
	public Tornar(E opnd) {
		op = opnd;
	}
	public TipoE tipo() {
		return TipoE.TORNAR;
	}

	public String toString() {return "tornar("+op.toString()+")";}

	public void vincula(TablaAmbitos T) {
		funcion = T.vinculaFuncion();
		op.vincula(T);
	}
	
	public void chequea() throws Exception{
		op.chequea();
		if(!funcion.tipo.igual(op.tipo))
			throw new Exception("ERROR: la funcion debe devolver un valor de tipo "+funcion.tipo);
	}
	
	public String codigo(List<String> lista) {
		return op.codigo(lista);
	}
}
