package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private AccountRepository repo;

	public String getAllAccounts() {
		LOGGER.info("In AccountServiceImpl getAllAccounts ");
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		return repo.createAccount(account);
	}

	@Override
	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);

	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}
}
