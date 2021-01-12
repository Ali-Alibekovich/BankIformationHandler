package com.example.task.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

@EnableScheduling
@Component
@SpringBootConfiguration
public class FileDownloader {

    //Property value injection
    @Value("${file.path}") String file;

    final CurrentUrlForDownload currentUrlForDownload;

    public FileDownloader(CurrentUrlForDownload currentUrlForDownload) {
        this.currentUrlForDownload = currentUrlForDownload;
    }

    @SneakyThrows
    public void downloadUsingStream(String urlStr) {
        URL url = new URL(urlStr);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count;
        while ((count = bufferedInputStream.read(buffer, 0, 1024)) != -1) {
            fileOutputStream.write(buffer, 0, count);
        }
        fileOutputStream.close();
        bufferedInputStream.close();
    }

    @Scheduled(cron = "*/5 * * * * *", zone = "Europe/Moscow")
    public void downloadXlsFile() {
            downloadUsingStream(currentUrlForDownload.url());
    }
}
