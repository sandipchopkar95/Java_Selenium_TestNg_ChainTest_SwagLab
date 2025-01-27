package com.saucedemo.testutility;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JiraTestCaseUtils {
    public static List<String> attachJiraTestId(Method method) {
        List<String> jiraLinks = new ArrayList<>();
        try {
            List<String> descriptions = new ArrayList<>();

            // Check if @Test annotation is present
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                String description = testAnnotation.description();
                if (description != null && !description.trim().isEmpty()) {
                    descriptions.add(description);
                }
            }

            // Check for @AdditionalDescriptions
            if (method.isAnnotationPresent(AdditionalDescriptions.class)) {
                AdditionalDescriptions additionalDescription = method.getAnnotation(AdditionalDescriptions.class);
                String[] additionalDescriptions = additionalDescription.value();
                for (String desc : additionalDescriptions) {
                    if (desc != null && !desc.trim().isEmpty()) {
                        descriptions.add(desc);
                    }
                }
            }

            // If no descriptions are found, log a warning
            if (descriptions.isEmpty()) {
                throw new IllegalArgumentException("No description provided for the test: " + method.getName());
            }

            // Create Jira links for all descriptions
            for (String description : descriptions) {
                String jiraLink = "https://app.atlassian.net/browse/" + description;
                jiraLinks.add(jiraLink);
            }
        } catch (Exception e) {
            System.out.println("Failed to attach Jira Test IDs: " + e.getMessage());
        }
        return jiraLinks;
    }
}
