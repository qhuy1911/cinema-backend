# CINEMA BACKEND

### *MOVIES*

| Methods | URLs            | Actions                                 |
|---------|-----------------|-----------------------------------------|
| GET     | /api/movies     | Retrieve all Movies                     |
| GET     | /api/movies/:id | Retrieve a movie with its ID            |
| POST    | /api/movies     | Create a new Movie                      |
| PUT     | /movies/{id}    | Update a Movie by ID                    |
| DELETE  | /movies/{id}    | Delete a Movie (and its Schedule) by ID |
| DELETE  | /movies         | Delete all Movies                       |

### *ROOMS*

| Methods | URLs           | Actions                                |
|---------|----------------|----------------------------------------|
| GET     | /api/rooms     | Retrieve all Rooms                     |
| GET     | /api/rooms/:id | Retrieve a Room with its ID            |
| POST    | /api/rooms     | Create a new Room                      |
| PUT     | /rooms/{id}    | Update a Room by ID                    |
| DELETE  | /rooms/{id}    | Delete a Room (and its Schedule) by ID |
| DELETE  | /rooms         | Delete all Movies                      |

### *SCHEDULES*

| Methods | URLs                                            | Actions                                         |
|---------|-------------------------------------------------|-------------------------------------------------|
| GET     | /api/schedules                                  | Retrieve all Schedules                          |
| GET     | /api/movies/:movieId/schedules                  | Retrieve all Schedules with movie id            |
| GET     | /api/schedules/:id                              | Retrieve a Schedule with its ID                 |
| POST    | /api/movies/:movieId/rooms/:roomId/schedules    | Create a new Schedule with movie id and room id |
| PUT     | /movies/{movieId}/rooms/{roomId}/schedules/{id} | Update a Schedule by id with movie, room id     |
| DELETE  | /schedules/{id}                                 | Delete a Schedule (and its Ticket) with id      |
| DELETE  | /movies/{movieId}/schedules                     | Delete all Schedules of a Movie                 |
| DELETE  | /rooms/{roomId}/schedules                       | Delete all Schedules of a Room                  |

### *SEATS*

| Methods | URLs                             | Actions                             |
|---------|----------------------------------|-------------------------------------|
| GET     | /api/schedules/:scheduleId/seats | Retrieve all Seats with schedule id |
| GET     | /api/seats/:id                   | Retrieve a Room with its ID         |
| POST    | /api/schedules/:scheduleId/seats | Create a new Seat with schedule id  |

### *TICKETS*

| Methods | URLs                               | Actions                               |
|---------|------------------------------------|---------------------------------------|
| GET     | /api/schedules/:scheduleId/tickets | Retrieve all Tickets with schedule id |
| GET     | /api/tickets/:id                   | Retrieve a Ticket with its ID         |
| POST    | /api/schedules/:scheduleId/tickets | Create a new Ticket with schedule id  |

### *BOOKINGS*

| Methods | URLs                         | Actions                            |
|---------|------------------------------|------------------------------------|
| GET     | /api/bookings                | Retrieve all Bookings              |
| GET     | /api/users/{userId}/bookings | Retrieve all Bookings with user id |
| GET     | /api/bookings/{id}           | Retrieve a Booking with its ID     |
| POST    | /api/user/{userId}/bookings  | Create a new Booking with user id  |

### *BOOKING DETAILs*

| Methods | URLs                                                                | Actions                                                   |
|---------|---------------------------------------------------------------------|-----------------------------------------------------------|
| GET     | /api/details                                                        | Retrieve all BookingDetails                               |
| GET     | /api/bookings/{bookingId}/details                                   | Retrieve all BookingDetails with booking id               |
| GET     | /api/booking-details/{id}                                           | Retrieve a BookingDetails with its ID                     |
| POST    | /api/bookings/{bookingId}/tickets/{ticketId}/seats/{seatId}/details | Create a new BookingDetails with booking, ticket, seat id |

### *USER*

| Methods | URLs             | Actions                     |
|---------|------------------|-----------------------------|
| POST    | /api/auth/signin | Login by username, password |
| POST    | /api/auth/signup | Register a new user         |

#### - Sign in:

{<br/>
    "username": "admin",<br/>
    "password": "123123"<br/>
}<br/>

#### - Sign up:

{<br/>
    "fullname": "Quoc Huy",<br/>
    "username": "qhuy1911",<br/>
    "password": "191100",<br/>
    "email": "qhuy1911@gmail.com",<br/>
    "phone": "#",<br/>
    "address": "#",<br/>
    "role": ["user"]<br/>
}<br/>

### *ROLES*

#### - Create 2 default roles:
    INSERT INTO roles(name) VALUES('ROLE_USER');
    INSERT INTO roles(name) VALUES('ROLE_ADMIN');