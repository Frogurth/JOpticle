package org.jopticle.lenses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Implementation of the abstract {@linkplain Lens}. Uses java reflection to
 * look for the getter and setter methods. <br/>
 * <br/>
 * The setter sets the new value directly into the given object and returns it.
 * 
 * @author Frogurth
 */
public class BeanLens<A, B> extends Lens<A, B> {
	private Method getMethod;
	private Method setMethod;

	/**
	 * Construct a new BeanLens
	 * 
	 * @param fieldName
	 *            the name of the field to be viewed.
	 * @param theType
	 *            the {@link Class} of the source.
	 */
	public BeanLens(String fieldName, Class<A> theType) {
		for (Method m : theType.getMethods()) {
			if (m.getName().toLowerCase()
					.equals("get" + fieldName.toLowerCase())) {
				this.getMethod = m;
			}
			if (m.getName().toLowerCase()
					.equals("set" + fieldName.toLowerCase())) {
				this.setMethod = m;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public B get(A a) {
		B b = null;
		try {
			b = (B) getMethod.invoke(a, new Object[0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public A set(A a, B b) {
		try {
			setMethod.invoke(a, b);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return a;
	}

}
