package com.example.lessons.models;

import java.util.HashMap;
import java.util.ArrayList;

/*
Family Information Class in Static form
*/
public class FamilyStatic {
    /*
    Family Hash Map is built in a sequential process
     */
    public static void main(String[] arg) {
        // kvPairsToMap is used to shorten map.put statements
        HashMap<Object, Object> me = Family.kvPairsToMap(Family.nameKy, "John", Family.dobKy, "10/21/1951");
        HashMap<Object, Object> lora = Family.kvPairsToMap(Family.nameKy, "Lora", Family.dobKy, "9/23/1971");
        HashMap<Object, Object> trent = Family.kvPairsToMap(Family.nameKy, "Trent", Family.dobKy, "1/2/1988", Family.adoptKy, true);
        HashMap<Object, Object> corey = Family.kvPairsToMap(Family.nameKy, "Corey", Family.dobKy, "3/24/1989", Family.adoptKy, true);
        HashMap<Object, Object> tiernan = Family.kvPairsToMap(Family.nameKy, "Tiernan", Family.dobKy, "8/2/1994", Family.adoptKy, true);
        HashMap<Object, Object> claire = Family.kvPairsToMap(Family.nameKy, "Claire", Family.dobKy, "7/7/1997", Family.adoptKy, true);
        HashMap<Object, Object> shay = Family.kvPairsToMap(Family.nameKy, "Shay", Family.dobKy, "5/2/2009");

        // children hashmaps are added to an Array List
        ArrayList<Object> children = new ArrayList<>();
        children.add(trent);
        children.add(corey);
        children.add(tiernan);
        children.add(claire);
        children.add(shay);

        // family is formed as it becomes complete object, this is valuable to Web programming
        HashMap<Object, Object> family = Family.kvPairsToMap(Family.individualKy, me, Family.spouseKy, lora, Family.childrenKy, children);
        // print the family object
        Family.testPrint(family);
    }
}
