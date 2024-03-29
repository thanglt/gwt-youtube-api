package com.google.gdata.util.common.base;

import java.util.HashMap;
import java.util.Map;

public class CharEscaperBuilder {
	/**
	 * Simple decorator that turns an array of replacement char[]s into a CharEscaper, this results in a very fast
	 * escape method.
	 */
	private static class CharArrayDecorator extends CharEscaper {

		private final char[][] replacements;
		private final int replaceLength;

		public CharArrayDecorator(char[][] replacements) {
			this.replacements = replacements;
			this.replaceLength = replacements.length;
		}

		/*
		 * Overriding escape method to be slightly faster for this decorator. We test the replacements array directly,
		 * saving a method call.
		 */
		@Override
		public String escape(String s) {
			int slen = s.length();
			for (int index = 0; index < slen; index++) {
				char c = s.charAt(index);
				if (c < replacements.length && replacements[c] != null) {
					return escapeSlow(s, index);
				}
			}
			return s;
		}

		@Override
		protected char[] escape(char c) {
			return c < replaceLength ? replacements[c] : null;
		}
	}

	// Replacement mappings.
	private final Map<Character, String> map;

	// The highest index we've seen so far.
	private int max = -1;

	/**
	 * Construct a new sparse array builder.
	 */
	public CharEscaperBuilder() {
		this.map = new HashMap<Character, String>();
	}

	/**
	 * Add a new mapping from an index to an object to the escaping.
	 */
	public CharEscaperBuilder addEscape(char c, String r) {
		map.put(c, r);
		if (c > max) {
			max = c;
		}
		return this;
	}

	/**
	 * Add multiple mappings at once for a particular index.
	 */
	public CharEscaperBuilder addEscapes(char[] cs, String r) {
		for (char c : cs) {
			addEscape(c, r);
		}
		return this;
	}

	/**
	 * Convert this builder into an array of char[]s where the maximum index is the value of the highest character that
	 * has been seen. The array will be sparse in the sense that any unseen index will default to null.
	 * 
	 * @return a "sparse" array that holds the replacement mappings.
	 */
	public char[][] toArray() {
		char[][] result = new char[max + 1][];
		for (Map.Entry<Character, String> entry : map.entrySet()) {
			result[entry.getKey()] = entry.getValue().toCharArray();
		}
		return result;
	}

	/**
	 * Convert this builder into a char escaper which is just a decorator around the underlying array of replacement
	 * char[]s.
	 * 
	 * @return an escaper that escapes based on the underlying array.
	 */
	public CharEscaper toEscaper() {
		return new CharArrayDecorator(toArray());
	}

}
