package com.wyl.dp.factory.abstractfactory;

/**
 * 产品等级1的实现类
 */
public class Creator1 extends AbstractCreator {

    /**
     * 只生产产品等级为1的A产品
     *
     * @return
     */
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    /**
     * 只生产产品等级为1的B产品
     *
     * @return
     */
    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
