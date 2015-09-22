package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import core.utils.MD5Util;
import base.BaseControllerTest;

/**

 *  create by Liujishuai on 2015年9月22日

 */
public class commonTest extends BaseControllerTest {
    
	@Test
	public void testMD5(){
		String passwordString="123456";
	     String sk=	MD5Util.getMD5(passwordString.getBytes());
	     System.out.println(sk);
		
	}
}

