package com.example.testing.utils.android

import android.content.Context

class ResourceComparator {
	fun isEqual (context: Context, resId: Int, value: String): Boolean {
		return context.getString(resId) == value
	}
}