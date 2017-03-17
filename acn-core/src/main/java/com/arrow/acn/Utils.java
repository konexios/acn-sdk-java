/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.arrow.acs.Loggable;

public class Utils {
	private static Loggable logger = new Loggable(Utils.class.getName()) {
	};

	public static byte[] gzip(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return gzip(str.getBytes(StandardCharsets.UTF_8));
	}

	public static byte[] gzip(byte[] input) {
		String method = "gzip";
		if (input == null || input.length == 0) {
			return input;
		}
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gos = null;
		try {
			bos = new ByteArrayOutputStream();
			gos = new GZIPOutputStream(bos);
			gos.write(input);
			gos.close();
			bos.close();
			byte[] output = bos.toByteArray();
			logger.logDebug(method, "efficiency: %d ---> %d", input.length, output.length);
			return output;
		} catch (Throwable t) {
			throw new RuntimeException("gzip error", t);
		} finally {
			if (gos != null) {
				try {
					gos.close();
				} catch (Exception e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
