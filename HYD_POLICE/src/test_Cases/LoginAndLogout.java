package test_Cases;

import java.io.IOException;

import org.testng.annotations.Test;

import business_Library.KeywordActions;

public class LoginAndLogout extends KeywordActions{

	public LoginAndLogout() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void LoginMethod() throws Exception {		
		KeywordActions a = KeywordActions.getActionInstance();
		a.executeKeyword("LoginAndLogout");
	}
}
