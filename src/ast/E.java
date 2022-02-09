package ast;

import java.util.List;

import asem.Memoria;
import asem.TablaAmbitos;
import tipado.MiTipo;

public abstract class E {
   
   protected MiTipo tipo;

   public abstract TipoE tipo(); 
   public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
   public E opnd2() {throw new UnsupportedOperationException("opnd2");} 

   
   public void vincula(TablaAmbitos T) {throw new UnsupportedOperationException("FUNCION VINCULA NO IMPLEMENTADA");};
   public void chequea() throws Exception {throw new UnsupportedOperationException("FUNCION CHEQUEA NO IMPLEMENTADA");};
   						//Siguiente pos de memoria estatica libre
   public String codigo(List<String> lista) {throw new UnsupportedOperationException("FUNCION CODIGO NO IMPLEMENTADA(per o lista)");};
   public void memoria(Memoria m) {};
   public int size(int delta) {return 1; }; //Por defecto el tam de cualquier tipo en SiUsPlau es 1
}
