package com.distributed.fileServer.service;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class FileService {

    public FileService() {
    }

    public File getFile(String fileName) throws IOException {

        Random random = new Random();
        int fileSizeMB = random.nextInt(9) + 2;
        System.out.println("File Size: " + fileSizeMB + "MB");
        long fileSizeByte = fileSizeMB * 1024 * 1024;

        File file = new File(fileName);
        file.createNewFile();

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.setLength(fileSizeByte);
        randomAccessFile.close();

        return file;
    }

    public String getHash(File file) throws NoSuchAlgorithmException, IOException {
        int count;
        byte[] dataBytes = new byte[65536];


        MessageDigest digest = MessageDigest.getInstance("SHA-256");


        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        while ((count = bufferedInputStream.read(dataBytes)) > 0) {
            digest.update(dataBytes, 0, count);
        }
        bufferedInputStream.close();

        byte[] hash = digest.digest();
        String encodedHash = new BASE64Encoder().encode(hash);
        System.out.println("Hash code: " + encodedHash);

        return encodedHash;
    }
}
