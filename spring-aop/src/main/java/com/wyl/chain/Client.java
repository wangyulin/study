package com.wyl.chain;

/**
 * Created by wangyulin on 14/02/2018.
 */
public class Client {

    public static void main(String[] args) {
        Handler handlerA = new HandleA();
        Handler handlerB = new HandleB();
        Handler handlerC = new HandleC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        handlerA.execute();
    }

    static class HandleA extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("handle by a");
        }
    }

    static class HandleB extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("handle by b");
        }
    }

    static class HandleC extends Handler {
        @Override
        protected void handleProcess() {
            System.out.println("handle by c");
        }
    }

}
