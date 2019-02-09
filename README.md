# MultiThreaded-Server

In this application, three files have been created. One is the clientWindow which takes input from the user and send 
it through the socketConn function. In the second file, ServerMultithreaded the server is managed. 
The ExecuteCommand mainly run the command receive through the socket from the user. 
After that the result is again sent back to the clientWindow through the socket.

Run Instructions:
1. Run the ServerMultithreaded as a Java App.
2. Run the clientWindow Java file as a Java app.
3. To run a linux command, type the linux command in the empty field, e.g. "cmd /c dir" and press the button.
