package com.wyl.java7concurrency.executor.demo7;

import java.util.concurrent.CompletionService;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class ReportRequest implements Runnable {

    private String name;

    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
        service.submit(reportGenerator);
    }
}
