package com.example.task.util;

import com.example.task.dao.OrganizationEntity;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ParserFromXlsToCollection {
    List<OrganizationEntity> organizationEntityList = new ArrayList<>();

    @Value("${file.path}")
    String filename;

    @Value("${file.sheet}")
    String sheetList;

    //Парсер
    public void parse() {
        XSSFWorkbook workbook = readWorkbook();
        XSSFSheet sheet = workbook.getSheet(sheetList);
        getOrganizationEntityList().clear();
        Iterator<Row> rowIter = sheet.rowIterator();
        rowIter.next();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            OrganizationEntity organizationEntity = readOrganization(row);
            if (organizationEntity != null) {
                organizationEntityList.add(organizationEntity);
            }
        }
    }

    //Возращаем HSSFWorkBook для чтения из xls
    public XSSFWorkbook readWorkbook() {
        try {
            OPCPackage pkg = OPCPackage.open(new File(filename));
            return new XSSFWorkbook(pkg);
        } catch (Exception e) {
            return null;
        }
    }

    //чтение организаций
    public OrganizationEntity readOrganization(XSSFRow row) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        try {
            organizationEntity.setNewctbank(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            organizationEntity.setCsname(row.getCell(3).getStringCellValue());
            organizationEntity.setNewcopf(row.getCell(6).getStringCellValue());
            organizationEntity.setCregnum((int) row.getCell(7).getNumericCellValue());
            organizationEntity.setCdreg(row.getCell(10).getStringCellValue());
            organizationEntity.setLic(row.getCell(11, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            organizationEntity.setStrcuraddr(row.getCell(12).getStringCellValue());
            organizationEntity.setOgrn(row.getCell(13).getStringCellValue());
        } catch (Exception ex) {
            return null;
        }
        return organizationEntity;
    }

    public List<OrganizationEntity> getOrganizationEntityList() {
        return organizationEntityList;
    }
}
