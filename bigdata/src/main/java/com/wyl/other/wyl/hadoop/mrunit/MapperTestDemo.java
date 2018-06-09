package com.wyl.other.wyl.hadoop.mrunit;

import com.wyl.hadoop.mr.demo1.WordCount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wangyulin on 19/02/2017.
 */
public class MapperTestDemo {

    @Test
    public void testTokenizerMapperProcess() throws IOException {
        Text value = new Text("hello world !");
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new WordCount.TokenizerMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("hello"), new IntWritable(1))
                .withOutput(new Text("world"), new IntWritable(1))
                .withOutput(new Text("!"), new IntWritable(1))
                .runTest();
    }

}
