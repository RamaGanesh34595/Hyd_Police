package test_Cases;

import java.io.IOException;

import org.testng.annotations.Test;

import business_Library.KeywordActions;

public class TestCase_002 extends KeywordActions {

	public TestCase_002() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void LoginMethod() throws Exception {		
		KeywordActions a = KeywordActions.getActionInstance();
		a.executeKeyword("TestCase_002");
	}

}
