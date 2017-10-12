//package demo.pkcs.pkcs11.wrapper;
import iaik.pkcs.pkcs11.Module;
import iaik.pkcs.pkcs11.Info;
public class ModuleInfo {
public static void main(String[] args) {
if (args.length == 1){
try {
Module pkcs11Module = Module.getInstance(args[0]);
pkcs11Module.initialize(null);
Info info = pkcs11Module.getInfo();
System.out.println(info);
pkcs11Module.finalize(null);
} catch (Throwable ex) {
ex.printStackTrace();
}
} else {
printUsage();
System.exit(1);
}
}
protected static void printUsage() {
System.out.println("ModuleInfo <PKCS#11 module name>");
System.out.println("e.g.: ModuleInfo cryptoki.dll");
}
}