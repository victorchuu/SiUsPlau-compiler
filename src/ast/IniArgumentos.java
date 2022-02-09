package ast;
import java.util.List;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class IniArgumentos extends E {

	private E sig;
	private String i;
	//protected int num_arg; Siempre es 0
	public IniArgumentos(MiTipo t, String id, E entrada) {
    	this.tipo = t;  
		this.i = id;
		this.sig = entrada;
   	}     

   	public TipoE tipo() {return TipoE.INIARGUMENTOS;}
   	public String toString() {return "parametros( ("+tipo.toString() +" " +i+")"+sig.toString()+")";}
   
  	public void vincula(TablaAmbitos T) {
	   	T.insertaId(i, this);
	   	sig.vincula(T);
   	}

   	public boolean comprueba(IniParametro p){
		boolean ok = true;
		if (sig.tipo() == TipoE.BLANCO) { 
			if(p.getSig().tipo() != TipoE.BLANCO){
				ok = false;
			}
		} 
		else if (p.getSig().tipo() == TipoE.BLANCO){
			ok = false;
		} 
		else {
			if(!tipo.igual(p.tipo))
		   		return false;
			ok = ((Argumentos) sig).comprueba((Parametro) p.getSig());	
		}
		return ok; 
	}
   	
   	public void chequea() throws Exception {};
   	
   	public String codigo(List<String> lista) {
   		if(sig.tipo() == TipoE.ARGUMENTOS)
   			((Argumentos) sig).setNum(1);
   		return "(param "+(tipo.tipo == TPrimitivo.REAL && tipo.dimension == 0 ? "f32 " : "i32 ") + sig.codigo(lista) + ")";
   	}
   	
}
