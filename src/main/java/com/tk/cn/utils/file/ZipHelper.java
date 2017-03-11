package com.tk.cn.utils.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipHelper {
	private final static int CacheSize = 1024;

	public static byte[] zipByte(byte[] data) {
		Deflater compresser = new Deflater();
		compresser.reset();
		compresser.setInput(data);
		compresser.finish();
		byte result[] = new byte[0];
		ByteArrayOutputStream o = new ByteArrayOutputStream(1);

		try {
			byte[] buf = new byte[CacheSize];
			int got = 0;
			while (!compresser.finished()) {
				got = compresser.deflate(buf);
				o.write(buf, 0, got);
			}
			result = o.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			compresser.end();
		}
		return result;
	}

	public static byte[] zipString(String data) {
		byte[] input = new byte[0];
		try {
			input = data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		byte[] result = ZipHelper.zipByte(input);
		return result;
	}

	public static String zipString1(String data) {
		byte[] input = new byte[0];
		String outputString = "";
		try {
			input = data.getBytes("UTF-8");
			byte[] result = ZipHelper.zipByte(input);
			outputString = new String(result, 0, result.length, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		return outputString;
	}

	public static byte[] unZipByte(byte[] data) {
		Inflater decompresser = new Inflater();
		decompresser.setInput(data);
		byte result[] = new byte[0];
		ByteArrayOutputStream o = new ByteArrayOutputStream(1);

		try {
			byte[] buf = new byte[CacheSize];
			int got = 0;
			while (!decompresser.finished()) {
				got = decompresser.inflate(buf);
				o.write(buf, 0, got);
			}
			result = o.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			decompresser.end();
		}
		return result;
	}

	public static String unZipByteToString(byte[] data) {
		byte[] result = unZipByte(data);
		String outputString = null;
		try {
			outputString = new String(result, 0, result.length, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outputString;
	}

	public static String unZipStringToString(String data) {

		String outputString = null;
		try {
			byte[] result = unZipByte(data.getBytes("UTF-8"));
			outputString = new String(result, 0, result.length, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outputString;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "19,˼é,SM ,378,378,0,1,1@23,����,WW ,75,75,0,1,1@04,������,DAX,125,125,0,1,1@04,����,DF ,126,126,0,1,1@10,����,JX ,173,173,0,1,1@25,����,YC ,180,180,0,1,1@02,��";
		byte[] b = zipString(str);
		try {
			String str0 = new String(b, 0, b.length, "UTF-8");
			System.out.println(str0);
			String str1 = unZipByteToString(str0.getBytes("UTF-8"));
		} catch (Exception e) {
		}

		// System.out.println(str1);
		// String str1=zipString1(str);
		// System.out.println(str1);

		// System.out.println(unZipStringToString(str1));
	}

}
