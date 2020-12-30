# chat-app
A Chatting App made in Java using concepts of Networking and Socket Programming that allows communication between a Server and Client. The app has an easy to use User Interface which has been created using Java Swing. 

## How to run?
Open two seperate terminal/command-prompt windows and then from project directory:

1. `cd bin` (On both windows)

2. `java Server` (On one window)

3. `java Client` (On other window)

Now you can type messages in either the Client or Server window and press the "Send" button to send the message. 

## Notes: 
- Server should be started before running the Client
- Since it is a **TCP** type connection, communication will happen only when both Server and Client are up and running and a connection is established between them
- The Server also tells the activity status of the client i.e when the Client is running and connection is established, the Server ChatFrame displays "Active Now" under the "Client" heading
- On closing the Client, the "Active Now" text goes away and the chat clears out. Now the Server is waiting for a new Client
- On closing the Server, the Client closes automatically.

## Extension Plans:
- Implement a group chat feauture using multi-threading which allows multiple clients to connect to the Server at the same time and communicate with one another.
