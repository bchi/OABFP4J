package com.github.oabfp.test;

import static com.github.oabfp.OABFP.eval;
import static com.github.oabfp.OABFPLib.display;

import com.github.oabfp.OABFP.Fn;

public class TestHOF2 extends Object {

	public static void main(String[] args) throws Exception {

		/*
		 * f(x) = x + 3
		 */
		final Fn xPlus3 = new Fn() {
			public Object f(Object... args) {
				return (Integer)eval(args[0]) + 3;
			}
		};
		
		/*
		 * g (function, x) = f(x) * f(x)
		 */
		final Fn x2 = new Fn() {
			public Object f(Object... args) {
				return (Integer)eval(args[0]) * (Integer)eval(args[0]);
			}
		};
		
		eval(display, xPlus3, 1); // = 4
		
		// same as above but declared anonymously
		//println(eval(new M() {public Object m(Object... args) {return (Integer)eval(args[0]) + 3;}}, 1)); // = 4
		
		// g (f(x), x) = f(x) * f(x)
		eval(display, x2, 2); // = x2 = xPlus3 + 2 = 5; 5 * 5 = 25
		
		// g (f(x), x) = f(x) * f(x)
		//println(eval(new M() {public Object m(Object... args) {return (Integer)eval(xPlus3, 2) * (Integer)eval(xPlus3, 2);}}, 1)); // = 4
		
		// eval(xPlus3, 3) = 3 + 3 = 6
		// 6 * 6 = 36
		eval(display, x2, eval(xPlus3, 3)); // = 4
		
		// lazy evaluation
		eval(display, x2, new Object[] {xPlus3, 3});
		
		Object[] formula = {x2, new Object[] {xPlus3, 3}};
		
		eval(display, formula);
	}

}
