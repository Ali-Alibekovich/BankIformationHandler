package com.example.task.service;

import com.example.task.model.OrganizationDAO;

import java.util.List;


public interface ServiceInterface {
    void updateDataBase();

    void create
            (List<OrganizationDAO> xlsOrganizationList, List<OrganizationDAO> dataBaseOrganizationList);
    void clear
            (List<OrganizationDAO> xlsOrganizationList, List<OrganizationDAO> dataBaseOrganizationList);
    List<OrganizationDAO> getList();

}
