package org.jopticle.lenses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.rits.cloning.Cloner;

/**
 * Implementation of the abstract {@linkplain Lens}. Uses java reflection to
 * look for the getter and setter methods. <br/>
 * <br/>
 * The setter performs a deep copy of the source object and insert the value
 * into the copy than returns the copy.
 * 
 * @author Frogurth
 */
public class BeanDeepCopyLens<A, B> extends Lens<A, B> {
	private Method getMethod;
	private Method setMethod;

	public BeanDeepCopyLens(String fieldName, Class<A> theType) {
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
		Cloner cloner = new Cloner();
		A copy = cloner.deepClone(a);
		try {
			setMethod.invoke(copy, b);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return copy;
	}

}
