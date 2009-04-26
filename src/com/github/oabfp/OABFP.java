package com.github.oabfp;

import java.util.ArrayList;

/**
 * Object Array Based Functional Programming
 * 
 * Enables limited Functional Programming in Java:
 * 
 * @author Benjamin Chi
 * @since 2009 04 26
 * 
 */
public class OABFP {

	/**
	 * Delegate interface. The interface can take an array of Object arguments,
	 * perform some tasks and return an Object. It could return an Object array
	 * or a single Object.
	 * 
	 * @author bchi49
	 * 
	 */
	public interface M {
		public Object m(Object... args);
	}

	// Empty Object array
	public static final Object Nil = new Object[] {};

	/**
	 * Evaluate value(s); The calling program should keep a reference of
	 * objAndArgs (program/functions), for it to invoked dynamically. The
	 * calling method should always call eval(...) to compute delegates and
	 * values.
	 * 
	 * The objAndArgs should consist of Object array where the first element is
	 * a instance of M object (predicate) implementing m method, and rest of the
	 * Objects in the array are passed to the m method as argument for
	 * execution. The arguments could be operands, expressions, or another set
	 * of predicate and expressions. The terminal state is if the Object array
	 * is consist of one Object that doesn't implement M and is not Object[].
	 * 
	 * Note that if m.m(args) returns another set of Object array where the
	 * first element is M instance, then it's evaluated recursively again.
	 * 
	 * The M interface could return another set of M function for eval.
	 * 
	 * If it cannot not be evaluated further, the object is returned.
	 * 
	 * If there are empty objAndArgs, a empty Object array is returned.
	 * 
	 * Recursion is used for flow control, and shouldn't have side effect.
	 * 
	 * @param objAndArgs
	 *            , Object[] {} is considered closure; and are lazy evaluated.
	 * @return
	 */
	public static Object eval(Object... objAndArgs) {
		if (objAndArgs != null && objAndArgs.length > 0) {
			Object o = objAndArgs[0];
			if (o instanceof M) {
				M m = (M) o;
				Object[] args = getArgs(1, objAndArgs);
				return eval(m.m(args));
			} else if (o instanceof Object[]) {
				// must do this cast, otherwise infinite loop occurs.
				Object[] oa = (Object[]) o;
				return eval(oa); // lazy evaluation
			} else {
				if (objAndArgs.length > 1)
					throw new RuntimeException(
							"No predicate (M) defined as the first element in "
									+ "the Object array, and has multiple arguments: "
									+ formatArgs(objAndArgs));
				return o;
			}
		} else {
			return Nil;
		}
	}

	/**
	 * Retrieve the rest of arguments from the starting position.
	 * 
	 * @param startingPos
	 * @param objAndArgs
	 * @return
	 */
	private static Object[] getArgs(int startingPos, Object... objAndArgs) {
		ArrayList<Object> argsTmp = new ArrayList<Object>();
		for (int i = startingPos; i < objAndArgs.length; i++) {
			argsTmp.add(objAndArgs[i]);
		}
		return argsTmp.toArray();
	}

	/**
	 * format the args by enclosing in [ ... ] and delimited by comma.
	 * 
	 * @param args
	 * @return
	 */
	public static String formatArgs(Object... args) {
		String a = "[";
		for (Object o : args) {
			a += o.toString() + ", ";
		}
		a = a.substring(0, a.length() - 2); // removes last ", "
		return a + "]";
	}

}
