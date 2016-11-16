package com.wyl.common.utils.duoyin;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinTest {
	
	public static void main(String[] args) {  
		  
        try {  
              
//          String str = "长沙绿爱旅行";  
            String str = "出差";  
              
            System.out.println(str + " pyf=" + PinyinUtils.chineseToPinYinF(str));  
            System.out.println(str + " pys=" + PinyinUtils.chineseToPinYinS(str));  
              
        } catch (BadHanyuPinyinOutputFormatCombination e) {  
            e.printStackTrace();  
        }  
    }
	
}
