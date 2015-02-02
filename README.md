POCr-POC
========
A proof of concept for POCr, a web application to offer support for designing new applications by generating simple java enterprise applications, compiling them, deploying them and generating documentation for them, based on the choices made in the UI.

This Proof of concept is aimed at generating simple JSF application based on input parameters.
It requires a Weblogic server (>= 11g) installation. 
It can be tested by running the main method of POCrPOCMain class.

At this moment the following functionalities are explored:
- generating JSF 2 managed bean (using the CodeModel library)
- generating a new POM (using maven-model library)
- generating a web.xml deployment descriptor (using the JAXB classes generated from the Java EE 7 xsds) 
- calling a maven from inside Java code (using maven-invoker library)
- generating facelets 
- generating documents from inside Java code (?)
