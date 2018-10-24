package TDALista;

import java.util.Iterator;

import excepciones.*;

public class LinkedList<E> implements PositionList<E> {
	protected Node<E> head,tail;
	protected int size;

	public LinkedList() {
		head=tail=null;
		size=0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public Position<E> first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("lista vacia");
		}
		return head;
	}

	public Position<E> last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("lista vacia");
		}
		return tail;
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> nodo = checkPosition(p);
		if (nodo==tail) {
			throw new BoundaryViolationException("next de tail");
		}

		return nodo.getNext();
	} 

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> nodo = checkPosition(p);
		if (nodo==head) {
			throw new BoundaryViolationException("prev de head");
		}
		Node<E> puntero = head;
		boolean encontre=false;
		while (puntero.getNext()!= null && !encontre) {
			if (puntero.getNext() == nodo) {
				encontre=true;
			}
			else {
				puntero=puntero.getNext();
			}
		}
		return puntero;
	}

	public void addFirst(E element) {
		Node<E> nuevo=new Node<E>(element,head);
		head = nuevo;
		if (isEmpty()) {
			tail=nuevo;
		}
		size++;
	}

	public void addLast(E element) {
		Node<E> nuevo=new Node<E>(element);
		if (isEmpty()) {
			head=tail=nuevo;
		}
		else {
			tail.setNext(nuevo);
		}
		tail=nuevo;
		size++;
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		Node<E> nuevo = new Node<E>(element,n.getNext());
		n.setNext(nuevo);
		if(n==tail) {
			tail=nuevo;
		}
		size++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		Node<E> nuevo = new Node<E>(element);
		if (n==head) {
			nuevo.setNext(head);
			head=nuevo;
			size++;
		}
		else {
			Node<E> puntero = head;
			boolean encontre=false;
			while (puntero.getNext()!= null && !encontre) {
				if (puntero.getNext() == n) {
					encontre=true;
				}
				else {
					puntero=puntero.getNext();
				}
			}
			if (encontre==true) {
				nuevo.setNext(puntero.getNext());
				puntero.setNext(nuevo);
				size++;
			}
			else {
				throw new InvalidPositionException("pos no pertence a la lista");
			}

		}
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		E elem=p.element();
		try {
			if (n==head) {
				head=n.getNext();
				n.setNext(null);
			}
			else {
				if (n==tail) {
					tail=(Node<E>) prev(n);
					tail.setNext(null);
				}
				else {
					Node<E> prev = (Node<E>) prev(n);
					prev.setNext(n);
					n.setNext(null);
				}
			}
		}catch (BoundaryViolationException|ClassCastException e) {
			throw new InvalidPositionException("Posicicion invalida");
		}

		size--;
		return elem;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		E toreturn = p.element();
		n.setElement(element);
		return toreturn;
	}

	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toreturn = new LinkedList<Position<E>>();
		if (!isEmpty()) {
			try
			{
				Position<E> aux = first();
				while (aux != last())
				{
					toreturn.addLast(aux);
					aux = next(aux);
				}
				toreturn.addLast(aux);
			}
			catch (InvalidPositionException|BoundaryViolationException|EmptyListException e)
			{
				e.printStackTrace();
			}
		}
		return toreturn;
	}

	private Node<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p == null) {
			throw new InvalidPositionException("pos nula");
		}
		if (isEmpty()) {
			throw new InvalidPositionException("lista vacia");
		}
		try
		{
			Node<E> temp = (Node<E>) p;
			return temp;
		}
		catch (ClassCastException e)
		{
			throw new InvalidPositionException("cast error");
		}
	}
}
