package com.supportsys.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SecurityUtilities {


	public String makeSha1(String data)
	{
		String sha1ToUser = "";

		try {

			MessageDigest crypt = MessageDigest.getInstance("sha1");
			crypt.reset();
			crypt.update(data.getBytes("utf8"));
			sha1ToUser = String.format("%40x", new BigInteger(1,crypt.digest()));

			return sha1ToUser;

		} catch (Exception e) {
			System.out.println("Error:: " + e);
			return null;
		}
	}
}
