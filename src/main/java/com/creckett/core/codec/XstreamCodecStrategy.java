package com.creckett.core.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

import com.creckett.core.exception.TechnicalException;

/**
 * Concrete CodecStrategy based on Xstream implementation
 * @author vkarmani
 *
 */

public class XstreamCodecStrategy implements CodecStrategy  {

	private Marshaller marshaller;
	
	private Unmarshaller unmarshaller;
		

	@Override
	public String encode(Object objectToEncode) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(byteArrayOutputStream);
		try {
			marshaller.marshal(objectToEncode, streamResult);
			return new String(byteArrayOutputStream.toByteArray());
		} catch (XmlMappingException e) {
			throw new TechnicalException(e);
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
		
	}


	public Marshaller getMarshaller() {
		return marshaller;
	}


	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}


	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}


	@Override
	public Object decode(Object objectToDecode) {
		String objectStr = (String) objectToDecode;
		StreamSource streamSource = new StreamSource(new ByteArrayInputStream(objectStr.getBytes()));
		try {
			return unmarshaller.unmarshal(streamSource);
		} catch (XmlMappingException e) {
			throw new TechnicalException(e);
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
		
	}

	
	


}
