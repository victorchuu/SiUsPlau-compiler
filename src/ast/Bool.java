package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Bool extends E {
  private boolean b;
  public Bool(boolean b) {
   this.b = b;   
  }

  public TipoE tipo() {return TipoE.BOOL;}   
  public String toString() {
	  if (b) 
		  return "cert";
	  else return "false";
  }
  public void vincula(TablaAmbitos T) {
		
	}
  
  public void chequea() throws Exception{
		tipo = new MiTipo(TPrimitivo.BOOL);
	}
  
  public String codigo(List<String> lista) {
	  if (b) 
		  return "i32.const 1\n";
	  else return "i32.const 0\n";
  }
  
}
