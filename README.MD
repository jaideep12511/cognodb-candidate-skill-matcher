t application that demonstrates candidate-to-job matching using CognoDB as a graph database.

The application models Candidates, Skills, and Jobs as graph nodes and connects them using relationships. Cypher queries are then used to traverse the graph and identify jobs that match a candidate's skills.

📌 Overview

Traditional relational databases often require multiple JOIN operations when working with highly connected data.

This project demonstrates how a graph database can simplify relationship-heavy queries.

The core relationship is:

Candidate → Skill → Job

For example:

Gurram Jaideep
      │
      ├── Java ───────────────┐
      │                       │
      ├── Spring Boot ────────┤
      │                       │
      └── CognoDB ────────────┤
                              ▼
                       Software Engineer
                              │
                           Wexa AI

The application finds jobs by traversing the candidate's skills and comparing them with the skills required by available jobs.

🚀 Features
✅ Spring Boot REST API
✅ CognoDB graph database integration
✅ Candidate, Skill, and Job graph nodes
✅ Candidate-to-Skill relationships
✅ Job-to-Skill relationships
✅ Multi-hop Cypher graph traversal
✅ Automatic graph data seeding
✅ REST API for job matching
✅ Simple web-based frontend
✅ Bootstrap-based UI
✅ Graph visualization
✅ Maven-based project
✅ JSON API responses
🏗️ Architecture

The application follows a simple layered architecture:

                    ┌──────────────────┐
                    │   Web Frontend   │
                    │ HTML / Bootstrap │
                    │   JavaScript     │
                    └────────┬─────────┘
                             │
                             │ HTTP
                             ▼
                    ┌──────────────────┐
                    │   REST API       │
                    │  Spring Boot     │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │ Matching Service │
                    │   Cypher Query   │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │     CognoDB      │
                    │  Graph Database  │
                    └──────────────────┘
🗄️ Graph Data Model

The application uses three primary node types:

Candidate

Represents a job candidate.

(:Candidate)

Example:

Candidate
id: C101
name: Gurram Jaideep
Skill

Represents a technical or professional skill.

(:Skill)

Examples:

Java
Spring Boot
CognoDB
Angular
Job

Represents a job opportunity.

(:Job)

Example:

Job
title: Software Engineer
company: Wexa AI
🔗 Relationships

Candidates are connected to their skills using:

(:Candidate)-[:HAS_SKILL]->(:Skill)

Jobs are connected to the skills they require using:

(:Job)-[:REQUIRES_SKILL]->(:Skill)

The complete graph structure is:

(:Candidate)-[:HAS_SKILL]->(:Skill)<-[:REQUIRES_SKILL]-(:Job)

This allows the application to find matching jobs through graph traversal.

🧠 How Job Matching Works

Suppose a candidate has:

Java
Spring Boot
CognoDB

And a job requires:

Java
Spring Boot
CognoDB

The graph traversal identifies all matching skills.

Conceptually:

Candidate
   │
   ├── Java ──────────────┐
   │                      │
   ├── Spring Boot ───────┤──→ Job
   │                      │
   └── CognoDB ───────────┘

The API returns the matching job along with the skills that matched.

🛠️ Technology Stack
Technology	Purpose
Java	Application development
Spring Boot	Backend framework
Spring Web	REST API
Spring Data Neo4j / Neo4j Driver	Graph database interaction
CognoDB	Graph database
Cypher	Graph query language
Maven	Build and dependency management
HTML	Frontend structure
Bootstrap	Frontend styling
JavaScript	Frontend functionality
📂 Project Structure
cognodb-candidate-skill-matcher/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html
│   │       │   └── ...
│   │       │
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── README.md
├── .gitignore
├── mvnw
└── mvnw.cmd

The exact package and file structure may vary depending on the implementation.

⚙️ Prerequisites

Before running the application, make sure the following are installed:

Java

Java 17 or the version specified by the project configuration.

Check your Java installation:

java -version
Maven

Maven is required if you are not using the included Maven Wrapper.

Check Maven:

mvn -version

The project also includes the Maven Wrapper, so Maven does not necessarily need to be installed globally.

CognoDB

A running CognoDB instance is required.

You will need the following connection details:

Database URI
Username
Password
🔧 Configuration

Configure the CognoDB connection in:

src/main/resources/application.properties

Example:

spring.application.name=candidate-skill-matcher

spring.neo4j.uri=<COGNODB_URI>
spring.neo4j.authentication.username=<USERNAME>
spring.neo4j.authentication.password=<PASSWORD>

Replace the placeholder values with your CognoDB connection details.

⚠️ Do not commit real database credentials, passwords, API keys, or other secrets to GitHub.

For a public repository, environment variables or another secure configuration mechanism should be preferred.

▶️ How to Run the Application
1. Clone the Repository
git clone git@github.com:jaideep12511/cognodb-candidate-skill-matcher.git

Move into the project directory:

cd cognodb-candidate-skill-matcher
2. Configure CognoDB

Update:

src/main/resources/application.properties

with your CognoDB connection details.

3. Build the Application

Using Maven:

mvn clean install

Or using the Maven Wrapper:

Windows
.\mvnw.cmd clean install
Linux / macOS
./mvnw clean install
4. Start the Application
Windows
.\mvnw.cmd spring-boot:run
Linux / macOS
./mvnw spring-boot:run

Alternatively, run the generated JAR:

java -jar target/<application-name>.jar
🌐 Access the Application

Once the Spring Boot application starts successfully, open:

http://localhost:8080

The web interface allows users to interact with the candidate skill matching functionality.

🔌 REST API
Find Matching Jobs
Endpoint
GET /api/match?candidateId=C101
Example
http://localhost:8080/api/match?candidateId=C101
Request
GET /api/match?candidateId=C101
Example Response
[
  {
    "jobTitle": "Software Engineer",
    "company": "Wexa AI",
    "matchedSkills": [
      "Java",
      "Spring Boot",
      "CognoDB"
    ]
  }
]
📊 Example Data
Candidate
ID: C101
Name: Gurram Jaideep
Skills
Java
Spring Boot
CognoDB
Job
Title: Software Engineer
Company: Wexa AI
Required Skills
Java
Spring Boot
CognoDB

The matching algorithm traverses the graph and identifies the skills shared between the candidate and the job.