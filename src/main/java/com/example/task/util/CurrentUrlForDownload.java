package com.example.task.util;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class CurrentUrlForDownload {

    //Creating request url with current date
    @SneakyThrows
    public String url() {
        Document doc = Jsoup.connect("http://www.cbr.ru/banking_sector/credit/FullCoList/").get();
        String currentUrl = doc.getElementsByClass("b-export_button").attr("href");
        return "http://www.cbr.ru" + currentUrl;
    }
}
