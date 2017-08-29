package com.wyl.thread.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo [] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo threadInfo: threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
		}
	}

}
