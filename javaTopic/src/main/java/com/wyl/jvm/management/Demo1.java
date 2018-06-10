package com.wyl.jvm.management;

import java.lang.management.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static com.sun.deploy.util.BufferUtil.MB;

/**
 * @Auther: wangyulin
 * @Date: 2018/6/10 13:31
 * @Description:
 */
public class Demo1 {

    public static void main(String[] args) {
        OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(system.getArch());
        System.out.println(system.getAvailableProcessors());
        System.out.println(system.getName());
        System.out.println(system.getSystemLoadAverage());
        System.out.println(system.getVersion());
        System.out.println(system.getObjectName());
        System.out.println("----------------------------------------");
        printMemoryManagerInfo();
        printMemoryInfo();
        printOperatingSystemInfo();
    }

    private static void printOperatingSystemInfo() {
        OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();
        //相当于System.getProperty("os.name").
        System.out.println("系统名称:" + system.getName());
        //相当于System.getProperty("os.version").
        System.out.println("系统版本:" + system.getVersion());
        //相当于System.getProperty("os.arch").
        System.out.println("操作系统的架构:" + system.getArch());
        //相当于 Runtime.availableProcessors()
        System.out.println("可用的内核数:" + system.getAvailableProcessors());

        if (isSunOsMBean(system)) {
            long totalPhysicalMemory = getLongFromOperatingSystem(system, "getTotalPhysicalMemorySize");
            long freePhysicalMemory = getLongFromOperatingSystem(system, "getFreePhysicalMemorySize");
            long usedPhysicalMemorySize = totalPhysicalMemory - freePhysicalMemory;

            System.out.println("总物理内存(M):" + totalPhysicalMemory / MB);
            System.out.println("已用物理内存(M):" + usedPhysicalMemorySize / MB);
            System.out.println("剩余物理内存(M):" + freePhysicalMemory / MB);

            long totalSwapSpaceSize = getLongFromOperatingSystem(system, "getTotalSwapSpaceSize");
            long freeSwapSpaceSize = getLongFromOperatingSystem(system, "getFreeSwapSpaceSize");
            long usedSwapSpaceSize = totalSwapSpaceSize - freeSwapSpaceSize;

            System.out.println("总交换空间(M):" + totalSwapSpaceSize / MB);
            System.out.println("已用交换空间(M):" + usedSwapSpaceSize / MB);
            System.out.println("剩余交换空间(M):" + freeSwapSpaceSize / MB);
        }
    }

    private static long getLongFromOperatingSystem(OperatingSystemMXBean operatingSystem, String methodName) {
        try {
            final Method method = operatingSystem.getClass().getMethod(methodName,
                    (Class<?>[]) null);
            method.setAccessible(true);
            return (Long) method.invoke(operatingSystem, (Object[]) null);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof Error) {
                throw (Error) e.getCause();
            } else if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new IllegalStateException(e.getCause());
        } catch (final NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    private static boolean isSunOsMBean(OperatingSystemMXBean operatingSystem) {
        final String className = operatingSystem.getClass().getName();
        return "com.sun.management.OperatingSystem".equals(className)
                || "com.sun.management.UnixOperatingSystem".equals(className);
    }

    private static void printMemoryInfo() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();
        System.out.println("head堆:");
        System.out.println("\t初始(M):" + headMemory.getInit() / MB);
        System.out.println("\t最大(上限)(M):" + headMemory.getMax() / MB);
        System.out.println("\t当前(已使用)(M):" + headMemory.getUsed() / MB);
        System.out.println("\t提交的内存(已申请)(M):" + headMemory.getCommitted() / MB);
        System.out.println("\t使用率:" + headMemory.getUsed() * 100 / headMemory.getCommitted() + "%");

        System.out.println("non-head非堆:");
        MemoryUsage nonheadMemory = memory.getNonHeapMemoryUsage();
        System.out.println("\t初始(M):" + nonheadMemory.getInit() / MB);
        System.out.println("\t最大(上限)(M):" + nonheadMemory.getMax() / MB);
        System.out.println("\t当前(已使用)(M):" + nonheadMemory.getUsed() / MB);
        System.out.println("\t提交的内存(已申请)(M):" + nonheadMemory.getCommitted() / MB);
        System.out.println("\t使用率:" + nonheadMemory.getUsed() * 100 / nonheadMemory.getCommitted() + "%");
    }

    private static void printMemoryManagerInfo() {
        List<MemoryManagerMXBean> managers = ManagementFactory.getMemoryManagerMXBeans();
        if (managers != null && !managers.isEmpty()) {
            for (MemoryManagerMXBean manager : managers) {
                System.out.println("vm内存管理器：名称=" + manager.getName() + ",管理的内存区="
                        + Arrays.deepToString(manager.getMemoryPoolNames()) + ",ObjectName=" + manager.getObjectName());
            }
        }
    }

    private static void printRuntimeInfo() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        System.out.println("进程PID=" + runtime.getName().split("@")[0]);
        System.out.println("jvm规范名称:" + runtime.getSpecName());
        System.out.println("jvm规范运营商:" + runtime.getSpecVendor());
        System.out.println("jvm规范版本:" + runtime.getSpecVersion());
        //返回虚拟机在毫秒内的开始时间。该方法返回了虚拟机启动时的近似时间
        System.out.println("jvm启动时间（毫秒）:" + (runtime.getStartTime() / 1000 / 1000));
        //相当于System.getProperties
        System.out.println("获取System.properties:" + runtime.getSystemProperties());
        System.out.println("jvm正常运行时间（毫秒）:" + runtime.getUptime());
        //相当于System.getProperty("java.vm.name").
        System.out.println("jvm名称:" + runtime.getVmName());
        //相当于System.getProperty("java.vm.vendor").
        System.out.println("jvm运营商:" + runtime.getVmVendor());
        //相当于System.getProperty("java.vm.version").
        System.out.println("jvm实现版本:" + runtime.getVmVersion());
        List<String> args = runtime.getInputArguments();
        if (args != null && !args.isEmpty()) {
            System.out.println("vm参数:");
            for (String arg : args) {
                System.out.println(arg);
            }
        }
        System.out.println("类路径:" + runtime.getClassPath());
        System.out.println("引导类路径:" + runtime.getBootClassPath());
        System.out.println("库路径:" + runtime.getLibraryPath());
    }

}
