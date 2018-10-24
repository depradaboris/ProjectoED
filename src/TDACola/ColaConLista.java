package TDACola;

import excepciones.EmptyQueueException;

public class ColaConLista<E> implements Queue<E> {
	protected Node<E> front,rear;
	protected int size;
	
	public ColaConLista() {
		front=null;
		rear=null;
		size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		
		return size==0;
	}

	public E front() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Cola Vacia");
		}
		
		return front.element();
	}

	public void enqueue(E element) {
		if(isEmpty()) {
			front=rear=new Node<E>(element);
		}
		else {
			rear.setNext(new Node<E>(element));
			rear=rear.getNext();
		}
		size++;
	}

	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Cola Vacia");
		}
		E aux=front.element();
		Node <E> nfr=front.getNext();
		front.setNext(null);
		front=nfr;
		size--;
		
		return aux;
	}

}
