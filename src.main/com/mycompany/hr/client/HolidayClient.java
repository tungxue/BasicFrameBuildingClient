/**
 * 
 */
package com.mycompany.hr.client;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.ResourceSource;
import org.springframework.xml.transform.StringResult;

/**
 * @author TUNG
 * 
 */
public class HolidayClient {
	private static final String MESSAGE = "<message xmlns=\"http://tempuri.org\">Hello Web Service World</message>";

	private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
	private Resource request;

	public void setDefaultUri(String defaultUri) {
		webServiceTemplate.setDefaultUri(defaultUri);
	}

	public void setRequest(Resource request) {
		this.request = request;
	}

	// send to the configured default URI
	public void simpleSendAndReceive() {
		Source requestSource;
		try {
			requestSource = new ResourceSource(request);
			StringResult result = new StringResult();
			webServiceTemplate.sendSourceAndReceiveToResult(requestSource,
					result);
			// 由于服务端没有返回，所以此处没有返回
			System.out.println("tungxue" + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// send to an explicit URI
	public void customSendAndReceive() {
		StreamSource source = new StreamSource(new StringReader(MESSAGE));
		StreamResult result = new StreamResult(System.out);
		webServiceTemplate.sendSourceAndReceiveToResult(
				"http://localhost:8080/AnotherWebService", source, result);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HolidayClient.class);
		HolidayClient holidayClient = (HolidayClient) applicationContext
				.getBean("holidayClient");
		holidayClient.simpleSendAndReceive();
	}
}