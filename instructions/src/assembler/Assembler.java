package assembler;



abstract class Instruction{ abstract int execute(int[] registers, int index); }

class LoadConstant extends Instruction{
	
	int register;
	int value;
	
	LoadConstant(int r, int c) {
		this.register = r;
		this.value = c;
	}
	
	@Override
	int execute(int[] registers, int index) {
		registers[this.register] = this.value;
		return index + 1;
	}
}

class Decrement extends Instruction{
	
	int register;
	
	Decrement(int r) {
		this.register = r;
	}
	
	@Override
	int execute(int[] registers, int index) {
		registers[this.register]--;
		return index + 1;
	}
}

class Multiply extends Instruction{
	
	int register1;
	int register2;
	
	Multiply(int r1, int r2) {
		this.register1 = r1;
		this.register2 = r2;
	}
	
	@Override
	int execute(int[] registers, int index) {
		registers[this.register1] *= registers[this.register2];
		return index + 1;
	}
}

class JumpIfZero extends Instruction{
	
	int register;
	int index;
	
	JumpIfZero(int r, int a) {
		this.register = r;
		this.index = a;
	}
	
	@Override
	int execute(int[] registers, int index) {
		if (registers[this.register] == 0)
			return this.index;
		else
			return index + 1;
	}
}

class Jump extends Instruction{
	
	int index;
	
	Jump(int a) {
		this.index = a;
	}
	
	@Override
	int execute(int[] registers, int index) {
		return this.index;
	}
}

class Halt extends Instruction{
	
	@Override
	int execute(int[] registers, int index) {
		return -1;
	}
}	

class Assembler {
	
	void execute(int[] registers, Instruction[] instructions) {
		int index = 0;
		while(index != -1) {
			index = instructions[index].execute(registers, index);
		}
		for (int i = 0; i < registers.length; i++)
			System.out.printf("R%d: %d\n", i, registers[i]);
	}
	
	public static void main(String[] args) {
		Assembler assembler = new Assembler();
		assembler.execute(new int[] {40, 10, 0}, new Instruction[] {new LoadConstant(2, 1),
															   	  	new JumpIfZero(1, 5),
															   	  	new Multiply(2, 0),
															   	  	new Decrement(1),
															   	  	new Jump(1),
															   	 	new Halt()});
	}
}

