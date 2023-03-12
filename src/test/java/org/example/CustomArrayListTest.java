package org.example;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomArrayListTest {
    private CustomArrayList<String> customArrayList;

    @BeforeEach
    void setUp() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    void add_shouldAddElementToEmptyList() {
        customArrayList.add("Element");
        assertEquals("Element", customArrayList.get(0));
        assertEquals(1, customArrayList.size());
    }

    @Test
    void get_shouldReturnElementAtIndex() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        assertEquals("Element 1", customArrayList.get(0));
        assertEquals("Element 2", customArrayList.get(1));
    }

    @Test
    void remove_shouldRemoveElementByIndex() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.remove(0);
        assertEquals("Element 2", customArrayList.get(0));
        assertEquals(1, customArrayList.size());
    }

    @Test
    void clear_shouldResetListToInitialState() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.clear();
        assertEquals(0, customArrayList.size());
        assertNull(customArrayList.get(0));
    }

    @Test
    void size_shouldReturnNumberOfElementsInList() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        assertEquals(2, customArrayList.size());
    }

    @Test
    void quickSort_shouldSortListInNaturalOrder() {
        customArrayList.add("Charlie");
        customArrayList.add("Alice");
        customArrayList.add("Echo");
        customArrayList.add("Bravo");
        customArrayList.add("Delta");
        customArrayList.quickSort();
        assertEquals("[Alice, Bravo, Charlie, Delta, Echo]", customArrayList.toString());
    }
}

