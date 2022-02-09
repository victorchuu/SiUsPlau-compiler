package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class AccesoArray extends E {

	private static final int List = 0;
	private E indice, objeto;
		
	public AccesoArray(E o, E i) {
		indice = i;
		objeto = o;
	}
	
	public TipoE tipo() {
		return TipoE.ACCESOARRAY;
	}

	public String toString() {
		return objeto.toString()+"["+indice.toString()+"]";
	}
	
	public void vincula(TablaAmbitos T) {
		indice.vincula(T);
		objeto.vincula(T);
	}

	public void chequea() throws Exception {
		indice.chequea();
		objeto.chequea();
		if(indice.tipo.tipo != TPrimitivo.INT || indice.tipo.dimension > 0 || objeto.tipo.dimension == 0)
			throw new Exception("ERROR: El indice de acceso a un array debe ser un entero.");
		this.tipo = new MiTipo(objeto.tipo);
		this.tipo.dimension--;
	}
	
	private String micodigo(List<String> lista) {
		if (objeto.tipo() == TipoE.IDEN ) {
			return  ((Identificador) objeto).codigoAsig() +
					"tee_local $darr\r\n"
					+ "i32.load\r\n" +
					"get_local $darr\r\n" +
					"i32.const 4\r\n" + 
					"i32.add\r\n" + 
					"set_local $darr\r\n" + 
					indice.codigo(lista);
		} else {
			return 	((AccesoArray) objeto).micodigo(lista) +
					"get_local $darr\r\n" + 
					"i32.const 4\r\n" + 
					"i32.add\r\n" + 
					"tee_local $darr\r\n" + 
					"i32.load\r\n" + 
					"i32.mul\r\n" + 
					indice.codigo(lista) + 
					"i32.add\r\n";
		}
	}
	
	public String codigo(List<String> lista) {
		return codigoAsig(lista) + "i32.load\r\n";
	}
	
	public String codigoAsig(List<String> lista) {
		return ";; -----------------ACCESO ARRAY --------------\r\n" 
	+ micodigo(lista) 
	+ "i32.const 4\r\n"
	+ "i32.mul\r\n"
	+ "i32.add\r\n";
				
				
	}
	
}
