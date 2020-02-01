
module RegisterFile(
inputData,
writeRegister,
readRegister1,
readRegister2,
registerWrite,
clock,
reset,
registerData1,
registerData2,
);

input  [17:0] inputData;
input [3:0] writeRegister; //destination
input [3:0] readRegister1;  //SRC1	
input [3:0] readRegister2;  //SRC2
input  registerWrite;
input clock;
input reset;
output [17:0] registerData1;
output [17:0] registerData2;

integer i;

reg [17:0] registers [0:17];


initial begin
	for(i=0; i<18; i=i+1)
	begin
	registers[i] = 18'h0;
	end
end

always @ (reset)
begin
	if(reset) begin // reseting
		for(i=0; i<18; i=i+1)
		begin
		registers[i] = 18'h0;
		end
	end
end


always @ (posedge clock)
begin
	if(registerWrite) begin
		registers[writeRegister] = inputData;
	end
end
assign registerData1 = registers[readRegister1];//assigning
assign registerData2 = registers[readRegister2];

endmodule