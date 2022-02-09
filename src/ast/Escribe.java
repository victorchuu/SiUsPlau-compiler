package ast;
import java.util.List;
import asem.TablaAmbitos;
import tipado.TPrimitivo;

public class Escribe extends E {

	private E elem;
	
	public Escribe(E e) {
		elem = e;
	}
	
	public TipoE tipo() {
		return TipoE.ESCRIBE;
	}
	
	public String toString() {
		return "escribe: "+elem.toString();
	}

	public void vincula(TablaAmbitos T){
		elem.vincula(T);
	}

	public void chequea() throws Exception {
		   elem.chequea();
		   if (elem.tipo.tipo == TPrimitivo.BUIT || elem.tipo.dimension > 0)
			   throw new Exception("ERROR: Escritura permitida solo a tipos primitivos.");
	   }
	public String codigo(List<String> lista) {
		return elem.codigo(lista) + "call $print\n";
	}
	
}
