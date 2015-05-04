package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Player.Account;



public class AccountTest {

	private Account Account;

	@Before
	public void setUp(){
	this.Account = new Account(5000);
	}
	
	@Test
	public void testAccount() {
		Account = new Account(2500);
		assertEquals(Account.getBalance(),2500);
	}
	
	@Test
	public void testgetBalance() {
		Account.getBalance();
		assertEquals(Account.getBalance(),5000);
	}
	
	@Test
	public void testsetBalance() {
		Account.setBalance(10000);
		assertEquals(Account.getBalance(),10000);
	}
	
	@Test
	public void testdeposit() {
		Account.deposit(5000);
		assertEquals(Account.getBalance(),10000);
	}
	
	@Test
	public void testwithdraw() {
		Account.withdraw(2000);
		assertEquals(Account.getBalance(),3000);
	}

}
