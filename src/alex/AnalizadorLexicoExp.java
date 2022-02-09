package alex;
import errors.GestionErroresExp;


public class AnalizadorLexicoExp implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoExp (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoExp (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoExp () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"3:8,4:2,1,3:2,4,3:18,4,44,3:4,40,16,45,46,35,33,5,34,6,36,15:10,2,3,43,25,4" +
"2,22,3,31:26,47,3,48,3:3,8,18,19,32,13,20,29,32,28,32:2,14,26,10,11,27,32,9" +
",21,17,12,7,32,30,32:2,49,41,50,3:65,24,3:2,23,3:31,37,3:7994,38,3:57311,39" +
",3:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,108,
"0,1:2,2,1:2,3,4,1,5,1:6,6,7,1:6,8,9,10,1:5,11:5,1,11:12,12,1,13,14,15,16,11" +
",17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41" +
",42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,11,60,61,62,63,64,65" +
",66")[0];

	private int yy_nxt[][] = unpackFromString(67,51,
"1,2,3,51,2,4,5,6,90,96,99,100:2,52,101,7,51,102,103,104,105,100,8,53,51,9,1" +
"06,107,100:5,10,11,12,13,55,51:2,14,15,16,17,57,18,19,20,21,22,23,-1:53,24," +
"-1:55,100,54,100:6,56,-1,100:5,-1:4,100:5,56,100,-1:33,7,26,-1:59,28,-1:50," +
"29,-1:50,30,-1:27,24:49,-1:7,100:8,56,-1,91,100:4,-1:4,100:5,56,100,-1:33,2" +
"6,-1:42,100:8,56,-1,100:5,-1:4,100:5,56,100,-1:57,37,-1:18,100:3,25,100:4,5" +
"6,-1,100:4,61,-1:4,100:5,56,100,-1:42,27,-1:33,100:2,32,100:5,56,-1,100:5,-" +
"1:4,100:5,56,100,-1:56,50,-1:37,31,-1:32,100:8,56,-1,100,33,100:3,-1:4,100:" +
"5,56,100,-1:25,100,70,100:6,56,-1,100:4,71,-1:4,100:5,56,100,-1:25,100:5,34" +
",100:2,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:8,56,-1,100:2,97,100:2,-1:4," +
"100:5,56,100,-1:25,100:6,72,100,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:2,9" +
"8,100:5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:4,73,100:3,56,-1,100:5,-1:4" +
",100:5,56,100,-1:25,100:8,56,-1,100:5,-1:4,100:2,74,100:2,56,100,-1:25,100:" +
"7,76,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:3,77,100:4,56,-1,100:5,-1:4,10" +
"0:5,56,100,-1:25,100:2,35,100:5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:2,3" +
"6,100:5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:7,38,56,-1,100:5,-1:4,100:5" +
",56,100,-1:25,100:8,56,-1,78,100:4,-1:4,100:5,56,100,-1:25,100:8,56,-1,100:" +
"5,-1:4,100:3,81,100,56,100,-1:25,100:7,39,56,-1,100:5,-1:4,100:5,56,100,-1:" +
"25,100:8,56,-1,40,100:4,-1:4,100:5,56,100,-1:25,100:8,56,-1,41,100:4,-1:4,1" +
"00:5,56,100,-1:25,100:8,56,-1,100:4,42,-1:4,100:5,56,100,-1:25,100:8,56,-1," +
"100:2,95,100:2,-1:4,100:5,56,100,-1:25,100,43,100:6,56,-1,100:5,-1:4,100:5," +
"56,100,-1:25,100:2,44,100:5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:8,56,-1" +
",100:5,-1:4,100:2,84,100:2,56,100,-1:25,100:6,85,100,56,-1,100:5,-1:4,100:5" +
",56,100,-1:25,100,86,100:6,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:2,88,100" +
":5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:5,45,100:2,56,-1,100:5,-1:4,100:" +
"5,56,100,-1:25,100:8,56,-1,100:5,-1:4,100:2,89,100:2,56,100,-1:25,100:2,46," +
"100:5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:4,47,100:3,56,-1,100:5,-1:4,1" +
"00:5,56,100,-1:25,100:6,48,100,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:8,56" +
",-1,100:5,-1:4,100:4,49,56,100,-1:25,100:8,56,-1,100:5,-1:4,58,100:4,56,100" +
",-1:25,100:6,79,100,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:2,75,100:5,56,-" +
"1,100:5,-1:4,100:5,56,100,-1:25,100:3,94,100:4,56,-1,100:5,-1:4,100:5,56,10" +
"0,-1:25,100:8,56,-1,83,100:4,-1:4,100:5,56,100,-1:25,100:8,56,-1,100:5,-1:4" +
",100:2,87,100:2,56,100,-1:25,100:6,59,100,56,-1,100:5,-1:4,100:5,56,100,-1:" +
"25,100:2,80,100:5,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:3,82,100:4,56,-1," +
"100:5,-1:4,100:5,56,100,-1:25,100:4,60,100:3,56,-1,100:5,-1:4,100:5,56,100," +
"-1:25,100:7,62,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:4,63,100:3,56,-1,100" +
":5,-1:4,100:5,56,100,-1:25,100:4,64,65,100:2,56,-1,100:5,-1:4,100:5,56,100," +
"-1:25,100:6,92,100,56,-1,100:5,-1:4,100:5,56,100,-1:25,100,66,100:3,67,68,1" +
"00,56,-1,100:5,-1:4,100:5,56,100,-1:25,100:6,93,100,56,-1,100:5,-1:4,100:5," +
"56,100,-1:25,100:6,69,100,56,-1,100:5,-1:4,100:5,56,100,-1:18");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{errores.errorLexico(fila(),columna(),lexema());}
					case -4:
						break;
					case 4:
						{return ops.unidadComa();}
					case -5:
						break;
					case 5:
						{return ops.unidadPunto();}
					case -6:
						break;
					case 6:
						{return ops.unidadId();}
					case -7:
						break;
					case 7:
						{return ops.unidadEnt();}
					case -8:
						break;
					case 8:
						{return ops.unidadICierre();}
					case -9:
						break;
					case 9:
						{return ops.unidadAsig();}
					case -10:
						break;
					case 10:
						{return ops.unidadSuma();}
					case -11:
						break;
					case 11:
						{return ops.unidadResta();}
					case -12:
						break;
					case 12:
						{return ops.unidadPor();}
					case -13:
						break;
					case 13:
						{return ops.unidadDivision();}
					case -14:
						break;
					case 14:
						{return ops.unidadAnd();}
					case -15:
						break;
					case 15:
						{return ops.unidadOr();}
					case -16:
						break;
					case 16:
						{return ops.unidadMayor();}
					case -17:
						break;
					case 17:
						{return ops.unidadMenor();}
					case -18:
						break;
					case 18:
						{return ops.unidadPAp();}
					case -19:
						break;
					case 19:
						{return ops.unidadPCierre();}
					case -20:
						break;
					case 20:
						{return ops.unidadCAp();}
					case -21:
						break;
					case 21:
						{return ops.unidadCCierre();}
					case -22:
						break;
					case 22:
						{return ops.unidadLAp();}
					case -23:
						break;
					case 23:
						{return ops.unidadLCierre();}
					case -24:
						break;
					case 24:
						{}
					case -25:
						break;
					case 25:
						{return ops.unidadEn();}
					case -26:
						break;
					case 26:
						{return ops.unidadReal();}
					case -27:
						break;
					case 27:
						{return ops.unidadIAp();}
					case -28:
						break;
					case 28:
						{return ops.unidadIgual();}
					case -29:
						break;
					case 29:
						{return ops.unidadMayorIgual();}
					case -30:
						break;
					case 30:
						{return ops.unidadMenorIgual();}
					case -31:
						break;
					case 31:
						{return ops.unidadDistinto();}
					case -32:
						break;
					case 32:
						{return ops.unidadVar();}
					case -33:
						break;
					case 33:
						{return ops.unidadAmb();}
					case -34:
						break;
					case 34:
						{return ops.unidadNou();}
					case -35:
						break;
					case 35:
						{return ops.unidadFer();}
					case -36:
						break;
					case 36:
						{return ops.unidadPer();}
					case -37:
						break;
					case 37:
						{return ops.unidadNot();}
					case -38:
						break;
					case 38:
						{return ops.unidadIdReal();}
					case -39:
						break;
					case 39:
						{return ops.unidadIdBool();}
					case -40:
						break;
					case 40:
						{return ops.unidadBuit();}
					case -41:
						break;
					case 41:
						{return ops.unidadCert();}
					case -42:
						break;
					case 42:
						{return ops.unidadFals();}
					case -43:
						break;
					case 43:
						{return ops.unidadElse();}
					case -44:
						break;
					case 44:
						{return ops.unidadIdEnt();}
					case -45:
						break;
					case 45:
						{return ops.unidadEscriu();}
					case -46:
						break;
					case 46:
						{return ops.unidadTornar();}
					case -47:
						break;
					case 47:
						{return ops.unidadFuncio();}
					case -48:
						break;
					case 48:
						{return ops.unidadMentre();}
					case -49:
						break;
					case 49:
						{return ops.unidadLlegeix();}
					case -50:
						break;
					case 51:
						{errores.errorLexico(fila(),columna(),lexema());}
					case -51:
						break;
					case 52:
						{return ops.unidadId();}
					case -52:
						break;
					case 53:
						{errores.errorLexico(fila(),columna(),lexema());}
					case -53:
						break;
					case 54:
						{return ops.unidadId();}
					case -54:
						break;
					case 55:
						{errores.errorLexico(fila(),columna(),lexema());}
					case -55:
						break;
					case 56:
						{return ops.unidadId();}
					case -56:
						break;
					case 57:
						{errores.errorLexico(fila(),columna(),lexema());}
					case -57:
						break;
					case 58:
						{return ops.unidadId();}
					case -58:
						break;
					case 59:
						{return ops.unidadId();}
					case -59:
						break;
					case 60:
						{return ops.unidadId();}
					case -60:
						break;
					case 61:
						{return ops.unidadId();}
					case -61:
						break;
					case 62:
						{return ops.unidadId();}
					case -62:
						break;
					case 63:
						{return ops.unidadId();}
					case -63:
						break;
					case 64:
						{return ops.unidadId();}
					case -64:
						break;
					case 65:
						{return ops.unidadId();}
					case -65:
						break;
					case 66:
						{return ops.unidadId();}
					case -66:
						break;
					case 67:
						{return ops.unidadId();}
					case -67:
						break;
					case 68:
						{return ops.unidadId();}
					case -68:
						break;
					case 69:
						{return ops.unidadId();}
					case -69:
						break;
					case 70:
						{return ops.unidadId();}
					case -70:
						break;
					case 71:
						{return ops.unidadId();}
					case -71:
						break;
					case 72:
						{return ops.unidadId();}
					case -72:
						break;
					case 73:
						{return ops.unidadId();}
					case -73:
						break;
					case 74:
						{return ops.unidadId();}
					case -74:
						break;
					case 75:
						{return ops.unidadId();}
					case -75:
						break;
					case 76:
						{return ops.unidadId();}
					case -76:
						break;
					case 77:
						{return ops.unidadId();}
					case -77:
						break;
					case 78:
						{return ops.unidadId();}
					case -78:
						break;
					case 79:
						{return ops.unidadId();}
					case -79:
						break;
					case 80:
						{return ops.unidadId();}
					case -80:
						break;
					case 81:
						{return ops.unidadId();}
					case -81:
						break;
					case 82:
						{return ops.unidadId();}
					case -82:
						break;
					case 83:
						{return ops.unidadId();}
					case -83:
						break;
					case 84:
						{return ops.unidadId();}
					case -84:
						break;
					case 85:
						{return ops.unidadId();}
					case -85:
						break;
					case 86:
						{return ops.unidadId();}
					case -86:
						break;
					case 87:
						{return ops.unidadId();}
					case -87:
						break;
					case 88:
						{return ops.unidadId();}
					case -88:
						break;
					case 89:
						{return ops.unidadId();}
					case -89:
						break;
					case 90:
						{return ops.unidadId();}
					case -90:
						break;
					case 91:
						{return ops.unidadId();}
					case -91:
						break;
					case 92:
						{return ops.unidadId();}
					case -92:
						break;
					case 93:
						{return ops.unidadId();}
					case -93:
						break;
					case 94:
						{return ops.unidadId();}
					case -94:
						break;
					case 95:
						{return ops.unidadId();}
					case -95:
						break;
					case 96:
						{return ops.unidadId();}
					case -96:
						break;
					case 97:
						{return ops.unidadId();}
					case -97:
						break;
					case 98:
						{return ops.unidadId();}
					case -98:
						break;
					case 99:
						{return ops.unidadId();}
					case -99:
						break;
					case 100:
						{return ops.unidadId();}
					case -100:
						break;
					case 101:
						{return ops.unidadId();}
					case -101:
						break;
					case 102:
						{return ops.unidadId();}
					case -102:
						break;
					case 103:
						{return ops.unidadId();}
					case -103:
						break;
					case 104:
						{return ops.unidadId();}
					case -104:
						break;
					case 105:
						{return ops.unidadId();}
					case -105:
						break;
					case 106:
						{return ops.unidadId();}
					case -106:
						break;
					case 107:
						{return ops.unidadId();}
					case -107:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
