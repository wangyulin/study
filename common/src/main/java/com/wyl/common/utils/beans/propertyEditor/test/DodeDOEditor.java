package com.wyl.common.utils.beans.propertyEditor.test;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DodeDOEditor extends PropertyEditorSupport {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[] tokens = text.split("\\|");
        NodeDO nodeDo = new NodeDO();
        nodeDo.setName(tokens[0]);
        nodeDo.setEmail(tokens[1]);
        try{
            nodeDo.setDate(sdf.parse(tokens[2]));
        }catch(ParseException e){
            throw new IllegalArgumentException(e);
        }
        setValue(nodeDo);
    }
}