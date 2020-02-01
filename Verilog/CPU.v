module CPU(
clock,
clearPC,
resetRegisterFile,
carryOut,
resetInstructionRegister,
resetPCRegister,
instruction
);

input wire clock;
input wire clearPC;
input wire resetInstructionRegister;
input wire resetRegisterFile;
input wire resetPCRegister;

output carryOut;

output reg [17:0] instruction;

wire [3:0] dest;
wire [3:0] SR1;
wire [3:0] SR2;
wire [13:0] imm;
wire immSignal;
wire [13:0] ADDR;
wire [3:0] SRC1;
wire [3:0] SRC2;
wire registerWrite;
wire branch;
wire jumpSig;
wire [1:0] aluControl;
wire [13:0] jumpAddr;
wire pcRead;
wire instRead;
wire memLoad;
wire memStore;
wire compare;


wire [17:0] aluResult;
wire [17:0] dataMemValue;

// REGISTER FILE
wire [17:0] registerFileInput;
assign registerFileInput = (memLoad) ? dataMemValue : aluResult;

wire [3:0] readRegister1;
wire [3:0] readRegister2;
wire [17:0] registerData1;
wire [17:0] registerData2;
wire [17:0] reg1Out;
wire [17:0] reg2Out;
wire [17:0] src1Value;
wire [17:0] src2Value;

assign readRegister1 = (memStore) ? dest : ((branch) ? SRC1 : SR1);
assign readRegister2 = (branch) ? SRC2 : SR2;
assign src1Value = (compare) ? registerData1 : 18'h00000;
assign src2Value = (compare) ? registerData2 : 18'h00000;

assign reg1Out = (compare) ? 18'h00000 : registerData1;
assign reg2Out = (compare) ? 18'h00000 : registerData2;

wire [17:0] aluInput2;
assign aluInput2 = (immSignal) ? imm : src2Value;


wire [17:0] instructionQ;
always @(posedge clock)
begin
	instruction = instructionQ;
end


wire [13:0] PCAddress;


endmodule
