package com.ddm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LexicalParser {

    public enum ton {TYPE, NAME, R_OPEN, R_CLOSE, F_OPEN, F_CLOSE, ASSIGN, PLUS, VALUE, SEMICOLON, SPACE, ENTER};
    static HashMap<Character, Character> delimiter = new HashMap<Character, Character>();

    static HashSet<String> num = new HashSet<>();
    static HashSet<String> typeData = new HashSet<>();

    static
    {
        typeData.add("int");
        for(int i = 0; i < 10; i++) {
            num.add(String.valueOf(i));
        }
        delimiter.put(' ', ' ');
        delimiter.put('=', '=');
        delimiter.put(';', ';');
        delimiter.put('+', '+');
    };

    public static boolean isSpace(char space) {
        if (delimiter.get(' ') == delimiter.get(space)) {
            return true;
        }
        return false;
    }
    public static boolean isAssign(char eq)
    {
        if (delimiter.get('=') == delimiter.get(eq)) {
            return true;
        }
        return false;
    }
    public static boolean isPlus(char plus) {
        if (delimiter.get('+') == delimiter.get(plus)) {
            return true;
        }
        return false;
    }
    public static boolean isSemicolon(char semicolon) {
        if (delimiter.get(';') == delimiter.get(semicolon)) {
            return true;
        }
        return false;
    }
    //All delimiter checks can be casted to a one big check isDelimiter(), but i can't come up with any ideas for now.
    public static boolean isType(String type) {
        if(typeData.contains(type)) {
            return true;
        }
        return false;
    }
    public static boolean isNum(String s) {
        String[] partNum = s.split("");
        for(int i = 0; i < partNum.length; i++) {
            if(!num.contains(partNum[i])) {
                return false;
            }
        }
        return true;
    }
    public static ArrayList<Token> LexParse(String input){
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        char[] inputArray = input.toCharArray();
        int size = inputArray.length;
        for(int i = 0; i < size; i++) {
            char semicolon = 0;
            stringBuffer.append(inputArray[i]);
            if(isSpace(inputArray[i])||isSemicolon(inputArray[i])) {
                if(isSemicolon(inputArray[i])) {
                    semicolon = inputArray[i];
                }
                if(String.valueOf(stringBuffer).contains("\r\n")) {
                    stringBuffer.delete(0,2);
                }
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
                if(isType(String.valueOf(stringBuffer))) {
                    tokens.add(new Token(ton.TYPE, String.valueOf(stringBuffer)));
                }
                else if(isNum(String.valueOf(stringBuffer))) {
                    tokens.add(new Token(ton.VALUE, String.valueOf(stringBuffer)));
                }
                else if(isAssign(inputArray[i-1])) {
                    tokens.add(new Token(ton.ASSIGN, String.valueOf(stringBuffer)));
                }
                else if(isPlus(inputArray[i-1])) {
                    tokens.add(new Token(ton.PLUS, String.valueOf(stringBuffer)));
                }
                else {
                    tokens.add(new Token(ton.NAME, String.valueOf(stringBuffer)));
                }
                if(isSemicolon(semicolon)) {
                    tokens.add(new Token(ton.SEMICOLON, String.valueOf(semicolon)));
                }
                stringBuffer.delete(0, 10);
            }

        }
        return tokens;
    }
    public static class Token {
        public ton type;
        public String data;

        public Token(ton type, String data) {
            this.type = type;
            this.data = data;
        }

        @Override
        public String toString() {
            return "\tToken " +
                    "type=" + type +
                    ", data='" + data + '\'' +
                    "\n";
        }
    }


    public static String readFile(String fileName) throws IOException {
        InputStream is = LexicalParser.class.getResourceAsStream("/"+fileName);
        byte[] bytes = is.readAllBytes();
        is.close();
        return new String(bytes);
    }






    public static void main(String[] args) throws IOException {

        LexicalParser l = new LexicalParser();

        System.out.println(LexParse(readFile("test.tiny")).toString());
    }
}
