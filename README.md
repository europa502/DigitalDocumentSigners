# DigitalDocumentSigners

This repository contains pkcs11 text signer as well as pcks12 pdf signer.

## pkcs11wrapper- 

IAIK PKCS#11 Wrapper for Java, Version 1.3


The PKCS#11 API is specified in the ANSI-C programming 
language. This library maps the complete PKCS#11 API to 
an equivalent Java API in a straight forward style. 
This allows to access PKCS#11 modules from Java.

It does not contain a JCA/JCE provider implementation. 
This means that the PKCS#11 Wrapper alone is not 
compatible with the Java cryptographic APIs like JCA 
and JCE.
There is a different product which provides this - the 
IAIK PKCS#11 Provider. 

The current version of this package is available from http://jce.iaik.tugraz.at/download/

After the installation has finished use your favorite 
browser to view the Readme.html for further information.


Your SIC/IAIK JavaSecurity Team
___________________________________________________________________________________________________________________________________


Pkcs11wrapper.rar contains pkcs11 java cli and applet signer.
Go to \Pkcs11Wrapper\examples\src\demo\pkcs\pkcs11\wrapper\signatures\SignRawData.java for the souce code.
Go to \Pkcs11Wrapper\examples\src\demo\pkcs\pkcs11\wrapper\signatures\as\ for the applet source code, class file and its signed jar file.

**Usage-**

Go to \Pkcs11Wrapper\examples\ and open index.html.

PS- None of latest versions of firefox/chrome/safari/IE supports applets. please install the applet supported versions of respective browsers to execute the program correctly. You must also need to plug in the smart card that supports pkcs11. 

## Pkcs 12 PDF Signer

pkcs 12 pdf signer is a JS based pkcs 12 signer. 

**Usage-**

Open the sign2.html and eneter the data. Also provide the path to your p12 cerificate in your system. click "Sign Document" and it will download a signed pdf.  
