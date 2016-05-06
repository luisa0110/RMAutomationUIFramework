package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConferenceRoomsPage {
   
	public ConferenceRoomsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
				
   @FindBy (xpath = ConferenceRoomsMap.ROOMS_ROWS) List<WebElement> rooms;
	public List<WebElement> getRooms()
	{
	   return rooms;
	}
	
	@FindBy (xpath = ConferenceRoomsMap.ENABLED_ROOMS_ROWS) List<WebElement> enabledButtons;
	private List<WebElement> getEnableRooms() {
       return enabledButtons;
	}
	
	@FindBy (xpath = ConferenceRoomsMap.DISABLED_ROOMS_ROWS) List<WebElement> disabledButtons;
	private List<WebElement> getDisabledRooms() {
       return disabledButtons;
	}
	
	private WebElement getRoom(String roomName)
	{
		for(WebElement room : getRooms()){
	    	String roomText = room.getText();
	    	roomText = roomText.replaceAll("\\s+","");
	    	if (roomText.equals(roomName)){
				return room;
			}
	    }
		return null;
	}
	
    public RoomInfoPage openConfigurationPage(String roomToModify) {
		
	    UIActions.doubleClick(getRoom(roomToModify));
		return new RoomInfoPage();
	}

	public ConferenceRoomsPage enableRoom() {
		
		return this;
	}
	public ConferenceRoomsPage disableRoom(String roomToModify) {
		return this;
	}
	
	public boolean roomIsEnabled(String roomName)
	{
		return true;
	}

}