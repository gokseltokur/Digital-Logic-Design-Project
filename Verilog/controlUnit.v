module controlUnit(
clock,
instruction,
src1Value,
src2Value,
dr,
sr1,
sr2,
immSignal,
imm,
addr,
src1,
src2,
registerWrite,
branchSignal,
store,
jump,
aluOption,
ldStAddr,
address14,
pcRead,
instRead,
memLoad,
memStore,
compare
);

input clock;
input wire [17:0] instruction;
input [17:0] src1Value;
input [17:0] src2Value;

output wire [3:0] dr;
output wire [3:0] sr1;
output wire [3:0] sr2;
output wire immSignal;
output wire [13:0] imm;
output wire [13:0] addr;
output wire [3:0] src1;
output wire [3:0] src2;
output wire registerWrite;
output wire branchSignal;
output wire store;
output wire jump;
output wire[1:0] aluOption;
output wire [13:0] ldStAddr;
output wire [13:0] address14;
output wire pcRead;
output wire instRead;
output wire memLoad;
output wire memStore;
output wire compare;

wire state1;
wire state2;
wire state3;
wire state4;
wire state5;
wire [3:0] opcode;
assign opcode = instruction[17:14];

assign AND = (opcode == 4'b0000) ? 1'b1 : 1'b0;
assign ANDI = (opcode == 4'b0001) ? 1'b1 : 1'b0;
assign ADD = (opcode == 4'b0010) ? 1'b1 : 1'b0;
assign ADDI = (opcode == 4'b0011) ? 1'b1 : 1'b0;
assign OR = (opcode == 4'b0100) ? 1'b1 : 1'b0;
assign ORI = (opcode == 4'b0101) ? 1'b1 : 1'b0;
assign XOR = (opcode == 4'b0110) ? 1'b1 : 1'b0;
assign XORI = (opcode == 4'b0111) ? 1'b1 : 1'b0;
assign LD = (opcode == 4'b1000) ? 1'b1 : 1'b0;
assign ST = (opcode == 4'b1001) ? 1'b1 : 1'b0;
assign JUMP = (opcode == 4'b1010) ? 1'b1 : 1'b0;
assign BEQ = (opcode == 4'b1011) ? 1'b1 : 1'b0;
assign BGT = (opcode == 4'b1100) ? 1'b1 : 1'b0;
assign BLT = (opcode == 4'b1101) ? 1'b1 : 1'b0;
assign BGE = (opcode == 4'b1110) ? 1'b1 : 1'b0;
assign BLE = (opcode == 4'b1111) ? 1'b1 : 1'b0;


assign pcRead = state1;
assign instRead = state2;

endmodule
