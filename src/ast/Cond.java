package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.TPrimitivo;

public class Cond extends E {
	private E c,b,e;
   public Cond(E cond, E bloque, E els) {
     c=cond;
	b=bloque;
	e=els;
   }     
   public TipoE tipo() {return TipoE.CONDICIONAL;}
   public String toString() {return "if("+c.toString()+","+b.toString()+"),\n "+e.toString();}
   public void vincula(TablaAmbitos T){
      c.vincula(T);
      T.abreBloque();
      b.vincula(T);
      
      e.vincula(T);
   }
   
   public void chequea() throws Exception {
	   c.chequea();
	   if (c.tipo.tipo != TPrimitivo.BOOL || c.tipo.dimension > 0)
		   throw new Exception("ERROR: Condicion no booleana.");
	   b.chequea();
	   e.chequea();
   }
   public void memoria(Memoria m) {
	   b.memoria(m);
	   e.memoria(m);
   }
   public String codigo(List<String> lista) {
	   return c.codigo(lista) +
			   "if\n"
			   + b.codigo(lista)
			   + "else\n"
			   + e.codigo(lista)
			   + "end\n";
   }
}
