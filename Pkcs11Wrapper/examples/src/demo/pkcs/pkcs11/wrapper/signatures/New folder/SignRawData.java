// Copyright (c) 2002 Graz University of Technology. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without modification,
// are permitted provided that the following conditions are met:
// 
// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
// 
// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
// 
// 3. The end-user documentation included with the redistribution, if any, must
//    include the following acknowledgment:
// 
//    "This product includes software developed by IAIK of Graz University of
//     Technology."
// 
//    Alternately, this acknowledgment may appear in the software itself, if and
//    wherever such third-party acknowledgments normally appear.
// 
// 4. The names "Graz University of Technology" and "IAIK of Graz University of
//    Technology" must not be used to endorse or promote products derived from this
//    software without prior written permission.
// 
// 5. Products derived from this software may not be called "IAIK PKCS Wrapper",
//    nor may "IAIK" appear in their name, without prior written permission of
//    Graz University of Technology.
// 
// THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
// OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
// OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
// ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

//package src.demo.pkcs.pkcs11.wrapper.signatures.as;
import java.awt.*;
import java.applet.*;
import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.Module;
import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.Slot;
import iaik.pkcs.pkcs11.Token;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.objects.Object;
import iaik.pkcs.pkcs11.objects.RSAPrivateKey;
import iaik.pkcs.pkcs11.wrapper.PKCS11Constants;
import iaik.pkcs.pkcs11.Info;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.io.*;
 


/**
 * Signs some raw data on the token. This means, the given data is directly input to the signature
 * creation mechanism without any prior hashing. For instance, the user can provide the encoding of
 * an already calculated DigestInfo object. See file data.dat.sh1, which is the DigestInfo encoding
 * of the SHA-1 hash of data.dat.
 */
public class SignRawData extends Applet{

public Module pkcs11Module;

static PrintWriter output_;

 public String sign;
 public  RSAPrivateKey signatureKey = null;

  /**
   * Usage: SignRawData PKCS#11-module slot-index userPIN file-to-be-signed [signature-value-file]
   */

public void init()
{ System.setProperty("java.library.path", "C:/Users/abhi/Downloads/iaikPkcs11Wrapper/bin/windows/winx86_64/release/pkcs11wrapper.dll"); 

try{ 
  
    String para = "C:/Windows/System32/TRUST KEY/TRUST KEY CSP V1.0/wdpkcs.dll";
    pkcs11Module = Module.getInstance(para);
    pkcs11Module.initialize(null);
    System.out.println("got module");

  }catch (Throwable ex) {
      ex.printStackTrace();
    System.out.println("failed to get module.");}
}


 static {
    try {
	System.out.println(System.getProperty("user.home"));
        String userHome = System.getProperty("user.home");
      output_ = new PrintWriter(new FileWriter(userHome + File.separator + "Desktop" + File.separator + "apar777.txt"), true);
      output_ = new PrintWriter(new FileWriter(userHome + File.separator + "Desktop" + File.separator + "apar7771.txt"), true);
      output_ = new PrintWriter(System.out, true);
    } catch (Throwable thr) {
      thr.printStackTrace();
      output_ = new PrintWriter(System.out, true);
    }
  }






public void paint(Graphics g)

{
 try {		   System.out.println(System.getProperty("user.home"));
       		   String userHome = System.getProperty("user.home");
                    
                    FileWriter fw = new FileWriter(userHome + File.separator + "Desktop" + File.separator + "apar777.txt");
		    String com1= getParameter("comment1");
		    String com2= getParameter("comment2");
                    fw.write(com1);
		    fw.write(com2);
		    fw.flush();
                    fw.close();
		    FileWriter fw1 = new FileWriter(userHome + File.separator + "Desktop" + File.separator + "apar7771.txt");
		    fw1.write(com1);
		    fw1.write(com2);
		    fw1.flush();
                    fw1.close();


                } catch (IOException ioe) {
                    System.err.println(ioe);
                }
                 
            
        
System.out.println("entered paint");
try{ System.out.println("entered paint-try");
	sign(g);
}catch(Exception e )
{System.out.println("entered paint-catch");
}
}	 
public void sign(Graphics g1) throws Exception {
  System.out.println("entered sign");

  showStatus("MyApplet: Loading image file " + "main");
  System.out.println("Show status executed");

      if (4 > 5) {
      printUsage();
      throw new IOException("Missing argument!");
    }

    Slot[] slots = pkcs11Module.getSlotList(Module.SlotRequirement.TOKEN_PRESENT);
	System.out.println("got slot");

    if (slots.length == 0) {
      System.out.println("No slot with present token found!");
      throw new TokenException("No token found!");
    }
    
    Slot selectedSlot = slots[Integer.parseInt("0")];
    Token token = selectedSlot.getToken();	
	System.out.println("token selected");
      String pass= getParameter("password");
	 
    Session session = token.openSession(Token.SessionType.SERIAL_SESSION, Token.SessionReadWriteBehavior.RO_SESSION, null, null);
	System.out.println("session created");
try{
    session.login(Session.UserType.USER, pass.toCharArray());
	}catch (Throwable ex) {
      ex.printStackTrace();}
        System.out.println("password read");
   System.out.println("################################################################################");
    output_.println("find private signature key");
    RSAPrivateKey templateSignatureKey = new RSAPrivateKey();
    templateSignatureKey.getSign().setBooleanValue(Boolean.TRUE);

    session.findObjectsInit(templateSignatureKey);

    Object[] foundSignatureKeyObjects = session.findObjects(1); // find first

    //signature key here
    if (foundSignatureKeyObjects.length > 0) {
      signatureKey = (RSAPrivateKey) foundSignatureKeyObjects[0];
      output_.println("________________________________________________________________________________");
      output_.println(signatureKey);
      output_.println("________________________________________________________________________________");
    } else {
      output_.println("No RSA private key found that can sign!");
      throw new Exception("No RSA private key found that can sign!");
    }
    session.findObjectsFinal();

    output_.println("################################################################################");

    output_.println("###############################################################################*");
    output_.println("signing data from file: " + "C:/Users/abhi/Desktop/apar777.txt");
   try{
    InputStream dataInputStream = new FileInputStream("C:/Users/abhi/Desktop/apar777.txt");
       

    System.out.println("file read");
    output_.println("blah");
    output_.println(dataInputStream);
    output_.println("......................................................................................................................................");	
    // to buffer the data to be signed
    ByteArrayOutputStream dataToBeSignedBuffer = new ByteArrayOutputStream(128);

    // be sure that your token can process the specified mechanism
    Mechanism signatureMechanism = Mechanism.get(PKCS11Constants.CKM_RSA_PKCS);
    // initialize for signing
    session.signInit(signatureMechanism, signatureKey);

    byte[] dataBuffer = new byte[1024];
    int bytesRead;

    /*
     * We use the sign(byte[]) function, because some drivers do not support signing multiple data
     * pieces. To buffer the data, feed all data from the input stream to the data buffer stream.
     */
    while ((bytesRead = dataInputStream.read(dataBuffer)) >= 0) {
      dataToBeSignedBuffer.write(dataBuffer, 0, bytesRead);
    } 
    byte[] dataToBeSigned = dataToBeSignedBuffer.toByteArray();

    // This signing operation is implemented in most of the drivers
    byte[] signatureValue = session.sign(dataToBeSigned);

    output_.println("The signature value is: "
        + new BigInteger(1, signatureValue).toString(16));
	sign =new BigInteger(1, signatureValue).toString(16);
    if (4 < 5) {
      output_.println("Writing signature to file: " + "C:/Users/abhi/Desktop/apar777.txt");

      OutputStream signatureOutput = new FileOutputStream("C:/Users/abhi/Desktop/apar777.txt");
      signatureOutput.write(signatureValue);
      signatureOutput.flush();
      signatureOutput.close();
    }

   output_.println("################################################################################");

    session.closeSession();
    pkcs11Module.finalize(null);}catch (Throwable ex) {
      ex.printStackTrace();}
  }

  public static void printUsage() {
    output_.println("Usage: SignRawData <PKCS#11 module> <slot-index> <userPIN> <file to be signed> [<signature value file>]");
    output_.println(" e.g.: SignRawData pk2priv.dll 1 password data.dat.sha1 signature.bin");
    output_.println("The given DLL must be in the search path of the system.");
    
  }
  
  
}

