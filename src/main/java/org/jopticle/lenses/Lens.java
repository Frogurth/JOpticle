package org.jopticle.lenses;

import fj.F;

/**
 * A Lens wraps a getter and a setter of a particular field of a particular
 * class. <br/>
 * <br/>
 * The abstract Lens class, with implemented compose-, andThen- and mod-methods.
 * 
 * @author Frogurth
 * 
 * @param <A>
 *            the source class
 * @param <B>
 *            the type of the field to be viewed.
 */
public abstract class Lens<A, B> {
	/**
	 * Returns the viewed field of the given instance.
	 * 
	 * @param a
	 *            the object where the field should be read from.
	 * @return the field of the object.
	 */
	public abstract B get(A a);

	/**
	 * Sets the given data into the field viewed by this lens of the given
	 * object. And returns the updated object.
	 * 
	 * @param a
	 *            the object where the field should be set.
	 * @param b
	 *            the data that should be set.
	 * @return the updated object.
	 */
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

	/**
	 * Composes a new lens out of this and the given lens.
	 * 
	 * @param that
	 *            the lens that should be composed with.
	 * @return a new lens that views the value of the second lens from the
	 *         object of the first lens.
	 */
	public <C> Lens<A, C> andThen(Lens<B, C> that) {
		return that.compose(this);
	}

	/**
	 * Modifies the value viewed by this lens with the given function.
	 * 
	 * @param a
	 *            the object where the value should be modified.
	 * @param f
	 *            the function that modifies the value.
	 * @return an updated object.
	 */
	public A mod(A a, F<B, B> f) {
		return set(a, f.f(get(a)));
	}
}
