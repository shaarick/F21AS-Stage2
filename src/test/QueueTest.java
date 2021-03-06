package test;

import main.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QueueTest {
	Queue<String> queue;
	
	@Before
	public void setup() {
		queue = new Queue<String>();
	}
	
	
	@Test
	public void test() {
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		try {
			queue.dequeue();
			fail();
		}
		catch(EmptyQueueException exception) {
			assertTrue(exception.getMessage().equals("Queue is empty"));
		}
		try {
			queue.front();
			fail();
		}
		catch(EmptyQueueException exception) {
			assertTrue(exception.getMessage().equals("Queue is empty"));
		}
		
		try {
			queue.enqueue("Andrew");
			assertFalse(queue.isEmpty());
			assertEquals(1,queue.size());
			assertTrue(queue.front().equals("Andrew"));
		}
		catch(EmptyQueueException exception) {
			fail();
		}
		try {
			queue.enqueue("Shariq");
			assertFalse(queue.isEmpty());
			assertEquals(2,queue.size());
			assertTrue(queue.front().equals("Andrew"));
		}
		catch(EmptyQueueException exception) {
			fail();
		}
		
		try {
			queue.dequeue();
			assertFalse(queue.isEmpty());
			assertEquals(1,queue.size());
			assertTrue(queue.front().equals("Shariq"));
		}
		catch(EmptyQueueException exception) {
			fail();
		}
		try {
			queue.enqueue("Nicolas");
			assertFalse(queue.isEmpty());
			assertEquals(2,queue.size());
			assertTrue(queue.front().equals("Shariq"));
		}
		catch(EmptyQueueException exception) {
			fail();
		}
		
		try {
			queue.dequeue();
			assertFalse(queue.isEmpty());
			assertEquals(1,queue.size());
			assertTrue(queue.front().equals("Nicolas"));
		}
		catch(EmptyQueueException exception) {
			fail();
		}
		
		try {
			queue.dequeue();
			assertEquals(0,queue.size());
			assertTrue(queue.isEmpty());
		}
		catch(EmptyQueueException exception) {
			fail();
		}
		try {
			queue.dequeue();
			fail();
		}
		catch(EmptyQueueException exception) {
			assertTrue(exception.getMessage().equals("Queue is empty"));
		}
		try {
			queue.front();
			fail();
		}
		catch(EmptyQueueException exception) {
			assertTrue(exception.getMessage().equals("Queue is empty"));
		}

		try {
			queue.enqueue("Rashid");
			assertFalse(queue.isEmpty());
			assertEquals(1,queue.size());
			assertTrue(queue.front().equals("Rashid"));
		}
		catch(EmptyQueueException exception) {
			fail();
		}
	}
	
	
	@Test(expected = EmptyQueueException.class)
	public void dequeueException() throws EmptyQueueException {
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		queue.dequeue();
	}
	
	
	@Test(expected = EmptyQueueException.class)
	public void frontException() throws EmptyQueueException {
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		queue.front();
	}
}
