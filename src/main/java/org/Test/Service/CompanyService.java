package org.Test.Service;

import java.util.List;
import org.Test.Entities.Company;
import org.Test.Metier.CompanyMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyService {
	@Autowired
	private CompanyMetier companyMetier;

	@RequestMapping(value = "/Company", method = RequestMethod.POST)
	public Company saveCompany(@RequestBody Company c) {
		return companyMetier.saveCompany(c);
	}

	@RequestMapping(value = "/Company", method = RequestMethod.GET)
	public List<Company> listCompany() {
		return companyMetier.listCompany();
	}

}
