package ast;

import asem.TablaAmbitos;
import tipado.TPrimitivo;
import java.util.List;

public class Lee extends E {

	private E variable;
	
	public Lee(E e) {
		variable = e;
	}
	public TipoE tipo() {
		return TipoE.LEE;
	}
	public String toString() {
		return "lee: "+variable.toString();
	}
	public void vincula(TablaAmbitos T){
		variable.vincula(T);
	}
	
	public void chequea() throws Exception {
		variable.chequea();
		
		if (variable.tipo.tipo == TPrimitivo.BOOL || 
			variable.tipo.tipo == TPrimitivo.BUIT || 
			variable.tipo.dimension > 0)
			throw new Exception("ERROR: Solo se permite lectura de tipos primitivos enter y real.");
		
	}
	
	public String codigo(List<String> lista) {
		if(variable.tipo() == TipoE.IDEN) {
			return ((Identificador) variable).codigoAsig()	+
					"call $read\r\n" +
					( variable.tipo.tipo == TPrimitivo.REAL ? "f32.store\n" : "i32.store\n");
					}
		else {
			return ((AccesoArray) variable).codigoAsig(lista)	+
					"call $read\r\n" +
					( variable.tipo.tipo == TPrimitivo.REAL ? "f32.store\n" : "i32.store\n");
		}
	}
}
