package com.wyl.java7concurrency.executor.demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        String name = "test";
        String password = "test";

        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");

        TaskValidator ldapTask = new TaskValidator(ldapValidator, name, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, name, password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        ExecutorService executor = Executors.newCachedThreadPool();

        String result;

        try {
            result = executor.invokeAny(taskList);
            System.out.printf("Main: result: %s\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");
    }

}
