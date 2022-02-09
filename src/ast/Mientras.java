package ast;
import java.util.List;
import asem.Memoria;
import asem.TablaAmbitos;
import tipado.TPrimitivo;

public class Mientras extends E {
	private E c,b;
   public Mientras (E cond, E bloque) {
     c=cond;
	b=bloque;
   }     
   public TipoE tipo() {return TipoE.MIENTRAS;}
   public String toString() {return "while("+c.toString()+","+b.toString()+")";}
   
   public void vincula(TablaAmbitos T){
      c.vincula(T);
      
      T.abreBloque();
      b.vincula(T);
   }
   
   public void chequea() throws Exception {
	   c.chequea();
	   if (c.tipo.tipo != TPrimitivo.BOOL || c.tipo.dimension > 0)
		   throw new Exception("ERROR: Condicion no booleana.");
	   b.chequea();
   }
   
   public void memoria(Memoria m) {
	   b.memoria(m);
   }
   
   public String codigo(List<String> lista) {
	   return "block\n"
	   		+ "loop\n"
			   + c.codigo(lista)
	   		+ "i32.eqz\n"
	   		+ "br_if 1\n"
	   		+ b.codigo(lista)
	   		+ "br 0\n"
	   		+ "end\n"
	   		+ "end\n";
   }
}
