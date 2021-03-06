package test;

import main.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QueueTest {
	Queue queue;
	
	@Before
	public void setup() {
		queue = new Queue();
	}
	
	
	@Test
	public void test() {
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		
		queue.enqueue("Andrew");
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Andrew"));
		assertFalse(queue.isEmpty());
		
		queue.enqueue("Stuart");
		assertEquals(2,queue.size());
		assertTrue(queue.front().equals("Andrew"));
		assertFalse(queue.isEmpty());
		
		queue.dequeue();
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Stuart"));
		assertFalse(queue.isEmpty());

		queue.dequeue();
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		
		queue.enqueue("Andrew");
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Andrew"));
		assertFalse(queue.isEmpty());
		
		queue.enqueue("Stuart");
		assertEquals(2,queue.size());
		assertTrue(queue.front().equals("Andrew"));
		assertFalse(queue.isEmpty());
		
		queue.dequeue();
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Stuart"));
		assertFalse(queue.isEmpty());

		queue.dequeue();
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
	}
}
