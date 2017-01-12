package com.wyl.akka.hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by wangyulin on 08/01/2017.
 */
public class HelloWorld extends UntypedActor{

    ActorRef greeter;

    public void preStart() {
        greeter = getContext ().actorOf ( Props.create ( Greeter.class ), "greeter");
        System.out.println ("Greeter Actor Path : " + greeter.path ());
        greeter.tell ( Greeter.Msg.GREETER, getSelf ());
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg == Greeter.Msg.DONE) {
            greeter.tell(Greeter.Msg.GREETER, getSelf ());
            getContext ().stop ( getSelf () );
        } else {
            unhandled ( msg );
        }
    }
}
