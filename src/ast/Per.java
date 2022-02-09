package ast;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Per extends E {
	private String elem;
	private E lista, c, b;
   public Per(String a,E b2,E cond, E bloque) {
       elem = a;
	   lista = b2;
	   c= cond;
	   b= bloque;
   }     
   public TipoE tipo() {return TipoE.PER;}
   public String toString() {return "for("+elem+","+lista+","+c.toString()+","+b.toString()+")";}
   
   
   public void vincula(TablaAmbitos T){
      
      lista.vincula(T);
      T.abreBloque();
      T.insertaId(elem, this);
      
      c.vincula(T);
      
      b.vincula(T);
   }
   
   public void chequea() throws Exception {
	   lista.chequea();
	   if (lista.tipo.dimension == 0 || lista.tipo.tipo == TPrimitivo.BUIT) {
		   throw new Exception("ERROR: Solo se puede iteraar sobre listas.");
	   }
	   this.tipo = new MiTipo(lista.tipo);
	   this.tipo.dimension--;
	   c.chequea();
	   if (c.tipo() != TipoE.BLANCO) {
		   if (c.tipo.tipo != TPrimitivo.BOOL || 
				 c.tipo.dimension > 0)
			   throw new Exception("ERROR: Condicion no booleana.");
	   }
	   
	   b.chequea();
   }
   
   public void memoria(Memoria m) {
	   b.memoria(m);
   }
   
}
