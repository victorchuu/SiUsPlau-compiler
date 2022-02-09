package ast;
import java.util.List;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Mul extends EBin {
   public Mul(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.MUL;}
   public String toString() {return "mul("+opnd1().toString()+","+opnd2().toString()+")";}
   
   public void chequea() throws Exception {
	   opnd1.chequea();
	   opnd2.chequea();
	   if (!opnd1.tipo.igual(opnd2.tipo) || 
			opnd1.tipo.tipo == TPrimitivo.BUIT ||
			opnd1.tipo.tipo == TPrimitivo.BOOL ||
			opnd1.tipo.dimension > 0 )
		   throw new Exception("ERROR: uso correcto exp * exp ");
	   this.tipo = new MiTipo(opnd1.tipo.tipo);
   }
   
   public String codigo(List<String> lista) { 
	   String str =  opnd1.codigo(lista)
	   		+ opnd2.codigo(lista);
	   if (opnd1.tipo.tipo == TPrimitivo.INT)
	   		 str+="i32.mul\n";
	   else 
		   str+="f32.mul\n";
	   return str;
   }
}
