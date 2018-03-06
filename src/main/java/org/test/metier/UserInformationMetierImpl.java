package org.test.metier;

import java.util.List;
import org.test.dao.UserInformationRepository;
import org.test.entities.UserInformation;
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
