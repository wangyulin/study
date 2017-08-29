package com.wyl.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args :
 -verbose:gc
 -Xms20m
 -Xmx20m
 -XX:+HeapDumpOnOutOfMemoryError
 -XX:+PrintGCDetails
 -XX:+ScavengeBeforeFullGC
 -XX:+UseConcMarkSweepGC
 -XX:+PrintTenuringDistribution
 -XX:+PrintGCTimeStamps
 -XX:+PrintGCApplicationConcurrentTime
 -XX:+PrintGCApplicationStoppedTime
 -XX:+PrintCompilation
 -Xloggc:/Users/wangyulin/workDir/HeapOOM.log

 -Djava.rmi.server.hostname=192.168.1.107
 -Dcom.sun.management.jmxremote.port=8888
 -Dcom.sun.management.jmxremote.ssl=false
 -Dcom.sun.management.jmxremote.authenticate=false

 * Created by wangyulin on 4/7/16.
 */
public class HeapOOM {

    public static String v = null;
    static class OOMObjcet {
    }

    public static void main(String[] args) throws InterruptedException {
        List<OOMObjcet> list = new ArrayList<OOMObjcet>();
        ClassLoader cl;
        //Bootstrap bs;
        v = new String("abcdefg");
        while(true) {
            list.add(new OOMObjcet());
            Thread.sleep(10);
        }
    }

}

/**
 /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/bin/
 java -verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -Didea.launcher.port=7536
 "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 15.app/Contents/bin"
 -Dfile.encoding=UTF-8
 -classpath
    "/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/charsets.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/deploy.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/dnsns.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/localedata.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/nashorn.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/sunec.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/zipfs.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/javaws.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jce.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jfr.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jfxswt.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jsse.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/management-agent.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/plugin.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/resources.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/rt.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/ant-javafx.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/dt.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/javafx-mx.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/jconsole.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/packager.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/sa-jdi.jar:
     /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/tools.jar:
     .../javaTopic/target/classes:
     /Users/wangyulin/.m2/repository/com/google/guava/guava/15.0/guava-15.0.jar:
     /Applications/IntelliJ IDEA 15.app/Contents/lib/idea_rt.jar"
 com.intellij.rt.execution.application.AppMain com.wyl.jvm.HeapOOM
 [GC (Allocation Failure)  5632K->3641K(19968K), 0.0082797 secs]
 [GC (Allocation Failure)  9198K->8248K(19968K), 0.0081622 secs]
 [Full GC (Ergonomics)  17045K->12840K(19968K), 0.1440292 secs]
 [Full GC (Ergonomics)  16459K->16352K(19968K), 0.1215535 secs]
 [Full GC (Allocation Failure)  16352K->16325K(19968K), 0.1147840 secs]
 java.lang.OutOfMemoryError: Java heap space
 Dumping heap to java_pid73204.hprof ...
 Heap dump file created [27920120 bytes in 0.268 secs]
 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 at java.util.Arrays.copyOf(Arrays.java:3210)
 at java.util.Arrays.copyOf(Arrays.java:3181)
 at java.util.ArrayList.grow(ArrayList.java:261)
 at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
 at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
 at java.util.ArrayList.add(ArrayList.java:458)
 at com.wyl.jvm.HeapOOM.main(HeapOOM.java:18)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:497)
 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)

 Process finished with exit code 1
*/
