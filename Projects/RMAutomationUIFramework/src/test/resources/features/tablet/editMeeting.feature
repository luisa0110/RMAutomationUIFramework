Feature: Edit meeting
	I want to edit alll fields allowed in a created meeting

#Scenario 1
@DeleteMeeting
Scenario: The organizer is not able to modified in a meeting created
Given I have a created meeting with "Pizza" subject in the "Room152" room 
 When I display the meeting "Pizza" in the "Room152" room
 Then Validate that in the meeting "Pizza" of the "Room152" room the organizer is not able to modified 


#Scenario 2
@DeleteMeeting
Scenario: The attendees text field is modified in a meeting created
Given I had a created meeting in the "Room153" room, with "New Project RM" subject and attendees: 
|RoomManager5@roommanager.local|
|RoomManager6@roommanager.local|
When I modified the meeting "New Project RM" in the "Room153" room  adding the attendees
|RoomManager7@roommanager.local|
|RoomManager8@roommanager.local|
  And Save the changes
 Then Validate that in the meeting "New Project RM" of the "Room153" room the attendees has been modified with
|RoomManager5@roommanager.local|
|RoomManager6@roommanager.local|
|RoomManager7@roommanager.local|
|RoomManager8@roommanager.local|

#Scenario 3
@IMP-DisableImpersonationUI @DeleteMeeting
Scenario Outline: A meeting created is modified when the information meeting  is edited using impersonation
Given I have a created in the "<room>" room with subject "meeting QADEV06"
 And the schedule with start time: "16:00" end time "16:30"
 And attendees
|RoomManager1@roommanager.local|
|RoomManager4@roommanager.local|
 And body "This is a new meeting"
 And impersonation is enabled by UI
When I modify the "<field>" with "<value>" in the "meeting QADEV06" meeting in "<room>" room
 And Confirm the changes with the user "RoomManager1" and password "Control*123"
Then Validate that the "<field>" has been modified with the value "<value>" of the "meeting QADEV06" in the "<room>" room  

#field to modify (subject, startTime, endTime, attendees, body)
Examples:
|room|field		|value													|
|Room171|subject	|new subject|
|Room172|startTime|15:30|
|Room173|endTime	|18:00|
|Room174|attendees|RoomManager5@roommanager.local	|
|Room175|body			|This meeting has been modified	|

#Scenario 4
@DeleteMeeting @DeleteOutOfOrders
Scenario: A message is displayed when a meeting is modified over the same time of out of order time
Given I have a created meeting from "08:00" to "09:00", with "Meeting QDAEV06" subject in the "Room017" room
  And the "Room017" room is out of order
 When I want to modify the meeting  "Meeting QDAEV06" in the "Room017" room over the same time of out of order
 Then validate that an error message is displayed with conflict of time interval in the "Meeting QDAEV06" of the "Room017" room

#Scenario 5
@DeleteMeeting
Scenario: The schedule in a meeting is modified when the new time is entered
Given I have a created meeting with "meeting QADEV06" subject in the "Room157" room
When I modify the "meeting QADEV06" meeting in "Room157" room from "18:00" to "19:30"
Then validate that "meeting QADEV06" meeting in the "Room157" room has been modified with schedule from "18:00" to "19:30"

#Scenario 6
@DeleteMeeting
Scenario: The subject text field is modified when it is edited
Given I have a created meeting with "meeting QADEV06" subject in the "Room158" room
When I modify the "meeting QADEV06" meeting in "Room158" room with new subject "subject modified"
Then validate that "subject modified" subject has been modified in the "Room158" room

#Scenario 7
@DeleteMeeting
Scenario: The body text field is modified when it is edited
Given I have a created meeting with "meeting QADEV06" subject in the "Room159" room
When I modify the "meeting QADEV06" meeting in "Room159" room with new body "This is a meeting modified in body field"
Then validate that the body "This is a meeting modified in body field" has been modified in "meeting QADEV06" in the "Room159" room

#Scenario 8
@DeleteTwoMeetings
Scenario: An error message is displayed when a meeting is modified over the same interval than other meeting
Given I have a created meeting with "first meeting" subject in the "Room160" room 
  And I have a created meeting from "08:00:00.000" to "09:00:00.000", with "second meeting" subject in the "Room160" room
 When I want to modify the schedule of the meeting "first meeting" from "07:00" to "11:00"
 Then validate that an error message is displayed in the "first meeting" and "second meeting" meetings of the "Room160" room 

#Scenario 9
@DeleteMeeting
Scenario: An error message is displayed when start time is modified with a bigger value than end time
Given I have a created meeting from "08:00:00.000" to "09:00:00.000", with "meeting QADEV06" subject in the "Room162" room 
 When I want to modify the start time in the meeting "meeting QADEV06" with "12:30"
 Then validate that an error message is displayed in the startTime field of the meeting "meeting QADEV06" in the "Room162" room
 
#Scenario 10
Scenario: When created meeting is clicked the information to modify is displayed
Given I have a created in the "Room163" room with subject "meeting QADEV06"
And the schedule with start time: "07:00" end time "08:30"
 And attendees
|RoomManager1@roommanager.local|
|RoomManager4@roommanager.local|
 And body "This is a new meeting"
When I select the meeting "meeting QADEV06"
Then validate that information meeting is displayed subject "meeting QADEV06"
 And schedule from "07:00" to "08:30"
 And body is "This is a new meeting" 
 And attendees: 
|RoomManager1@roommanager.local|
|RoomManager4@roommanager.local|

#Scenario 11
@DeleteTwoMeetings
Scenario: When subject meeting is modified with the same subject of other meeting
Given I have a created meeting with "first meeting" subject in the "Room164" room
  And I have a created meeting from "08:00:00.000" to "09:00:00.000", with "second meeting" subject in the "Room164" room
 When I modify the meeting "second meeting" with same subject "first meeting" the first meeting in the "Room164" room
 Then Validate that there two meeting with subject "first meeting" in the "Room164" room

#Scenario 12
@DeleteMeeting
Scenario: The schedule is modified in future time when a meeting in past time is edited
Given I have a created meeting in past time with "QADEV06 meeting" subject in the "Room165" room
 When I modify the schedule from "23:00" to "23:45" in the meeting "QADEV06 meeting" of the "Room165" room
Then validate that "QADEV06 meeting" meeting in the "Room165" room has been modified with schedule from "23:00" to "23:45"

#Scenario 13 This work only with times o'clock, hours positive integer
@DeleteMeeting
 Scenario Outline: The schedule of a created meeting is modified doing drag and drop
Given I have a created meeting from "<start>" to "<end>", with "Meeting Scheduled" subject in the "Room201" room
When I modify the schedule in the "Meeting Scheduled" meeting on "Room201" drag and drop the whole meeting to the "<direction>" "<hours>" hour 
Then Validate that in the "Room201" the schedule start "<start>" and end time "<end>" on "Meeting Scheduled" were "<status>" "<hours>" hour
Examples:
|start |end |direction	|status	|hours |
|08:00	|09:00	|left		|ahead	|1	|
|08:00	|09:00	|right	|delayed	|1	|
#Scenario 12 This work only with times o'clock, at least 2 hours of duration, hours must be positive integer
@DeleteMeeting 
 Scenario Outline: The start time in a meeting created is modified when the start time in time line is pulled
Given I have a created meeting from "<start>" to "<end>", with "Meeting Start pulled to <direction>" subject in the "<room>" room
When I modify the schedule in the "Meeting Start pulled to <direction>" meeting on "<room>" drag and drop the start time in "<direction>" "<hours>" hour 
Then Validate that in the "<room>" the schedule start "<start>" with end time "<end>" on "Meeting Start pulled to <direction>" were "<status>" "<hours>" hour
Examples:
|room	|start |end |direction	|status	|hours |
|Room203|08:00	|10:00	|left		|ahead	|1	|
|Room204|08:00	|10:00	|right	|delayed	|1	|

@DeleteMeeting
 Scenario Outline: The end time in a meeting created is modified when the end time in time line is pulled
Given I have a created meeting from "<start>" to "<end>", with "Meeting End pulled to <direction>" subject in the "<room>" room
When I modify the schedule in the "Meeting End pulled to <direction>" meeting on "<room>" drag and drop the end time in "<direction>" "<hours>" hour 
Then Validate that in the "<room>" the schedule end "<end>"  with start time "<start>" on "Meeting End pulled to <direction>" were "<status>" "<hours>" hour
Examples:
|room	|start |end |direction	|status	|hours |
|Room205|08:00	|10:00	|left		|ahead	|1	|
|Room206|08:00	|10:00	|right	|delayed	|1	|


 