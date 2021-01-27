package com.example.task.CRUDInterface;

import com.example.task.model.OrganizationDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseCRUD extends CrudRepository <OrganizationDAO, Long> {

}
