# TeamScheduler
TeamScheduler is an application I created for my B.S. in Computer Science.  The project was meant to demonstrate competency in object oriented patterns, lambda functional programming, SQL/JDBC integration, encapsulation, abstraction, exception handling, APIs, Internationalization/Localization and JAVA.   

The scenario specified requirements for an extendable application that a global service team could use for scheduling, tracking and reporting of customer appointments.

## Pre-Requisites:
Java 8
MySql 5.5.62

String members for DATABASENAME, DB_URL, USERNAME, and PASSWORD in the DBConnection class will need to be updated according to the sql instance you are connecting to:
	`
	private static final String DATABASENAME = "db_name";
	private static final String DB_URL = "jdbc:mysql://localhost/" + DATABASENAME;
	private static final String USERNAME = "user_name";
	private static final String PASSWORD = "password";
        private static final String DRIVER = "com.mysql.jdbc.Driver";
	`

## Design:
A combination of the DAO and MVC design pattern were chosen to create the main structure of the program.  

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5da0c9e731f66c1af3141080/ca97aa5387d29974b94c2b0f27462786/image.png">

The DAO serves as an abstraction layer between the database and Java data objects found in the model.  When a list of appointments is needed a new DAO object can be instantiated and used to access and return a new list of Java objects one for each row in the database.  The Dao API is implemented by all each object's DAO classes enabling a standard set of CRUD operations.  This makes it easy to extend the applications schema and create new DAOs quickly and uniformally.  

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5da0c9e731f66c1af3141080/5a928f6a81db1f26a617ec01d372c652/image.png">

The MVC implementation was used to organize and abstract the Java objects in model, away from the view.  This is done by using controller methods that are called when certain user actions are performed on the view as an interface to the model.

**The overall data flow through the program:** When a user action on the view calls an associated controller method that in turn instantiates a DAO used to return a list of model objects and these are then displayed or updated on the view.

**Other Patterns:**

- Factory Pattern is introduced when creating customer objects, however other model objects have not had this pattern implemented yet(see planned improvements).  This pattern helps the caller by providing an interface for creating objects especially if they are immutable.
- Singleton Pattern was implemented in order to share and restrict the copies of the hosts session information like the user across the program.  It also ensures the object is only created once(after user login).  Access to the singleton object is not synchronized to support concurrency(See planned improvements).
- JavaFX resource bundles are used to allow for easy addition of new language files and extending support for internationalization.
- Application specific exception handling is done using extended exception classes.  This pattern was established after most of the project was completed and should be followed to allow for code reuse and organization.


## Usage and Features:

**Internationalization:**

The Log-in form can determine the user’s location and translate log-in and error control messages by using the current locale and a resource bundle in the LoginController class.  If your locale indicates you are in a Spanish speaking country the login, success, and failure windows will , success, and failure windows translate the text for the user.

  <img src="https://trello-attachments.s3.amazonaws.com/5d601d99a2f58d88a8e37ef3/1034x716/e1a97d614514374f6f33ae1a3d6554e0/image.png" width="600">
  <img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/e95de6a0e2e490cfe040e00c53012efe/image.png" width="400">
  
 <img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/6d57920587b99990fd0ae056a6ee7c72/image.png" width="400">


**Manage Customers and Appointments:**

Add, update, and delete appointments and customers records in the database.  Customer appointments are linked to customers.  If a customer is deleted all of their appointments are deleted too.

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/25cbc086f6257b11243061b2b7f2cad5/image.png" width="700">

Adding new customers and Appointments:

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/cc84c4d431262a2969e4069c77d3ec37/image.png" width="300">
<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/bd46b022d76f18e555a899b52a2a1f24/image.png" width="510">

Identical views are used for the Modification of customer and appt records, when modifying, the selected appt/customer on the MainMenu is passed and used to populate the fields for modification by the user.

**This Month's and This Week's Appointments:**

The MainMenu Appointments tableview can be filtered to show only appts that are within the next month or week.
 <img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/37516ee264df725c031f61a73faafaa6/image.png" width="700">

**AutoMagic TimeZone adjustment**

 Appointment times are adjusted according to the logged in user by performing the conversion as the data is retrieved from the DB and converting back to UTC before new appt is added to the DB in the AppointmentDao class.

`LocalDateTime.parse(appointment.getStart(),dtf).atZone(ZoneId.systemDefault()).withZoneSameInstant(utcZoneId).format(dtf)`

**Exception Handling**

Below is a screenshot of each alert the user will see when an exception is reached.  I used custom exceptions in a separate package to add more detail and accuracy to each of the exceptions, as well as reuse them or alter the exception details.  Both throws/throw and try/catch mechanisms for exception control.  

•   scheduling an appointment outside business hours

 <img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/f0139d4b56a7536de46450eee1503c11/image.png" width="400">

•   scheduling overlapping appointments

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/fd527ad9c8a9cb9aacbad173a8f78950/image.png" width="400">

•   entering nonexistent or invalid customer data

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/fd527ad9c8a9cb9aacbad173a8f78950/image.png" width="400">

•   entering an incorrect username and password

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/d9d85b1a449b717f74a90aed7853ade5/image.png" width="400">


**Upcoming Meeting Notification:**

Upon successful login the user will be notified with the following alert if any appts are in the next 15 minutes with the customer and start time.

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/53ecfc4ab4737d4a3c501fa1b0599d5b/image.png" width="400">

**Reporting:**

Each report is contained in it's own view and tableview. 

•   number of appointment types by month

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/ffede1a9e8daf4f7699574f2ab6505c6/image.png" width="600">

•   the schedule for each consultant

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/6ca23b0f1f9a2941f6c65c0fa2c3019f/image.png" width="600">

•   one additional report of your choice

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/f93da0d08e0f0cd580f109140501b5c5/image.png" width="600">


**User Activity Logging**

All successful and failed login attempts are logged in the UserLoginController and output to a file relative to the root project folder called log.txt.

<img src="https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/fcb3765974750367a618e7e2819bb50e/image.png" width="600">



Planned Improvements:
-----
**Note: Planned Improvements may never be implemented.  This project is NOT being actively maintained other than for portfolio purposes.**

- Write Unit and Integration tests
- Protect sql inserts from duplicate entries.  
- Encrypt persisted passwords in db.
- Create denormalized views from the db and move city, country, and address to Customer class and customer, user, appt to an appt class.  This would reduce the number of queries used just to view and create data.
- Restrict ability to create an appt with an End Date before the Start Date.
- The DAO implementation is a bit different in some areas.  In some scenarios a list of objects are returned and then that dataset is manipulated as an in-memory object and returns a filtered value to the view rather than performing the filtering/aggregations in the targeted sql views which would be a better solution.
- The factory classes while are NOT a pure factory design pattern and I purposely bypass this in a few controller methods(This was to showcase more than one implementation of data retrieval and manipulation).  This was meant to showcase abstracting the complicated creation of customer and appointment objects from the caller.  Reducing the complexity of object creation by introducing more specific CRUD operations.  That way instead of retrieving the full customer object every time a value is needed, the value can be retrieved from the DB.
- Protect the singleton Environment from thread/access conflicts.  Right now all operations on the singleton are performed serially, but not at the same time so there isn't much chance of a lock, however best practice and any growth of the application could increase that risk.
-Better exception handling.  Several IO and SQL operations would benefit from more detailed exception messages helping determine which CRUD operations were being performed if the stack trace isn't obvious.
- Exception handling should be expanded to all user input forms.  Most of the error handling was created to be extra verbose to show off functionality, however these messages should be paired down to be more meaningful and concise for users.


## Support
If you have any questions about this repo or the TeamScheduler application please reach out to my email <a href="mailto:clair.james88@gmail.com?subject=[GitHub]%20TeamScheduler%20Question">clair.james88@gmail.com</a>
## Contributing
Closed

##Acknowledgement:
Sierra, K. (2018). OCP Java SE 8 Programmer II Exam Guide (Exam 1Z0-809). Retrieved from <a href="http://search.ebscohost.com.wgu.idm.oclc.org/login.aspx?direct=true&db=edsssb&AN=edsssb.bks000142597&site=eds-live&scope=site">http://search.ebscohost.com.wgu.idm.oclc.org/login.aspx?direct=true&db=edsssb&AN=edsssb.bks000142597&site=eds-live&scope=site</a>

