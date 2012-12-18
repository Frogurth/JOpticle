package org.jopticle.lenses;

import fj.F;
import fj.F2;

public class FunctionalLens<A, B> extends Lens<A, B> {
	private F<A, B> getter;
	private F2<A, B, A> setter;

	public FunctionalLens(F<A, B> getter, F2<A, B, A> setter) {
		this.getter = getter;
		this.setter = setter;
	}

	@Override
	public B get(A a) {
		return getter.f(a);
	}

	@Override
	public A set(A a, B b) {
		return setter.f(a, b);
	}

	public F<A, B> getGetter() {
		return getter;
	}

	public void setGetter(F<A, B> getter) {
		this.getter = getter;
	}

	public F2<A, B, A> getSetter() {
		return setter;
	}

	public void setSetter(F2<A, B, A> setter) {
		this.setter = setter;
	}
}
