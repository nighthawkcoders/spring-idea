package com.example.lessons.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Family Information Class in Static form
*/
public class FamilyTester {
    private static Date str2date(String date) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(date);
    }
    /*
    Family Hash Map is built in a sequential process
     */
    public static void main(String[] arg) throws ParseException {
        // create a family object
        Family mortensen = new Family();
        mortensen.setPerson("John", 61, str2date("10/21/1959"));

        mortensen.print("Family Object Test, single");

        // add spouse to object
        mortensen.setSpouse("Lora", 49, str2date("9/23/1971"));
        mortensen.print("Family Object Test, set spouse");

        // add many children, notice usage of different amount of parameters
        mortensen.addChild("Trent", 33, str2date("1/2/1988"));
        mortensen.addChild("Corey", 31, str2date("3/24/1989"));
        mortensen.addChild("Tiernan", 26, str2date("8/2/1994"));
        mortensen.addChild("Claire", 23, str2date("7/7/1997"));
        mortensen.addChild("Shay", 11, str2date("5/2/2009"));
        mortensen.print("Family Object Test, add children");
    }
}
