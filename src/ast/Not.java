package ast;
import java.util.List;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Not extends E {

	private E op;
	public Not(E opnd) {
		op = opnd;
	}
	public TipoE tipo() {
		return TipoE.NOT;
	}

	public String toString() {return "not("+op.toString()+")";}
	
	public void vincula(TablaAmbitos T) {
		op.vincula(T);
	}
	
	public void chequea() throws Exception {
		op.chequea();
		if (op.tipo.tipo != TPrimitivo.BOOL || 
			op.tipo.dimension > 0 )
			throw new Exception("ERROR: uso correcto ¬bool ");
		this.tipo = new MiTipo(TPrimitivo.BOOL);
	}
	
	public String codigo(List<String> lista) {
		   return op.codigo(lista)
		   		+ "i32.eqz\n";
	   }
}
