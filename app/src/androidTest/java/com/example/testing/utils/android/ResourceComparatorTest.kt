package com.example.testing.utils.android

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import org.junit.*

class ResourceComparatorTest {
	// must not init here, all test units must be independent from each other.
	private lateinit var comparator: ResourceComparator

	// contains logic that executes before each test
	@Before
	fun setup() {
		comparator = ResourceComparator()
	}
	// contains logic that executes after each test eg: closing room database.
	@After
	fun taildown() {

	}

	// test equality state:
	@Test
	fun valueEqualsResource_mustTrue() {
		val context = ApplicationProvider.getApplicationContext<Context>()
		val resId = com.example.testing.R.string.app_name
		val result = comparator.isEqual(context, resId, "Testing")
		Truth.assertThat(result).isTrue()
	}
	// test difference state:
	@Test
	fun valueNotEqualResource_mustFalse() {
		val context = ApplicationProvider.getApplicationContext<Context>()
		val resId = com.example.testing.R.string.app_name
		val result = comparator.isEqual(context, resId, "Not Testing")
		Truth.assertThat(result).isFalse()
	}
}