package com.example.task.service;

import com.example.task.dao.CRUDInterface.DataBaseCRUD;
import com.example.task.dao.OrganizationEntity;
import com.example.task.util.ParserFromXlsToCollection;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class DataService {
    final DataBaseCRUD dataBaseCRUD;
    final ParserFromXlsToCollection parserFromXlsToCollection;



    public DataService(DataBaseCRUD dataBaseCRUD, ParserFromXlsToCollection parserFromXlsToCollection1) {
        this.dataBaseCRUD = dataBaseCRUD;
        this.parserFromXlsToCollection = parserFromXlsToCollection1;
    }

    public void updateDataBase(){
        parserFromXlsToCollection.parse();
        List<OrganizationEntity> organizationEntityList = parserFromXlsToCollection.getOrganizationEntityList();
        cleaningDeletedInformation(organizationEntityList, (List<OrganizationEntity>) dataBaseCRUD.findAll());
        create(organizationEntityList, (List<OrganizationEntity>) dataBaseCRUD.findAll());
    }

    //Добавление в бд если нет записей в ней, если есть то обновление данных
    @Transactional
    public void create(List<OrganizationEntity> xlsOrganizationList, List<OrganizationEntity> dataBaseOrganizationList) {
        if (dataBaseOrganizationList.size() != 0) {
            xlsOrganizationList.removeAll(dataBaseOrganizationList);
        }
        if(xlsOrganizationList.size()!=0) {
            dataBaseCRUD.saveAll(xlsOrganizationList);
            System.out.println("Добавлено "+xlsOrganizationList.size()+" элементов");
        }
    }

    //Удаление того что есть в Бд, но нет в xls файле
    @Transactional
    public void cleaningDeletedInformation
            (List<OrganizationEntity> xlsOrganizationList, List<OrganizationEntity> dataBaseOrganizationList){
            dataBaseOrganizationList.removeAll(xlsOrganizationList);
            if(dataBaseOrganizationList.size()>0) {
                dataBaseCRUD.deleteAll(dataBaseOrganizationList);
                System.out.println("Удалено "+dataBaseOrganizationList.size()+" элементов");
            }
    }

    public List<OrganizationEntity> getList(){
        return (List<OrganizationEntity>) dataBaseCRUD.findAll();
    }

}
