package asem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import ast.E;

public class TablaAmbitos {
	
	private List<HashMap<String,E>> tablaVinculacion;
	int n = -1;
	private Stack<E> listaFunciones = new Stack<E>(); 
	//Como SiUsPlau permite funciones anidadas, la pila de funciones
	//para identificar con cual se corresponde cada instruccion tornar.
	

									
	public void iniFuncion(E f) {
		listaFunciones.push(f);
	}

	public void finFuncion(){
		listaFunciones.pop();
	}

	public E vinculaFuncion() {
		return listaFunciones.peek();
	}
	public void inicializa() {
		tablaVinculacion = new ArrayList<HashMap<String,E>>();
		this.abreBloque();
	}
	
	public void abreBloque() {
		tablaVinculacion.add(new HashMap<String,E>());
		n++;
	}
	
	public void cierraBloque() {
		tablaVinculacion.remove(n);
		n--;
	}
	
	public void insertaId(String s, E e) {
		if (!tablaVinculacion.get(n).containsKey(s))
			tablaVinculacion.get(n).put(s, e);
		else
			System.out.println("WARNING: variable con el mismo nombre ("+s+")");
	}
	
	public E buscaId(String s) {
		int m = n;
		while (m >= 0) {
			if (tablaVinculacion.get(m).containsKey(s)) {
				E encontrado = tablaVinculacion.get(m).get(s);
				System.out.println("Nivel: "+Integer.toString(m)+". Variable: "+s+". Vinculado a: "+encontrado.toString());
				return encontrado;
			} 
			m--;
		}
		System.out.println("ERROR: No se ha encontrado la variable "+s);
		return null;
	}
	
}
