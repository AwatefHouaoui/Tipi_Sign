package org.Test.Metier;

import java.util.List;
import org.Test.Dao.CompanyRepository;
import org.Test.Entities.Company;
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
