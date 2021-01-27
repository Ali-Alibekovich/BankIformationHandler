package com.example.task.utils;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CurrentUrlForDownload {
    final private Logger log = Logger.getAnonymousLogger();

    //Creating request url with current date
    @SneakyThrows
    public String url() {
        log.info(()->"Getting url from http://www.cbr.ru for downloading xlsx file...");
        Document doc = Jsoup.connect("http://www.cbr.ru/banking_sector/credit/FullCoList/").get();
        String currentUrl = doc.getElementsByClass("b-export_button").attr("href");
        log.info(()->"Url received successfully.");
        return "http://www.cbr.ru" + currentUrl;
    }
}
