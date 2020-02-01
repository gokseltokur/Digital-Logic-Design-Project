//Göksel Tokur 150116049 - Ertuğrul Sağdıç 150116061 - Furkan Aras 150116019
import java.util.Scanner;
import java.io.*;

public class Assembler {
	public static void main(String[] args) {
		String[] configArr = new String[4];

		try {
			FileInputStream fstream = new FileInputStream("input.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int i = 0;
			
			File file = new File("out.hex");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
			FileWriter fileWriter = new FileWriter(file, false);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			bWriter.write("v2.0 raw");
			bWriter.newLine();
			bWriter.newLine();
			while ((strLine = br.readLine()) != null) {
				if(strLine.equals("")) 
					continue;
				//strLine = strLine.replaceAll("[,]", " ");
				String[] tokens = strLine.split(" |, |,");
				for (int q = 0; q < configArr.length; q++) {
					configArr[q] = null;
				}

				for (int j = 0; j < tokens.length && configArr[j] == null; j++) {
					configArr[j] = tokens[j].replaceAll("[,]", "");
				}
				//writing to file
				bWriter.write(binaryToHex(getBinaryCode(configArr)));
				bWriter.write(" ");
				i++;
			}
			bWriter.close();
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	// Merge all converted assembly code.
	public static String getBinaryCode(String[] asm) {
		String hex = "";

		if (asm[0].equalsIgnoreCase("and")) {
			hex = hex.concat("0000");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat("00");
			hex = hex.concat(registerToBinary(asm[3]));
		} else if (asm[0].equalsIgnoreCase("andi")) {
			hex = hex.concat("0001");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // immediate 6 bit twos complement
		} else if (asm[0].equalsIgnoreCase("add")) {
			hex = hex.concat("0010");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat("00");
			hex = hex.concat(registerToBinary(asm[3]));
		} else if (asm[0].equalsIgnoreCase("addi")) {
			hex = hex.concat("0011");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // immediate 6 bit twos complement
		} else if (asm[0].equalsIgnoreCase("or")) {
			hex = hex.concat("0100");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat("00");
			hex = hex.concat(registerToBinary(asm[3]));
		} else if (asm[0].equalsIgnoreCase("ori")) {
			hex = hex.concat("0101");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // immediate 6 bit twos complement
		} else if (asm[0].equalsIgnoreCase("xor")) {
			hex = hex.concat("0110");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat("00");
			hex = hex.concat(registerToBinary(asm[3]));
		} else if (asm[0].equalsIgnoreCase("xori")) {
			hex = hex.concat("0111");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // immediate 6 bit twos complement
		} else if (asm[0].equalsIgnoreCase("ld")) {
			hex = hex.concat("1000");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[2]), 10)); // 10 bit address
		} else if (asm[0].equalsIgnoreCase("st")) {
			hex = hex.concat("1001");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[2]), 10)); // 10 bit address
		} else if (asm[0].equalsIgnoreCase("jump")) {
			hex = hex.concat("1010");
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[1]), 14)); // 14 bit address
		} else if (asm[0].equalsIgnoreCase("beq")) {
			hex = hex.concat("1011");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // 3 address + n + z + p

		} else if (asm[0].equalsIgnoreCase("bgt")) {
			hex = hex.concat("1100");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // 3 address + n + z + p

		} else if (asm[0].equalsIgnoreCase("blt")) {
			hex = hex.concat("1101");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // 3 address + n + z + p

		} else if (asm[0].equalsIgnoreCase("bge")) {
			hex = hex.concat("1110");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // 3 address + n + z + p

		} else if (asm[0].equalsIgnoreCase("ble")) {
			hex = hex.concat("1111");
			hex = hex.concat(registerToBinary(asm[1]));
			hex = hex.concat(registerToBinary(asm[2]));
			hex = hex.concat(decimalToBinary(Integer.parseInt(asm[3]), 6)); // 3 address + n + z + p
		}

		return hex;
	}
	// Convert decimal value to fixed length binary string
	public static String decimalToBinary(int val, int fixed) {
		char[] buff = new char[fixed];

		for (int i = fixed - 1; i >= 0; i--) {
			int mask = 1 << i;
			buff[fixed - 1 - i] = (val & mask) != 0 ? '1' : '0';
		}
		return new String(buff);
	}

	// Function to parse register input and convert register's decimal to binary
	// value.
	public static String registerToBinary(String register) {
		String binaryFormOfRegister = "";
		String[] tokens = register.split("R");

		return decimalToBinary((Integer.parseInt(tokens[1])), 4);
	}

	// Function to convert binary string to hexadecimal string
	public static String binaryToHex(String binary) {
		String str="";
		int decimal = Integer.parseInt(binary, 2);//binary to decimal.
		if((Integer.toString(decimal, 16)).length() == 4) {//to write 0 to begining.
				str= str.concat("0");	
				str= str.concat(Integer.toString(decimal, 16));
					
					return str;
		}
		return  Integer.toString(decimal, 16);
			
	}
}
