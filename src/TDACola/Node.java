package TDACola;

public class Node<E> {
	protected E elem;
	protected Node<E> next;
	
	public Node() {this(null,null);};
	public Node(E item) {this(item,null);}
	public Node(E item, Node<E> siguiente) {
		elem=item;
		next=siguiente;
	}
	
	public E element() {
		return elem;
	}
	
	public void setElement(E elem) {
		this.elem=elem;
	}
	
	public Node<E> getNext(){
		return next;
	}
	
	public void setNext(Node<E> next) {
		this.next=next;
	}
}
