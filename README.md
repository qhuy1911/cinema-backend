# CINEMA BACKEND

### *MOVIES*

| Methods | URLs            | Actions                      |
|---------|-----------------|------------------------------|
| GET     | /api/movies     | Retrieve all Movies          |
| GET     | /api/movies/:id | Retrieve a movie with its ID |
| POST    | /api/movies     | Create a new Movie           |

### *ROOMS*

| Methods | URLs           | Actions                     |
|---------|----------------|-----------------------------|
| GET     | /api/rooms     | Retrieve all Rooms          |
| GET     | /api/rooms/:id | Retrieve a Room with its ID |
| POST    | /api/rooms     | Create a new Room           |

### *SCHEDULES*

| Methods | URLs                                         | Actions                                         |
|---------|----------------------------------------------|-------------------------------------------------|
| GET     | /api/movies/:movieId/schedules               | Retrieve all Schedules with movie id            |
| GET     | /api/schedules/:id                           | Retrieve a Schedule with its ID                 |
| POST    | /api/movies/:movieId/rooms/:roomId/schedules | Create a new Schedule with movie id and room id |

### *SEATS*

| Methods | URLs                     | Actions                         |
|---------|--------------------------|---------------------------------|
| GET     | /api/rooms/:roomId/seats | Retrieve all Seats with room id |
| GET     | /api/seats/:id           | Retrieve a Room with its ID     |
| POST    | /api/rooms/:roomId/seats | Create a new Seat with room id  |

### *TICKETS*

| Methods | URLs                               | Actions                               |
|---------|------------------------------------|---------------------------------------|
| GET     | /api/schedules/:scheduleId/tickets | Retrieve all Tickets with schedule id |
| GET     | /api/tickets/:id                   | Retrieve a Ticket with its ID         |
| POST    | /api/schedules/:scheduleId/tickets | Create a new Ticket with schedule id  |

### *BOOKINGS*

| Methods | URLs                         | Actions                            |
|---------|------------------------------|------------------------------------|
| GET     | /api/users/{userId}/bookings | Retrieve all Bookings with user id |
| GET     | /api/bookings/{id}           | Retrieve a Booking with its ID     |
| POST    | /api/user/{userId}/bookings  | Create a new Booking with user id  |

### *BOOKING DETAILs*

| Methods | URLs                                                                | Actions                                                   |
|---------|---------------------------------------------------------------------|-----------------------------------------------------------|
| GET     | /api/bookings/{bookingId}/details                                   | Retrieve all BookingDetails with booking id               |
| GET     | /api/booking-details/{id}                                           | Retrieve a BookingDetails with its ID                     |
| POST    | /api/bookings/{bookingId}/tickets/{ticketId}/seats/{seatId}/details | Create a new BookingDetails with booking, ticket, seat id |

