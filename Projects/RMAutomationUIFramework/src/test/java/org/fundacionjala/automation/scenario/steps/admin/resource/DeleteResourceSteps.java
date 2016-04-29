package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteResourceSteps {

	@Given("^I have a resource created with the name \"([^\"]*)\"$")
	public void i_have_a_resource_created_with_the_name(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		login
			.setUserName("SamuelSahonero")
			.setPassword("Control123!")
			.clickOnSigInButton()
			.leftMenu
			.clickOnResourcesButton()
			.clickOnAddButton()
			.setResourceName(arg1)
			.setDisplayName(arg1)
			.setDescription(arg1)
			.clickOnSaveButton();
	}

	@When("^I delete the resource with the name \"([^\"]*)\"$")
	public void i_delete_the_resource_with_the_name(String arg1) throws Throwable {
	   ResourcePage resource = new ResourcePage();
	   
	   resource
	   		.selectResource(arg1)
	   		.clickOnRemoveButton();
	}

	@When("^Confirm the changes$")
	public void confirm_the_changes() throws Throwable {
	    RemoveResourcePage removeResource = new RemoveResourcePage();
	   
	    removeResource.clickOnRemoveButton();
	}

	@Then("^I verify that the resource with the name \"([^\"]*)\" has been deleted$")
	public void i_verify_that_the_resource_with_the_name_has_been_deleted(String arg1) throws Throwable {
		ResourcePage resource = new ResourcePage();
		
		boolean isResourcePresent = !resource.verifyResourceExist(arg1);
		
		Assert.assertTrue(isResourcePresent);
		
		BrowserManager.getDriver().quit();
	}
}