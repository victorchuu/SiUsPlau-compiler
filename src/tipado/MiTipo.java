package tipado;

public class MiTipo{

	public TPrimitivo tipo;
	public int dimension = 0;
	
	public MiTipo(TPrimitivo t) {
		tipo = t;
	}
	
	public MiTipo(MiTipo mt) {
		tipo = mt.tipo;
		dimension = mt.dimension;
	}
	
	public void makeList() {
		dimension++;
	}
	
	public String toString() {
		String str = "";
		switch (tipo) {
		case BOOL: str = "bool"; break;
		case INT: str = "enter";break;
		case REAL: str = "real";break;
		}
		for (int i = 0; i < dimension; i++)
			str+="[]";
		return str;
	}
	
	public boolean igual(MiTipo tipo2) {
		boolean b1 = this.tipo == tipo2.tipo;
		boolean b2 = this.dimension == tipo2.dimension;
		return b1 && b2;
	}

}
