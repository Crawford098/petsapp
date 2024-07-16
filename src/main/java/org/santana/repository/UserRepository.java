
package org.santana.repository;

import org.santana.model.UsersModel;

public class UserRepository extends Repository{

    public UserRepository() {
        super(new UsersModel());
    }

}