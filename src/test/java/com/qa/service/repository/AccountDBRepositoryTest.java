package com.qa.service.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {

	@InjectMocks
	private AccountDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("John", "Doe", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account has been sucessfully added\"}");
	}

	@Test
	public void testUpdateAccount() {
		String reply = repo.updateAccount(1L, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
	}

	@Test
	public void testDeleteAccount() {
		String reply = repo.deleteAccount(1L);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully deleted\"}");
	}

}
