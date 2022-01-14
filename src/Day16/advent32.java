package Day16;
import java.util.*;
import java.io.*;

public class advent32 {
    static int ExtraBits = 0;
    static long res;
    public static void main(String[] args) throws IOException {
        File f = new File("advent32.txt");
        Scanner sc = new Scanner(f);
    
        String input = sc.nextLine();
        input = decoder(input);
        packet(input,0);
        System.out.println("Resultado:"+res);
        sc.close();
    }

    public static long packet(String input,int bit){
        //int packetVersion = binaryToDecimal(""+input.charAt(bit)+input.charAt(bit+1)+input.charAt(bit+2));
        long packetTypeID = binaryToDecimal(""+input.charAt(bit+3)+input.charAt(bit+4)+input.charAt(bit+5));
        bit += 6;


        if(packetTypeID==0){
            res = sumPacket(input, bit);
            bit = ExtraBits;

        }else if(packetTypeID==1){
            res = productPacket(input, bit);
            bit = ExtraBits;
            
        }else if(packetTypeID==2){
            res = minimumPacket(input, bit);
            bit = ExtraBits;
            
        }else if(packetTypeID==3){
            res = maximumPacket(input, bit);
            bit = ExtraBits;
            
        }else if(packetTypeID==4){
            res = literalPacket(input,bit);
            bit = ExtraBits;

        }else if(packetTypeID==5){
            res = GTPacket(input, bit);
            bit = ExtraBits;
            
        }else if(packetTypeID==6){
            res = LTPacket(input, bit);
            bit = ExtraBits;
            
        }else{
            res = equalPacket(input, bit);
            bit = ExtraBits;
            
        }
        return res;
    }

    public static long sumPacket(String input,int bit){
        long sum=0;
        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                sum+=packet(input.substring(bit, input.length()),0);
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                sum+=packet(input.substring(bit, input.length()),0);
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        return sum;
    }

    public static long productPacket(String input,int bit){
        long product=1;
        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                product*=packet(input.substring(bit, input.length()),0);
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                product*=packet(input.substring(bit, input.length()),0);
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        return product;
    }

    public static long minimumPacket(String input,int bit){
        List<Long> lista = new ArrayList<Long>();
        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        return minimum(lista);
    }

    private static long minimum(List<Long> lista){
        long min=lista.remove(0);
        long size = lista.size();
        long temp;
        for (int i = 0; i < size; i++) {
            temp=lista.remove(0);
            if(temp<min){
                min = temp;
            }
        }
        return min;
    }

    public static long maximumPacket(String input,int bit){
        List<Long> lista = new ArrayList<Long>();
        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        return maximum(lista);
    }

    private static long maximum(List<Long> lista){
        long max=lista.remove(0);
        long size = lista.size();
        long temp;
        for (int i = 0; i < size; i++) {
            temp=lista.remove(0);
            if(temp>max){
                max = temp;
            }
        }
        return max;
    }
    

    public static long literalPacket(String input,int bit){
        String output="";
        long checkBit;
        do{
            output += input.substring(bit+1,bit+5);
            checkBit = Integer.parseInt(input.substring(bit,bit+1));
            bit+=5;
        }while(checkBit==1);
        ExtraBits = bit;
        return binaryToDecimal(output);
    }

    public static long GTPacket(String input,int bit){
        List<Long> lista = new ArrayList<Long>();

        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        if(compare(lista)==1){
            return 1;
        }else{
            return 0;
        }
    }

    public static long LTPacket(String input,int bit){
        List<Long> lista = new ArrayList<Long>();

        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        if(compare(lista)==-1){
            return 1;
        }else{
            return 0;
        }
    }

    public static long equalPacket(String input,int bit){
        List<Long> lista = new ArrayList<Long>();

        if(input.charAt(bit)=='0'){
            bit++;
            long length = binaryToDecimal(input.substring(bit, bit+15));
            bit+=15;
            long check = bit + length;
            while(check>bit){
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }else{
            bit++;
            long nPackets = binaryToDecimal(input.substring(bit, bit+11));
            bit+=11;
            for (int i = 0; i < nPackets; i++) {
                lista.add(packet(input.substring(bit, input.length()),0));
                bit+=ExtraBits;
            }
        }
        ExtraBits = bit;

        if(compare(lista)==0){
            return 1;
        }else{
            return 0;
        }
    }

    private static long compare(List<Long> lista){
        long value1 = lista.remove(0);
        long value2 = lista.remove(0); 
        long res=2;
        if(value1>value2){
            res=1;
        }else if(value1<value2){
            res=-1;
        }else if(value1==value2){
            res=0;
        }
        return res;
    }

    public static long binaryToDecimal(String input){
        long output=0;

        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)=='1'){
                output += Math.pow(2,input.length()-1-i);
            }
        }

        return output;
    }

    public static String decoder(String input){
        String output="";
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