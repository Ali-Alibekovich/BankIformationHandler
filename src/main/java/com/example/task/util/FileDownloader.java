package com.example.task.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;


@Component
public class FileDownloader {

    //Property value injection
    @Value("${file.path}")
    String file;

    final CurrentUrlForDownload currentUrlForDownload;

    public FileDownloader(CurrentUrlForDownload currentUrlForDownload) {
        this.currentUrlForDownload = currentUrlForDownload;
    }


    @SneakyThrows
    public void downloadUsingStream(String urlStr) {
        URL url = new URL(urlStr);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[2048];
        int count;
        while ((count = bufferedInputStream.read(buffer, 0, 2048)) != -1) {
            fileOutputStream.write(buffer, 0, count);
        }
        fileOutputStream.close();
        bufferedInputStream.close();
    }

    public void downloadXlsFile() {
        downloadUsingStream(currentUrlForDownload.url());
    }
}
