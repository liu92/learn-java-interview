package com.learn.interview.chapter1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName: MultiThread
 * @Description:
 * @Author: lin
 * @Date: 2020/9/7 12:33
 * History:
 * @<version> 1.0
 */
public class MultiThread {
    public static void main(String[] args) {
        //获取java线程管理 mxBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,
                false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " +
                    threadInfo.getThreadName());
        }

    }
}
