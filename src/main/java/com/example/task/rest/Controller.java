package com.example.task.rest;

import com.example.task.model.OrganizationDAO;
import com.example.task.service.ServiceImpl.DataService;
import com.example.task.utils.FileDownloader;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
@RequestMapping("/")
@EnableScheduling
public class Controller {

    final private FileDownloader fileDownloader;
    final private DataService dataService;

    Controller(FileDownloader fileDownloader, DataService dataService) {
        this.fileDownloader = fileDownloader;
        this.dataService = dataService;
    }

    @GetMapping("uploadXlsFile")
    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Moscow")
    public void uploadDataFile() {
        fileDownloader.downloadXlsFile();
        dataService.updateDataBase();
    }

    @RequestMapping(value = "getEntities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationDAO> getEntities() {
        return dataService.getList();
    }

}
