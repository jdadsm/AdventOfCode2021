package Day16;

import java.util.*;
import java.io.*;

public class advent31 {
    static int sum = 0;
    static int literalPacketBits = 0;

    public static void main(String[] args) throws IOException {
        File f = new File("advent31.txt");
        Scanner sc = new Scanner(f);

        String input = sc.nextLine();
        input = decoder(input);
        packet(input, 0);
        System.out.println("Sum:" + sum);
        sc.close();
    }

    public static int packet(String input, int bit) {
        int packetVersion = binaryToDecimal("" + input.charAt(bit) + input.charAt(bit + 1) + input.charAt(bit + 2));
        int packetTypeID = binaryToDecimal("" + input.charAt(bit + 3) + input.charAt(bit + 4) + input.charAt(bit + 5));
        sum += packetVersion;
        bit += 6;
        if (packetTypeID == 4) {
            System.out.println(literalPacket(input, bit));
            bit = literalPacketBits;
        } else {
            if (input.charAt(bit) == '0') {
                bit++;
                int length = binaryToDecimal(input.substring(bit, bit + 15));
                bit += 15;
                int check = bit + length;
                while (check > bit) {
                    bit += packet(input.substring(bit, input.length()), 0);
                }
            } else {
                bit++;
                int nPackets = binaryToDecimal(input.substring(bit, bit + 11));
                bit += 11;
                for (int i = 0; i < nPackets; i++) {
                    bit += packet(input.substring(bit, input.length()), 0);
                }
            }

        }
        return bit;
    }

    public static int literalPacket(String input, int bit) {
        String output = "";
        int checkBit;
        do {
            output += input.substring(bit + 1, bit + 5);
            checkBit = Integer.parseInt(input.substring(bit, bit + 1));
            bit += 5;
        } while (checkBit == 1);
        literalPacketBits = bit;
        return binaryToDecimal(output);
    }

    public static int binaryToDecimal(String input) {
        int output = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '1') {
                output += Math.pow(2, input.length() - 1 - i);
            }
        }

        return output;
    }

    public static String decoder(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '0':
                    output += "0000";
                    break;

                case '1':
                    output += "0001";
                    break;

                case '2':
                    output += "0010";
                    break;

                case '3':
                    output += "0011";
                    break;

                case '4':
                    output += "0100";
                    break;

                case '5':
                    output += "0101";
                    break;

                case '6':
                    output += "0110";
                    break;

                case '7':
                    output += "0111";
                    break;

                case '8':
                    output += "1000";
                    break;

                case '9':
                    output += "1001";
                    break;

                case 'A':
                    output += "1010";
                    break;

                case 'B':
                    output += "1011";
                    break;

                case 'C':
                    output += "1100";
                    break;

                case 'D':
                    output += "1101";
                    break;

                case 'E':
                    output += "1110";
                    break;

                case 'F':
                    output += "1111";
                    break;

                default:
                    break;
            }
        }
        return output;
    }
}