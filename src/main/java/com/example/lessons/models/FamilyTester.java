package com.example.lessons.models;

import com.fasterxml.jackson.databind.JsonNode;

/*
Family Information Class in Static form
*/
public class FamilyTester {
    /*
    Family Hash Map is built in a sequential process
     */
    public static void main(String[] arg) {
        // create a family object
        Family mortensen = new Family("John", 61);
        mortensen.print("Family Object Test, single");

        // add spouse to object
        mortensen.setSpouse("Lora", 49);
        mortensen.print("Family Object Test, set spouse");

        // add many children, notice usage of different amount of parameters
        mortensen.addChild("Trent", 33);
        mortensen.addChild("Corey", 31);
        mortensen.addChild("Tiernan", 26);
        mortensen.addChild("Claire", 23);
        mortensen.addChild("Shay", 11);
        mortensen.print("Family Object Test, add children");
    }
}
