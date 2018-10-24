package TDALista;

public class Dnode<E> implements Position<E>{
	
	E element;
	Dnode<E> prev,next;
	
	public Dnode(){
		this(null, null, null);
	}

	public Dnode(E element, Dnode<E> prev, Dnode<E> next){
		this.element = element;
		this.prev = prev;
		this.next = next;
	}

	public Dnode<E> getPrev(){
		return prev;
	}

	public void setPrev(Dnode<E> prev){
		this.prev = prev;
	}

	public Dnode<E> getNext(){
		return next;
	}

	public void setNext(Dnode<E> next){
		this.next = next;
	}

	public void setElement(E element){
		this.element = element;
	}

	public E element() {
		return element;
	}
}
