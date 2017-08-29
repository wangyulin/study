package com.wyl.jvm;

/**
 * Created by wangyulin on 4/7/16.
 * -Xss2m
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while(true) {

        }
    }

    public void stackLeakByThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}

/**
 /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/bin/java -Xss2m -Didea.launcher.port=7534 "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 15.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/lib/tools.jar:/Users/wangyulin/workDir/workspaces/javaTopic/target/classes:/Users/wangyulin/.m2/repository/com/google/guava/guava/15.0/guava-15.0.jar:/Applications/IntelliJ IDEA 15.app/Contents/lib/idea_rt.jar" com.intellij.rt.execution.application.AppMain com.wyl.jvm.JavaVMStackOOM
 2016-04-07 23:15:21
 Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.45-b02 mixed mode):

 "Attach Listener" #1034 daemon prio=9 os_prio=31 tid=0x00007fec4b193800 nid=0x84a03 runnable [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 "Thread-1023" #1033 prio=5 os_prio=31 tid=0x00007fec4a124000 nid=0x84803 runnable [0x00007000822c6000]
 java.lang.Thread.State: RUNNABLE
 at com.wyl.jvm.JavaVMStackOOM.dontStop(JavaVMStackOOM.java:10)
 at com.wyl.jvm.JavaVMStackOOM.access$000(JavaVMStackOOM.java:7)
 at com.wyl.jvm.JavaVMStackOOM$1.run(JavaVMStackOOM.java:19)
 at java.lang.Thread.run(Thread.java:745)

 "Thread-1022" #1032 prio=5 os_prio=31 tid=0x00007fec4a123000 nid=0x84603 runnable [0x00007000820c3000]
 java.lang.Thread.State: RUNNABLE
 at com.wyl.jvm.JavaVMStackOOM.dontStop(JavaVMStackOOM.java:10)
 at com.wyl.jvm.JavaVMStackOOM.access$000(JavaVMStackOOM.java:7)
 at com.wyl.jvm.JavaVMStackOOM$1.run(JavaVMStackOOM.java:19)
 at java.lang.Thread.run(Thread.java:745)

 "Thread-2" #12 prio=5 os_prio=31 tid=0x00007fec4a055000 nid=0x5103 runnable [0x0000700001ccf000]
 java.lang.Thread.State: RUNNABLE
 at com.wyl.jvm.JavaVMStackOOM.dontStop(JavaVMStackOOM.java:10)
 at com.wyl.jvm.JavaVMStackOOM.access$000(JavaVMStackOOM.java:7)
 at com.wyl.jvm.JavaVMStackOOM$1.run(JavaVMStackOOM.java:19)
 at java.lang.Thread.run(Thread.java:745)

 "Thread-1" #11 prio=5 os_prio=31 tid=0x00007fec4a022000 nid=0x4f03 runnable [0x0000700001acc000]
 java.lang.Thread.State: RUNNABLE
 at com.wyl.jvm.JavaVMStackOOM.dontStop(JavaVMStackOOM.java:10)
 at com.wyl.jvm.JavaVMStackOOM.access$000(JavaVMStackOOM.java:7)
 at com.wyl.jvm.JavaVMStackOOM$1.run(JavaVMStackOOM.java:19)
 at java.lang.Thread.run(Thread.java:745)

 "Thread-0" #10 prio=5 os_prio=31 tid=0x00007fec4b841800 nid=0x4d03 runnable [0x00007000018c9000]
 java.lang.Thread.State: RUNNABLE
 at com.wyl.jvm.JavaVMStackOOM.dontStop(JavaVMStackOOM.java:10)
 at com.wyl.jvm.JavaVMStackOOM.access$000(JavaVMStackOOM.java:7)
 at com.wyl.jvm.JavaVMStackOOM$1.run(JavaVMStackOOM.java:19)
 at java.lang.Thread.run(Thread.java:745)

 "Monitor Ctrl-Break" #9 daemon prio=5 os_prio=31 tid=0x00007fec4a04f000 nid=0x4b03 runnable [0x00007000016c6000]
 java.lang.Thread.State: RUNNABLE
 at java.net.PlainSocketImpl.socketAccept(Native Method)
 at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:404)
 at java.net.ServerSocket.implAccept(ServerSocket.java:545)
 at java.net.ServerSocket.accept(ServerSocket.java:513)
 at com.intellij.rt.execution.application.AppMain$1.run(AppMain.java:90)
 at java.lang.Thread.run(Thread.java:745)

 "Service Thread" #8 daemon prio=9 os_prio=31 tid=0x00007fec4a013000 nid=0x4703 runnable [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 "C1 CompilerThread2" #7 daemon prio=9 os_prio=31 tid=0x00007fec4a012800 nid=0x4503 waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 "C2 CompilerThread1" #6 daemon prio=9 os_prio=31 tid=0x00007fec4b82f800 nid=0x4303 waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 "C2 CompilerThread0" #5 daemon prio=9 os_prio=31 tid=0x00007fec4b03a800 nid=0x4103 waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 "Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007fec4b801000 nid=0x3b0b waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 "Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007fec4b02b000 nid=0x3003 in Object.wait() [0x0000700000c2e000]
 java.lang.Thread.State: WAITING (on object monitor)
 at java.lang.Object.wait(Native Method)
 - waiting on <0x000000076ab06f58> (a java.lang.ref.ReferenceQueue$Lock)
 at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 - locked <0x000000076ab06f58> (a java.lang.ref.ReferenceQueue$Lock)
 at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
 at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

 "Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007fec4b02a800 nid=0x2e03 in Object.wait() [0x0000700000a2b000]
 java.lang.Thread.State: WAITING (on object monitor)
 at java.lang.Object.wait(Native Method)
 - waiting on <0x000000076ab06998> (a java.lang.ref.Reference$Lock)
 at java.lang.Object.wait(Object.java:502)
 at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:157)
 - locked <0x000000076ab06998> (a java.lang.ref.Reference$Lock)

 "main" #1 prio=5 os_prio=31 tid=0x00007fec4b008000 nid=0x1703 runnable [0x0000700000318000]
 java.lang.Thread.State: RUNNABLE
 at com.wyl.jvm.JavaVMStackOOM.stackLeakByThread(JavaVMStackOOM.java:17)
 at com.wyl.jvm.JavaVMStackOOM.main(JavaVMStackOOM.java:28)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:497)
 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)

 "VM Thread" os_prio=31 tid=0x00007fec4b028000 nid=0x2c03 runnable

 "GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007fec4b013800 nid=0x2403 runnable

 "GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007fec4b014000 nid=0x2603 runnable

 "GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007fec4b014800 nid=0x2803 runnable

 "GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007fec4b015000 nid=0x2a03 runnable

 "VM Periodic Task Thread" os_prio=31 tid=0x00007fec4b03b800 nid=0x4903 waiting on condition

 JNI global references: 31

 Heap
 PSYoungGen      total 76288K, used 7864K [0x000000076ab00000, 0x0000000770000000, 0x00000007c0000000)
 eden space 65536K, 12% used [0x000000076ab00000,0x000000076b2ae3d0,0x000000076eb00000)
 from space 10752K, 0% used [0x000000076f580000,0x000000076f580000,0x0000000770000000)
 to   space 10752K, 0% used [0x000000076eb00000,0x000000076eb00000,0x000000076f580000)
 ParOldGen       total 175104K, used 0K [0x00000006c0000000, 0x00000006cab00000, 0x000000076ab00000)
 object space 175104K, 0% used [0x00000006c0000000,0x00000006c0000000,0x00000006cab00000)
 Metaspace       used 3135K, capacity 4500K, committed 4864K, reserved 1056768K
 class space    used 344K, capacity 388K, committed 512K, reserved 1048576K

 Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 at java.lang.Thread.start0(Native Method)
 at java.lang.Thread.start(Thread.java:714)
 at com.wyl.jvm.JavaVMStackOOM.stackLeakByThread(JavaVMStackOOM.java:22)
 at com.wyl.jvm.JavaVMStackOOM.main(JavaVMStackOOM.java:28)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:497)
 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 Java HotSpot(TM) 64-Bit Server VM warning: Exception java.lang.OutOfMemoryError occurred dispatching signal SIGINT to handler- the VM may need to be forcibly terminated

 */
