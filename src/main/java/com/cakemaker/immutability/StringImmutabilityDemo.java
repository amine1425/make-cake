package com.cakemaker.immutability;

import java.util.HashMap;
import java.util.Map;

/**
 * Comprehensive demonstration of Java String Immutability
 * 
 * This class demonstrates:
 * - What is immutability in Java Strings
 * - Why Strings are immutable by design
 * - How immutability improves performance and security
 * - Real-world use cases and best practices
 * - Common misconceptions and FAQs
 */
public class StringImmutabilityDemo {

    public static void main(String[] args) {
        System.out.println("=== Java String Immutability Demonstration ===\n");
        
        // 1. What is immutability in Java Strings?
        demonstrateImmutability();
        
        // 2. Why are Strings immutable by design?
        demonstrateDesignReasons();
        
        // 3. Performance benefits
        demonstratePerformanceBenefits();
        
        // 4. Security benefits
        demonstrateSecurityBenefits();
        
        // 5. Real-world use cases
        demonstrateRealWorldUseCases();
        
        // 6. Best practices
        demonstrateBestPractices();
        
        // 7. Common misconceptions
        demonstrateCommonMisconceptions();
    }

    /**
     * Demonstrates what immutability means for Java Strings
     */
    public static void demonstrateImmutability() {
        System.out.println("1. WHAT IS STRING IMMUTABILITY?");
        System.out.println("=====================================");
        
        String original = "Hello";
        String modified = original.concat(" World");
        
        System.out.println("Original string: '" + original + "'");
        System.out.println("After concat(): '" + original + "' (unchanged!)");
        System.out.println("New string returned: '" + modified + "'");
        
        // Memory addresses demonstration
        System.out.println("\nMemory reference check:");
        System.out.println("original == modified: " + (original == modified));
        System.out.println("original.hashCode(): " + original.hashCode());
        System.out.println("modified.hashCode(): " + modified.hashCode());
        
        // String operations that seem to modify but actually create new objects
        String str = "Cake";
        System.out.println("\nString operations that create new objects:");
        System.out.println("Original: " + str);
        System.out.println("toUpperCase(): " + str.toUpperCase() + " (original unchanged: " + str + ")");
        System.out.println("substring(): " + str.substring(1) + " (original unchanged: " + str + ")");
        System.out.println("replace(): " + str.replace('C', 'B') + " (original unchanged: " + str + ")");
        
        System.out.println();
    }

    /**
     * Explains the design reasons behind String immutability
     */
    public static void demonstrateDesignReasons() {
        System.out.println("2. WHY ARE STRINGS IMMUTABLE BY DESIGN?");
        System.out.println("=========================================");
        
        System.out.println("Reason 1: String Pool Efficiency");
        String str1 = "CakeMaker";  // Goes to string pool
        String str2 = "CakeMaker";  // References same object in pool
        System.out.println("str1 == str2: " + (str1 == str2) + " (same reference!)");
        
        System.out.println("\nReason 2: Thread Safety");
        System.out.println("Multiple threads can safely read the same String without synchronization");
        
        System.out.println("\nReason 3: Hashcode Caching");
        String key = "recipe_chocolate_cake";
        System.out.println("Hashcode calculated once: " + key.hashCode());
        System.out.println("Same hashcode on subsequent calls: " + key.hashCode());
        
        System.out.println("\nReason 4: Security");
        System.out.println("Prevents modification of sensitive data like passwords, file paths, URLs");
        
        System.out.println();
    }

    /**
     * Demonstrates performance benefits of String immutability
     */
    public static void demonstratePerformanceBenefits() {
        System.out.println("3. PERFORMANCE BENEFITS");
        System.out.println("========================");
        
        // String Pool demonstration
        System.out.println("String Pool Benefits:");
        String[] recipes = {"chocolate", "vanilla", "strawberry", "chocolate", "vanilla"};
        
        long startTime = System.nanoTime();
        for (String recipe : recipes) {
            String cakeName = recipe + " cake";  // String concatenation
        }
        long endTime = System.nanoTime();
        
        System.out.println("String operations completed in: " + (endTime - startTime) + " nanoseconds");
        
        // Hashcode caching demonstration
        System.out.println("\nHashcode Caching Benefits:");
        String ingredient = "flour_sugar_eggs_butter";
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ingredient.hashCode();  // Cached after first call
        }
        endTime = System.nanoTime();
        
        System.out.println("1000 hashCode() calls: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Fast because hashcode is cached after first calculation");
        
        System.out.println();
    }

    /**
     * Demonstrates security benefits of String immutability
     */
    public static void demonstrateSecurityBenefits() {
        System.out.println("4. SECURITY BENEFITS");
        System.out.println("=====================");
        
        // File path security
        String configPath = "/etc/cake-config.properties";
        System.out.println("Config path: " + configPath);
        
        // Simulate passing to a method that might try to modify
        processFilePath(configPath);
        System.out.println("After processing: " + configPath + " (unchanged - secure!)");
        
        // Password security simulation
        String password = "secret123";
        System.out.println("\nPassword security:");
        System.out.println("Original password reference cannot be modified by external methods");
        
        // Database connection string security
        String dbUrl = "jdbc:mysql://localhost:3306/cakedb";
        System.out.println("\nDatabase URL: " + dbUrl);
        System.out.println("URL cannot be maliciously modified after creation");
        
        System.out.println();
    }

    private static void processFilePath(String path) {
        // This method cannot modify the original string
        path = path.replace("/etc/", "/tmp/");  // Creates new string, doesn't affect original
        System.out.println("Inside method: " + path);
    }

    /**
     * Shows real-world use cases where String immutability is crucial
     */
    public static void demonstrateRealWorldUseCases() {
        System.out.println("5. REAL-WORLD USE CASES");
        System.out.println("========================");
        
        // Use Case 1: Map Keys
        System.out.println("Use Case 1: HashMap Keys");
        Map<String, String> recipeBook = new HashMap<>();
        String cakeType = "chocolate";
        recipeBook.put(cakeType, "Mix cocoa, flour, sugar...");
        
        System.out.println("Recipe stored with key: '" + cakeType + "'");
        System.out.println("Retrieved recipe: " + recipeBook.get(cakeType));
        System.out.println("Key remains valid because String is immutable");
        
        // Use Case 2: Configuration Properties
        System.out.println("\nUse Case 2: Configuration Properties");
        String appName = "CakeMaker";
        String version = "1.0.0";
        String environment = "production";
        
        System.out.println("App: " + appName + ", Version: " + version + ", Env: " + environment);
        System.out.println("Configuration values safe from accidental modification");
        
        // Use Case 3: Caching
        System.out.println("\nUse Case 3: String Interning/Caching");
        String recipe1 = new String("vanilla cake").intern();
        String recipe2 = "vanilla cake";
        System.out.println("Interned strings share memory: " + (recipe1 == recipe2));
        
        System.out.println();
    }

    /**
     * Demonstrates best practices when working with Strings
     */
    public static void demonstrateBestPractices() {
        System.out.println("6. BEST PRACTICES");
        System.out.println("==================");
        
        System.out.println("Best Practice 1: Use StringBuilder for multiple concatenations");
        
        // Poor practice - multiple String concatenations
        long startTime = System.nanoTime();
        String result1 = "";
        for (int i = 0; i < 1000; i++) {
            result1 += "ingredient" + i + " ";  // Creates new String each time
        }
        long endTime = System.nanoTime();
        long stringConcatTime = endTime - startTime;
        
        // Best practice - StringBuilder
        startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("ingredient").append(i).append(" ");
        }
        String result2 = sb.toString();
        endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        
        System.out.println("String concatenation: " + stringConcatTime + " nanoseconds");
        System.out.println("StringBuilder approach: " + stringBuilderTime + " nanoseconds");
        System.out.println("Performance improvement: " + (stringConcatTime / Math.max(stringBuilderTime, 1)) + "x faster");
        
        System.out.println("\nBest Practice 2: Use String literals when possible");
        String literal = "cake";  // Goes to string pool
        String object = new String("cake");  // Creates new object
        System.out.println("literal == object: " + (literal == object));
        System.out.println("literal == object.intern(): " + (literal == object.intern()));
        
        System.out.println("\nBest Practice 3: Be aware of string pool behavior");
        demonstrateStringPool();
        
        System.out.println();
    }

    private static void demonstrateStringPool() {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = s3.intern();
        
        System.out.println("s1 == s2 (both literals): " + (s1 == s2));
        System.out.println("s1 == s3 (literal vs new): " + (s1 == s3));
        System.out.println("s1 == s4 (literal vs interned): " + (s1 == s4));
    }

    /**
     * Addresses common misconceptions about String immutability
     */
    public static void demonstrateCommonMisconceptions() {
        System.out.println("7. COMMON MISCONCEPTIONS & FAQs");
        System.out.println("=================================");
        
        System.out.println("Misconception 1: 'Strings waste memory'");
        System.out.println("Reality: String pool actually saves memory by reusing identical strings");
        
        String str1 = "CakeMaker";
        String str2 = "CakeMaker";
        System.out.println("Two 'CakeMaker' strings use same memory: " + (str1 == str2));
        
        System.out.println("\nMisconception 2: 'String concatenation with + is always slow'");
        System.out.println("Reality: Modern JVMs optimize simple concatenations to StringBuilder");
        
        String name = "Chocolate";
        String type = "Cake";
        String result = name + " " + type;  // JVM may optimize this
        System.out.println("Result: " + result);
        
        System.out.println("\nMisconception 3: 'We can't modify Strings at all'");
        System.out.println("Reality: We can create new Strings based on existing ones");
        
        String original = "vanilla cake";
        String modified = original.replace("vanilla", "chocolate");
        System.out.println("Original: " + original);
        System.out.println("Modified (new string): " + modified);
        
        System.out.println("\nFAQ: What about StringBuilder and StringBuffer?");
        System.out.println("Answer: They are MUTABLE and designed for building strings efficiently");
        
        StringBuilder mutable = new StringBuilder("Building");
        mutable.append(" a cake");
        mutable.append(" recipe");
        System.out.println("StringBuilder result: " + mutable.toString());
        System.out.println("StringBuilder allows true modification of content");
        
        System.out.println("\nFAQ: When should I use String vs StringBuilder?");
        System.out.println("- Use String for fixed text, simple operations, and when immutability is desired");
        System.out.println("- Use StringBuilder for complex string building, loops, and performance-critical code");
        
        System.out.println();
    }
}