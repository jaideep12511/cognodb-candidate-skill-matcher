package com.example.congodb;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final Driver driver;

    public DataInitializer(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void run(String... args) {

        try (Session session = driver.session()) {

            // Create Candidate
            session.run(
                "MERGE (c:Candidate {id: $id}) " +
                "SET c.name = $name, c.role = $role",
                Values.parameters(
                    "id", "C101",
                    "name", "Gurram Jaideep",
                    "role", "Full Stack Engineer"
                )
            );

            // Create Skills
            session.run(
                "MERGE (s:Skill {name: $name})",
                Values.parameters("name", "Java")
            );

            session.run(
                "MERGE (s:Skill {name: $name})",
                Values.parameters("name", "Spring Boot")
            );

            session.run(
                "MERGE (s:Skill {name: $name})",
                Values.parameters("name", "CognoDB")
            );

            // Create Job
            session.run(
                "MERGE (j:Job {id: $id}) " +
                "SET j.title = $title, j.company = $company",
                Values.parameters(
                    "id", "J201",
                    "title", "Software Engineer",
                    "company", "Wexa AI"
                )
            );

            // Candidate -> Java
            session.run(
                "MATCH (c:Candidate {id: $candidateId}), " +
                "(s:Skill {name: $skillName}) " +
                "MERGE (c)-[:HAS_SKILL]->(s)",
                Values.parameters(
                    "candidateId", "C101",
                    "skillName", "Java"
                )
            );

            // Candidate -> Spring Boot
            session.run(
                "MATCH (c:Candidate {id: $candidateId}), " +
                "(s:Skill {name: $skillName}) " +
                "MERGE (c)-[:HAS_SKILL]->(s)",
                Values.parameters(
                    "candidateId", "C101",
                    "skillName", "Spring Boot"
                )
            );

            // Candidate -> CognoDB
            session.run(
                "MATCH (c:Candidate {id: $candidateId}), " +
                "(s:Skill {name: $skillName}) " +
                "MERGE (c)-[:HAS_SKILL]->(s)",
                Values.parameters(
                    "candidateId", "C101",
                    "skillName", "CognoDB"
                )
            );

            // Job -> Java
            session.run(
                "MATCH (j:Job {id: $jobId}), " +
                "(s:Skill {name: $skillName}) " +
                "MERGE (j)-[:REQUIRES_SKILL]->(s)",
                Values.parameters(
                    "jobId", "J201",
                    "skillName", "Java"
                )
            );

            // Job -> Spring Boot
            session.run(
                "MATCH (j:Job {id: $jobId}), " +
                "(s:Skill {name: $skillName}) " +
                "MERGE (j)-[:REQUIRES_SKILL]->(s)",
                Values.parameters(
                    "jobId", "J201",
                    "skillName", "Spring Boot"
                )
            );

            // Job -> CognoDB
            session.run(
                "MATCH (j:Job {id: $jobId}), " +
                "(s:Skill {name: $skillName}) " +
                "MERGE (j)-[:REQUIRES_SKILL]->(s)",
                Values.parameters(
                    "jobId", "J201",
                    "skillName", "CognoDB"
                )
            );

            System.out.println("Seed data inserted successfully!");
        }
    }
}