package com.wyl.spring.boot.demo.task.thread.job;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.wyl.spring.boot.demo.actor.GreetingActor;
import com.wyl.spring.boot.demo.service.DemoService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.wyl.spring.boot.demo.conf.SpringExtension.SPRING_EXTENSION_PROVIDER;
import static akka.pattern.Patterns.ask;

/**
 * Created by wangyulin on 24/03/2017.
 */
@Component("demoTask")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DemoTask extends Thread {

    final static Logger LOGGER= LoggerFactory.getLogger(DemoTask.class);

    @Autowired
    private ActorSystem system;

    @Resource(name = "demoService")
    private DemoService demoService;

    @Override
    public void run() {
        LOGGER.info("线程 : " + Thread.currentThread().getName() + "运行中....." + demoService);
        demoService.demo1("demoTask");
        ActorRef greetingActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("greetingActor"), "greetingActor");
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> result = ask(greetingActor, new GreetingActor.Greet("John"), timeout);
        try {
            Assert.assertEquals("John执行service成功 ！！！", Await.result(result, duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("");
    }

}
