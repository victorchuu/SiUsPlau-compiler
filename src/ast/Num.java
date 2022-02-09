package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Num extends E {
  private String v;
  public Num(String v) {
   this.v = v;   
  }
  public String num() {return v;}
  public TipoE tipo() {return TipoE.NUM;}   
  public String toString() {return v;}  
  
  public void vincula(TablaAmbitos T) {
		
	}
  
  public void chequea() throws Exception {
		tipo = new MiTipo(TPrimitivo.INT);
	}
  public String codigo(List<String> lista) {
	   return "i32.const "+v+"\n";
  }
}
