package TDALista;

import java.util.*;

import excepciones.*;

public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> lista;
	protected Position<E> cursor;

	public ElementIterator(PositionList<E> lista)
	{
		try
		{
			this.lista = lista;
			cursor = lista.first();
		}
		catch (EmptyListException e) {
			cursor = null;
		}
	}

	public boolean hasNext()
	{
		return cursor != null;
	}

	public E next()	throws NoSuchElementException
	{
		E toReturn = null;
		try
		{
			if (cursor == null) {
				throw new NoSuchElementException();
			}
			toReturn = cursor.element();
			cursor = (cursor == lista.last() ? null : lista.next(cursor));
		}
		catch (InvalidPositionException|BoundaryViolationException|EmptyListException e) {}
		return toReturn;
	}
}
