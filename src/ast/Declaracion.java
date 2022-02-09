package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Declaracion extends E {

	private String iden;
	private E valor;
	private Declaracion siguiente;
	protected int delta;
	
	public Declaracion(String op1,E op2, Declaracion op3) {
		iden=op1;
		valor=op2;
		siguiente = op3;
	}
	
	public TipoE tipo() {
		return TipoE.DECLARACION;
	}
	public String toString() {
		return "declaracion("+iden+","+valor.toString()+"), " +siguiente.toString();} 
	
	public void setTipo(MiTipo t){
		this.tipo = t;
		if (siguiente != null)
			siguiente.setTipo(t);
	}

	public void vincula(TablaAmbitos T) {	
		T.insertaId(iden, this);
		valor.vincula(T);
		siguiente.vincula(T);
	}

	public void chequea() throws Exception {
		
		if (valor.tipo() != TipoE.DEFAULT) {
		valor.chequea();
	
		if(!valor.tipo.igual(this.tipo))
			throw new Exception("ERROR: No coinciden los tipos en la declaracion de "+iden);
		}
		
		siguiente.chequea();
	}

	public void memoria (Memoria m) {
		delta = m.delta;//Mi posicion es esta
		m.delta += valor.size(delta);//Y la siguiente disponible es esta mas mi tam
		siguiente.memoria(m);
	}
	
	public String codigo(List<String> lista) {
		String str = "i32.const "+ (delta + 2)*4 +
				"\nget_global $MP" +
				"\ni32.add\n";
		if (valor.tipo() == TipoE.DEFAULT) {
			switch (tipo.tipo) {
			case INT: str += "i32.const 0\r\n"; break;
			case REAL: str += "f32.const 0.0\r\n"; break;
			case BOOL: str += "i32.const 1\r\n"; break;
			}
		} else {
				str += valor.codigo(lista);
		}
		str += (valor.tipo() == TipoE.NOU ? "" : "i32.store\n");
		return str;
	}

}
