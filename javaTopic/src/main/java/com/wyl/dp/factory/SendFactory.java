package com.wyl.dp.factory;

/**
 * Created by wangyulin on 2/18/16.
 */
public class SendFactory {

    public Sender product(String type){
        if("mail".equals(type)) {
            return new MailSender();
        }else if("sms".equals(type)){
            return new SMsSender();
        }else{
            System.out.println("请输入正确的sender类型!");
            return null;
        }
    }
}
