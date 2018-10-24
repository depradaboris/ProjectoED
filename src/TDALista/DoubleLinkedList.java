package TDALista;

import java.util.Iterator;

import excepciones.*;

public class DoubleLinkedList<E> implements PositionList<E> {
	
	protected Dnode<E> header,trailer;
	protected int size;

	public DoubleLinkedList() {
		header = new Dnode<E>();
		trailer = new Dnode<E>(null,header,null);
		header.setNext(trailer);
		size=0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public void addFirst(E element) {
		Dnode<E> nuevo = new Dnode<E>(element, header, header.getNext());
		header.getNext().setPrev(nuevo);
		header.setNext(nuevo);
		size++;
	}

	public void addLast(E element) {
		Dnode<E> nuevo = new Dnode<E>(element,trailer.getPrev(),trailer);
		trailer.getPrev().setNext(nuevo);
		trailer.setPrev(nuevo);
		size++;
	}

	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	public Position<E> first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		return header.getNext();
	}

	public Position<E> last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		return trailer.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Dnode<E> aux = checkPosition(p);
		if (aux.getNext() == trailer) {
			throw new BoundaryViolationException("sig del ulti");
		}
		return aux.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Dnode<E> aux = checkPosition(p);
		if (aux.getPrev() == header) {
			throw new BoundaryViolationException("ant del prim");
		}
		return aux.getPrev();
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Dnode<E> aux = checkPosition(p);
		Dnode<E> nuevo = new Dnode<E>(element,aux,aux.getNext());
		aux.getNext().setPrev(nuevo);
		aux.setNext(nuevo);
		size++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Dnode<E> aux = checkPosition(p);
		Dnode<E> nuevo = new Dnode<E>(element,aux.getPrev(),aux);
		aux.getPrev().setNext(nuevo);
		aux.setPrev(nuevo);
		size++;
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		Dnode<E> torem = checkPosition(p);
		E toret = p.element();
		torem.getNext().setPrev(torem.getPrev());
		torem.getPrev().setNext(torem.getNext());
		torem.setNext(null);torem.setPrev(null);
		size--;
		return toret;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		Dnode<E> aux = checkPosition(p);
		E toret = p.element();
		aux.setElement(element);
		return toret;
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toret = new DoubleLinkedList<Position<E>>();
		if(!isEmpty()){
			try {
				Position<E> cursor = first();
				while (cursor != last()) {
					toret.addLast(cursor);
					cursor=next(cursor);
				}
				toret.addLast(cursor);
			} catch(InvalidPositionException|BoundaryViolationException|EmptyListException e) {
				e.printStackTrace();
			}
		}
		return toret;
	}
	
	private Dnode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p == null) {
			throw new InvalidPositionException();
		}
		if (p == header) {
			throw new InvalidPositionException();
		}
		if (p == trailer) {
			throw new InvalidPositionException();
		}
		if (isEmpty()) {
			throw new InvalidPositionException();
		}
		try
		{
			Dnode<E> toret = (Dnode<E>)p;
			if ((toret.getPrev() == null) || (toret.getNext() == null)) {
				throw new InvalidPositionException();
			}
			return toret;
		}
		catch (ClassCastException e)
		{
			throw new InvalidPositionException();
		}
	}

}
