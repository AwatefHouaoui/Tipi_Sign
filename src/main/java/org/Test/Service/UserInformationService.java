package org.Test.Service;

import java.util.List;
import org.Test.Entities.UserInformation;
import org.Test.Metier.UserInformationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInformationService {
	@Autowired
	private UserInformationMetier userInformationMetier;

	@RequestMapping(value = "/UserInfo", method = RequestMethod.POST)
	public UserInformation saveUserInformation(@RequestBody UserInformation u) {
		return userInformationMetier.saveUserInformation(u);
	}

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public List<UserInformation> listUserInformation() {
		return userInformationMetier.listUserInformation();
	}

}
