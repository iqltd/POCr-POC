POCr-POC
========
A proof of concept for POCr, a web application which will be able to generate simple java enterprise applications, compile them, deploy them and generate documentation for them, based on the choices made in the UI.

At this moment the following functionalities are explored:
- generating JSF 2 managed bean (using the CodeModel library)
- generating a new POM (using maven-model library)
- generating a web.xml deployment descriptor (using the JAXB classes generated from the Java EE 7 xsds) 
- calling a maven from inside Java code (using maven-invoker library)
- generating facelets (?)
- generating documents from inside Java code (?)
