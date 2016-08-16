package com.wyl.datasourceTM.utils;

public class BeTestClass {  
    static Logger log=Logger.getLogger(BeTestClass.class);  
      
    public static void main(String[] args) {  
        for (int i = 0; i < 1000; i++) {  
            log.info("----------info");  
            log.debug("----------debug");  
            log.error("----------error");  
              
            System.out.println("***********************");  
            try {  
                Thread.sleep(2000);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
  
    }  
} 