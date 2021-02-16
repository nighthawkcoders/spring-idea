package com.example.lessons.models;

import com.fasterxml.jackson.databind.JsonNode;

/*
Family Information Class in Static form
*/
public class FamilyTester2 {
    /*
    Family Hash Map is built in a sequential process
     */
    public static void main(String[] arg) {
        // create a family object
        Family2 mortensen = new Family2("John", "10/21/1959");
        mortensen.print("Family Object Test, single");

        // add spouse to object
        mortensen.setSpouse("Lora", "9/23/1959");
        mortensen.print("Family Object Test, set spouse");

        // add many children, notice usage of different amount of parameters
        mortensen.addChild("Trent", "1/2/1988", true);
        mortensen.addChild("Corey", "3/24/1989", true);
        mortensen.addChild("Tiernan", "8/2/1994", true);
        mortensen.addChild("Claire", "7/7/1997", true);
        mortensen.addChild("Shay", "5/2/2009");
        mortensen.print("Family Object Test, add children");

        // making a JSON object with Jackson
        JsonNode mortensenJSON = mortensen.getJSON();
        System.out.println("JsonNode creation, object");
        System.out.println(mortensenJSON);
        System.out.println("JsonNode toString(), string");
        System.out.println(mortensenJSON.toString());
        System.out.println("=".repeat(50));

        // creating family object from a JSON object
        Family2 mortensen2 = new Family2(mortensenJSON);
        mortensen2.print("Family Object Test, constructing from JsonNode");
    }
}
