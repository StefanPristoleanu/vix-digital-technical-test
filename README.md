# vix-digital-technical-test

## How to run it

The backend uses Java 11 and gradle, so after running inside the project folder
 `./gradlew build`
one is able to start up the application by running the main class in an IDE

https://github.com/StefanPristoleanu/vix-digital-technical-test/blob/main/exercise/src/main/java/vix/digital/exercise/ExerciseApplication.java

The database details are in the application.properties file if they are needed, and the server port is 8080.

For the frontend application, please try 
`yarn`
`yarn start`
inside the project folder and the server should start on port 3000.

## How I built it

I went for a back-end and front-end split, with Java 11 SpringBoot, an embedded H2 database and ReactJS as the main technologies.

The 'Basic' product was polling vix.digital, so by making it take in any url it was a bit easier for the later tasks.

I then gave some thought to the information I should poll for. In the end I thought the following would be useful:

* status code
* response time
* timestamp

which had been persisted into the `history` table, as follows

`
CREATE TABLE IF NOT EXISTS history (
    id INT AUTO_INCREMENT,
    service_id INT,
    status INT NOT NULL,
    response_time INT,
    log_date TIMESTAMP,
    CONSTRAINT history_pk PRIMARY KEY(id),
    FOREIGN KEY(service_id) REFERENCES services(id)
);
`

`history` is linked to `services`, so that each service can identify its history.

`
CREATE TABLE IF NOT EXISTS services (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    url VARCHAR(250) NOT NULL,
    latest_status INT,
    CONSTRAINT service_pk PRIMARY KEY(id)
);
`

By also adding the 'latest_status' column, one is able to get an accurate impression when a certain service is unavailable.

A logger has also been set up, which uses the warning level whenever the services respond with a status code other than 200.

With regards to the actual implementation of the poller, I made use of an external library for web requests, called [Unirest](http://kong.github.io/unirest-java/), through which I've handled the timeout system as well.
It was a bit tricky without it at first, as I needed a way to disable SSL verification in order for the https://vix.digital GET call to work.

The final implementation uses a ping service with a `@PostConstruct` initialise method which has a `ScheduledThreadPoolExecutor` that repeatedly spawns a runnable worker class every 5000 miliseconds.
The runnable class does the actual ping and persists the data into the h2 database. I've also extended the functionality of this class to send a ping out to every service in the `services` table, by passing down the `servicesRepository` and doing a for loop onto it.

Afterwards I've followed the standard Model View Controller architecture.
I took special care to create a `ServiceDTO` which contains the minimum amount of data in order to manipulate the `ServiceEntity`, for security reasons.

The frontend was done utilising React bootstrap and Axios as the main external libraries.
It features functionality for adding, deleting, updating and displaying the services logs and data.
Were there more time I would have structured it a bit nicer, with separate pages and components instead of everything in one place.
Tests are lacking too, but I've left some comments as to how I'd have done them.

Thank you for this opportunity.
