package vm;

import java.util.Scanner;

public class Terminal {

	public Terminal() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		Memory memory = new Memory();
		Scanner input = new Scanner(System.in);
		memory.Write(0, 1);
		memory.Write(1, 0);
		memory.Write(2, 1);
		memory.Write(3, 1);
		memory.Write(4, 1);
		memory.Write(5, 2);
		memory.Write(6, 5);
		memory.Write(7, 0);
		memory.Write(8, 1);
		memory.Write(9, 3);
		memory.Write(10, 100);
		memory.Write(11, 0);
		memory.Write(12, 4);
		memory.Write(13, 100);
		memory.Write(14, 0);
		CPU test = new CPU(memory);
		test.Run();
		input.close();
	}

}
