package org.Test.Metier;

import java.util.List;
import org.Test.Dao.UserInformationRepository;
import org.Test.Entities.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationMetierImpl implements UserInformationMetier {

	@Autowired
	private UserInformationRepository userInformationRepository;

	@Override
	public UserInformation saveUserInformation(UserInformation u) {
		return userInformationRepository.save(u);
	}

	@Override
	public List<UserInformation> listUserInformation() {
		return userInformationRepository.findAll();
	}

}