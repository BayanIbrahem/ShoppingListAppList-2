package com.example.testing.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class SecondUtilTest {
	//fib:
	@Test
	fun fib0 () {
		val fib0 = SecondUtil.fib(0)
		assertThat (fib0).isEqualTo(0)
	}
	@Test
	fun fib1 () {
		val fib1 = SecondUtil.fib(1)
		assertThat(fib1).isEqualTo(1)
	}
	@Test
	fun randFib() {
		val index = 3
		val randFib = SecondUtil.fib(index)
		val preRandFib = SecondUtil.fib(index - 1)
		val prePreRandFib = SecondUtil.fib(index - 2)
		assertThat(randFib).isEqualTo(preRandFib + prePreRandFib)
	}

	//braces:
	@Test
	fun notEqualOpenAndClosedBraces() {
		val string = "(((()))"
		val check = SecondUtil.checkBraces(string)
		assertThat(check).isFalse()
	}
	@Test
	fun closedBracketAtFirst() {
		val string = ")(((()))"
		val check = SecondUtil.checkBraces(string)
		assertThat(check).isFalse()
	}
	@Test
	fun openBracketAtLast() {
		val string = "(((()))))("
		val check = SecondUtil.checkBraces(string)
		assertThat(check).isFalse()
	}
	@Test
	fun allOk() {
		val string = "()(())((()))()()()(())"
		val check = SecondUtil.checkBraces(string)
		assertThat(check).isTrue()
	}
}