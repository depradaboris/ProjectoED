package TDAArbol;

import TDALista.*;

public class Tnode<E> implements Position<E> {
	protected Tnode<E> padre;
	protected PositionList<Tnode<E>> hijos;
	protected E element;
	
	public Tnode(E element,Tnode<E> padre){
		this.element=element;
		this.padre = padre;
		hijos = new DoubleLinkedList<Tnode<E>>();
	}
	
	public Tnode(E element) {
		this(element,null);
	}
	
	public Tnode() {
		this(null,null);
	}
	
	public E element() {
		return this.element;
	}
	
	public void setPadre(Tnode<E> padre){
		this.padre=padre;
	}
	
	public void setElement(E element) {
		this.element=element;
	}
	
	public Tnode<E> getPadre(){
		return padre;
	}
	
	public PositionList<Tnode<E>> getHijos(){
		return hijos;
	}
}
