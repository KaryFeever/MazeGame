Project Title: IJA 2022/23: Pac-Man Game Design and Implementation

Autors: Maksim Naumenko  (xnaume01)
        Tatiana Fedorova (xfedor14)

The Pac-Man style game application provides an engaging experience for users as 
they control a character navigating through a maze from the starting position to
the target. The game features ghosts that move independently within the maze, 
adding a challenging element. Encountering a ghost results in an unsuccessful 
level completion.

At the start of the application, users can choose from multiple saved maps as 
the maze map is loaded from a file. The application also includes a logging 
feature that allows users to save their gameplay to a file and replay it later. 
This feature enhances user engagement and allows them to review their 
performance.

In addition to the core gameplay, the application offers several interactive 
elements that dynamically change based on the situation (the number of lives 
remaining, key).

Information about the method of launching the application:
- Make sure you have JDK 17 installed on your system.
- Open a terminal or command prompt and navigate to the directory containing the pom.xml file
- Use the following command to compile the application:
        mvn clean compile
- After a successful compilation, you can package the application into a JAR file using the following command:
        mvn package
- If the packaging was successful, you can run the application using the following command:
        java --module-path <path-to-javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml -jar target/mazegame-1.0-SNAPSHOT.jar

To generate documentation you can use next command:
        mvn javadoc:javadoc