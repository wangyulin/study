package com.wyl.akka.hello;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Created by wangyulin on 09/01/2017.
 */
public class HelloMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create ("Hello", ConfigFactory.load ("samplehello.conf"));
        ActorRef a = system.actorOf ( Props.create (HelloWorld.class), "helloWorld" );
        System.out.println ("HelloWorld Actor Path : " + a.path ());
    }

}
