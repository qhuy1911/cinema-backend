# CINEMA BACKEND

### *MOVIES*

| Methods | URLs            | Actions                      |
|---------|-----------------|------------------------------|
| GET     | /api/movies     | Retrieve all Movies          |
| GET     | /api/movies/:id | Retrieve a movie with its ID |
| POST    | /api/movies     | Create a new Movie           |

### *ROOM*

| Methods | URLs           | Actions                     |
|---------|----------------|-----------------------------|
| GET     | /api/rooms     | Retrieve all Rooms          |
| GET     | /api/rooms/:id | Retrieve a Room with its ID |
| POST    | /api/rooms     | Create a new Room           |

### *SCHEDULE*

| Methods | URLs                                         | Actions                                         |
|---------|----------------------------------------------|-------------------------------------------------|
| GET     | /api/movies/:movieId/schedules               | Retrieve all Schedules with movie id            |
| GET     | /api/schedules/:id                           | Retrieve a Schedule with its ID                 |
| POST    | /api/movies/:movieId/rooms/:roomId/schedules | Create a new Schedule with movie id and room id |

### *SEAT*

| Methods | URLs                     | Actions                         |
|---------|--------------------------|---------------------------------|
| GET     | /api/rooms/:roomId/seats | Retrieve all Seats with room id |
| GET     | /api/seats/:id           | Retrieve a Room with its ID     |
| POST    | /api/rooms/:roomId/seats | Create a new Seat with room id  |