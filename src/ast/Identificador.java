package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Identificador extends EIden {

	public Identificador() {}
	
	public TipoE tipo() {
		return TipoE.IDEN;
	}
	public String toString() {return this.iden;}
	
	public void vincula(TablaAmbitos T) {
		puntero = T.buscaId(iden);
	}
	
	public void chequea() throws Exception {
		if (puntero == null) {
			this.tipo = new MiTipo(TPrimitivo.BUIT);
			throw new Exception("ERROR: La variable "+this.iden+" no ha sido vinculada.");
		}
		else
			this.tipo = puntero.tipo;
	}
	
	public String codigo(List<String> lista) {
		if (puntero.tipo() == TipoE.INIARGUMENTOS)
			return "get_local 0\r\n";
		if (puntero.tipo() == TipoE.ARGUMENTOS)
			return "get_local "+((Argumentos) puntero).num_arg+"\r\n";
		
		return ";; ---------------- IDENTIFICADOR --------------------\r\n"
				+ "i32.const "+ (((Declaracion) puntero).delta + 2)*4 + 
				"\nget_global $MP" +
				"\ni32.add\n"
				+ ( tipo.tipo == TPrimitivo.REAL ? "f32.load\n" : "i32.load\n");
	}
	
	public String codigoAsig() {
		return "i32.const "+ (((Declaracion) puntero).delta + 2)*4 + 
				"\nget_global $MP" +
				"\ni32.add\n";
	}
	
}