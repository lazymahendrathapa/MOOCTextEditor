package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int listSize;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.head = new LLNode<>(null);
		this.tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
		listSize = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	@Override
	public boolean add(E element) {

		if (element == null)
			throw new NullPointerException();

		LLNode<E> node = new LLNode<>(element);
		node.next = tail;
		node.prev = tail.prev;
		tail.prev.next = node;
		tail.prev = node;

		++listSize;

		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	@Override
	public E get(int index) {

		if (index > listSize || index < 0)
			throw new IndexOutOfBoundsException();

		int count = 0;

		LLNode<E> node = head.next;

		while (count != index) {

			node = node.next;
			++count;
		}

		if (node.next == null)
			throw new IndexOutOfBoundsException();

		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	@Override
	public void add(int index, E element) {

		if (index > listSize || index < 0)
			throw new IndexOutOfBoundsException();

		if (element == null) {
			throw new NullPointerException();
		}

		int count = 0;

		LLNode<E> node = head.next;

		while (count != index) {

			node = node.next;
			++count;
		}

		LLNode<E> newNode = new LLNode<>(element);

		newNode.next = node;
		newNode.prev = node.prev;
		node.prev.next = newNode;
		node.prev = newNode;

		++listSize;
	}

	/** Return the size of the list */
	@Override
	public int size() {
		return listSize;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	@Override
	public E remove(int index) {

		if (index > listSize || index < 0)
			throw new IndexOutOfBoundsException();

		int count = 0;

		LLNode<E> node = head.next;

		while (count != index) {

			node = node.next;
			++count;
		}

		if (node.next == null)
			throw new IndexOutOfBoundsException();

		node.prev.next = node.next;
		node.next.prev = node.prev;
		--listSize;

		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	@Override
	public E set(int index, E element) {

		if (index > listSize || index < 0)
			throw new IndexOutOfBoundsException();

		if (element == null) {
			throw new NullPointerException();
		}

		int count = 0;

		LLNode<E> node = head.next;

		while (count != index) {

			node = node.next;
			++count;
		}

		if (node.next == null)
			throw new IndexOutOfBoundsException();

		E oldElement = node.data;
		node.data = element;
		return oldElement;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	@Override
	public String toString() {
		return "LLNode [prev=" + prev + ", next=" + next + ", data=" + data + "]";
	}
}
