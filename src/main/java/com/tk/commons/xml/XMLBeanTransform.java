package com.tk.commons.xml;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @ClassName: XMLBeanTransform
 * @Description: (XML转换工具类)
 * @author 唐阔   
 * @date 2017年6月4日 下午1:22:34
 * 
 */
public class XMLBeanTransform
{
	private static Log LOG = LogFactory.getLog(XMLBeanTransform.class);

	public static Object transformXML2Object(InputStream ins, Class<?> clasz)
	{
		JAXBContext context = null;
		Unmarshaller ums = null;
		try
		{
			context = JAXBContext.newInstance(clasz.getPackage().getName());
			ums = context.createUnmarshaller();
			ums.setEventHandler(new XLSValidationEventHandler());
			return ums.unmarshal(ins);
		} catch (JAXBException ex)
		{
			String errorMsg = new StringBuilder().append("transform xml to Bean error,class name:")
					.append(clasz.getName()).toString();
			LOG.error(errorMsg, ex);
			throw new XMLTransformException(errorMsg, ex);
		}
	}

	public static Object transformXML2Object(InputStream ins, Class<?> clasz, boolean closeIns)
	{
		JAXBContext context = null;
		Unmarshaller ums = null;
		try
		{
			context = JAXBContext.newInstance(clasz.getPackage().getName());
			ums = context.createUnmarshaller();
			ums.setEventHandler(new XLSValidationEventHandler());
			return ums.unmarshal(ins);
		} catch (JAXBException ex)
		{
			String errorMsg = new StringBuilder().append("transform xml to Bean error,class name:")
					.append(clasz.getName()).toString();
			LOG.error(errorMsg, ex);
			throw new XMLTransformException(errorMsg, ex);
		} finally
		{
			if (closeIns && (ins != null))
			{
				try
				{
					ins.close();
				} catch (Exception ex)
				{
				}
			}
		}
	}

	public static void transformObject2XML(Object object, OutputStream ous)
	{
		JAXBContext context = null;
		Marshaller ms = null;
		try
		{
			context = JAXBContext.newInstance(object.getClass().getPackage().getName());
			ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(object, ous);
		} catch (JAXBException ex)
		{
			String errorMsg = new StringBuilder().append("transform object to XML error,object:")
					.append(object.toString()).toString();
			LOG.error(errorMsg, ex);
			throw new XMLTransformException(errorMsg, ex);
		}
	}

	public static void transformObject2XML(Object object, OutputStream ous, boolean closeOus)
	{
		JAXBContext context = null;
		Marshaller ms = null;
		try
		{
			context = JAXBContext.newInstance(object.getClass().getPackage().getName());
			ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(object, ous);
		} catch (JAXBException ex)
		{
			String errorMsg = new StringBuilder().append("transform object to XML error,object:")
					.append(object.toString()).toString();
			LOG.error(errorMsg, ex);
			throw new XMLTransformException(errorMsg, ex);
		} finally
		{
			if (closeOus && (ous != null))
			{
				try
				{
					ous.close();
				} catch (Exception e)
				{
				}
			}
		}
	}

	public static void transformObject2XML(Object object, OutputStream ous, String encoding)
	{
		JAXBContext context = null;
		Marshaller ms = null;
		try
		{
			context = JAXBContext.newInstance(object.getClass().getPackage().getName());
			ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_ENCODING, encoding);
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(object, ous);
		} catch (JAXBException ex)
		{
			String errorMsg = new StringBuilder().append("transform object to XML error,encoding:").append(encoding)
					.append(" object:").append(object.toString()).toString();
			LOG.error(errorMsg, ex);
			throw new XMLTransformException(errorMsg, ex);
		}
	}

	protected static class XLSValidationEventHandler implements ValidationEventHandler
	{

		public boolean handleEvent(ValidationEvent event)
		{
			if (event.getSeverity() > ValidationEvent.WARNING)
			{
				// LOG.fatal(validationEvent.getMessage());
				return false;
			}
			return true; // continue unmarshalling
		}
	}

}
