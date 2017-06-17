package com.tk.commons.xml;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * 
 * @ClassName: XPPParser
 * @Description: ( XPPParser解析)
 * @author 唐阔
 * @date 2017年6月4日 下午1:47:02
 * 
 */
public class XPPParser
{
	private final static Log LOG = LogFactory.getLog(XPPParser.class);

	private static XmlPullParserFactory parserFactory = null;

	static
	{
		try
		{
			parserFactory = XmlPullParserFactory.newInstance();
			parserFactory.setNamespaceAware(true);
		} catch (XmlPullParserException e)
		{
			LOG.error("XPPParser initialize error!!");
		}
	}

	/**
	 * 解析XML格式留，获取所有子元素名和值
	 * 
	 * @param reader
	 * @return name和value值
	 */
	public static Map<String, String> parse(Reader reader)
	{
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			XmlPullParser xpp = parserFactory.newPullParser();
			xpp.setInput(reader);

			int eventType = xpp.getEventType();
			String currentTag = null;
			while (eventType != XmlPullParser.END_DOCUMENT)
			{
				if (eventType == XmlPullParser.START_TAG)
				{
					currentTag = xpp.getName();
				} else if (eventType == XmlPullParser.TEXT)
				{
					map.put(currentTag, xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (Exception ex)
		{
			LOG.error("xml parse exception.", ex);
		}

		return map;
	}

	public static Map<String, String> parse(String strXML)
	{
		return parse(new StringReader(strXML));
	}

}
