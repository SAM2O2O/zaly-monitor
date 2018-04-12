package com.akaxin.zaly.monitor;

import java.util.concurrent.atomic.AtomicLong;

public class ZalyCounter {

	private final AtomicLong count;

	private volatile long prevCount;

	public ZalyCounter() {
		count = new AtomicLong(0);
	}

	public ZalyCounter(int count) {
		this.count = new AtomicLong(count);
	}

	public long inc() {
		return count.incrementAndGet();
	}

	public long inc(long i) {
		return count.addAndGet(i);
	}

	public long dec() {
		return count.decrementAndGet();
	}

	public long dec(long i) {
		return count.getAndAdd(-i);
	}

	/**
	 * reset counter to 0
	 */
	public void clear() {
		count.set(0);
		prevCount = 0;
	}

	public long getCount() {
		return count.get();
	}

	public String getCountString() {
		return String.valueOf(count.get());
	}

	public long getCountChange() {
		long tempCount = count.get();
		long change = tempCount - prevCount;
		prevCount = tempCount;
		return change;
	}
}
