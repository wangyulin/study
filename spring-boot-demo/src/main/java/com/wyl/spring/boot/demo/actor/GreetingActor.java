package com.wyl.spring.boot.demo.actor;

import akka.actor.UntypedActor;
import com.wyl.spring.boot.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 24/03/2017.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedActor {

    final static Logger LOGGER = LoggerFactory.getLogger(GreetingActor.class);

    private DemoService demoService;

    public GreetingActor(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            LOGGER.info("actor : {}", name);
            getSender().tell(demoService.demo1(name), getSelf());
        } else {
            unhandled(message);
        }
    }

    public static class Greet {

        private String name;

        public Greet(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}