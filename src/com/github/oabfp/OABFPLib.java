package com.github.oabfp;

import static com.github.oabfp.OABFP.eval;
import static com.github.oabfp.OABFP.formatArgs;
import com.github.oabfp.OABFP.M;

/**
 * 
 * Object Array Based Functional Programming Library
 * 
 * @author Benjamin Chi
 * 
 */
public class OABFPLib {
	public static M sum = new M() {
		public Double m(Object... args) {
			double i = 0;
			for (Object o : args) {
				i += (Double) eval(o);
			}
			return i;
		}
	};

	public static M multiply = new M() {
		public Double m(Object... args) {
			double i = 1;
			for (Object o : args) {
				i *= (Double) eval(o);
			}
			return i;
		}
	};

	/*
	 * g (f(x)) = f(x) * f(x)
	 */
	public static M square = new M() {
		public Object m(Object... args) {
			if (args.length != 1)
				throw new RuntimeException("should have 1 argument only");
			Double v = Double.valueOf(eval(args[0]).toString());
			return v * v;
		}
	};

	public static M concatStrs = new OABFP.M() {
		public Object m(Object... args) {
			String s = "";
			for (Object o : args) {
				s += eval(o);
			}
			return s;
		}
	};

	public static M display = new OABFP.M() {
		public Object m(Object... args) {
			String s = eval(args).toString();
			System.out.println(formatArgs(s));
			return s;
		}
	};

}
