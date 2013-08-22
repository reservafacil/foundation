package com.google.gwt.jso;

import java.util.Iterator;

import com.google.gwt.core.client.*;

public class JSIterable {

	public static <J extends JavaScriptObject> Iterable<J> from(JsArray<J> values) {
		return new JSObjectIterable<J>(values);
	}

	public static Iterable<Boolean> from(JsArrayBoolean values) {
		return new BooleanIterable(values);
	}

	public static Iterable<Integer> from(JsArrayInteger values) {
		return new IntegerIterable(values);
	}

	public static Iterable<Number> from(JsArrayNumber values) {
		return new NumberIterable(values);
	}

	public static Iterable<String> from(JsArrayString values) {
		return new StringIterable(values);
	}

	static class BooleanIterable
	    extends AbstractIterable<Boolean> {

		private JsArrayBoolean values;

		BooleanIterable(JsArrayBoolean values) {
			super(values.length());
			this.values = values;
		}

		@Override
		public Boolean next() {
			return this.values.get(this.doNext());
		}
	}

	static class IntegerIterable
	    extends AbstractIterable<Integer> {

		private JsArrayInteger values;

		IntegerIterable(JsArrayInteger values) {
			super(values.length());
			this.values = values;
		}

		@Override
		public Integer next() {
			return this.values.get(this.doNext());
		}
	}

	static class NumberIterable
	    extends AbstractIterable<Number> {

		private JsArrayNumber values;

		NumberIterable(JsArrayNumber values) {
			super(values.length());
			this.values = values;
		}

		@Override
		public Number next() {
			return this.values.get(this.doNext());
		}
	}

	static class StringIterable
	    extends AbstractIterable<String> {

		private JsArrayString values;

		StringIterable(JsArrayString values) {
			super(values.length());
			this.values = values;
		}

		@Override
		public String next() {
			return this.values.get(this.doNext());
		}
	}

	static class JSObjectIterable<J extends JavaScriptObject>
	    extends AbstractIterable<J> {

		private JsArray<J> values;

		JSObjectIterable(JsArray<J> values) {
			super(values.length());
			this.values = values;
		}

		@Override
		public J next() {
			return this.values.get(this.doNext());
		}
	}

	static abstract class AbstractIterable<T>
	    implements Iterable<T>, Iterator<T> {

		private int index;

		private int length;

		AbstractIterable(int length) {
			this.length = length;
		}

		int doNext() {
			return this.index++;
		}

		int index() {
			return this.index;
		}

		@Override
		public Iterator<T> iterator() {
			this.index = 0;
			return this;
		}

		@Override
		public boolean hasNext() {
			boolean hasNext = this.index < this.length;
			if (!hasNext) {
				this.index = 0;
			}

			return hasNext;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove for JSArrays is not supported");
		}
	}
}
