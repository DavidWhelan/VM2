package vm;

public class Memory 
{
	private int memory[] = new int[4096];
	public int maxAddress = memory.length-1;
	
	public Memory() 
	{
		Clear();
	}

	public void Clear()
	{
		for(int i = 0; i<memory.length; i++)
		{
			memory[i] = 0;
		}
	}
	
	public int Read(int address)
	{
		return memory[address];
	}
	
	public void Write(int address, int value)
	{
		memory[address] = value;
	}
	
}
