package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Real extends E {
  private String v;
  public Real(String v) {
   this.v = v;   
  }
  public String real() {return v;}
  public TipoE tipo() {return TipoE.REAL;}   
  public String toString() {return v;} 
  
  public void vincula(TablaAmbitos T) {
		
	}
  
  public void chequea() throws Exception {
		tipo = new MiTipo(TPrimitivo.REAL);
	}
  private void toPunto() {
	  String str = "";
	  for (int i = 0; i < v.length(); i++)
		  if(v.charAt(i) == '\'') 
			  str += '.';
		  else str += v.charAt(i);
	  v = str;
  }
  public String codigo(List<String> lista) {
	  toPunto();
	  return "f32.const "+v+"\n";
  }
}
