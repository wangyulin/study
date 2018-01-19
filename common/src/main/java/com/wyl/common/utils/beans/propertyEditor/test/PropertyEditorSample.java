package com.wyl.common.utils.beans.propertyEditor.test;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PropertyEditorSample {

    static{
        PropertyEditorManager.registerEditor(NodeDO.class, DodeDOEditor.class);
    }

    public static void main(String[] args) throws Exception{
        Object obj;
        Map<String, String> parameters = new HashMap<String, String>(){
            {
                put("nodeName", "小胖测试");
                put("nodeDO", "xiaopang|xiaopang@163.com|2015-10-20 12:00:00");
            }
        };
        TestDO testDo = convert(parameters);
        System.out.println(testDo.getNodeName());
        System.out.println(testDo.getNodeDO());
    }

    private static TestDO convert(Map<String, String> parameters)throws Exception {
        TestDO testDO = new TestDO();
        BeanInfo bi = Introspector.getBeanInfo(TestDO.class);
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds){
            Class<?> propertyType = pd.getPropertyType();
            Method writeMethod = pd.getWriteMethod();
            if(propertyType == Class.class){
                //ignore
            }else if(propertyType == String.class){
                writeMethod.invoke(testDO, parameters.get(pd.getName()));
            }else{
                PropertyEditor editor = PropertyEditorManager.findEditor(propertyType);
                if(editor != null){
                    editor.setAsText(parameters.get(pd.getName()));
                    writeMethod.invoke(testDO, editor.getValue());
                }else{
                    System.out.println("no editor for:"+pd.getName());
                }
            }
        }
        return testDO;
    }

}
