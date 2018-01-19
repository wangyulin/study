package com.wyl.common.utils.lambda.demo1;

import java.util.List;
@FunctionalInterface
public interface ICreater<T extends List<?>> {
    T create();
}
