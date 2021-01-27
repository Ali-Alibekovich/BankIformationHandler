package com.example.task.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "organizations")
@Data
public class OrganizationDAO {

    @Id
    private int cregnum;     //  Регистрационный номер


    private String newctbank;   //  Вид

    @NotNull
    private String ogrn;        //  ОГРН

    @NotNull
    private String csname;      //  Наименование

    @NotNull
    private String newcopf;     //  Организационно-правовая форма

    @NotNull
    private String cdreg;       //  Дата регистрации Банком России

    private String lic;         //  Статус лицензии

    @NotNull
    private String strcuraddr;  //  Местонахождение

}
