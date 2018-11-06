package com.wyl.ip;

/**
 * @Auther: wangyulin
 * @Date: 2018/6/19 20:52
 * @Description:
 */
public class IPDemo {

    public static void main(String[] args) {
        System.out.println(longToStr(ip2Int("192.168.1.105")));
    }

    public static long ip2Int(String ip) {
        String[] nums = ip.split("\\.");
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += (Long.parseLong(nums[i]) << (24 - (i * 8)));
        }
        return result;
        //return (Long.parseLong(nums[0]) << 24) + (Long.parseLong(nums[1]) << 16) + (Long.parseLong(nums[2]) << 8) + Long.parseLong(nums[3]);
    }

    public static String longToStr(long ipLong) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((ipLong >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移8位
        sb.append(String.valueOf((ipLong & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((ipLong & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((ipLong & 0x000000FF)));
        return sb.toString();
    }

}
