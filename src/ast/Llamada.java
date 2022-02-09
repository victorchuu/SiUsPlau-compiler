package ast;

import java.util.List;

import asem.TablaAmbitos;
import tipado.MiTipo;
import tipado.TPrimitivo;

public class Llamada extends EIden {	
	private E p;
   public Llamada (E parametros) {
	   p=parametros;
   }     
   public TipoE tipo() {return TipoE.LLAMADA;}
   public String toString() {return "llamada("+iden+","+p.toString()+")";}
   
   public void vincula(TablaAmbitos T) {
		puntero = T.buscaId(iden);
		p.vincula(T);
	}
   
   public void chequea() throws Exception{
      if (puntero == null) {
    	  this.tipo = new MiTipo(TPrimitivo.BUIT);
    	  throw new Exception("ERROR: La funcion "+this.iden+" no ha sido vinculada.");
			
		}
      if (puntero.tipo() != TipoE.FUNCIO)
    	  throw new Exception("ERROR: " + this.iden+" no es una funcion.");
      else {
         p.chequea();

         this.tipo=puntero.tipo;
         if(!((Funcio) puntero).comprueba(p)){
        	 throw new Exception("ERROR: el parametro no coincide con los argumentos esperados");
         }       
      }
  }
   
   public String codigo(List<String> lista) {
	   return p.codigo(lista)  //nombre
			   + "call $" + ((Funcio) puntero).i +"\r\n";
   }
   
}
