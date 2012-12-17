package org.jopticle.lenses;

public abstract class Lens<A, B> {
	public abstract B get(A a);
	public abstract A set(A a, B b);
	
	public <C> Lens<C, B> compose(final Lens<C, A> that) {
		final Lens<A, B> thisLens = this;
		return new Lens<C, B>() {
			@Override
			public B get(C c) {
				return thisLens.get(that.get(c));
			}

			@Override
			public C set(C c, B b) {
				return that.set(c, thisLens.set(that.get(c), b));
			}
		};
	}
	
	public <C> Lens<A, C> andThen(Lens<B, C> that) {
		return that.compose(this);
	}
}
