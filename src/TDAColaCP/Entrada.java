package TDAColaCP;

public class Entrada<K, V> implements Entry<K, V> {
	K key;
	V value;
	
	public Entrada(K clave, V valor) {
		key=clave;
		value=valor;
	}
	
	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	public void setKey(K clave) {
		key=clave;
	}
	
	public void setValue(V valor) {
		value=valor;
	}

}
