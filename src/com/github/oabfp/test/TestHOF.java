package com.github.oabfp.test;

import static com.github.oabfp.OABFP.eval;
import static com.github.oabfp.OABFPLib.concatStrs;
import static com.github.oabfp.OABFPLib.multiply;
import static com.github.oabfp.OABFPLib.sum;
import static com.github.oabfp.OABFPLib.display;

import com.github.oabfp.OABFP.M;

public class TestHOF extends Object {

	public static String test3() {
		return "Test3";
	}

	public static void main(String[] args) throws Exception {

		M m3 = new M() {
			public Object m(Object... args) {
				return test3();
			}
		};

		M m4 = new M() {
			public Object[] m(Object... args) {
				if (args.length != 0) throw new RuntimeException("arguments not allowed.");
				return new Object[] { sum, 2.0, 3.0, 4.0,
						new Object[] { multiply, 10.0, 10.0 } };
			}
		};

		eval(display, "Test1");

		eval(display, new M() {
			public Object m(Object... args) {
				return "Test2";
			}
		});

		eval(display, new M() {
			public Object m(Object... args) {
				return test3();
			}
		});

		eval(display, "A");

		eval(display, concatStrs, "A", "B", "C");

		eval(display, sum, 1.0, 2.0, 3.0);

		eval(display, concatStrs, "A", "B", sum, 1.0, 2.0);

		eval(display, concatStrs, "A", "B", eval(sum, 1.0, 2.0));

		eval(display, concatStrs, "A", "B", m3, eval(sum, 1.0, 2.0));

		Object[] f = { concatStrs, "a", "b", m3, "--",
				new Object[] { sum, 1.0, 2.0 } };

		eval(display, f);

		eval(display, m4);

		//eval(display, m4, 100.0); // error.

		eval(display, sum, m4, 100.0);
	}

}
