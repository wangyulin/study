package com.wyl.hadoop.serialization;

import org.apache.hadoop.io.*;
import org.apache.hadoop.util.StringUtils;
import org.apache.orc.impl.IntegerWriter;

import java.io.*;
import java.nio.ByteBuffer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by wangyulin on 16/02/2017.
 */
public class WritableDemo {

    public static void main(String[] args) throws IOException {
        IntWritable intWritable = new IntWritable(163);
        byte[] res = serialize(intWritable);
        IntWritable a = new IntWritable();
        deserialize(a, res);

        //System.out.println(res.length);
        System.out.println(StringUtils.byteToHexString(res));
        System.out.println(a.get());

        IntWritable m = new IntWritable(12);
        byte[] m_b = serialize(m);
        IntWritable n = new IntWritable(11);
        byte[] n_b = serialize(n);
        System.out.println(IntWritable.Comparator.compareBytes(m_b, 0, m_b.length, n_b, 0, n_b.length));

        RawComparator<IntWritable> comparator = WritableComparator.get(IntWritable.class);
        System.out.println(comparator.compare(m, n));

        byte[] data = serialize(new VIntWritable(163101100));
        //assertThat(StringUtils.byteToHexString(data), is("8fa3"));
        System.out.println(data.length);

        Text text = new Text();
        text.set("Text");
        System.out.println(text.find("e"));
        //Aß東𐐀
        String s = "\uD801\uDC00"; // "\u0041\u00DF\u6771\uD801\uDC00"
        System.out.println(s);
        //s.codePointAt()
        System.out.println(s.getBytes("UTF-8").length);

        Text t = new Text("\u0041\u00DF\u6771\uD801\uDC00");
        assertThat(t.getLength(), is(10));
        assertThat(t.find("\u0041"), is(0));
        assertThat(t.find("\u00DF"), is(1));
        assertThat(t.find("\u6771"), is(3));
        assertThat(t.find("\uD801\uDC00"), is(6));
        assertThat(t.charAt(0), is(0x0041));
        assertThat(t.charAt(1), is(0x00DF));
        assertThat(t.charAt(3), is(0x6771));
        assertThat(t.charAt(6), is(0x10400));

        System.out.println("----------------");
        ByteBuffer buf = ByteBuffer.wrap(t.getBytes(), 0, t.getLength());
        int cp ;
        while(buf.hasRemaining() && (cp = Text.bytesToCodePoint(buf)) != -1) {
            System.out.println(Integer.toHexString(cp));
        }

        Text t1 = new Text();
        t1.set("Hadoop ");
        t1.set("hello ");
        t1.set("! ");
        t1.set("world");
        System.out.println(t1.toString());
    }

    public static byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        writable.write(dataOut);
        dataOut.close();
        return out.toByteArray();
    }

    public static byte[] deserialize(Writable writable, byte[] bytes) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream dataIn = new DataInputStream(in);
        writable.readFields(dataIn);
        dataIn.close();
        return bytes;
    }

}
