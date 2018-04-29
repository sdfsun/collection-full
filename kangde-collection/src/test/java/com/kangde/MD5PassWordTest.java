package com.kangde;

import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.security.util.MD5Util;

public class MD5PassWordTest {
	public static void main(String[] args) {
		//明文密码
		String plaintextPwd = "123";
		String salt = UUIDUtil.UUID32();
		String md5Pwd = MD5Util.md5(plaintextPwd, salt);
		System.out.println(md5Pwd);
		System.out.println(salt);
	}
}
