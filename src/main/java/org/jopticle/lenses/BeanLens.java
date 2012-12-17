package org.jopticle.lenses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanLens<A, B> extends Lens<A, B> {
	private Method getMethod;
	private Method setMethod;
	
	public BeanLens(String fieldName, Class<A> theType) {
		for(Method m: theType.getMethods()) {
			if(m.getName().toLowerCase().equals("get" + fieldName.toLowerCase())) {
				this.getMethod = m;
			}
			if(m.getName().toLowerCase().equals("set" + fieldName.toLowerCase())) {
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
