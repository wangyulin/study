package com.wyl.volatiledemo;

public class Money {
    private String HANDIGISTR[] = new String[]{"零", "壹", "贰", "叁", "肆", "伍",
            "陆", "柒", "捌", "玖"};
    private String HANDIVISTR[] = new String[]{"", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰",
            "仟", "万", "拾", "佰", "仟"};


    public static void main(String[] args) {
        System.out.println(new Money().positiveIntegerToHanStr("1000123"));
        System.out.println(new Money().convV1("1000123"));
    }

    public String convV1(String numStr) {
        int length = numStr.length();
        boolean lastZero = false;
        boolean preIsZero = false;
        boolean start = true;
        StringBuffer res = new StringBuffer();

        for(int i = 0; i < length; i++ ) {
            char c = numStr.charAt(i);
            int n = c - '0';
            if(n < 0 || n > 9) {
                //异常
            }

            if(n == 0 && start) { //输入最开头出现数字0
                continue;
            }

            if( n ==0 && !start) { //数字0出现在中间
                lastZero = false;
                preIsZero = true;
                continue;
            }

            if(n >= 1 && n <= 9) {
                if(preIsZero && !lastZero) {
                    lastZero = true;
                    preIsZero = false;
                    res.append(HANDIGISTR[0]);
                }
                start = false;
                res.append(HANDIGISTR[n]).append(HANDIVISTR[length - 1 - i]);
            }


        }
        return res.toString();
    }

    /**
     * @param numStr 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
     * @return
     */
    String positiveIntegerToHanStr(String numStr) {
        String resStr = "";
        boolean lastZero = false;
        boolean hasValue = false; // 亿、万进位前有数值标记
        int len, n;
        len = numStr.length();
        if (len > 15)
            return "数值过大!";
        for (int i = len - 1; i >= 0; i--) {
            char c = numStr.charAt(len - i - 1);
            if (c == ' ')
                continue;
            n = c - '0';
            if (n < 0 || n > 9)
                return "输入含非数字字符!";

            if (n != 0) {
                if (lastZero)
                    resStr += HANDIGISTR[0]; // 若干零后若跟非零值，只显示一个零
                // 除了亿万前的零不带到后面
                // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
                // 如十进位前有零也不发壹音用此行
                if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
                    resStr += HANDIGISTR[n];
                resStr += HANDIVISTR[i]; // 非零值后加进位，个位为空
                hasValue = true; // 置万进位前有值标记

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasValue)) // 亿万之间必须有非零值方显示万
                    resStr += HANDIVISTR[i]; // “亿”或“万”
            }
            if (i % 8 == 0)
                hasValue = false; // 万进位前有值标记逢亿复位
            lastZero = (n == 0) && (i % 4 != 0);
        }

        if (resStr.length() == 0)
            return HANDIGISTR[0]; // 输入空字符或"0"，返回"零"
        return resStr;
    }
}
