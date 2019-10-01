# TeamScheduler
TeamScheduler is an application for scheduling, tracking and reporting of customer appointments.

## Requirements:
**A.   Create a log-in form that can determine the user’s location and translate log-in and error control messages (e.g., “The username and password did not match.”) into two languages.**

  This was accomplished by using the current locale and a resource bundle in the LoginController class.  If your locale indicates you are in a spanish speaking country the login, success, and failure windows will translate the text for the user.
  ![Image of translated login](https://trello-attachments.s3.amazonaws.com/5d601d99a2f58d88a8e37ef3/1034x716/e1a97d614514374f6f33ae1a3d6554e0/image.png)
  ![Image of translated login](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/e95de6a0e2e490cfe040e00c53012efe/image.png)
  ![Image of translated login](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/6d57920587b99990fd0ae056a6ee7c72/image.png)


**B.   Provide the ability to add, update, and delete customer records in the database, including name, address, and phone number
C.   Provide the ability to add, update, and delete appointments, capturing the type of appointment and a link to the specific customer record in the database.**

From the MainMenu view the user is able to add, modify, delete appointments or customers:
![Image of MainMenu](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/25cbc086f6257b11243061b2b7f2cad5/image.png)

Adding new customers and Appointments
![Image of add customers](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/cc84c4d431262a2969e4069c77d3ec37/image.png)
![Image of add appts](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/bd46b022d76f18e555a899b52a2a1f24/image.png)

Identical views are used for the Modification of customer and appt records, when modifying, the selected appt/customer on the MainMenu is passed and used to populate the fields for modification by the user.

**D.   Provide the ability to view the calendar by month and by week.**

The MainMenu Appointments tableview can be filtered to show only appts that are within the next month or week.
 ![Image of MainMenu filtering](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/37516ee264df725c031f61a73faafaa6/image.png)

**E.    Provide the ability to automatically adjust appointment times based on user time zones and daylight saving time.**

 Appointment times are adjusted according to the logged in user by performing the conversion as the data is retrieved from the DB and converting back to UTC before new appt is added to the DB in the AppointmentDao class.

`LocalDateTime
					.parse(appointment.getStart(), dtf)
					.atZone(ZoneId.systemDefault())
					.withZoneSameInstant(utcZoneId)
					.format(dtf)`

**F.   Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.**

Below is a screenshot of each alert the user will see when an exception is reached.  I used custom exceptions in a separate package to add more detail and accuracy to each of the exceptions, as well as reuse them or alter the exception details.  I use both throws/throw and try/catch mechanisms for exception control.  

•   scheduling an appointment outside business hours

 ![Image of out of business hours ex](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/f0139d4b56a7536de46450eee1503c11/image.png)

•   scheduling overlapping appointments

![Image of overlapping appts ex](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/fd527ad9c8a9cb9aacbad173a8f78950/image.png)

•   entering nonexistent or invalid customer data

![Image of invalid customer data](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/fd527ad9c8a9cb9aacbad173a8f78950/image.png)

•   entering an incorrect username and password

![Image of bad creds](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/d9d85b1a449b717f74a90aed7853ade5/image.png)


**G.  Write two or more lambda expressions to make your program more efficient, justifying the use of each lambda expression with an in-line comment.**
 Both lamda expressions are in the MainMenuController along with in-line justifications.  They are tagged with "Requirement Lambda #(1|2)"
 
 
**H.   Write code to provide an alert if there is an appointment within 15 minutes of the user’s log-in.**

Upon successful login the user will be notified with the following alert if any appts are in the next 15 minutes with the customer and start time.

![Image of appt notification](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/53ecfc4ab4737d4a3c501fa1b0599d5b/image.png)

**I.   Provide the ability to generate each  of the following reports:**

Each report is contained in it's own view and tableview.  For the optional report I chose to present how many appts each customer had.  Screenshots of each below.

•   number of appointment types by month
![Image of appt type report](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/ffede1a9e8daf4f7699574f2ab6505c6/image.png)

•   the schedule for each consultant
![Image of team schedule](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/6ca23b0f1f9a2941f6c65c0fa2c3019f/image.png)

•   one additional report of your choice
![Image of customer appts report](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/f93da0d08e0f0cd580f109140501b5c5/image.png)


**J.   Provide the ability to track user activity by recording timestamps for user log-ins in a .txt file. Each new record should be appended to the log file, if the file already exists.**

All successful and failed login attempts are logged in the UserLoginController and output to a file relative to the root project folder called log.txt.
![Image of customer appts report](https://trello-attachments.s3.amazonaws.com/5c44b3e3573060864433706c/5d601d99a2f58d88a8e37ef3/fcb3765974750367a618e7e2819bb50e/image.png)




Planned Improvements:
-----

- Protect sql inserts from duplicate entries.  
- Encrypt persisted passwords in db.
- Create denormalized views from the db and move city, country, and address to Customer class and customer, user, appt to an appt class.  This would reduce the number of queries used just to view and create data.
- Restrict ability to create an appt with an End Date before the Start Date.
- The factory classes while are NOT a pure factory design pattern and I purposely bypass this in a few controller methods(This was to showcase more than one implementation of data retrieval and manipulation).  This was meant to showcase abstracting the complicated creation of customer and appointment objects from the caller.  Reducing the complexity of object creation by introducing more specific CRUD operations.  That way instead of retrieving the full customer object every time a value is needed, the value can be retrieved from the DB.
- Protect the singleton Environment from thread/access conflicts.  Right now all operations on the singleton are performed serially, but not at the same time so there isn't much chance of a lock, however best practice and any growth of the application could increase that risk.
-Better exception handling.  Several IO and SQL operations would benefit from more detailed exception messages helping determine which CRUD operations were being performed if the stack trace isn't obvious.
