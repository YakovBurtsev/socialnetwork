# socialnetwork

This project is simple example of social network with the following features:
 - Search friend
 - Messaging
 - Posting photos

The project is based on microservice architecture. There is one core module with domain classes and interfaces, also 4 microservices:
 - <b>user</b>: for managing by user profile
 - <b>friends</b>: for managing (add or delete) by a list of friends
 - <b>message</b>: users can send and receive messages
 - <b>photo</b>: for posting photos