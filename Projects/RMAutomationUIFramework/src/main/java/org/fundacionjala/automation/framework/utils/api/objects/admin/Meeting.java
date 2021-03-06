package org.fundacionjala.automation.framework.utils.api.objects.admin;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Representation of MongoDB Meeting collection
 * @author alejandraneolopan
 */
public class Meeting extends RequestObject {
    public String organizer;
    public String title;
    public String start;
    public String end;
    public String location;
    public String roomEmail;
    public ArrayList<String> resources = new ArrayList<String>();
    public ArrayList<String> attendees = new ArrayList<String>();
    public String _id;
    
    public Meeting(String organizer, String title, String start, String end, 
	    String location, String roomEmail, String resources, List<String> attendees) {
	
	this.organizer = organizer;
	this.title = title;
	this.start = start;
	this.end = end;
	this.location = location;
	this.roomEmail = roomEmail;
	this.resources.add(resources);
	for (String attendee : attendees) {
	    this.attendees.add(attendee);
	}
	
    }
    
    /**
     * Initialize a Meeting from a JSON response from API service
     * @param obj JSON which contains one item of Meetings collection
     */
    public Meeting(JSONObject obj) {
	    this.organizer = obj.get("organizer").toString();
	    this.title = obj.get("title").toString();
	    this.start = obj.get("start").toString();
	    this.end = obj.get("end").toString();
	    this.location = obj.get("location").toString();
	    this.roomEmail = obj.get("roomEmail").toString();
	    JSONArray resourceList = obj.getJSONArray("resources");
	    for (int i = 0; i < resourceList.length(); i++){
		this.resources.add(resourceList.get(i).toString());
	    }
	    JSONArray attendeesList = obj.getJSONArray("attendees");
	    for (int i = 0; i < attendeesList.length(); i++){
		this.attendees.add(attendeesList.get(i).toString());
	    }
	    this._id = obj.get("_id").toString();
    }
    
    /**
     * Create JSON object with Meeting main data for POST request body
     * @return JSON object
     */
    public JSONObject getJsonObject() {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("organizer", this.organizer);
	jsonObject.put("title", this.title);
	jsonObject.put("start", this.start);
	jsonObject.put("end", this.end);
	jsonObject.put("location", this.location);
	jsonObject.put("roomEmail", this.roomEmail);
	jsonObject.put("resources", this.resources);
	jsonObject.put("attendees", this.attendees);
	jsonObject.put("_id", this._id);
	return jsonObject;
    }
    
    /**
     * Constructor by default
     */
    public Meeting() {
    }
}
