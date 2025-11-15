package com.cakemaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller demonstrating String immutability concepts
 */
@RestController
@RequestMapping("/api/string-immutability")
public class StringImmutabilityController {

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Hello Cake Maker, your cake is being cooked! 🎂";
    }

    @GetMapping("/demo/basic")
    public Map<String, Object> basicImmutabilityDemo() {
        Map<String, Object> result = new HashMap<>();
        
        String original = "Vanilla Cake";
        String modified = original.concat(" with Frosting");
        
        result.put("concept", "String Immutability Basics");
        result.put("originalString", original);
        result.put("afterConcat", original); // Still unchanged!
        result.put("newStringCreated", modified);
        result.put("memoryReference", original == modified ? "Same" : "Different");
        result.put("explanation", "Original string remains unchanged. concat() creates a new String object.");
        
        return result;
    }

    @GetMapping("/demo/pool")
    public Map<String, Object> stringPoolDemo() {
        Map<String, Object> result = new HashMap<>();
        
        String str1 = "Chocolate Cake";
        String str2 = "Chocolate Cake";
        String str3 = new String("Chocolate Cake");
        
        result.put("concept", "String Pool");
        result.put("literal1", str1);
        result.put("literal2", str2);
        result.put("newObject", str3);
        result.put("literal1_equals_literal2", str1 == str2); // true
        result.put("literal1_equals_newObject", str1 == str3); // false
        result.put("content_equals", str1.equals(str3)); // true
        result.put("explanation", "String literals share memory through the String pool, 'new String()' creates separate objects");
        
        return result;
    }

    @GetMapping("/demo/performance/{iterations}")
    public Map<String, Object> performanceDemo(@PathVariable int iterations) {
        Map<String, Object> result = new HashMap<>();
        
        // String concatenation (inefficient for many operations)
        long startTime = System.nanoTime();
        String stringResult = "";
        for (int i = 0; i < iterations; i++) {
            stringResult += "ingredient" + i + " ";
        }
        long stringTime = System.nanoTime() - startTime;
        
        // StringBuilder (efficient for many operations)
        startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("ingredient").append(i).append(" ");
        }
        String builderResult = sb.toString();
        long builderTime = System.nanoTime() - startTime;
        
        result.put("concept", "Performance Comparison");
        result.put("iterations", iterations);
        result.put("stringConcatenationTime", stringTime + " nanoseconds");
        result.put("stringBuilderTime", builderTime + " nanoseconds");
        result.put("performanceRatio", builderTime > 0 ? (double)stringTime / builderTime : "N/A");
        result.put("recommendation", "Use StringBuilder for multiple concatenations");
        result.put("explanation", "String immutability causes performance issues with repeated concatenation");
        
        return result;
    }

    @GetMapping("/demo/security/{filepath}")
    public Map<String, Object> securityDemo(@PathVariable String filepath) {
        Map<String, Object> result = new HashMap<>();
        
        String originalPath = filepath;
        
        // Simulate a method that tries to modify the path
        String processedPath = processFilePath(originalPath);
        
        result.put("concept", "Security Benefits");
        result.put("originalPath", originalPath);
        result.put("afterProcessing", originalPath); // Still unchanged!
        result.put("processedResult", processedPath);
        result.put("pathStillSecure", originalPath.equals(filepath));
        result.put("explanation", "Original string cannot be modified by external methods, ensuring security");
        
        return result;
    }

    @GetMapping("/demo/hashcode/{text}")
    public Map<String, Object> hashCodeDemo(@PathVariable String text) {
        Map<String, Object> result = new HashMap<>();
        
        // First hashCode call
        long startTime = System.nanoTime();
        int hash1 = text.hashCode();
        long firstCallTime = System.nanoTime() - startTime;
        
        // Subsequent hashCode calls (should be faster due to caching)
        startTime = System.nanoTime();
        int hash2 = text.hashCode();
        long secondCallTime = System.nanoTime() - startTime;
        
        result.put("concept", "HashCode Caching");
        result.put("text", text);
        result.put("hashCode", hash1);
        result.put("firstCallTime", firstCallTime + " nanoseconds");
        result.put("secondCallTime", secondCallTime + " nanoseconds");
        result.put("hashCodesEqual", hash1 == hash2);
        result.put("explanation", "HashCode is calculated once and cached for performance");
        
        return result;
    }

    @GetMapping("/demo/best-practices")
    public Map<String, Object> bestPracticesDemo() {
        Map<String, Object> result = new HashMap<>();
        
        result.put("concept", "Best Practices for String Usage");
        
        Map<String, String> practices = new HashMap<>();
        practices.put("1", "Use String literals when possible for String pool benefits");
        practices.put("2", "Use StringBuilder for multiple concatenations");
        practices.put("3", "Use intern() cautiously - only for long-lived strings");
        practices.put("4", "Prefer String.format() or StringBuilder over multiple + operations");
        practices.put("5", "Remember Strings are immutable - operations create new objects");
        practices.put("6", "Use StringBuffer only when thread-safety is required");
        
        result.put("bestPractices", practices);
        
        // Example of good vs bad practices
        result.put("example_bad", "String result = str1 + str2 + str3 + str4; // Multiple concatenations");
        result.put("example_good", "StringBuilder sb = new StringBuilder().append(str1).append(str2).append(str3).append(str4); String result = sb.toString();");
        
        return result;
    }

    @GetMapping("/demo/misconceptions")
    public Map<String, Object> misconceptionsDemo() {
        Map<String, Object> result = new HashMap<>();
        
        result.put("concept", "Common Misconceptions");
        
        Map<String, String> misconceptions = new HashMap<>();
        misconceptions.put("myth1", "Strings waste memory");
        misconceptions.put("reality1", "String pool actually saves memory by reusing identical strings");
        
        misconceptions.put("myth2", "String concatenation with + is always slow");
        misconceptions.put("reality2", "Modern JVMs optimize simple concatenations to StringBuilder");
        
        misconceptions.put("myth3", "We cannot modify Strings at all");
        misconceptions.put("reality3", "We can create new Strings based on existing ones");
        
        misconceptions.put("myth4", "Immutable objects are always slower");
        misconceptions.put("reality4", "Immutability enables optimizations like string pooling and hashcode caching");
        
        result.put("misconceptions", misconceptions);
        
        return result;
    }

    private String processFilePath(String path) {
        // This method cannot modify the original string
        // It creates a new string
        return path.replace("/dangerous/", "/safe/");
    }
}