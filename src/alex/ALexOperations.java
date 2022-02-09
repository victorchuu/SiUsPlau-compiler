package alex;

import constructorast.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoExp alex;
  public ALexOperations(AnalizadorLexicoExp alex) {
   this.alex = alex;   
  }
	  
	  
	//-------------------DECLARACION VARIABLES----------------------------
	  public UnidadLexica unidadVar() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VAR,"var");
		}
	  public UnidadLexica unidadNou() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NOU,"nou");
		}
	  public UnidadLexica unidadIdEnt() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENTER,"enter");
		}
	  public UnidadLexica unidadEnt() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema()); 
		  } 
	  public UnidadLexica unidadIdBool() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOL,"bool");
		}
	  public UnidadLexica unidadCert() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CERT,"cert");
		}
	  public UnidadLexica unidadFals() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FALS,"false");
		}
	  public UnidadLexica unidadIdReal() {
			return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDREAL,"real");
		}
	  public UnidadLexica unidadReal() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.REAL,alex.lexema()); 
		  } 	
	  public UnidadLexica unidadId() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN,alex.lexema()); 
		  } 

	  
	  //-----------------PARENTESIS, LLAVES, CORCHETES-----------------------
	  
	  public UnidadLexica unidadPAp() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PAP,"("); 
		  } 
	  public UnidadLexica unidadPCierre() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRE,")"); 
		  } 
	  public UnidadLexica unidadCAp() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CAP,"["); 
	  } 
	  public UnidadLexica unidadCCierre() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CCIERRE,"]"); 
	  } 
	  public UnidadLexica unidadLAp() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LAP,"{"); 
	  } 
	  public UnidadLexica unidadLCierre() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LCIERRE,"}"); 
	  } 
	  public UnidadLexica unidadIAp() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IAP,"interrogacion"); 
	  }
	  public UnidadLexica unidadICierre() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ICIERRE,"?"); 
	  } 
	  
	  
	  //-------------------INSTRUCCIONES-----------------------------
	  
	  public UnidadLexica unidadAsig() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASIG,"="); 
	  } 
	  public UnidadLexica unidadElse() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSE,"else"); 
	  } 
	  public UnidadLexica unidadMentre() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENTRE,"mentre"); 
	  } 
	  public UnidadLexica unidadPer() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PER,"per"); 
	  } 
	  public UnidadLexica unidadFer() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FER,"fer"); 
	  } 
	  public UnidadLexica unidadAmb() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AMB,"amb"); 
	  } 
	  public UnidadLexica unidadEn() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EN,"en"); 
	  } 
	  public UnidadLexica unidadFuncio() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FUNCIO,"funcio"); 
	  } 
	  public UnidadLexica unidadTornar() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TORNAR,"tornar"); 
	  } 
	  public UnidadLexica unidadBuit() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BUIT,"buit"); 
	  } 
	  public UnidadLexica unidadEscriu() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ESCRIU,"escriu"); 
	  } 
	  public UnidadLexica unidadLlegeix() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLEGEIX,"llegeix"); 
	  } 
	  
	  //---------------------OPERADORES ARITMETICOS-----------------
	 
	  public UnidadLexica unidadSuma() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAS,"+"); 
		  } 
	  public UnidadLexica unidadResta() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOS,"-"); 
		  } 
	  public UnidadLexica unidadPor() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.POR,"*"); 
	  } 
	  public UnidadLexica unidadDivision() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV,"/"); 
	  } 
	  
	  
	  //----------------OPERADORES BOOLEANOS-----------------------------
	  
	  public UnidadLexica unidadNot() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NOT,"not"); 
		  } 
	  public UnidadLexica unidadAnd() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AND,"&"); 
		  } 
	  public UnidadLexica unidadOr() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OR,"|"); 
		  } 
		 
	  public UnidadLexica unidadMayorIgual() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORIGUAL,">="); 
	  } 
	  public UnidadLexica unidadMenorIgual() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORIGUAL,"<="); 
	  } 
	  public UnidadLexica unidadMayor() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR,">"); 
	  } 
	  public UnidadLexica unidadMenor() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR,"<"); 
	  } 
	  public UnidadLexica unidadIgual() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL,"="); 
	  }
	  public UnidadLexica unidadDistinto() {
		  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DISTINTO,"!="); 
	  } 
	  
	  
	  public UnidadLexica unidadComa() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA,","); 
		  } 
	  public UnidadLexica unidadPunto() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTO,"."); 
		  } 
	  
	  public UnidadLexica unidadEof() {
		     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF,"<EOF>"); 
		  }
	  public void error() {
	    System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
	  }
}
