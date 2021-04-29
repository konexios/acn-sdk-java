/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Util {
	public static final int BUFFER_SIZE = 8192;

	public static byte[] calcMD5Checksum(Path path) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		try (SeekableByteChannel sbc = Files.newByteChannel(path)) {
			ByteBuffer buf = ByteBuffer.allocateDirect(BUFFER_SIZE);
			while (sbc.read(buf) > 0) {
				buf.flip();
				md.update(buf);
				buf.clear();
			}
		}
		return md.digest();
	}

	public static byte[] calcMD5Checksum(File file) throws NoSuchAlgorithmException, IOException {
		return calcMD5Checksum(file.toPath());
	}

	public static byte[] calcMD5Checksum(String pathname) throws NoSuchAlgorithmException, IOException {
		return calcMD5Checksum(new File(pathname));
	}

	public static String calcMD5ChecksumString(Path path) throws NoSuchAlgorithmException, IOException {
		return Hex.encodeHexString(calcMD5Checksum(path));
	}

	public static String calcMD5ChecksumString(File file) throws NoSuchAlgorithmException, IOException {
		return Hex.encodeHexString(calcMD5Checksum(file));
	}

	public static String calcMD5ChecksumString(String pathname) throws NoSuchAlgorithmException, IOException {
		return Hex.encodeHexString(calcMD5Checksum(pathname));
	}
}
