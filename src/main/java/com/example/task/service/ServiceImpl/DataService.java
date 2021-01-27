package com.example.task.service.ServiceImpl;

import com.example.task.CRUDInterface.DataBaseCRUD;
import com.example.task.model.OrganizationDAO;
import com.example.task.service.ServiceInterface;
import com.example.task.utils.ParserFromXlsToCollection;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;


@Service
public class DataService implements ServiceInterface {
    final private DataBaseCRUD dataBaseCRUD;
    final private ParserFromXlsToCollection parserFromXlsToCollection;
    final private Logger log = Logger.getAnonymousLogger();



    public DataService(DataBaseCRUD dataBaseCRUD, ParserFromXlsToCollection parserFromXlsToCollection1) {
        this.dataBaseCRUD = dataBaseCRUD;
        this.parserFromXlsToCollection = parserFromXlsToCollection1;
    }

    @Override
    public void updateDataBase(){
        log.info(()->"Updating database information...");
        parserFromXlsToCollection.parse();
        List<OrganizationDAO> organizationEntityList = parserFromXlsToCollection.getOrganizationEntityList();
        clear(organizationEntityList, (List<OrganizationDAO>) dataBaseCRUD.findAll());
        create(organizationEntityList, (List<OrganizationDAO>) dataBaseCRUD.findAll());
        log.info(()->"Database information is updated.");
    }

    @Override
    public void create(List<OrganizationDAO> xlsOrganizationList, List<OrganizationDAO> dataBaseOrganizationList) {
        if (!dataBaseOrganizationList.isEmpty()) {
            xlsOrganizationList.removeAll(dataBaseOrganizationList);
        }
        if(xlsOrganizationList.size()!=0) {
            dataBaseCRUD.saveAll(xlsOrganizationList);
            log.info(()->"Added "+xlsOrganizationList.size()+" elements.");
        }
    }
    @Override
    public void clear
            (List<OrganizationDAO> xlsOrganizationList, List<OrganizationDAO> dataBaseOrganizationList){
            dataBaseOrganizationList.removeAll(xlsOrganizationList);
            if(dataBaseOrganizationList.size()>0) {
                dataBaseCRUD.deleteAll(dataBaseOrganizationList);
                log.info(()->"Removed "+dataBaseOrganizationList.size()+" elements.");
            }
    }

    @Override
    public List<OrganizationDAO> getList(){
        return (List<OrganizationDAO>) dataBaseCRUD.findAll();
    }

}
