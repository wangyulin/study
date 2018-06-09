package com.wyl.image;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FileDemo {

    public static long fileCount = 0;
    public static long mediaFileCount = 0;
    public static Map<String, List<String>> dict = new HashMap<>();
    public static Map<String, String> format = new HashMap<String, String>(){{
        put("JPG","");
        put("BMP","");

        /*put("MOV","");
        put("AVI","");
        put("MP4","");
        put("3GP","");*/
    }};

    public static void main(String[] args) throws FileNotFoundException {
        // /Users/wangyulin/Downloads
        // /Users/wangyulin/workdir/miui/study
        // /Users/wangyulin/Downloads/留念
        String pathStr = "/Users/wangyulin/Downloads/留念";
        listFolder(pathStr);
        System.out.println(fileCount);
        System.out.println("--------------------------------");
        for(String hash : dict.keySet()) {
            List<String> files = dict.get(hash);
            if(files.size() > 1) {
                System.out.println("********************************");
                System.out.println(hash);
                for (String path : files) {
                    System.out.println("\t" + path);
                }
            }
        }
    }

    public static void listFolder(String pathStr) throws FileNotFoundException {
        File file = new File(pathStr);
        File[] childes = file.listFiles();
        List<File> chileList = Arrays.asList(childes);
        Collections.sort(chileList);
        for (int i = 0; i < chileList.size(); i++) {

            File f = chileList.get(i);
            if(Objects.nonNull(f) && f.exists() && f.isDirectory()) {
                listFolder(f.getPath());
            } else {
                String fileName = f.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if(format.containsKey(suffix.toUpperCase())) {
                    mediaFileCount++;
                    String hash = md5HashCode(f.getPath()); //md5HashCodeByNIO(f.getPath());

                    if(dict.containsKey(hash)) {
                        List<String> files = dict.get(hash);
                        try {
                            String newFilePath = f.getPath();
                            files.add(newFilePath);
                        } catch(Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        List<String> files = new ArrayList<String>(){{add(f.getPath());}};
                        dict.put(hash, files);
                    }
                }
                fileCount++;
            }
        }
    }

    /**
     * 获取文件的md5值 ，有可能不是32位
     * @param filePath  文件路径
     * @return
     * @throws FileNotFoundException
     */
    public static String md5HashCode(String filePath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        return md5HashCode(fis);
    }

    public static String md5HashCodeByNIO(String filePath) {
        try {
            FileInputStream fin = new FileInputStream(filePath);
            long startTime = System.nanoTime();
            int fileSize = fin.available();
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileChannel fcin = fin.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 1024 * 1024 * 50
            while(true) {
                buffer.clear();
                int flag = fcin.read(buffer);
                if(flag == -1) {
                    break;
                }
                md.update(buffer.array(), 0, flag);
            }
            long endTime = System.nanoTime();
            //System.out.printf("File Size : \t%10d  耗时 : \t %8d \n", fileSize,  (endTime - startTime)/1000000);

            byte[] md5Bytes  = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);//1代表绝对值
            return bigInt.toString(16);//转换为16进制
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保证文件的MD5值为32位
     * @param filePath  文件路径
     * @return
     * @throws FileNotFoundException
     */
    public static String md5HashCode32(String filePath) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(filePath);
        return md5HashCode32(fis);
    }

    /**
     * java获取文件的md5值
     * @param fis 输入流
     * @return
     */
    public static String md5HashCode(InputStream fis) {
        try {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256

            long startTime = System.nanoTime();
            int fileSize = fis.available();

            MessageDigest md = MessageDigest.getInstance("MD5");

            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();

            long endTime = System.nanoTime();

            //System.out.printf("File Size : \t%10d  耗时 : \t %8d \n", fileSize,  (endTime - startTime)/1000000);

            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes  = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);//1代表绝对值
            return bigInt.toString(16);//转换为16进制
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * java计算文件32位md5值
     * @param fis 输入流
     * @return
     */
    public static String md5HashCode32(InputStream fis) {
        try {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("MD5");

            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();

            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes  = md.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;//解释参见最下方
                if (val < 16) {
                    /**
                     * 如果小于16，那么val值的16进制形式必然为一位，
                     * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                     * 此处高位补0。
                     */
                    hexValue.append("0");
                }
                //这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 方法md5HashCode32 中     ((int) md5Bytes[i]) & 0xff   操作的解释：
     * 在Java语言中涉及到字节byte数组数据的一些操作时，经常看到 byte[i] & 0xff这样的操作，这里就记录总结一下这里包含的意义：
     * 1、0xff是16进制（十进制是255），它默认为整形，二进制位为32位，最低八位是“1111 1111”，其余24位都是0。
     * 2、&运算: 如果2个bit都是1，则得1，否则得0；
     * 3、byte[i] & 0xff：首先，这个操作一般都是在将byte数据转成int或者其他整形数据的过程中；使用了这个操作，最终的整形数据只有低8位有数据，其他位数都为0。
     * 4、这个操作得出的整形数据都是大于等于0并且小于等于255的
     */

}
