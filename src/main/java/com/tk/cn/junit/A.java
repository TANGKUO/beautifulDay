package com.tk.cn.junit;

import org.junit.Test;

public class A {
	@Test
	public void a() {
		assertThat(3, is(1));
	}

	private void assertThat(int i, Object object) {
	}

	private Object is(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void b() {
		assertThat(3, not(1));
	}

	private Object not(int i) {
		return null;
	}

}
