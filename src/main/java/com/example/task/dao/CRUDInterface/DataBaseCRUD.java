package com.example.task.dao.CRUDInterface;

import com.example.task.dao.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseCRUD extends CrudRepository <OrganizationEntity, Long> {

}
