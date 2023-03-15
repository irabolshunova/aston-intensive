package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void add_shouldAddElementByIndexToFullList() {
        customArrayList.add("Element 1");
        customArrayList.add("Element 2");
        customArrayList.add("Element 3");
        customArrayList.add("Element 4");
        customArrayList.add("Element 5");
        customArrayList.add("Element 6");
        customArrayList.add("Element 7");
        customArrayList.add("Element 8");
        customArrayList.add("Element 9");
        customArrayList.add("Element 10");

        customArrayList.add(5, "Element");
        assertEquals("Element", customArrayList.get(5));
        assertEquals("Element 6", customArrayList.get(6));
        assertEquals(11, customArrayList.size());
    }


    @Test
    void add_shouldAddElementInFirstBucket() {
        int serialNumber = 1;
        for (int i = 0; i < 1000; i++) {
            customArrayList.add(0, "String " + serialNumber);
        }
        assertEquals(1000, customArrayList.size());
        assertEquals("String " + serialNumber, customArrayList.get(0));
        assertEquals("String " + 1, customArrayList.get(999));
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

