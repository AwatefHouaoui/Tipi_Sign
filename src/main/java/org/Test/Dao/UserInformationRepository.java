package org.Test.Dao;

import org.Test.Entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

}
