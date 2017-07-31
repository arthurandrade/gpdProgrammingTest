# Programming Skill (GDP)

Creation of a rest service for checkout system. For more information, read the pdf contained in the repository.
The system was built using java 8 and springboot. A web application was developed to consume the Rest service. The web application was building using angularjs and bootstrap.

### Pre-requisites

```
- Java 8
- Maven
```

### Installing Pre-requisites

- Installing Java

Open the terminal and update the package index:

```
sudo apt-get update
```

Add the Oracle PPA, then update your package repository.

```
sudo apt-get update
sudo add-apt-repository ppa:webupd8team/java
```

Run the command to install java 8:

```
sudo apt-get install oracle-java8-installer
```

After installation check the installed version:

```
java -version
```

- Installing Maven

Open the terminal and run the command:

```
sudo apt-get install maven
```

## Getting Started

To run the program locally it is necessary to clone the repository. Run the command:

```
git clone https://github.com/arthurandrade/gpdProgrammingTest.git
```

Next command will download the dependencies and run the tests. (Go to prepare coffee, because it's going to take a while). Inside the folder of project, run:

```
mvn clean package
```
- To start the server, run the command:

```
mvn spring-boot:run
```

open your browser end access "http://localhost:8080/"







