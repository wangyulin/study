package com.wyl.utils.eventbus;

import com.google.common.collect.Lists;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by wangyulin on 28/02/2017.
 */

public class DataEventBus {

    //private static EventBus eventBus = new EventBus("test");
    private static FakeExecutor executor = new FakeExecutor();

    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(executor);

        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new DataEvent(200));
        eventBus.post(new DataEvent(300));
        eventBus.post(new DataEvent(400));

        System.out.println("LastMessage:" + listener.getLastMessage());
    }

    public static class FakeExecutor implements Executor {
        List<Runnable> tasks = Lists.newArrayList();

        @Override
        public void execute(Runnable task) {
            tasks.add(task);
        }

        public List<Runnable> getTasks() {
            return tasks;
        }
    }

}
