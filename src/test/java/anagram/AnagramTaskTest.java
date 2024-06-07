package anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramTaskTest {

    @Test
    void onMapWhenAzByThenFalse() {
        assertFalse(AnagramTask.isAnagramOnMap("by","az"));
        assertFalse(AnagramTask.isAnagramOnMap("az", "by"));
    }

    @Test
    void onMapWhenRetCarThenFalse() {
        assertFalse(AnagramTask.isAnagramOnMap("rat","car"));
        assertFalse(AnagramTask.isAnagramOnMap("car", "rat"));
    }

    @Test
    void onMapWhenAnagramThenTrue() {
        assertTrue(AnagramTask.isAnagramOnMap("anagram","nagaram"));
        assertTrue(AnagramTask.isAnagramOnMap("nagaram","anagram"));
    }

    @Test
    void twoCyclesWhenAzByThenFalse() {
        assertFalse(AnagramTask.isAnagramOnTwoEmbeddedCycles("by","az"));
        assertFalse(AnagramTask.isAnagramOnTwoEmbeddedCycles("az", "by"));
    }

    @Test
    void twoCyclesWhenAnagramThenTrue() {
        assertTrue(AnagramTask.isAnagramOnTwoEmbeddedCycles("anagram","nagaram"));
        assertTrue(AnagramTask.isAnagramOnTwoEmbeddedCycles("nagaram","anagram"));
    }

    //isAnagramOnTwoSortedArrays
    @Test
    void twoSortedListWhenAzByThenFalse() {
        assertFalse(AnagramTask.isAnagramOnTwoSortedArrays("by","az"));
        assertFalse(AnagramTask.isAnagramOnTwoSortedArrays("az", "by"));
    }

    @Test
    void twoSortedListWhenAnagramThenTrue() {
        assertTrue(AnagramTask.isAnagramOnTwoSortedArrays("anagram","nagaram"));
        assertTrue(AnagramTask.isAnagramOnTwoSortedArrays("nagaram","anagram"));
    }
}