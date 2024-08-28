package com.ddm;

import java.io.IOException;
import java.util.ArrayList;

import static com.ddm.LexicalParser.readFile;
import static com.ddm.LexicalParser.semicolonPos;
import static com.ddm.Templates.listOfTemplates;



public class SyntaxParser {
//    ArrayList<ArrayList<Token>> strings = new ArrayList<>(s);
    ArrayList<Token> tokenList;
    int pos = 0;
    public SyntaxParser(ArrayList<Token> tokens) {
        this.tokenList = tokens;
    }


    public boolean isSemicolomn(int i) {
        if(tokenList.get(i).type == ton.SEMICOLON) {
            return true;
        }
        return false;
    }
    public int strLength(int k) {
        if(k == 0) {
            return semicolonPos.get(k);
        }
        return semicolonPos.get(k) - semicolonPos.get(k-1) - 1;
    }

    public void templateChecker() {
        for(int k = 0; k < semicolonPos.size(); k++) {
            boolean checkForException = false;
            for(int i = 0; i < listOfTemplates.size(); i++) {
                ArrayList<ton> dataTypeCheck = new ArrayList<>();
                for (int j = 0; j < listOfTemplates.get(i).size(); j++) {
                    dataTypeCheck.add(tokenList.get(semicolonPos.get(k) + j - strLength(k)).type);
                    if(isSemicolomn(semicolonPos.get(k) + j - strLength(k))) {
                        if(dataTypeCheck.equals(listOfTemplates.get(i))){
                            checkForException = true;
                            System.out.println("Yes");
                        }
                        break;
                    }
                }
            }
            if(!checkForException) {
                throw new CompileErrorException("Compile Error");
            }
        }
    }



    public static void main(String[] args) throws IOException {
        SyntaxParser s = new SyntaxParser(LexicalParser.LexParse(readFile("test.tiny")));
        s.templateChecker();
    }
}
