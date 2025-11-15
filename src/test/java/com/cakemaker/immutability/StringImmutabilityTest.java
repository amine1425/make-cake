package com.cakemaker.immutability;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests demonstrating String immutability concepts
 */
public class StringImmutabilityTest {

    @Test
    @DisplayName("String concatenation creates new objects")
    public void testStringConcatenationImmutability() {
        String original = "Cake";
        String result = original.concat(" Maker");
        
        // Original string remains unchanged
        assertEquals("Cake", original);
        assertEquals("Cake Maker", result);
        
        // Different objects in memory
        assertNotSame(original, result);
    }

    @Test
    @DisplayName("String literals share the same reference (String Pool)")
    public void testStringPool() {
        String str1 = "chocolate";
        String str2 = "chocolate";
        String str3 = new String("chocolate");
        
        // String pool - same reference
        assertSame(str1, str2);
        
        // New object - different reference
        assertNotSame(str1, str3);
        
        // Content is equal
        assertEquals(str1, str3);
        
        // Interning makes them same reference
        assertSame(str1, str3.intern());
    }

    @Test
    @DisplayName("String operations create new objects")
    public void testStringOperationsImmutability() {
        String original = "vanilla cake";
        
        // All these operations return new String objects
        String upperCase = original.toUpperCase();
        String replaced = original.replace("vanilla", "chocolate");
        String substring = original.substring(8);
        
        // Original unchanged
        assertEquals("vanilla cake", original);
        
        // New objects created
        assertEquals("VANILLA CAKE", upperCase);
        assertEquals("chocolate cake", replaced);
        assertEquals("cake", substring);
        
        // All different references
        assertNotSame(original, upperCase);
        assertNotSame(original, replaced);
        assertNotSame(original, substring);
    }

    @Test
    @DisplayName("StringBuilder is mutable unlike String")
    public void testStringBuilderMutability() {
        StringBuilder sb = new StringBuilder("Baking");
        StringBuilder result1 = sb.append(" a");
        StringBuilder result2 = sb.append(" cake");
        
        // StringBuilder modifies the same object
        assertSame(sb, result1);
        assertSame(sb, result2);
        
        // Content has been modified
        assertEquals("Baking a cake", sb.toString());
        assertEquals("Baking a cake", result1.toString());
        assertEquals("Baking a cake", result2.toString());
    }

    @Test
    @DisplayName("String hashCode is cached for performance")
    public void testHashCodeCaching() {
        String str = "performance_test_string";
        
        // First call calculates and caches
        int hash1 = str.hashCode();
        
        // Subsequent calls return cached value
        int hash2 = str.hashCode();
        int hash3 = str.hashCode();
        
        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    @DisplayName("Strings are safe to use as HashMap keys")
    public void testStringAsMapKey() {
        java.util.Map<String, String> recipes = new java.util.HashMap<>();
        
        String key = "chocolate_cake";
        recipes.put(key, "Delicious chocolate recipe");
        
        // Key works reliably because String is immutable
        assertEquals("Delicious chocolate recipe", recipes.get(key));
        assertEquals("Delicious chocolate recipe", recipes.get("chocolate_cake"));
        
        // Even if we try to "modify" the key, original mapping remains
        String modifiedKey = key.toUpperCase(); // Creates new String
        assertNull(recipes.get(modifiedKey)); // Different key
        assertNotNull(recipes.get(key)); // Original key still works
    }

    @Test
    @DisplayName("String comparison behavior")
    public void testStringComparison() {
        String str1 = "test";
        String str2 = "test";
        String str3 = new String("test");
        String str4 = str3.intern();
        
        // Reference equality (==)
        assertTrue(str1 == str2);      // Pool objects
        assertFalse(str1 == str3);     // Different objects
        assertTrue(str1 == str4);      // Interned object
        
        // Content equality (equals)
        assertTrue(str1.equals(str2));
        assertTrue(str1.equals(str3));
        assertTrue(str1.equals(str4));
    }
}