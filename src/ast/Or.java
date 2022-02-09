package ast;

import java.util.List;

import tipado.MiTipo;
import tipado.TPrimitivo;

public class Or extends EBin {
   public Or(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.OR;}
   public String toString() {return "OR("+opnd1().toString()+","+opnd2().toString()+")";}
   
   public void chequea() throws Exception {
	   opnd1.chequea();
	   opnd2.chequea();
	   if (!opnd1.tipo.igual(opnd2.tipo) || 
			   opnd1.tipo.tipo != TPrimitivo.BOOL || 
			   opnd1.tipo.dimension > 0 )
		   throw new Exception("ERROR: uso correcto bool | bool ");
	   this.tipo = new MiTipo(TPrimitivo.BOOL);
   }
   
   public String codigo(List<String> lista) {
	   return opnd1.codigo(lista)
	   		+ opnd2.codigo(lista)
	   		+ "i32.or\n";
   }
}
