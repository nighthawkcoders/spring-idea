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
    HashMap<Object, Object> family = null;
    // Keys Used in HashMaps
    static public String individualKy = "Individual";
    static public String spouseKy = "Spouse";
    static public String childrenKy = "Children";
    static public String nameKy = "Name";
    static public String dobKy = "DOB";
    static public String adoptKy = "Adopted";

    /*
    Family constructor
     */
    public Family(String name, String DOB) {
        this.me = kvPairsToMap(nameKy, name, dobKy, DOB);
    }

    /*
    Export of Family object
     */
    public HashMap<Object, Object> getFamily() {
        return kvPairsToMap(individualKy, this.me, spouseKy, this.spouse, childrenKy, this.children);
    }

    /*
    Add spouse to Family
     */
    public void setSpouse(String name, String DOB) {
        this.spouse = kvPairsToMap(nameKy, name, dobKy, DOB);
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
    Test Print family object
     */
    public static void testPrint(HashMap<Object, Object> family) {

        System.out.print("Family Object");
        System.out.println(family);

        // get individual from family, type in this example is confident
        HashMap<?, ?> applicant = (HashMap<?, ?>) family.get(individualKy);
        System.out.printf("%s: %s, %s%n", individualKy, applicant.get(nameKy), applicant.get(dobKy));
        // get spouse from family, type in this example is confident
        HashMap<?, ?> spouse = (HashMap<?, ?>)family.get(spouseKy);
        if (spouse != null)
            System.out.printf("%s: %s, %s%n", spouseKy, spouse.get(nameKy), spouse.get(dobKy));
        // get children from family, type in this example is confident
        ArrayList<?> children = (ArrayList<?>) family.get(childrenKy);
        if (children != null) {
            System.out.println("Children");
            // System.out.println(family.get(childrenKy));
            for (Object child : children) {
                // get children from family, type in this example is confident
                HashMap<?, ?> hChild = (HashMap<?, ?>) child;
                if (hChild.containsKey(adoptKy))
                    System.out.printf("%s, %s, %s%n", hChild.get(nameKy), hChild.get(dobKy), adoptKy);
                else
                    System.out.printf("%s, %s%n", hChild.get(nameKy), hChild.get(dobKy));
            }
        }
    }

    public static void main(String[] arg) throws JsonProcessingException {
        String separator = "=";

        // create a family object
        Family mortensen = new Family("John", "10/21/1959");
        // family object test,single
        Family.testPrint(mortensen.getFamily());
        System.out.println(separator.repeat(50));

        // add spouse to object
        mortensen.setSpouse("Lora", "9/23/1959");
        // family object, couple only
        Family.testPrint(mortensen.getFamily());
        System.out.println(separator.repeat(50));
        
        // add many children, notice usage of different amount of parameters
        mortensen.addChild("Trent", "1/2/1988", true);
        mortensen.addChild("Corey", "3/24/1989", true);
        mortensen.addChild("Tiernan", "8/2/1994", true);
        mortensen.addChild("Claire", "7/7/1997", true);
        mortensen.addChild("Shay", "5/2/2009");
        // family object with children
        Family.testPrint(mortensen.getFamily());

        // making a JSON object with Jackson
        ObjectMapper mapper = new ObjectMapper();
        JsonNode mortensenJSON = mapper.convertValue(mortensen, JsonNode.class);
        System.out.println(mortensenJSON);
        System.out.println(mortensenJSON.toString());
    }
}
