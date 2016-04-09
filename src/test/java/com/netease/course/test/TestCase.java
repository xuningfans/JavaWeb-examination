package com.netease.course.test;

import org.junit.Test;

import junit.framework.Assert;

public class TestCase {

	@Test
	public void test() {
		
		String requestURI = "/font/account";
		Assert.assertTrue(!(requestURI.startsWith("/font/") || requestURI.startsWith("/back/")));
	}

}
