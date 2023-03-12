package org.example;

import java.util.Comparator;

/**
 * Пользовательская реализация ArrayList.
 * Может принимать объекты любого типа, происходящие от класса Object.
 * Предоставляет базовые функции, такие как добавление, удаление и получение элементов.
 * Предоставляет метод quickSort(), который использует CustomComparator для сортировки элементов.
 */
public class CustomArrayList<T extends Object> {
    private T[] elements;
    private int size;
    private static final CustomComparator CUSTOM_COMPARATOR = new CustomComparator();

    public CustomArrayList() {
        elements = (T[]) new Object[10];
        size = 0;
    }

    /**
     * Добавляет новый элемент в массив.
     * Увеличивает размер массива в 2 раза, если массив заполнен.
     *
     * @param element – элемент, добавляемый в массив.
     */
    public void add(T element) {
        if (size == elements.length) {
            Object[] newElements = new Object[size * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = (T[]) newElements;
        }
        elements[size++] = element;
    }

    /**
     * Получает объект из массива элементов по индексу.
     *
     * @param index – индекс элемента в массиве
     * @return полученный по индексу объект
     */
    public Object get(int index) {
        return elements[index];
    }

    /**
     * Удаляет элемент из массива по индексу.
     * Все элементы после удаляемого объекта сдвигает влево на 1 ячейку.
     * Последнему элементу массива присваивает значение null и уменьшает размер на 1.
     *
     * @param index – индекс элемента в массиве
     */
    public void remove(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size--] = null;
    }

    /**
     * Очищает список.
     * Присваивает массиву elements ссылку на новый массив Object размером 10.
     * Устанавливает размер массива в 0.
     */
    public void clear() {
        elements = (T[]) new Object[10];
        size = 0;
    }

    /**
     * Возвращает количество элементов в массиве.
     *
     * @return количество хранимых элементов.
     */
    public int size() {
        return size;
    }

    /**
     * Сортирует элементы массива в порядке возрастания, используя алгоритм QuickSort.
     * Использует вспомогательные методы для разделения массива на две части,
     * для рекурсивной сортировки каждой часть, пока не будет отсортирован весь массив.
     */
    public void quickSort() {
        if (elements == null || size <= 1) {
            return;
        }
        int leftIndex = 0;
        int rightIndex = size - 1;
        quickSortHelper(elements, leftIndex, rightIndex);
    }

    /**
     * Рекурсивный вспомогательный метод для быстрой сортировки.
     * Сортирует массив от leftIndex до rightIndex,
     * Использует вспомогательный метод, чтобы определить точку опоры pivot.
     * Рекурсивно выполняется для двух половин массива.
     *
     * @param array – массив для быстрой сортировки
     * @param leftIndex – начальный индекс интервала элементов для сортировки
     * @param rightIndex – конечный индекс интервала элементов для сортировки
     */
    private static void quickSortHelper(Object[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int pivotIndex = getPivotIndex(array, leftIndex, rightIndex);
            quickSortHelper(array, leftIndex, pivotIndex - 1);
            quickSortHelper(array, pivotIndex + 1, rightIndex);
        }
    }

    /**
     * Получение индекса опорного элемента в массиве.
     * Опорой считается последний элемент массива.
     * Левый и правый индексы используются для обозначения границ массива.
     * Пользовательский компаратор используется для сравнения элементов массива.
     * Элементы, меньшие или равные опорной точке, перемещаются влево от опорной точки,
     * Элементы, превышающие опорную точку, перемещаются вправо.
     *
     * @param array – массив для быстрой сортировки
     * @param leftIndex – начальный индекс интервала элементов для сортировки
     * @param rightIndex – конечный индекс интервала элементов для сортировки
     * @return индекс опорного элемента
     */
    private static int getPivotIndex(Object[] array, int leftIndex, int rightIndex) {
        Object pivot = array[rightIndex];
        int i = leftIndex - 1;
        for (int j = leftIndex; j <= rightIndex - 1; j++) {
            if (CUSTOM_COMPARATOR.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, rightIndex);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве.
     *
     * @param array – массив, содержащий элементы для замены
     * @param i – индекс первого элемента для замены
     * @param j – индекс второго элемента для замены
     */
    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Выводит в формате строки все элементы массива.
     * StringBuilder используется для построения возвращаемой строки.
     * Цикл проходит по размеру массива элементов.
     * Если индекс больше 0, перед элементом будут добавлены запятая и пробел и элемент будет добавлен в строку.
     *
     * @return строку, построенную из элементов массива
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(i > 0 ? ", " : "");
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Реализует интерфейс Comparator и предоставляет функцию сравнения
     * Если оба объекта равны, вернет 0.
     * Если первый объект имеет значение null, вернет -1.
     * Если второй объект имеет значение null, вернет 1.
     * Сравнивает объекты либо с помощью интерфейса Comparable, либо методом toString().
     *
     * @return -1 если объект 1 меньше объекта 2
     * @return 0, если объекты равны
     * @return 1 если объект 1 больше объекта 2
     */
    public static class CustomComparator implements Comparator<Object> {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 == o2) return 0;
            if (o1 == null) return -1;
            if (o2 == null) return 1;

            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                return ((Comparable) o1).compareTo(o2);
            } else {
                return o1.toString().compareTo(o2.toString());
            }
        }
    }
}