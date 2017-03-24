package com.dark.guava.concurrent;

import static com.google.common.util.concurrent.Service.State.FAILED;
import static com.google.common.util.concurrent.Service.State.NEW;
import static com.google.common.util.concurrent.Service.State.RUNNING;
import static com.google.common.util.concurrent.Service.State.STARTING;
import static com.google.common.util.concurrent.Service.State.STOPPING;
import static com.google.common.util.concurrent.Service.State.TERMINATED;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit tests for {@link Service}
 */
public class ServiceTest {

	/**
	 * Assert on the comparison ordering of the State enum since we guarantee
	 * it.
	 */
	@Test
	public void testStateOrdering() {
		// List every valid (direct) state transition.
		assertLessThan(NEW, STARTING);
		assertLessThan(NEW, TERMINATED);

		assertLessThan(STARTING, RUNNING);
		assertLessThan(STARTING, STOPPING);
		assertLessThan(STARTING, FAILED);

		assertLessThan(RUNNING, STOPPING);
		assertLessThan(RUNNING, FAILED);

		assertLessThan(STOPPING, FAILED);
		assertLessThan(STOPPING, TERMINATED);
	}
	
	
	private static <T extends Comparable<? super T>> void assertLessThan(T a, T b) {
		if (a.compareTo(b) >= 0) {
			fail(String.format("Expected %s to be less than %s", a, b));
		}
	}
}