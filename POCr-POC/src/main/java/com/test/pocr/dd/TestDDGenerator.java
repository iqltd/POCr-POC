package com.test.pocr.dd;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jcp.xmlns.xml.ns.javaee.FullyQualifiedClassType;
import org.jcp.xmlns.xml.ns.javaee.ObjectFactory;
import org.jcp.xmlns.xml.ns.javaee.ServletNameType;
import org.jcp.xmlns.xml.ns.javaee.ServletType;
import org.jcp.xmlns.xml.ns.javaee.WebAppType;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class TestDDGenerator {

	public static void main(String[] args) {
			generateDeploymentDescriptor();

	}

	private static String FIELD = "camp";


	public static void generateDeploymentDescriptor() {
		try {

			WebAppType web = new WebAppType();
			ObjectFactory factory = new ObjectFactory();
			web.setId("aidii");
			List<JAXBElement<?>> list = web.getModuleNameOrDescriptionAndDisplayName();
			ServletType servlet = factory.createServletType();
			ServletNameType nameType = new ServletNameType();
			nameType.setValue("FacesServlet");
			servlet.setServletName(nameType );
			
			FullyQualifiedClassType classType = new FullyQualifiedClassType();
			classType.setValue("");
			servlet.setServletClass(classType );
			
			JAXBElement<ServletType> servletJaxb = factory.createWebAppTypeServlet(servlet);
					
			list.add(servletJaxb);

			// create JAXB context and initializing Marshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(WebAppType.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// for getting nice formatted output
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			// specify the location and name of xml file to be created
			File XMLfile = new File("./src/main/resources/web.xml");

			// Writing to XML file
			jaxbMarshaller.marshal(web, XMLfile);
			// Writing to console
			jaxbMarshaller.marshal(web, System.out);

		} catch (JAXBException e) {
			// some exception occured
			e.printStackTrace();
		}
	}
}
