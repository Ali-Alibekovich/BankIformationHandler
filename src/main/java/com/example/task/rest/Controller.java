package com.example.task.rest;

import com.example.task.util.FileDownloader;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/")
public class Controller {

    final FileDownloader fileDownloader;

    Controller(FileDownloader fileDownloader) {
        this.fileDownloader = fileDownloader;
    }

    @GetMapping("uploadXls")
    public void upload() {
        fileDownloader.downloadXlsFile();
    }

}
