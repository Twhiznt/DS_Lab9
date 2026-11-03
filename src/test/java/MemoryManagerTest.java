import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryManagerTest
{
	
	MemoryManager m; //= new MemoryManager(10);

	
	@BeforeEach
	void setUp() {
			m = new MemoryManager(10);
	}
	
	//if memory is all free (nothing is there), put memory in first place,
	//and node should be split
	
	@Test
	void emptySplitTest()
		{
			MemoryAllocation testAll = m.requestMemory(4, "A");
			//owner, len, pos
			assertEquals("A", testAll.getOwner());
			assertEquals(4, testAll.getLength());
			assertEquals(0, testAll.getPosition());
			
			MemoryAllocation testNext = m.requestMemory(6, "B");
			//owner, len, pos
			assertEquals("B", testNext.getOwner());
			assertEquals(6, testNext.getLength());
			assertEquals(4, testNext.getPosition());
			
			//no space
			assertNull(m.requestMemory(1, "C"));
			
			
		}
	
	//if previous and next node are free
	//Did those merges really work?
	
	@Test
	void testMerge() {
			MemoryAllocation A= m.requestMemory(5, "A");
			MemoryAllocation B= m.requestMemory(5, "B");
			
			m.returnMemory(A);
			m.returnMemory(B);
			
			MemoryAllocation C = m.requestMemory(10, "C");
			
			assertNotNull(C);
			
	}
	
	@Test
	void mergePrevNext() {
			MemoryAllocation A= m.requestMemory(5, "A");
			MemoryAllocation B= m.requestMemory(1, "B");	
			MemoryAllocation C= m.requestMemory(4, "C");

			m.returnMemory(A);
			m.returnMemory(C);
			
			//now just B is occupied
			assertNull(m.requestMemory(6, "D"));
			
			m.returnMemory(B);
			
			assertNotNull(m.requestMemory(10, "D"));
	}



}
