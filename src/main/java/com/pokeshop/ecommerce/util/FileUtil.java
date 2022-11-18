package com.pokeshop.ecommerce.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtil {

    public FileUtil() {
    }

    public static File writeBytesToFileNio(byte[] bFile, String fileDest) {
        try {
            Path path = Paths.get(fileDest);
            Files.write(path, bFile);
            return new File(fileDest);
        } catch (IOException var3) {
            throw new RuntimeException("Error al leer arreglo de bytes");
        }
    }

    public static File multiPartToFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(multipartFile.getBytes());
            } catch (Throwable var6) {
                try {
                    fos.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }
                throw var6;
            }
            fos.close();
            return file;
        } catch (IOException var7) {
            throw new RuntimeException("Error al leer el multipartFile");
        }
    }

}