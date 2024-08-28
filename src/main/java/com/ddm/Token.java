package com.ddm;

public class Token {
    public ton type;
    public String data;
    public int pos;
    public Token(ton type, String data, int pos) {
        this.type = type;
        this.data = data;
        this.pos = pos;
    }

//    @Override
//    public String toString() {
//        return "\tToken " +
//                "type=" + type +
//                ", data='" + data +
//                "', pos=" + pos + '\'' +
//                "\n";
//    }
}