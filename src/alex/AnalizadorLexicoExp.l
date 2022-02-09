package alex;

import errors.GestionErroresExp;

%%
%cup
%char
%line
%class AnalizadorLexicoExp
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  private int charline = 0;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yychar +1 - charline;}
  private void refrescaColumna() {
    charline = yychar + yytext().length();
  }
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}



letra  = ([A-Z]|[a-z])
digito = [0-9]

separador = [ \t\r\b\n]
comentario = ::[^\n]* 

identificador = {letra}({letra}|{digito})*

variable = var
nuevo = nou
entero = enter 
  numEntero = {digito}{digito}*
boolean = bool
  cierto = cert 
  falso = fals 
Real = real
  numReal = {numEntero}'{digito}*

asignacion = \=
instResta = resta
instMentre = mentre
instFer = fer
instEn = en
instPer = per
instAmb = amb
instFuncio = funcio
instTornar = tornar
instBuit = buit
instEscriu = escriu
instLLegeix = llegeix

operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = /

opNegacion = ┐
opAnd = &
opOr = \|

opMayorIgual = >\=
opMenorIgual = <\=
opMayor = >
opMenor = <
opIgual = \=\=
opDistinto = !\=

corcheteApertura = \[
corcheteCierre = \]
parentesisApertura = \(
parentesisCierre = \)
llaveApertura = \{
interrogacionApertura = ¿
llaveCierre = \}
interrogacionCierre = \?
coma  = ,
punto =\.


%%
{separador}               {}
{comentario}              {}
{coma}                    {return ops.unidadComa();}
{punto}                   {return ops.unidadPunto();}

{variable}                {return ops.unidadVar();}
{nuevo}                   {return ops.unidadNou();}
  {Real}                    {return ops.unidadIdReal();}
    {numReal}                 {return ops.unidadReal();}
  {entero}                  {return ops.unidadIdEnt();}
    {numEntero}               {return ops.unidadEnt();}
  {boolean}                 {return ops.unidadIdBool();}
    {cierto}                  {return ops.unidadCert();}
    {falso}                   {return ops.unidadFals();}
    
{interrogacionCierre}     {return ops.unidadICierre();}
{interrogacionApertura}   {return ops.unidadIAp();}


{asignacion}              {return ops.unidadAsig();}
{instResta} {return ops.unidadElse();}
{instMentre} {return ops.unidadMentre();}
{instFer} {return ops.unidadFer();}
{instEn} {return ops.unidadEn();}
{instPer} {return ops.unidadPer();}
{instAmb} {return ops.unidadAmb();}
{instFuncio} {return ops.unidadFuncio();}
{instTornar} {return ops.unidadTornar();}
{instBuit} {return ops.unidadBuit();}
{instEscriu} {return ops.unidadEscriu();}
{instLLegeix} {return ops.unidadLlegeix();}

{identificador}           {return ops.unidadId();}

{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadPor();}
{operadorDivision}        {return ops.unidadDivision();}

{opNegacion}              {return ops.unidadNot();}
{opAnd}                   {return ops.unidadAnd();}
{opOr}                    {return ops.unidadOr();}

{opMayorIgual}            {return ops.unidadMayorIgual();}
{opMenorIgual}            {return ops.unidadMenorIgual();}
{opMayor}                 {return ops.unidadMayor();}
{opMenor}                 {return ops.unidadMenor();}
{opIgual}                 {return ops.unidadIgual();}
{opDistinto}              {return ops.unidadDistinto();}

{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();}
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();}
{llaveApertura}           {return ops.unidadLAp();}
{llaveCierre}             {return ops.unidadLCierre();}




[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
