package com.wyl.dp.factory.simple;

import com.wyl.dp.factory.simple.product.Human;
import org.reflections.Reflections;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SampleFactory {

    private static Map<String, Class> allHumans;

    static {
        Reflections reflections = new Reflections("com.wyl.dp.factory.simple.product");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Product.class);
        allHumans = new ConcurrentHashMap<>();
        for (Class<?> classObject : annotatedClasses) {
            Product vehicle = classObject.getAnnotation(Product.class);
            allHumans.put(vehicle.type(), classObject);
        }
        allHumans = Collections.unmodifiableMap(allHumans);
    }

    public static Human makeHumanV2(String type) {
        Human human = null;
        try {
            human = (Human) Class.forName(type).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return human;
    }

    public static Human makeHumanV3(String type) {
        Human human = null;
        if (allHumans.containsKey(type)) {
            try {
                human = (Human) allHumans.get(type).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return human;
    }

}
