package org.test.metier;

import java.util.List;
import org.test.dao.CompanyRepository;
import org.test.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyMetierImpl implements CompanyMetier {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company saveCompany(Company c) {
		return companyRepository.save(c);
	}

	@Override
	public List<Company> listCompany() {
		return companyRepository.findAll();
	}

}
