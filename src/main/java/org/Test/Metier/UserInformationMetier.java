package org.Test.Metier;

import java.util.List;
import org.Test.Entities.UserInformation;

public interface UserInformationMetier {
	public UserInformation saveUserInformation(UserInformation u);

	public List<UserInformation> listUserInformation();

}
