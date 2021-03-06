package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ResourceAPIManager {

	public static List<Resource> getRequest(String endPoint) throws UnirestException{
		HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint, "get");
		LogManager.info("GET Response:" + jsonResponse.getStatusText());
		JSONArray a = jsonResponse.getBody().getArray();
		//ask for empty to do
		List<Resource> resourceList = new ArrayList<Resource>();
		for (int i = 0; i < a.length() ; i++) {
			JSONObject obj = (JSONObject) a.get(i);
			resourceList.add(new Resource(obj));
		}
		return resourceList;
	}
	
	public static Resource postRequest(String endPoint, RequestObject requestBody ) throws UnirestException{
		Resource resource = new Resource();
		resource = (Resource) requestBody;
		JSONObject jsonObject = resource.getJsonObject();
		HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint, jsonObject, "post");
		LogManager.info("POST Response:" +jsonResponse.getBody().getObject());
		return new Resource(jsonResponse.getBody().getObject());
	}
	
	public static JsonNode deleteRequest(String endPoint, String resourceID ) throws UnirestException{
		String deleteEndPoint = endPoint + "/" + resourceID;
		LogManager.info("Deleting by API:" + deleteEndPoint);
		HttpResponse<JsonNode> jsonResponse = APIManager.request(deleteEndPoint, "delete");
		LogManager.info("DELETE Response:" + jsonResponse.getBody());
		return jsonResponse.getBody();
	}
}
