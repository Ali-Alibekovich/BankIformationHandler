package com.example.task.dao;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "organizations")
@Data
public class OrganizationEntity {

    @Id
    int cregnum;     //  Регистрационный номер


    String newctbank;   //  Вид

    @NotNull
    String ogrn;        //  ОГРН

    @NotNull
    String csname;      //  Наименование

    @NotNull
    String newcopf;     //  Организационно-правовая форма

    @NotNull
    String cdreg;       //  Дата регистрации Банком России

    String lic;         //  Статус лицензии

    @NotNull
    String strcuraddr;  //  Местонахождение

}
