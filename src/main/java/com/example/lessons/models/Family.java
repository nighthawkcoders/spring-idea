package com.example.lessons.models;

// Building HashMap
import java.util.HashMap;
import java.util.ArrayList;
// Working with JSON
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
Family Information Class
 */
public class Family {
    // Family Data
    HashMap<Object, Object> me;
    HashMap<Object, Object> spouse = null;
    ArrayList<HashMap<Object, Object>> children = null;
    // Keys Used in HashMaps
    static private String individualKy = "Individual";
    static private String spouseKy = "Spouse";
    static private String childrenKy = "Children";
    static private String nameKy = "Name";
    static private String dobKy = "DOB";
    static private String adoptKy = "Adopted";

    /*
    Family constructor requires Individual
     */
    public Family(String name, String DOB) {
        this.setIndividual(name, DOB);
    }

    /*
    Family constructor by JSON
     */
    public Family(JsonNode node) {
        try {
            // unwrap JsonNode
            HashMap<?, ?> family = new ObjectMapper().readValue(node.toString(), HashMap.class);
            // set individual
            HashMap<?, ?> individual = (HashMap<?, ?>) family.get(individualKy);
            this.setIndividual((String)individual.get(nameKy), (String)individual.get(dobKy));
            // set spouse
            HashMap<?, ?> spouse = (HashMap<?, ?>) family.get(spouseKy);
            this.setSpouse((String)spouse.get(nameKy), (String)spouse.get(dobKy));
            // add children
            ArrayList<?> children = (ArrayList<?>) family.get(childrenKy);
            if (children != null) {
                for (Object child : children) {
                    HashMap<?, ?> hChild = (HashMap<?, ?>) child;
                    // Test for adopted
                    if (hChild.containsKey(adoptKy)) {
                        addChild((String)hChild.get(nameKy), (String)hChild.get(dobKy), true);
                    } else {
                        addChild((String)hChild.get(nameKy), (String)hChild.get(dobKy));
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /*
    Export a Family HashMap
     */
    public HashMap<Object, Object> getFamily() {
        return kvPairsToMap(individualKy, this.me, spouseKy, this.spouse, childrenKy, this.children);
    }

    /*
    Export a Family JSON
     */
    public JsonNode getJSON() {
        JsonNode node = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            node = mapper.convertValue(this.getFamily(), JsonNode.class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return node;
    }

    /*
    Add spouse to Family
     */
    public void setIndividual (String name, String DOB) {
        this.me = kvPairsToMap(nameKy, name, dobKy, DOB);
    }

    public String getIndividualName() {
        return (String) me.get(nameKy);
    }

    public String getIndividualDOB() {
        return (String) me.get(dobKy);
    }

    /*
    Add spouse to Family
     */
    public void setSpouse(String name, String DOB) {
        this.spouse = kvPairsToMap(nameKy, name, dobKy, DOB);
    }

    public String getSpouseName() {
        return (String) spouse.get(nameKy);
    }

    public String getSpouseDOB() {
        return (String) spouse.get(dobKy);
    }

    /*
    Add child(ren) to Family
     */
    public void addChild(String name, String DOB, Object... adopted) {
        HashMap<Object, Object> child;
        // initialization check
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        // adopted condition
        if (adopted.length > 0) {
            child = kvPairsToMap(nameKy, name, dobKy, DOB, adoptKy, true);
        } else {
            child = kvPairsToMap(nameKy, name, dobKy, DOB);
        }
        children.add(child);
    }

    /*
    Print Family object
     */
    public void print(String msg) {
        System.out.println(msg);
        System.out.println(this);               // use toString method
        System.out.println("=".repeat(50));
    }

    /*
    Multiple key,value HashMap helper function
     */
    public static HashMap<Object, Object> kvPairsToMap(Object...args) {
        HashMap<Object, Object> map = new HashMap<>();
        for (int i=0; i<args.length; i+=2) {
            map.put(args[i], args[i+1]);
        }
        return map;
    }

    /*
    Test Print family members
     */
    public String toString() {
        StringBuilder outString;

        // get individual datq
        outString = new StringBuilder(String.format("%s: %s, %s%n",
                individualKy, me.get(nameKy), me.get(dobKy)));

        // get spouse data
        if ( spouse != null)
            outString.append(String.format("%s: %s, %s%n",
                    spouseKy, spouse.get(nameKy), spouse.get(dobKy)));

        // get children data
        if (children != null) {
            outString.append(String.format("%s:%n", childrenKy));
            // System.out.println(family.get(childrenKy));
            for (Object child : children) {
                // hChild is map of child type in this example is confident
                HashMap<?, ?> map = (HashMap<?, ?>) child;
                if (map.containsKey(adoptKy))
                    outString.append(String.format("%s, %s, %s%n",
                            map.get(nameKy), map.get(dobKy), adoptKy));
                else
                    outString.append(String.format("%s, %s%n",
                            map.get(nameKy), map.get(dobKy)));
            }
        }
        return outString.toString();
    }



}
