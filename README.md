POCr-POC
========
A proof of concept for POCr, a web application to offer support for designing new applications by generating simple java enterprise applications, compiling them, deploying them and generating documentation for them, based on the choices made in the UI.

This Proof of concept is aimed at generating simple JSF application based on input parameters.
It requires:
- Maven 3.x (and M2_HOME environment variable to be set, pointing on the maven installation)
- Weblogic server (>= 11g) installation.
- Installing wls-maven-plugin (The first 2 steps here would suffice: http://docs.oracle.com/cd/E24329_01/web.1211/e24368/maven.htm#WLPRG587)
It can be tested by running the main method of POCrPOCMain class.

At this moment the following functionalities are explored:
- generating JSF 2 managed bean (using the CodeModel library)
- generating a new POM (using maven-model library)
- generating a web.xml deployment descriptor (using the JAXB classes generated from the Java EE 7 xsds) 
- calling a maven from inside Java code (using maven-invoker library)
- generating facelets (using jdom2)
- generating documents from inside Java code (?)
