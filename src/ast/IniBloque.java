package ast;
import java.util.List;
import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class IniBloque extends E {
	
	private E b;

	public IniBloque(E b) {
		this.b = b;
	}
	@Override
	public TipoE tipo() {
		return TipoE.INIBLOQUE;
	}
	public String toString() {
		return "inicio bloque{\n"+b.toString();
	}  	
	
	public void vincula(TablaAmbitos T) {
		b.vincula(T);	
	}
	
	public void chequea() throws Exception {
		b.chequea();
	}
	public void memoria (Memoria m) {
		int delta_ant = m.delta;

		b.memoria(m);
		if (m.delta > m.max)
			m.max = m.delta;
		
		m.delta = delta_ant;
	}
	public String codigo(List<String> lista) {
		   return b.codigo(lista);
	   }
}
