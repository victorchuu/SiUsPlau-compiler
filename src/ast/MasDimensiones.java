package ast;

import java.util.List;
import asem.TablaAmbitos;
import tipado.TPrimitivo;

public class MasDimensiones extends E {

	private E indice;
	private MasDimensiones mas;
	protected int dim;
	
	public MasDimensiones(E i, MasDimensiones m) {
		this.indice = i; 
		this.mas = m;
		if (m == null)
			dim = 1;
		else {
			this.dim = m.dim + 1;
		}
	}
	
	public TipoE tipo() {
		return TipoE.MASDIMENSIONES;
	}
	
	public String toString() {
		String s = "["+indice.toString()+"]";
		if (mas != null)
			s += mas.toString();
		return s;
	}
	
	public void vincula(TablaAmbitos T) {
		indice.vincula(T);
		if (mas != null)
			mas.vincula(T);
	}

	public void chequea() throws Exception{
		indice.chequea();
		if (indice.tipo.tipo != TPrimitivo.INT || 
			indice.tipo.dimension > 0) {
			throw new Exception("ERROR: La longitud de un array debe ser entero.");
			}
		if (mas != null)
			mas.chequea();
	}
	
	public int size() {
		if(mas == null)
			return 1;
		return 1 + mas.size();
	}
	
	public int tam() {
		if(mas == null)
			return 1;
		return dim * mas.tam();
	}

	public String codigo(List<String> lista) { //No se escribe para la primera dimension
		return (mas == null ? 
				";; ------------ MAS DIMENSIONES ------------\r\n" :
					mas.codigo(lista) ) +
				"get_local $darr\r\n" + 
				"i32.const 4\r\n" + 
				"i32.add\r\n" + 
				"tee_local $darr\r\n" + 
				indice.codigo(lista) + 
				"i32.store\r\n";
				
	}
}
