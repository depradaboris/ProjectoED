package main;
import TDAArbol.*;
import TDAArbol.Position;
import TDALista.*;
import TDACola.*;
import TDAPila.*;
import TDAColaCP.*;

import java.util.EmptyStackException;
import java.util.Iterator;
import excepciones.*;
public class Logica {
	protected Tree<String> arbol;
	public Logica(){
		arbol=new Arbol<String>();
	}
	public String cargarArbol(String raiz){
		String nodoRaiz="";
		try {					
			arbol.createRoot(raiz);
			nodoRaiz=arbol.root().element();			
		}catch(InvalidOperationException|EmptyTreeException e) {
			e.getMessage();			
		}	
		return nodoRaiz;
	}

	private Position<String> buscar(String padre){					
		Iterator<Position<String>> it =arbol.positions().iterator();
		boolean encontre=false;
		Position<String> cursor=it.next();
		while(it.hasNext() && !encontre) {
			if(cursor.element()==padre) {
				encontre=true;
			}
			else
				cursor=it.next();
		}
		return cursor;
	}

	private boolean repetido(String padre) {
		Iterator<Position<String>> it =arbol.positions().iterator();
		boolean encontre=false;
		Position<String> cursor=it.next();
		while(it.hasNext() && !encontre) {
			if(cursor.element()==padre) {
				encontre=true;
			}
			else
				cursor=it.next();
		}
		return encontre;
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
	public String AgregarNodo(String padre,String rotulo) throws RepeatedNodeException{
		String toReturn="";
		try {
			if(repetido(rotulo))
				throw new RepeatedNodeException();
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

	public String mostrarRuta(String pos) throws InvalidOperationException,InvalidFileException {
		String s="";		
		try {
			Position<String>a=buscar(pos);
			if(arbol.isInternal(a)) 
				throw new InvalidFileException("");
			if(a==null)throw new InvalidOperationException("");
			Stack<String> pila=new PilaEnlazada<String>();
			while(!arbol.isRoot(a)) {				
				pila.push(a.element());
				pila.push("/");
				a=arbol.parent(a);				
			}
			pila.push(a.element());

			while(!pila.isEmpty()) {
				s+=pila.pop();
			}
		}catch(EmptyStackException|InvalidPositionException|BoundaryViolationException e){
			e.printStackTrace();			
		}
		return s;
	}
	public Iterable<String> mostrarPorNiveles(){
		Position<String>pos=null;
		PositionList<String> list=new DoubleLinkedList<String>();
		Queue<Position<String>> cola=new ColaConLista<Position<String>>();
		try {
			Position<String> cursor=arbol.root();
			cola.enqueue(cursor);
			cola.enqueue(null);
			while(!cola.isEmpty()){
				pos=cola.dequeue();
				if(pos!=null) {
					list.addLast(pos.element());
					for(Position<String>p:arbol.children(pos)) {
						cola.enqueue(p);
					}
				}
				else {	
					if(!cola.isEmpty())
						cola.enqueue(null);
				}
			}
		}catch(EmptyQueueException|InvalidPositionException|EmptyTreeException e) {
			e.printStackTrace();
		}
		return list;
	}
	private int profundidad(Position<String> pos) {
		int profundo=0;
		try {
			if(!arbol.isRoot(pos))
				profundo=1+profundidad(arbol.parent(pos));
		}catch(InvalidPositionException|BoundaryViolationException e){
			e.printStackTrace();
		}
		return profundo;
	}
	public String imprimirArchivos() {
		String s="";
		PriorityQueue<Integer,String> pq=new HeapPQueue<Integer,String>(new Comparator<Integer>());
		Stack<String> pila = new PilaEnlazada<String>();
		try {
			for(Position<String>p:arbol.positions()) {
				if(arbol.isExternal(p))
					pq.insert(profundidad(p),p.element());
			}
			//Invierto la Cola para devolverla
			while(!pq.isEmpty()) {
				pila.push(pq.removeMin().getValue()+" ");
			}
			//Devuelvo el contenido de la pila
			while(!pila.isEmpty()){
				s+=pila.pop();
			}
		}catch(InvalidPositionException|InvalidKeyException|EmptyPriorityQueueException|EmptyStackException e){	
			e.printStackTrace();
		}
		return s;
	}
}
