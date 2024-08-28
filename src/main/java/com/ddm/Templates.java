package com.ddm;

import java.util.ArrayList;

public class Templates {
    private static int n = 3;
    static ArrayList<ArrayList<ton> > listOfTemplates = new ArrayList<>(n);
    static ArrayList<ton> template1 = new ArrayList<>();
    static ArrayList<ton> template2 = new ArrayList<>();
    static ArrayList<ton> template3 = new ArrayList<>();

    static {
        template1.add(ton.DATATYPE);
        template1.add(ton.NAME);
        template1.add(ton.ASSIGN);
        template1.add(ton.VALUE);
        template1.add(ton.SEMICOLON);
        listOfTemplates.add(template1);
    }
    static {
        template2.add(ton.DATATYPE);
        template2.add(ton.NAME);
        template2.add(ton.SEMICOLON);
        listOfTemplates.add(template2);
    }
    static {
        template3.add(ton.NAME);
        template3.add(ton.ASSIGN);
        template3.add(ton.VALUE);
        template3.add(ton.SEMICOLON);
        listOfTemplates.add(template3);
    }
}
