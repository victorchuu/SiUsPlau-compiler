package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.TPrimitivo;

public class Asignacion extends E {

	private E variable;
	private E valor;
	public Asignacion(E op1,E op2) {
		variable=op1;
		valor=op2;
	}
	
	public TipoE tipo() {
		return TipoE.ASIGNACION;
	}
	public String toString() {
		return "valor("+variable.toString()+","+valor.toString()+")";}  
	
	public void vincula(TablaAmbitos T) {
		variable.vincula(T);
		valor.vincula(T);
	}
	
	public void chequea() throws Exception{
        variable.chequea();
        valor.chequea();
	
		if(!variable.tipo.igual(valor.tipo))
			throw new Exception("ERROR: los tipos no coinciden en la asignacion "+this);
	}
	
	
	public String codigo(List<String> lista) {
		if(variable.tipo() == TipoE.IDEN) {
			return ((Identificador) variable).codigoAsig()	+
					valor.codigo(lista) +
					( variable.tipo.tipo == TPrimitivo.REAL ? "f32.store\n" : "i32.store\n");
					}
		else {
			return ((AccesoArray) variable).codigoAsig(lista)	+
					valor.codigo(lista) +
					( variable.tipo.tipo == TPrimitivo.REAL ? "f32.store\n" : "i32.store\n");
		}
	}
}
