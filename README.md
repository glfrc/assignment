# Programming assignment
I have implemented it in Java, in the form of a Maven project.
To be able to compile and run it you will need the JDK 8 and Maven installed.
Download the code or clone the repo in your local environment by issuing the command:
    git clone https://github.com/glfrc/assignment.git

To compile and run unit test the function:
    mvn compile test

To run the test program from windows powershell:
    mvn exec:java "-Dexec.args=-inputfile TEST.PRN -n 300 -c 4"

or from a nix like terminal:
    mvn exec:java -Dexec.args="-inputfile TEST.PRN -n 300 -c 4"

As an alternative you can import the project in an IDE supporting Maven, like Eclipse, and run it from there. 