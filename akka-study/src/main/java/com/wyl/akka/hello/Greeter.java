package com.wyl.akka.hello;

import akka.actor.UntypedActor;

/**
 * Created by wangyulin on 08/01/2017.
 */
public class Greeter extends UntypedActor {

    public static enum Msg {
        GREETER,DONE;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg == Msg.GREETER) {
            System.out.println ("Hello World !");
            getSender ().tell(Msg.DONE, getSelf ());
        } else {
            unhandled ( msg );
        }
    }
}
