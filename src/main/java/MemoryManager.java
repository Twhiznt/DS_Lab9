

public class MemoryManager
{
 
    
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   protected MemoryAllocation back; 
   protected MemoryAllocation head;
   protected MemoryAllocation MemSpace;
   

   protected long availSpace; 
   
   public MemoryManager(long size)
   {
		   MemSpace = new MemoryAllocation(Free, size, 0, null, null);
		   head = MemSpace; 
		   back  = MemSpace;
		   //space = size; 
		   //availSpace = size;
   
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size, String requester)
   {

	MemoryAllocation clearSpot = findMemorySlot(size);
	if(clearSpot==null) {return null;}
	if(size==clearSpot.len)
	{
		clearSpot.owner=requester;
		return clearSpot;
	}
	///THE ISSUE THAT IS HAPPENING IS A FIRST CASE BC THE BIG SLOT IS ONE BIG NODE
	MemoryAllocation newMem = new MemoryAllocation(requester, size, clearSpot.pos, clearSpot.prev, clearSpot);
	
	newMem.next.prev = newMem; 
	newMem.prev.next = newMem;
	
	clearSpot.len = clearSpot.len - size; 
	clearSpot.pos = clearSpot.pos + size;
	
	return newMem;
	/*
	if ((size>))
	{
		while()
		{
			
		}
	      //iterate through linked list here to find pos
		
	}
	if (availSpace == space)
	{
		newMem = new MemoryAllocation(requester, size, 0, null, null);
		head = newMem;
		availSpace = availSpace - size;
	}
	//loop through list or something to find first Free
	//go make mem allocation a d linked list*/ 
   }

    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
		   MemoryAllocation p = mem.prev;
		   MemoryAllocation n = mem.next;
		   
		   //Look at prev and Front merge whatever is free into the new free node youre releasing
		   mem.owner = Free;
		   
		   //if prev and next are free
		   if(p.owner == Free && n.owner == Free) {
			   p.len = p.len + mem.len + n.len;
			   p.next = n.next;
			   p.next.prev = p;
			   return;
		   }
		   //if prev is free
		   else if(p.owner == Free) {
			   p.len = p.len + mem.len;
			   p.next = mem.next;
			   p.next.prev = p;
			   return;
		   }
		   //if next is free
		   else if(n.owner == Free) {
			   mem.len = mem.len + n.len;
			   mem.next = n.next;
			   mem.next.prev = mem;
			   return;
		   }
		   
   }
    

   public MemoryAllocation findMemorySlot(long size)
   {
		   //MemoryAllocation memSlot = new MemoryAllocation(Free, 0, 0, null, null);
		   MemoryAllocation current = head; 
		   while(current!=null && size>current.len)
		   {
			   current = current.next;
		   }
		   
		   
		   return current;
   }

}
