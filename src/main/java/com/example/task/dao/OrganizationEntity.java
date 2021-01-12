package com.example.task.dao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "organizations")
@Getter
@Setter
public class OrganizationEntity {

    @Id
    private String id;

}
