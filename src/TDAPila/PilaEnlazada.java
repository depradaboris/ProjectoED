package TDAPila;

import java.util.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E> {
	protected Node<E> head;
	protected int size;
	
	public PilaEnlazada() {
		head=null;
		size=0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public E top() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return head.element();
	}

	public void push(E element) {
		Node<E> nuevo = new Node<E>(element,head);
		head=nuevo;
		size++;
	}

	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E aux = head.element();
		head=head.getNext();
		size--;
		return aux;
	}
	
	
}
