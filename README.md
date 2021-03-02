# Antssignments

Unit 8: Group Milestone - README Example
===

# TUNIN

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
A comprehensive app that uses Canvas API to create a todo app for Anteaters' assignments to keep track of and updated on.

### App Evaluation
- **Category:** Education
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer, to help UCI students keep on track for assignments.
- **Story:** Analyzes users music choices, and connects them to other users with similar choices. The user can then decide to message this person and befriend them if wanted.
- **Market:** Any individual with a UCINetID account could use this app.
- **Habit:** This app could be used often for students to know what is due to plan out their day.
- **Scope:** First we want to start gathering the information from Canvas and putting it onto our front-end, then we want to expand later by adding additional usages of the app such as a list of to-do's for each class.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User logins to their UCINetID account and accesses Canvas (DONE)
* User is able to click on a class to access a to-do list to enter in reminders and tasks

**Optional Nice-to-have Stories**

* Calendar implementation to see beyond the current day(s)/week(s) of assignments.

**Story Progress**

<img src="walkthrough1.gif" width=250><br>

### 2. Screen Archetypes

* Login 
* Assignments Screen
  * Recycler view of all the assignments from our classes
* To-do Screen
   * Clickable classes lead to another activity in which has all of the notes and reminders for the class


### 3. Navigation

**Tab Navigation** (Tab to Screen)



**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Music Selection (Or Queue if Optional) -> Jumps to Chat
* Profile -> Text field to be modified. 
* Settings -> Toggle settings

## Wireframes
<img src="wireframe.PNG" width=800><br>

## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | user          | Pointer to User| Canvas user |
   | assignments   | String     | name of each assignemnt on Canvas timeline |
   | dueAt         | DateTime | date when assignment is due (default field) |
   | className     | String | name of the student's class |

### Networking
#### List of network requests by screen
   - LoginScreen
      - (Read/GET) Get userID UCICanvas information
   - AssignmentsListFragment
      - (Read/GET) Assignments name + date information
   - ClassList
      - (Read/GET) Class names

## Notes
List of Issues During Development
* Push/Pull and Commit Issues
* * Starting the application and communicating when we were going to commit changes for the application was an issue while we got started. However, as we began to work more efficiently and communicated more frequently, this issue became quickly resolved.
* OAuth Implementation
* * Understanding the Canvas API and the types of information we were going to receive from the API was something that took us a while to figure out. The issue was that we did not have the availability of aa developer key and in order to retreive this, it would require a timely process of asking UCI for a key with a wide enough scope. Therefore, to resolve this issue, we used one person's information to build our application around.
* Bottom Navigation Bar
* * While implementing this feature onto our application, we desired for the bar to hae two distinct features. However, while implementing this crucial design, we came across issues that affected our MainActivity file, causing the application to crash when we tried to navigate through the login screen. This issue has been resolved and the navigation bar can navigate the user to their classes and their assignments.
