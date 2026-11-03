

public class MemoryManager
{
   protected MemoryAllocation head;
    
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   protected MemoryAllocation back; 
   protected long space;
   protected long availSpace; 
   
   public MemoryManager(long size)
   {
		   head = null; 
		   back  = null;
		   space = size; 
		   availSpace = size;
   
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
	MemoryAllocation newMem = null;
	if ((availSpace>=size))
	{
	      //iterate through linked list here to find pos
		
	}
	if (availSpace == space)
	{
		newMem = new MemoryAllocation(requester, size, 0, null, null);
		availSpace = availSpace - size;
	}
	//loop through list or something to find first Free
	//go make mem allocation a d linked list
	return newMem;
		   
   
   }


    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
   }
    



}
