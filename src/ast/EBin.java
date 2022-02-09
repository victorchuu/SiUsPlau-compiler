package ast;

import asem.TablaAmbitos;

public abstract class EBin extends E {
   protected E opnd1;
   protected E opnd2;
   public EBin(E opnd1, E opnd2) {
     this.opnd1 = opnd1;
     this.opnd2 = opnd2;
   }
   public E opnd1() {return opnd1;}
   public E opnd2() {return opnd2;}   
   
   public void vincula(TablaAmbitos T) {
		opnd1.vincula(T);
		opnd2.vincula(T);
	}
}
