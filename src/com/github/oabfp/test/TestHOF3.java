package com.github.oabfp.test;

import static com.github.oabfp.OABFP.eval;
import static com.github.oabfp.OABFPLib.square;
import static com.github.oabfp.OABFPLib.display;

import com.github.oabfp.OABFP.*;

public class TestHOF3 extends Object {

	static long time = new java.util.Date().getTime();

	public static void main(String[] args) throws Exception {

		/*
		 * f(x) = x + 3
		 */
		final F1 xPlus3 = new F1() {
			public Object f(Object arg) {
				return Double.parseDouble(eval(arg).toString()) + 3;
			}
		};

		final F0 getTime = new F0() {
			public Object f() {
				return new java.util.Date().getTime() - time;
			}
		};

		// currying
		final F1 xPlus3squareY = new F1() {
			public Object f(Object arg) {
				return new Object[] { xPlus3, new Object[] { square, arg } };
			}
		};

		Object[] f1 = { xPlus3, 1 }; // 1 + 3 = 4
		Object[] f2 = { square, 3 }; // 3 * 3 = 9
		Object[] f3 = { square, new Object[] { xPlus3, 3 } }; // (3+3) *
		// (3+3) =
		// 36
		Object[] f4 = { xPlus3, 3 };
		Object[] f5 = { square, f4 };
		//Object[] forumla6 = { square, formula4, formula4 };

		eval(display, f1);
		eval(display, f2);
		eval(display, f3);
		eval(display, f4);
		eval(display, f5);
		// println(eval(forumla6)); // error.
		eval(display, xPlus3squareY, 5); // (5^2=25) + 3 = 28
		//((Object[]) eval()).length);
		//eval(display, eval());
		

		// dynamic
		eval(display, "dynamic eval...");
		Thread.sleep(500);
		eval(display, getTime);
		Thread.sleep(500);
		eval(display, getTime);

		Thread.sleep(500);

		Test t = new Test(new Object[] { getTime });
		t.showData();
		Thread.sleep(500);
		t.showData();

		Thread.sleep(500);

		// static
		eval(display, "static call...");
		Test t2 = new Test(new Object[] { eval(getTime) });
		t2.showData();
		Thread.sleep(500);
		t2.showData();
	}

	static class Test {
		Object[] f;

		public Test(Object... f) {
			this.f = f;
		}

		public void showData() {
			eval(display, f);
		}
	};

}
