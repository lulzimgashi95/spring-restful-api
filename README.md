#Spring Restful API
Project Management Restful API with Spring 4 

#Prerequisites
**Java**

**Database**

## Other

**Postman** for tests (optional)

**[Download Postman](https://www.getpostman.com)**

<br />
#Installing

**1.** ``Create local Database using DDL queries located``[here](https://github.com/lulzimgashi95/spring-restful-api/blob/master/src/main/resources/database.sql) ``(I used MySQL local server)``

**2.** ``Clone repository in your local system following steps described``[here](https://help.github.com/articles/cloning-a-repository/)

**3.** ``Open project in any IDE and make sure that Maven download all dependencies that are in pom.xml (you can do that also in commandline, but it's harder way if you didnt done that before)``


**4.** ``Put your DB infos in that file for each particular field``[here](https://github.com/lulzimgashi95/spring-restful-api/blob/master/src/main/resources/db.properties)

**5.** ``Configure any servlet container I used Tomcat and run project``

<br />
#Built With
```
  • Spring 4 - The platform used
  
  • Maven - Dependency Management
  
  • Jackson - Data-Binding
```

<br />
# Tests - Download Postman Collection provided [here](https://github.com/lulzimgashi95/spring-restful-api/blob/master/src/main/tests/ProjectManagementRestful.postman_collection.json) (36)Requests

## Steps to import collection to Postman

**1.** ``Open Postman and Go To File Menu``

**2.** ``Click Open Option``

**3.** ``Locate ProjectManagementRestful.postman_collection.json``

**4.** ``New Folder is created in left sidebar named "Project Management"``

<br />
## How to use Postman to add data to API
 **1.** Under **ProjectManagement** Folder there are some other folders click the one named **Project**
  
  **2.** Click in **Add New Project** 
  
  **3.** Change url mine is **http://**localhost:8181**/projects/add** 
  
  **4.** Go to *Body* then you should see json like this
```
{
	"name":"Project",
	"description":"ProjectDescription",
	"startDate":"2016-01-01",
	"deadLine":"2016-01-01"
}
```
**5.** Edit fields as you want then click SEND

**6.** If you added successfully new Project you will recieve *Done* message else you will receive message which tell you what is wrong

<br />
## How to use Postman to retrieve data from API
  **1.** Under **ProjectManagement** Folder there are some other folders click the one named **Project**
  
  **2.** Click in **GET ALL PROJECTS** 
  
  **3.** Change url mine is **http://**localhost:8181**/projects/** and click SEND button
  
  **4.** You should recieve response something like this
  ```
[
  {
    "id": "1178a602-6b4e-492c-8bf3-2f31d6edfaff",
    "name": "Update",
    "description": "descUpdate",
    "startDate": "2016-01-03",
    "deadLine": "2015-01-03",
    "members": [
      {
        "id": "2f9f54fe-7c17-489a-8192-9ad5592260de",
        "firstName": "firstName",
        "lastName": "lastName",
        "startDate": "2016-01-01",
        "position": "2017-01-01",
        "activities": [
          {
            "id": "a39dab53-82fc-4d22-a49c-4a73d32d8654",
            "name": "name",
            "details": "detail",
            "memberId": "2f9f54fe-7c17-489a-8192-9ad5592260de"
          }
        ],
        "image": null,
        "projectId": "1178a602-6b4e-492c-8bf3-2f31d6edfaff"
      }
    ],
    "sponsors": [
      {
        "id": "e58ba0db-3237-41b4-bbb9-80a85e6c295e",
        "name": "name",
        "amount": 2,
        "comment": "comment1",
        "projectId": "1178a602-6b4e-492c-8bf3-2f31d6edfaff"
      }
    ],
    "comments": [
      {
        "id": "ab2d6b2c-fa23-4325-a46f-4068fdd04ae3",
        "comment": "comment1",
        "date": "2016-01-01",
        "projectId": "1178a602-6b4e-492c-8bf3-2f31d6edfaff"
      }
    ]
  }
]
``` 

###You can use example template to add Project with all childs
```
Name: Example Template

Method: Post
```

#Model
![alt tag](http://image.prntscr.com/image/ef7202737659407192fa4215d3621af5.png)
