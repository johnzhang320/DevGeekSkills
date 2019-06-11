package com.sun.security.tools.keytool;

import java.util.Objects;

public class Pair<A, B> {

	    public final A fst;
	    public final B snd;

	    public Pair(A fst, B snd) {
	        this.fst = fst;
	        this.snd = snd;
	    }

	    public String toString() {
	        return "Pair[" + fst + "," + snd + "]";
	    }

	    public boolean equals(Object other) {
	        return
	            other instanceof Pair &&
	            Objects.equals(fst, ((Pair)other).fst) &&
	            Objects.equals(snd, ((Pair)other).snd);
	    }

	    public int hashCode() {
	        if (fst == null) return (snd == null) ? 0 : snd.hashCode() + 1;
	        else if (snd == null) return fst.hashCode() + 2;
	        else return fst.hashCode() * 17 + snd.hashCode();
	    }

	    public static <A,B> Pair<A,B> of(A a, B b) {
	        return new Pair<>(a,b);
	    }
}
