package com.epam.mjc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String[] strings = null;
        try(FileInputStream aFile = new FileInputStream(file);
            FileChannel inChannel = aFile.getChannel();)
        {
            long fileSize = inChannel.size();

            ByteBuffer buffer =ByteBuffer.allocate((int)fileSize);
            inChannel.read(buffer);
            buffer.flip();
            String text = null;

            for (int i = 0;i<fileSize;i++){

                text +=String.valueOf((char) buffer.get());
            }
            text = text.replace("\n"," ");
            text = text.replace("\r","");
            strings = text.split(" ");

        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
        String name = strings[1];
        Integer age = Integer.valueOf(strings[3]);
        String email = strings[5];
        Long phone = Long.valueOf(strings[7]);
        return new Profile(name,age,email,phone);

    }
}
