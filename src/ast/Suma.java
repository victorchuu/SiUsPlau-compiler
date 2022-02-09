package ast;

import java.util.List;

import tipado.MiTipo;
import tipado.TPrimitivo;

public class Suma extends EBin {
   public Suma(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.SUMA;}
   public String toString() {return "suma("+opnd1().toString()+","+opnd2().toString()+")";}
   
   public void chequea() throws Exception {
	   opnd1.chequea();
	   opnd2.chequea();
	   if (!opnd1.tipo.igual(opnd2.tipo) || 
			opnd1.tipo.tipo == TPrimitivo.BUIT ||
			opnd1.tipo.tipo == TPrimitivo.BOOL ||
			opnd1.tipo.dimension > 0 )
		   throw new Exception("ERROR: uso correcto exp + exp ");
	   this.tipo = new MiTipo(opnd1.tipo.tipo);
   }
   
   public String codigo(List<String> lista) { 
	   String str =  opnd1.codigo(lista)
	   		+ opnd2.codigo(lista);
	   if (opnd1.tipo.tipo == TPrimitivo.INT)
	   		 str+="i32.add\n";
	   else 
		   str+="f32.add\n";
	   return str;
   }
}
