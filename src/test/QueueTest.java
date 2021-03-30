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
		//assertNull(queue.dequeue());
		assertNull(queue.front());

		queue.enqueue("Andrew");
		assertFalse(queue.isEmpty());
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Andrew"));

		queue.enqueue("Shariq");
		assertFalse(queue.isEmpty());
		assertEquals(2,queue.size());
		assertTrue(queue.front().equals("Andrew"));
		
		assertTrue(queue.dequeue().equals("Andrew"));
		assertFalse(queue.isEmpty());
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Shariq"));
		
		queue.enqueue("Nicolas");
		assertFalse(queue.isEmpty());
		assertEquals(2,queue.size());
		assertTrue(queue.front().equals("Shariq"));
		
		assertTrue(queue.dequeue().equals("Shariq"));
		assertFalse(queue.isEmpty());
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Nicolas"));
		
		assertTrue(queue.dequeue().equals("Nicolas"));
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		
		//assertNull(queue.dequeue());
		assertNull(queue.front());
		
		queue.enqueue("Rashid");
		assertFalse(queue.isEmpty());
		assertEquals(1,queue.size());
		assertTrue(queue.front().equals("Rashid"));
		
		assertTrue(queue.dequeue().equals("Rashid"));
		assertEquals(0,queue.size());
		assertTrue(queue.isEmpty());
		//assertNull(queue.dequeue());
		assertNull(queue.front());
		
	}
}
