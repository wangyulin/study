package com.wyl.seria.demo1;


import com.wyl.model.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class DemoFirst {

    public static void main(String[] args) throws IOException {
        //writeUserToFile();
        readUserFromFile();
    }

    public static void readUserFromFile() throws IOException {
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<>(
                new File("/Users/wangyulin/workdir/miui/study/kafka-demo/src/main/avro/out/users.avro"), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }

    public static void writeUserToFile() throws IOException {

        User user1 = new User();
        user1.setName("wyl");
        user1.setFavoriteNumber(168);
        user1.setFavoriteColor("Green");

        User user2 = new User("Ben", 168, "red");

        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(186)
                .build();

        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(),
                new File("/Users/wangyulin/workdir/miui/study/kafka-demo/src/main/avro/out/users.avro"));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();



    }

}
