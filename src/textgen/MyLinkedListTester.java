/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}

	/**
	 * Test if the get method is working correctly.
	 */
	/*
	 * You should not need to add much to this method. We provide it as an
	 * example of a thorough test.
	 */
	@Test
	public void testGet() {
		// test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

	}

	/**
	 * Test removing an element from the list. We've included the example from
	 * the concept challenge. You will want to add more tests.
	 */
	@Test
	public void testRemove() {

		// test empty list, get should throw an exception
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.remove(0));
		assertEquals("Check second", "B", shortList.remove(0));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for (int i = LONG_LIST_LENGTH - 1; i >= 0; i--) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.remove(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.remove(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	/**
	 * Test adding an element into the end of the list, specifically public
	 * boolean add(E element)
	 */
	@Test
	public void testAddEnd() {
		shortList.add("C");
		assertEquals("Check last shortList", "C", shortList.get(2));
		list1.add(100);
		assertEquals("Check last list", (Integer) 100, list1.get(3));
		try {
			longerList.add(null);
			fail("Element cannot be null");
		} catch (NullPointerException e) {
		}

	}

	/** Test the size of the list */
	@Test
	public void testSize() {
		assertEquals("Check shortList size", 2, shortList.size());
		assertEquals("Check list1 size", 3, list1.size());
	}

	/**
	 * Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element)
	 */
	@Test
	public void testAddAtIndex() {

		shortList.add(0, "C");
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "C", shortList.get(0));

		shortList.add(2, "E");
		assertEquals("Check second", "A", shortList.get(1));
		assertEquals("Check second", "E", shortList.get(2));
		assertEquals("Check second", "B", shortList.get(3));

		try {
			shortList.add(-1, "A");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i, i + 1);
			assertEquals("Check " + i + " element", (Integer) (i + 1), longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.add(-1, 10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.add(LONG_LIST_LENGTH*10, 100);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			longerList.add(1, null);
			fail("Element cannot be null");
		} catch (NullPointerException e) {
		}
	}

	/** Test setting an element in the list */
	@Test
	public void testSet() {

		// test empty list, get should throw an exception
		try {
			emptyList.set(0, 1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.set(0, "C"));
		assertEquals("Check second", "B", shortList.set(1, "D"));

		try {
			shortList.set(-1, "C");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.set(2, "E");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.set(i, 100));
		}

		// test off the end of the longer array
		try {
			longerList.set(-1, 100);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.set(LONG_LIST_LENGTH, 100);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			longerList.set(1, null);
			fail("Element cannot be null");
		} catch (NullPointerException e) {
		}
	}
}
