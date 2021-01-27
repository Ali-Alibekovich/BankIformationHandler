package com.example.task.utils;

import com.example.task.model.OrganizationDAO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Service;

@Service
public class OrganizationsFactory {

    public OrganizationDAO createOrganization (XSSFRow row) {
        OrganizationDAO organizationEntity = new OrganizationDAO();
        organizationEntity.setNewctbank(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
        organizationEntity.setCsname(row.getCell(3).getStringCellValue());
        organizationEntity.setNewcopf(row.getCell(6).getStringCellValue());
        organizationEntity.setCregnum((int) row.getCell(7).getNumericCellValue());
        organizationEntity.setCdreg(row.getCell(10).getStringCellValue());
        organizationEntity.setLic(row.getCell(11, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
        organizationEntity.setStrcuraddr(row.getCell(12).getStringCellValue());
        organizationEntity.setOgrn(row.getCell(13).getStringCellValue());
        return organizationEntity;
    }
}
