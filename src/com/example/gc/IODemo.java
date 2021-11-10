package com.example.gc;

import java.io.*;

public class IODemo {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(
                                new File("C:\\Users\\WeiJiangL\\Desktop\\a.txt")
                        )
                )
        );

        try {
            out.writeBoolean(true);
            out.writeInt(123);
            out.writeChar(45);
            out.writeUTF("123131313131233");
        } finally {
            out.close();
        }

        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(
                                new File("C:\\Users\\WeiJiangL\\Desktop\\a.txt")
                        )
                )
        );

        try {
            System.out.println(in.readInt());
            System.out.println(in.readBoolean());

            System.out.println((int) in.readChar());
            System.out.println(in.readUTF());
        } finally {
            in.close();
        }
    }
}
