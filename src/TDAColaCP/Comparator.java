package TDAColaCP;

import java.lang.Comparable;

public class Comparator<T> implements java.util.Comparator<T> {
	  public int compare(T e1, T e2) {
	    return ((Comparable<T>)e1).compareTo(e2);
	  }  
	}