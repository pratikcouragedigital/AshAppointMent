package com.mobitechs.ashAppointment.webService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebService {

	private static String NAMESPACE = "http://tempuri.org/";
	private static String URL = "http://ash.no-ip.biz/MyService.asmx";
	private static String SOAP_ACTION = "http://tempuri.org/";

	public static String CreateLogin(String mobileNo, String password,String spinnerItemId, String webMethName) {
		String resTxt = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		// Property which holds input parameters
		PropertyInfo celsiusPI = new PropertyInfo();
		// Set Name
		celsiusPI.setName("Username");
		// Set Value
		celsiusPI.setValue(mobileNo);
		// Set dataType
		celsiusPI.setType(String.class);
		// Add the property to request object
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("Password");
		celsiusPI.setValue(password);
		celsiusPI.setType(String.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("DepartmentId");
		celsiusPI.setValue(spinnerItemId);
		celsiusPI.setType(String.class);
		request.addProperty(celsiusPI);

		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			// Invole web service
			androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			// Assign it to fahren static variable
			resTxt = response.toString();

		} catch (Exception e) {
			e.printStackTrace();
			resTxt = "No Network Found";
		}

		return resTxt;
	}

	public static String ShowAttendanceDetailsForParents(String userId, String Type, int month, int year, String webMethName) {

		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		PropertyInfo celsiusPI = new PropertyInfo();
		celsiusPI.setName("UserId");
		celsiusPI.setValue(userId);
		celsiusPI.setType(String.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("Type");
		celsiusPI.setValue(Type);
		celsiusPI.setType(String.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("month");
		celsiusPI.setValue(month);
		celsiusPI.setType(Integer.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("Year");
		celsiusPI.setValue(year);
		celsiusPI.setType(Integer.class);
		request.addProperty(celsiusPI);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();

		} catch (Exception e) {
			e.printStackTrace();
			resTxt = "No Network Found";
		}
		return resTxt;
	}

	public static String GetDepartmentList(String webMethName) {
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE, webMethName);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();

		} catch (Exception e) {
			e.printStackTrace();
			resTxt = "No Network Found";
		}
		return resTxt;
	}

	public static String showDateWise_TotalAppoinment(String userId, String deptType, int selectedDay, int selectedMonth, int selectedYear, String webMethName) {
		String resTxt = null;
		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		PropertyInfo celsiusPI = new PropertyInfo();
		celsiusPI.setName("UserId");
		celsiusPI.setValue(userId);
		celsiusPI.setType(String.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("Type");
		celsiusPI.setValue(deptType);
		celsiusPI.setType(String.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("Date");
		celsiusPI.setValue(selectedDay);
		celsiusPI.setType(Integer.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("month");
		celsiusPI.setValue(selectedMonth);
		celsiusPI.setType(Integer.class);
		request.addProperty(celsiusPI);

		celsiusPI=new PropertyInfo();
		celsiusPI.setName("Year");
		celsiusPI.setValue(selectedYear);
		celsiusPI.setType(Integer.class);
		request.addProperty(celsiusPI);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();

		} catch (Exception e) {
			e.printStackTrace();
			resTxt = "No Network Found";
		}
		return resTxt;
	}
}
