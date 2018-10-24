package TDAColaCP;

import excepciones.EmptyPriorityQueueException;
import excepciones.InvalidKeyException;

public class HeapPQueue<K, V> implements PriorityQueue<K, V> {
	protected Entrada<K,V>[] elems;
	protected Comparator<K> comp;
	protected int size;
	
	public HeapPQueue(Comparator<K> c) {
		elems = (Entrada<K,V>[]) new Entrada[1000];
		comp=c;
		size=0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException();
		}
		return elems[1];
	}
	
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		//me fijo que el arreglo no este lleno	
		if (elems.length-1 == size) {
			reSize();
		}
		Entrada<K,V> toRet=elems[++size]=new Entrada<K,V>(key,value);
		//burbujeo
		int i = size;
		boolean termine=false;
		Entrada<K,V> hijo,padre,aux;
		while(!termine) {
			hijo=elems[i];
			padre=elems[i/2];
			int res = 0;
			if(padre != null)
				res=comp.compare(hijo.getKey(),padre.getKey());
			if(res<0) {
				aux = padre;
				elems[i/2]=hijo;
				elems[i]=aux;
			}
			i/=2;
			if(i==0)
				termine=true;
		}
		return toRet;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(size == 0)
			throw new EmptyPriorityQueueException("Cola Vacia");
		Entry<K,V> ret = min();
		if(size == 1){
			elems[1] = null;
			size = 0;
		}
		else{
			elems[1] = elems[size];
			elems[size] = null;
			size--;
			int i = 1;
			boolean seguir = true;
			while(seguir){
				//calculo los hijos de la posición en la que estoy
				int HI = 2*i;
				int HD = (2*i)+1;
				boolean EHI = HI <= size(); //determino si realmente existen los hijos de la posicion
				boolean EHD = HD <= size();
				if(!EHI) //si no hay Hijo Izquierdo, llegué a una hoja y ya moví todo de forma ordenada, no hace falta seguir
					seguir = false;
				else{
					int m; // Minimo de los hijos de i.
					if( EHD ) { //si existe Hijo Derecho, es necesario determinar el minimo de los dos
						// Calculo cual es el menor de los hijos.
						if( comp.compare( elems[HI].getKey(), elems[HD].getKey()) < 0 ) m = HI;
					 		else m = HD; 
					} 
					else m = HI; //sino, el minimo es el Hijo Izquierdo
					if(comp.compare(elems[i].getKey(), elems[m].getKey()) > 0){ //si el mínimo de los hijos es más grande que el padre
						Entrada<K,V> aux = elems[i]; //Guardo el elemento que estoy moviendo
						elems[i] = elems[m]; //intercambio de lugar el minimo de los hijos y el elemento que estoy moviendo
						elems[m] = aux;
						i = m; //sigo con "m" siendo la nueva posición
					}
					else
						seguir = false; //el minimo de los hijos es más chico, ya ordené la Heap
				}
			}
		}
		return ret;
	}
	
	private void checkKey(K key)throws InvalidKeyException{
		if(key==null)
			throw new InvalidKeyException();
		try {
			if (0!=comp.compare(key, key))
				throw new InvalidKeyException();
		}catch(ClassCastException e) {
			throw new InvalidKeyException();
		}
	}
	
	private void reSize() {
		Entrada<K,V> [] aux = (Entrada<K,V> []) new Entrada[elems.length*2];
		for (int i =1;i<elems.length;i++) {
			aux[i]=elems[i];
		}
		elems = aux;
	}
}
