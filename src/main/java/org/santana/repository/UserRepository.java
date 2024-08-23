package org.santana.repository;

import org.santana.model.UsersModel;
import org.santana.repository.core.Repository;

public class UserRepository extends Repository{

    public UserRepository(){
        super(new UsersModel());
    }

    public UserRepository(UsersModel model){
        super(model);
    }
}