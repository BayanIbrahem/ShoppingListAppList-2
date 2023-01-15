package com.example.testing.utils

object SecondUtil {

	/**
	 * fib function gives values:
	 * fib(0) = 0
	 * fib(1) = 1
	 * fib(n) = fib(n-1) + fib(n-2)
	 */
	fun fib (n: Int): Long {
		if (n == 0 || n == 1) {
			return n.toLong()
		}
		var a = 0L
		var b = 1L
		var c = 1L
		for (i in (1..n - 2)) {
			c = a + b
			a = b
			b = c
		}
		return c
	}

	/**
	 * checkBraces will gives false when:
	 * ...open braces not equal closed ones.
	 * ...there is a closed bracket before open one.
	 * ...there is open bracket without closing.
	 */
	fun checkBraces (string: String): Boolean {
		return string.count {it == '('} == string.count {it == ')'}
	}
}