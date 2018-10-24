package TDAArbol;

import java.util.Iterator;

import TDALista.*;
import excepciones.*;

public class Arbol<E> implements Tree<E> {

	protected Tnode<E> root;
	protected int size;
	
	public Arbol() {
		root=null;
		size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public Iterator<E> iterator() {
		PositionList<E> l = new DoubleLinkedList<E>();
		if (!isEmpty()) {
			recPre(root,l);
		}
		return new ElementIterator<E>(l);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new DoubleLinkedList<Position<E>>();
		if(!isEmpty()) {
			pre(root,l);
		}
		return l;
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		Tnode<E> p = checkPosition(v);
		E toret = v.element();
		p.setElement(e);
		return toret;
	}

	public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException();
		}
		return root;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		Tnode<E> n = checkPosition(v);
		if (n==root) {
			throw new BoundaryViolationException("root no tiene padre");
		}
		return n.getPadre();
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		Tnode<E> node = checkPosition(v);
		PositionList<Position<E>> toret = new DoubleLinkedList<Position<E>>();
		for(Position<E> i:node.getHijos()) {
			toret.addLast(i);
		}
		return toret;
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		Tnode<E> aux = checkPosition(v);
		return !aux.getHijos().isEmpty();
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		Tnode<E> aux = checkPosition(v);
		return aux.getHijos().isEmpty();
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		Tnode<E> aux = checkPosition(v);
		return aux==root;
	}

	public void createRoot(E e) throws InvalidOperationException {
		if (!isEmpty()) {
			throw new InvalidOperationException("ya tiene raiz");
		}
		root=new Tnode<E>(e);
		size++;
	}

	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		Tnode<E> father = checkPosition(p);
		Tnode<E> nuevo = new Tnode<E>(e,father);
		father.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		Tnode<E> father = checkPosition(p);
		Tnode<E> nuevo = new Tnode<E>(e,father);
		father.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		try {
       	 Tnode<E> padre=checkPosition(p);
       	 Tnode<E> hermanoDerecho=checkPosition(rb);
       	 Tnode<E> nuevo=new Tnode<E>(e,padre);
       	 PositionList<Tnode<E>> hijosPadre=padre.getHijos();
       	 // encontre: true si encontré ubicación en lista de hijos de su padre
       	 boolean encontre = false;
       	 TDALista.Position<Tnode<E>> pp;
       	 if (hijosPadre.isEmpty()) {
       		 pp=null;
       	 }
       	 else {
       		 pp = hijosPadre.first();
       	 while(pp!=null && !encontre ) {
       		 if( hermanoDerecho != pp.element()) {
       			 pp = pp!= hijosPadre.last() ? hijosPadre.next(pp) : null;
       		 }
       		 else {
       			 encontre=true;
       		 }
       	  }
       	 }
       			 if(!encontre) {
       				throw new InvalidPositionException("Posicion no valida,nodo no es hijo del padre");
       			 }
       			 hijosPadre.addBefore(pp,nuevo);
       			 size++;
       	 return nuevo;
        }
        catch(EmptyListException e1){
       	 System.out.println(e1.getMessage());
       	 return null;
        }
        catch (BoundaryViolationException e1) {
       	 System.out.println(e1.getMessage());
       	 return null;
        }
	}

	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		 if (isEmpty()) {
       	 throw new InvalidPositionException("Arbol vacio");
        }
        try {
       	 Tnode<E> padre=checkPosition(p);
       	 Tnode<E> hermanoDerecho=checkPosition(lb);
       	 Tnode<E> nuevo=new Tnode<E>(e,padre);
       	 PositionList<Tnode<E>> hijosPadre=padre.getHijos();
       	 // encontre: true si encontré ubicación en lista de hijos de su padre
       	 boolean encontre = false;
       	 TDALista.Position<Tnode<E>> pp;
       	 if (hijosPadre.isEmpty()) {
       		 pp=null;
       	 }
       	 else {
       		 pp = hijosPadre.first();
       	 while(pp!=null && !encontre ) {
       		 if( hermanoDerecho != pp.element()) {
       			 pp = pp!= hijosPadre.last() ? hijosPadre.next(pp) : null;
       		 }
       		 else {
       			 encontre=true;
       		 }
       	  }
       	 }
       			 if(!encontre) {
       				throw new InvalidPositionException("Posicion no valida,nodo no es hijo del padre");
       			 }
       			 hijosPadre.addAfter(pp,nuevo);
       			 size++;
       	 return nuevo;
        }
        catch(EmptyListException e1){
       	 System.out.println(e1.getMessage());
       	 return null;
        }
        catch (BoundaryViolationException e1) {
       	 System.out.println(e1.getMessage());
       	 return null;
        }
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		try {
			if (isInternal(p)) throw new InvalidPositionException("Position no valida,es un nodo interno");
			if( isEmpty() ) {
				throw new InvalidPositionException( "Arbol vacío" );
			}
			Tnode<E> n = checkPosition( p );
			if (n==root) {
			    	root=null;
			    	size=0;
			}
			else {
				if( !n.getHijos().isEmpty() ) {
					throw new InvalidPositionException( "p no es hoja" );
				}
				Tnode<E> padre = n.getPadre();
				PositionList<Tnode<E>> hijosPadre = padre.getHijos();
				boolean encontre = false; 
				if (hijosPadre.isEmpty()) {
					throw new InvalidPositionException ("P no es hijo de RB.");
				}
				TDALista.Position<Tnode<E>> pos = hijosPadre.first();
				while( pos!=null && !encontre ) {
					if( pos.element() == n ) {
						encontre = true;
					}
					else {
						if (pos==hijosPadre.last()) {
							pos=null;
						}
						else {
							pos=hijosPadre.next(pos);
						}
					}
				}
				if( !encontre ) {
					throw new InvalidPositionException( "p no aparece en la lista de hijos del padre puede ser un arbol incorrecto" );
				}
				hijosPadre.remove( pos );
				size--;
			}
		}
		catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		catch (BoundaryViolationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		Tnode<E> torem = checkPosition(p);
		if (isExternal(p)){
			throw new InvalidPositionException();
		}
		if (torem==root) {
			if (root.getHijos().size()>1) {
				throw new InvalidPositionException();
			}
			else {
				try {
					root= torem.getHijos().first().element();
					root.setPadre(null);
					size--;
				} catch (EmptyListException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			Tnode<E> padre = torem.getPadre();
			//recorro hijos para encontrar posicion del hijo en la lista
			PositionList<Tnode<E>> hijosPadre = padre.getHijos();
			try {
				boolean encontre=false;
				TDALista.Position<Tnode<E>> cursor = hijosPadre.first();
				while (cursor!= null && !encontre) {
					if (torem!=cursor.element())
						cursor=(cursor!=hijosPadre.last()? hijosPadre.next(cursor) : null);
					else
						encontre=true;
				}
				if (!encontre) {
					throw new InvalidPositionException();
				}
				else {
					while (!torem.getHijos().isEmpty()) {
						Tnode<E> aux2 = torem.getHijos().first().element();
						aux2.setPadre(padre);
						hijosPadre.addBefore(cursor, aux2);
						torem.getHijos().remove(torem.getHijos().first());
					}
					hijosPadre.remove(cursor);
					size--;
				}
			}catch(EmptyListException|BoundaryViolationException e1) {
				throw new InvalidPositionException();
			}
			
		}
		torem.setPadre(null);
	}

	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(isExternal(p))
			removeExternalNode(p);
		else
			removeInternalNode(p);
	}
	
	private Tnode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (isEmpty()) {
			throw new InvalidPositionException();
		}
		if (p == null) {
			throw new InvalidPositionException();
		}
		
		return (Tnode<E>) p;
		
	}
	
	private void recPre(Tnode<E> p,PositionList<E> l) {
		l.addLast(p.element());
		for(Tnode<E> i: p.getHijos()) {
			recPre(i,l);
		}
	}
	
	private void pre(Tnode<E> p,PositionList<Position<E>> l) {
		l.addLast(p);
		for(Tnode<E> i: p.getHijos()) {
			pre(i,l);
		}
	}
	
}
