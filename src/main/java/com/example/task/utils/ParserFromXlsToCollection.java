package com.example.task.utils;

import com.example.task.model.OrganizationDAO;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ParserFromXlsToCollection {
    private final List<OrganizationDAO> organizationEntityList = new ArrayList<>();

    final private Logger log = Logger.getAnonymousLogger();

    final OrganizationsFactory organizationsFactory;

    @Value("${file.path}")
    private String filename;

    @Value("${file.sheet}")
    private String sheetList;

    public ParserFromXlsToCollection(OrganizationsFactory organizationsFactory) {
        this.organizationsFactory = organizationsFactory;
    }

    public void parse() {
        getOrganizationEntityList().clear();
        log.info(() -> "Parsing xlsx to Collection...");
        XSSFWorkbook workbook = readWorkbook();
        XSSFSheet sheet = workbook.getSheet(sheetList);
        Iterator<Row> rowIter = sheet.rowIterator();
        rowIter.next();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            OrganizationDAO organizationEntity = readOrganization(row);
            organizationEntityList.add(organizationEntity);
        }
        log.info(() -> "Xlsx successful parsed into Collection.");
    }

    public XSSFWorkbook readWorkbook() {
        try {
            OPCPackage pkg = OPCPackage.open(new File(filename));
            return new XSSFWorkbook(pkg);
        } catch (Exception e) {
            log.log(Level.WARNING, () -> "File read error!");
            return null;
        }
    }

    public OrganizationDAO readOrganization(XSSFRow row) {
        return organizationsFactory.createOrganization(row);
    }

    public List<OrganizationDAO> getOrganizationEntityList() {
        return organizationEntityList;
    }
}
