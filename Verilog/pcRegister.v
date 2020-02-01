module pcRegister(
jumpAddr,
AddressSelect,
enable,
clock,
clear,
pcAddress
);

input [13:0] jumpAddr;
input enable;
input clock;
input AddressSelect;
input clear;
output reg [13:0] pcAddress;
wire [13:0] afterAddres;
parameter beg = 14'h001;		

initial begin
pcAddress = 14'h000;
end

assign afterAddres = (AddressSelect) ? pcAddress + jumpAddr : pcAddress + beg;
always @ (clear)
begin
	if(clear) begin
	pcAddress = 0;
	end
end

always @ (negedge clock)
begin
	pcAddress = afterAddres;
end


endmodule
