package com.tk.cn.utils.xml;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXML {
	public static void main(String[] args) {
		InputStream is = ReadXML.class.getClassLoader().getResourceAsStream("/readXml.xml");
		// System.out.println(is);
		SAXReader sr = new SAXReader();
		sr.setValidation(true);
		Document doc = null;
		try {
			doc = sr.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		// System.out.println(root.getName());
		
		@SuppressWarnings("unchecked")
		List<Element> elments = (List<Element>) root.elements();
		Element action = elments.get(0).element("action");
		System.out.println(action.getName());
	}
}
