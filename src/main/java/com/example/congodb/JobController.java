package com.example.congodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JobController {

    private final Driver driver;

    public JobController(Driver driver) {
        this.driver = driver;
    }

    @GetMapping("/match")
    public List<Map<String, Object>> getJobMatches(
            @RequestParam(defaultValue = "C101") String candidateId) {

        List<Map<String, Object>> results = new ArrayList<>();

        try (Session session = driver.session()) {

            String cypher =
                    "MATCH (c:Candidate {id: $cId})" +
                    "-[:HAS_SKILL]->(s:Skill)" +
                    "<-[:REQUIRES_SKILL]-(j:Job) " +
                    "RETURN j.title AS jobTitle, " +
                    "j.company AS company, " +
                    "collect(s.name) AS matchedSkills";

            var queryResult = session.run(
                    cypher,
                    Values.parameters("cId", candidateId)
            );

            while (queryResult.hasNext()) {

                Record record = queryResult.next();

                Map<String, Object> map = new HashMap<>();

                map.put(
                        "jobTitle",
                        record.get("jobTitle").asString()
                );

                map.put(
                        "company",
                        record.get("company").asString()
                );

                map.put(
                        "matchedSkills",
                        record.get("matchedSkills").asList()
                );

                results.add(map);
            }
        }

        return results;
    }
}