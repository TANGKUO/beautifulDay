package com.tk.cn.junit;

import org.junit.Test;

public class B {
	@Test
	public void c() {
		assertThat(3, greaterThan(1));
	}

	private void assertThat(int i, Object greaterThan) {
	}

	private Object greaterThan(int i) {
		return null;
	}

	@Test
	public void d() {
		assertThat(3, lessThan(1));
	}

	private Object lessThan(int i) {
		return null;
	}

}
