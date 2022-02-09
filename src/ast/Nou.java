package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Nou extends E {

	private MasDimensiones masdimensiones;
	private int delta;
	
	public Nou(MiTipo t,MasDimensiones  m) {
		tipo = t;
		masdimensiones = m;
	}
	
	public TipoE tipo() {
		return TipoE.NOU;
	}

	public String toString() {
		return "new("+tipo.toString()+masdimensiones.toString()+")";
	}
	
	public void vincula(TablaAmbitos T) {
		masdimensiones.vincula(T);
	}

	public void chequea() throws Exception {

		masdimensiones.chequea();
		
		this.tipo.dimension = masdimensiones.dim;
	}
	
	public int size(int delta) {
		this.delta = delta;
		return 1 + masdimensiones.size(); 
	}
	
	public String codigo(List<String> lista) {
		String s = ";; ---------------- NOU ---------------\r\n" +
				"tee_local $darr\r\n" + 
				"get_global $SP\r\n" + 
				"i32.store\r\n" +
				masdimensiones.codigo(lista) +
				"i32.const 1\r\n" +
				"set_local $prod\r\n" +
				"i32.const "+(delta+2)*4+"\r\n" + 
				"get_global $MP\r\n" + 
				"i32.add\r\n" +
				"set_local $darr\r\n";
		for (int i = 0; i < this.tipo.dimension; i++) {
			s += "get_local $darr\r\n"
					+ "i32.const 4\r\n"
					+ "i32.add\r\n"
					+ "tee_local $darr\r\n"
					+ "i32.load\r\n"
					+ "get_local $prod\r\n"
					+ "i32.mul\r\n"
					+ "set_local $prod\r\n";
		}
		return s += "get_global $SP\r\n"
				+ "get_local $prod\r\n"
				+ "i32.const 4\r\n"
				+ "i32.mul\r\n"
				+ "i32.add\r\n"
				+ "set_global $SP\r\n";
	}
	
	
	
	
	
}
