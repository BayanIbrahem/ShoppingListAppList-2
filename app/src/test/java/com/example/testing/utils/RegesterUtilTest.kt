package com.example.testing.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegesterUtilTest {

	// user:
	@Test
	fun usernameIsEmpty() {
		val register = RegisterUtil.registerIsValid(
			username = "",
			password = "123",
			confirmedPassword = "123",
		)
		assertThat(register).isFalse()
	}
	@Test
	fun alreadyExistedUser() {
		val register = RegisterUtil.registerIsValid(
			username = "Tim",
			password = "123",
			confirmedPassword = "123",
		)
		assertThat(register).isFalse()
	}
	// password:
	@Test
	fun passwordIsEmpty() {
		val register = RegisterUtil.registerIsValid(
			username = "John",
			password = "",
			confirmedPassword = "",
		)
		assertThat(register).isFalse()
	}
	@Test
	fun confirmedPasswordNotMatch() {
		val register = RegisterUtil.registerIsValid(
			username = "John",
			password = "123",
			confirmedPassword = "abc",
		)
		assertThat(register).isFalse()
	}
	@Test
	fun notValidPassword() {
		val register = RegisterUtil.registerIsValid(
			username = "John",
			password = "2abcd",
			confirmedPassword = "2abcd",
		)
		assertThat(register).isFalse()
	}
	// ok:
	@Test
	fun allOk() {
		val register = RegisterUtil.registerIsValid(
			username = "John",
			password = "123",
			confirmedPassword = "123",
		)
		assertThat(register).isTrue()
	}
}