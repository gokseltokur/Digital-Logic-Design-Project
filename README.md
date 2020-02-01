# Digital-Logic-Design-Project
We designed a basic CPU which has the 18 bit data size, 16 registers and instructions AND, ANDI, ADD, ADDI, OR, ORI, XOR, XORI, LD, ST, JUMP, BEQ, BGT, BLT, BGE, BLE.

## Instruction Set Architecture and Assembler
We first design an instruction set architecture and adjust data bits and address bits for each instruction (AND, ANDI, ADD, ADDI, OR, ORI, XOR, XORI, LD, ST, JUMP, BEQ, BGT, BLT, BGE, BLE).
Then we wrote an assembler with JAVA to produce machine code input to give the processor. Assembler input is a code sequence of assembly language. Assembler will convert given mnemonics to the binary codes.

![Instruction Set Architecture](https://github.com/gokseltokur/Digital-Logic-Design-Project/blob/master/Screenshot%20from%202020-02-01%2018-45-18.png)

## Finite State Machine
![Finite State Machine](https://github.com/gokseltokur/Digital-Logic-Design-Project/blob/master/finitestatemachine.png)

LD instruction consists of 4 states,
* 1. Read PC value
* 2. Fetch instruction from Instruction Memory with address given in PC.
* 3. Read Data Memory with given address in instruction.
* 4. Write content from Data Memory to destination register.

All of other instructions consist of 3 different steps,
* 1. Read PC value
* 2. Read Instruction
* 3. Process

## Control Unit and CPU
![Control Unit](https://github.com/gokseltokur/Digital-Logic-Design-Project/blob/master/controlunit.png)

![CPU](https://github.com/gokseltokur/Digital-Logic-Design-Project/blob/master/cpu.png)
