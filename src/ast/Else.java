package ast;
import java.util.List;
import asem.Memoria;
import asem.TablaAmbitos;
import tipado.TPrimitivo;

public class Else extends E {
	private E b;
   public Else(E bloque) {
	b=bloque;
   }     
   public TipoE tipo() {return TipoE.ELSE;}
   public String toString() {return "else("+b.toString()+")";}

   public void vincula(TablaAmbitos T){
	  T.abreBloque();
      b.vincula(T);
   }
   
   public void chequea() throws Exception {
	   b.chequea();
   }
   
   public void memoria(Memoria m) {
	   b.memoria(m);
   }
   
   public String codigo(List<String> lista) {
	   return b.codigo(lista);
   }

}
