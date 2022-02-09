package ast;

import java.awt.Window.Type;
import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Argumentos extends E{

   private E sig;
   private String i;
   protected int num_arg; 
   public Argumentos(MiTipo t, String id, E entrada) {
    tipo = t;  
	i=id;
	sig=entrada;
   }     

   public TipoE tipo() {return TipoE.ARGUMENTOS;}
   public String toString() {return ", ("+tipo.toString() +" " +i+")"+sig.toString();}
   
   public void vincula(TablaAmbitos T) {
	   T.insertaId(i, this);
	   sig.vincula(T);
   }
   public boolean comprueba(Parametro p){
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
   
   protected void setNum(int n) {
	   num_arg = n;
	   if(sig.tipo() == TipoE.ARGUMENTOS)
  			((Argumentos) sig).setNum(n+1);
   }
   
   public String codigo(List<String> lista) {
	   return tipo.tipo == TPrimitivo.REAL && tipo.dimension == 0 ? 
			   "f32 " : 
				"i32 " ;
   }
}

