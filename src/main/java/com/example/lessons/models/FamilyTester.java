package com.example.lessons.models;

// Jackson Object Mapping support
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    Export a Family Object into a JSON node
        -- Jackson provides ObjectMapper
     */
    public static JsonNode getJSON(Family obj) {
        JsonNode node = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            node = mapper.convertValue(obj, JsonNode.class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return node;
    }

    /*
    Import a JSON into a Family Object
        -- Jackson provides ObjectMapper
     */
    private static Family putJSON(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        Family obj = null;

        try {
            obj = mapper.readValue(node.toString(), Family.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /*
    Print Family object
     */
    private static void print(String msg, Family obj) {
        // JSON with Jackson conversions
        JsonNode node = getJSON(obj);
        Family objViaJSON = putJSON(node);

        System.out.println("=".repeat(50));
        System.out.println(msg);
        System.out.println("Lombok toString Boilerplate");System.out.println(obj);
        System.out.println("JSON via Jackson ObjectMapper (get from Object)");System.out.println(node);
        System.out.println("Lombok toString Boilerplate via Jackson (put from JSON)");System.out.println(objViaJSON);
        System.out.println("Family custom prettyPrint method");System.out.println(obj.prettyPrint()); // custom pretty print
        System.out.println("=".repeat(50));
    }

    /*
    Family Hash Map is built in a sequential process
     */
    public static void main(String[] arg) throws ParseException {
        // create a family object
        Family mortensen = new Family();
        Person person;

        person = new Person( "John", 61);
        mortensen.setPerson(person);
        print("Family Object Test, single", mortensen);

        // add spouse to object
        person = new Person( "Lora", 49);
        mortensen.setSpouse(person);
        print("Family Object Test, set spouse", mortensen);

        // add many children, notice usage of different amount of parameters
        mortensen.addChild("Trent", 33);
        mortensen.addChild("Corey", 31);
        mortensen.addChild("Tiernan", 26);
        mortensen.addChild("Claire", 23);
        mortensen.addChild("Shay", 11);
        print("Family Object Test, add children", mortensen);
    }
}
