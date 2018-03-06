package org.test.metier;

import java.util.List;
import org.test.entities.UserInformation;

public interface UserInformationMetier {
	public UserInformation saveUserInformation(UserInformation u);

	public List<UserInformation> listUserInformation();

}
