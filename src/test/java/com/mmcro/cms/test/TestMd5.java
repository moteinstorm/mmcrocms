package com.mmcro.cms.test;

import org.junit.Test;

import com.mmcro.utils.Md5Utils;

//import com.mmcro.utils.




public class TestMd5 {
	
	@Test
	public void  testMd5() {
		String s = "wode mima";
		String m = Md5Utils.md5(s);
		System.out.println("密文是 " + m);
		
	}

}
