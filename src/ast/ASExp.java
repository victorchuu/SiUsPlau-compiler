package ast;

import tipado.MiTipo;
import tipado.TPrimitivo;

public class ASExp {
	
	//---------OPERADORES ARITMETICOS---------------
	public E suma(E opnd1, E opnd2) 	{return new Suma(opnd1,opnd2);}  
	public E menos(E opnd1, E opnd2) 	{return new Resta(opnd1,opnd2);} 
	public E mul(E opnd1, E opnd2) 		{return new Mul(opnd1,opnd2);}  
	public E div(E opnd1, E opnd2) 		{return new Div(opnd1,opnd2);} 
	//---------OPERADORES BOOLEANOS---------------
	public E or(E opnd1, E opnd2) 		{return new Or(opnd1,opnd2);} 
	public E and(E opnd1, E opnd2) 		{return new And(opnd1,opnd2);} 
	public E neg(E opnd1) 				{return new Not(opnd1);} 
	public E igual(E opnd1, E opnd2) 	{return new Igual(opnd1,opnd2);} 
	public E dist(E opnd1, E opnd2) 	{return new Distinto(opnd1,opnd2);} 
	public E may(E opnd1, E opnd2) 		{return new Mayor(opnd1,opnd2);} 
	public E men(E opnd1, E opnd2) 		{return new Menor(opnd1,opnd2);}
	public E mayig(E opnd1, E opnd2) 	{return new MayorIgual(opnd1,opnd2);} 
	public E menig(E opnd1, E opnd2) 	{return new MenorIgual(opnd1,opnd2);} 
	
	
	//---------------------------TIPOS---------------------
	public E buit() 				{return new Buit();}     
	public E porDefecto() 			{return new PorDefecto();}
	public E blanco() 				{return new Blanco();}
	
	//---------------------------VALORES---------------------
	public E ent(String num) 			{return new Num(num);}      
	public E bool(boolean b) 			{return new Bool(b);}
	public E real(String num) 			{return new Real(num);}
	public EIden iden() 				{return new Identificador();}
	public MasDimensiones masDimensiones(E e, MasDimensiones m) 	{ return new MasDimensiones(e,m); }
	public E lista(E d, E l) 			{return new Lista(d,l);}
	public E nou(TPrimitivo t, MasDimensiones m) 	{return new Nou(new MiTipo(t),m); }
	public E accesoArray(E o, E i) 		{return new AccesoArray(o,i);}

	
	//***************VARIABLES***********************
	public E var(MiTipo t, Declaracion d) 						{return new Var(t,d);}
	public Declaracion declaracion(String id,E exp, Declaracion sig) 	{return new Declaracion(id,exp,sig);}
	public Declaracion finDec() {return new FinDeclaracion();}
	public E valor(E v, E op2) 					{return new Asignacion(v,op2);}

	//***************BLOQUE**********************
	public IniBloque iniBloque(E b) 		{return new IniBloque(b);}
	public E finBloque() 					{return new FinBloque();}
	public E bloque(E i,E b) 				{return new Bloque(i,b);}

	//***************FUNCIONES*******************
	public EIden llamada(E p) 						{return new Llamada(p);}
	public E iniParametro(E d, E p) 				{return new IniParametro(d,p);}
	public E parametro(E d, E p) 					{return new Parametro(d,p);}
	public E funcio(MiTipo t, String i, E p, IniBloque b){return new Funcio(t,i,p,b);}
	public E iniArgumentos(MiTipo t, String i, E p) 		{return new IniArgumentos(t,i,p);}
	public E argumentos(MiTipo t, String i, E p) 		{return new Argumentos(t,i,p);}

	//***************INSTRUCCIONES***************
	public E tornar(E opnd) 						{return new Tornar(opnd);} 
	public E si(E op1,E op2, E op3) 				{return new Cond(op1,op2,op3);} 
	public E _else(E op)							{return new Else(op);} 
	public E mientras(E op1,E op2) 					{return new Mientras(op1,op2);}
	public E per(String a,E b, E op3,E op4)			{return new Per(a,b,op3,op4);} 
	public E escribe(E e) 							{return new Escribe(e);}
	public E lee(E v)							{return new Lee(v); }


      
	//public EIden llamadaFuncion(E e) {return new LlamadaFuncion(e);}
}