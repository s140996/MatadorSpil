package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import Player.Account;


public class AccountTest {

	Account Account = new Account(5000);
	
	
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

}
