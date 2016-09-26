package vm;

public class CPU 
{
	/*
	 * A = 0
	 * B = 1
	 * C = 2
	 * D = 3
	 */
	//Defines our Hardware
	private Memory memory = new Memory();
	private int reg[] = new int[4];
	private int pc = 0;
	private boolean halt = false;

	public CPU(Memory memory) 
	{
		this.memory = memory;
		//Reset();
	}
	
	public void Reset()
	{
		pc = 0;
		memory.Clear();
		halt = false;
		for(int i = 0; i<4; i++)
		{
			reg[i] = 0;
		}
	}
	
	private void Halt()
	{
		halt = true;
	}
	
	private void LoadRegister()
	{
		int destination = memory.Read(pc);
		pc++;
		int sourceValue = memory.Read(pc);
		pc++;
		reg[destination] = sourceValue;
	}
	
	private void LoadFromMemory()
	{
		int destination = memory.Read(pc);
		pc++;
		int sourceAddress = memory.Read(pc);
		pc++;
		reg[destination] = memory.Read(sourceAddress);
	}
	
	private void StoreToMemory()
	{
		int destinationAddress = memory.Read(pc);
		pc++;
		int sourceReg = memory.Read(pc);
		pc++;
		memory.Write(destinationAddress, reg[sourceReg]);
	}
	
	private void Print()
	{
		int address = memory.Read(pc);
		int valueToPrint = memory.Read(address);
		pc++;
		System.out.println("[ " + address + " ] = " + valueToPrint);
	}
	
	private void Add()
	{
		int firstReg = memory.Read(pc);
		pc++;
		int secondReg = memory.Read(pc);
		pc++;
		reg[firstReg] = reg[firstReg] + reg[secondReg];
	}
	
	private void Subtract()
	{
		int firstReg = memory.Read(pc);
		pc++;
		int secondReg = memory.Read(pc);
		pc++;
		reg[firstReg] = reg[firstReg] - reg[secondReg];
	}

	private void Multiply()
	{
		int firstReg = memory.Read(pc);
		pc++;
		int secondReg = memory.Read(pc);
		pc++;
		reg[firstReg] = reg[firstReg] * reg[secondReg];
	}
	
	private void Divide()
	{
		int firstReg = memory.Read(pc);
		pc++;
		int secondReg = memory.Read(pc);
		pc++;
		reg[firstReg] = reg[firstReg] / reg[secondReg];
	}
	
	private int Fetch()
	{
		int opCode = memory.Read(pc);
		pc++;
		if(pc > 4093)
		{
			Halt();
			System.out.println("Error: That Ran to End of Memory");
		}
		return opCode;
	}
	
	private void Decode(int opCode)
	{
		if(halt)
		{
			return;
		}
		switch(opCode)
		{
		case 0:
			Halt();
			break;
		case 1:
			LoadRegister();
			break;
		case 2:
			LoadFromMemory();
			break;
		case 3:
			StoreToMemory();
			break;
		case 4:
			Print();
			break;
		case 5:
			Add();
			break;
		case 6:
			Subtract();
			break;
		case 7:
			Multiply();
			break;
		case 8:
			Divide();
			break;
		}
	}
	
	public void Run()
	{
		while(!halt)
		{
			int opCode = Fetch();
			Decode(opCode);
		}
	}
	
	//Define our OpCodes to make changing value easy;
	final int HALT = 0;
	final int LDR = 1;
	final int LDM = 2;
	final int STRM = 3;
	final int PRNT = 4;
	final int ADD = 5;
	final int SUB = 6;
	final int MUL = 7;
	final int DIV = 8;

}
