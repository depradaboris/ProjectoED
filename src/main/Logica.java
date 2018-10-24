package main;
import TDAArbol.*;
import TDAArbol.Position;
import TDALista.*;
import TDACola.*;
import TDAPila.*;
import TDAColaCP.*;
import java.util.Iterator;
import excepciones.*;
public class Logica {
	protected Tree<String> arbol;
	public Logica(){
		
	}
	public String cargarArbol(String raiz){
		try {
			arbol=new Arbol<String>();		
			String nodoRaiz;		
			arbol.createRoot(raiz);
			nodoRaiz=arbol.root().element();
			return nodoRaiz;
		}catch(InvalidOperationException|EmptyTreeException e) {
			e.getMessage();
			return null;
		}		
	}
	
	private Position<String> buscar(String padre){					
			for(Position<String>p:arbol.positions()) {
				if(p.element()==padre)
					return p;			
			}
			return null;		
	}
	
	public Iterable<String> listarArchivos(){
		PositionList<String> list=new DoubleLinkedList<String>();
		try {		
			if(!arbol.isEmpty()) {		
				for(Position<String>p:arbol.positions())
					if(arbol.isExternal(p))
						list.addLast(p.element());
			}
		}catch(InvalidPositionException e){e.printStackTrace();}
		
		return list;
	}
	
	public Iterable<String> listarCarpetas(){
		PositionList<String> list=new DoubleLinkedList<String>();
		try {		
			if(!arbol.isEmpty()) {		
				for(Position<String>p:arbol.positions())
					if(arbol.isInternal(p))
						list.addLast(p.element());
			}
		}catch(InvalidPositionException e){e.printStackTrace();}
		
		return list;
	}
	
	public String AgregarNodo(String padre,String rotulo){
		String toReturn="";
		try {
			Position<String> padrePos=buscar(padre);
		Position<String> nodo=arbol.addLastChild(padrePos,rotulo);
		toReturn=nodo.element();
		}catch(InvalidPositionException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	private void postOrden(PositionList<String>list,Position<String>pos) {	
		try {
			for(Position<String>p:arbol.children(pos)) {
				postOrden(list,p);
			}
			list.addLast(pos.element());
		}catch(InvalidPositionException e) {
			e.printStackTrace();
		}		
	}
	public Iterable<String> mostrarPostOrden(){
		PositionList<String>list=new DoubleLinkedList<String>();
		try {
			if(!arbol.isEmpty()) {
				postOrden(list,arbol.root());
			}
		}catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Iterable<String> mostrarPreOrden(){
		PositionList<String> list=new DoubleLinkedList<String>();
		try {
			if(!arbol.isEmpty()) {
				preOrden(list,arbol.root());
			}
		}catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		return list;
	}
	private void preOrden(PositionList<String>list,Position<String> pos) {
		try {	
			list.addLast(pos.element());		
			for(Position<String>p:arbol.children(pos)) {
				preOrden(list,p);
			}
		}catch(InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	private void clono(Tree<String> nuevo,Position<String> raiz,Position<String>raizNueva){
		try {
			for(Position<String>p:arbol.children(raiz)) {
				Position<String>pos=nuevo.addLastChild(raizNueva, p.element());
				clono(nuevo,p,pos);
			}
		}catch(InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	public Tree<String> clonar() {
		Tree<String> nuevo=new Arbol<String>();
		try {
			if(!arbol.isEmpty()) {
				nuevo.createRoot(arbol.root().element());
				clono(nuevo,arbol.root(),nuevo.root());
			}
		}catch(EmptyTreeException|InvalidOperationException e) {
			e.printStackTrace();
		}
		return nuevo;
	}
	
	public Iterable<String> mostrarPorNiveles(){
		if
	}
}
	