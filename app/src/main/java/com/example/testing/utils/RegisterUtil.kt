package com.example.testing.utils

object RegisterUtil {

	private val users = mutableListOf("Paul", "Carl", "Tim")

	/**
	 * registration is valid if:
	 *    username is empty
	 *    password is empty
	 *    confirmedPassword not equal password
	 *    user already exists
	 *    password is less than 2 letters.
	 */
	fun registerIsValid (
		username: String,
		password: String,
		confirmedPassword: String,
	): Boolean {
		val userValidation = checkUsername (username)
		val passwordValidation = checkPasswordValidation (password, confirmedPassword)
		return userValidation and passwordValidation
	}
	private fun checkUsername (username: String): Boolean{
		if (username.isEmpty()) {
			return false
		}
		if (users.contains(username)) {
			return false
		}
		return true
	}
	private fun checkPasswordValidation (pw: String, confirm: String): Boolean {
		if (pw.isEmpty()) {
			return false
		}
		if (pw != confirm) {
			return false
		}
		if ( pw.count{ it.isDigit() } < 2 ) {
			return false
		}
		return true
	}
}