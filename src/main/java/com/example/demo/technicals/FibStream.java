package com.example.demo.technicals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.*;

public class FibStream {
    int size;
    ArrayList<Long> list;
    HashMap<Long, ArrayList<Long>> hash;

    /*
     Construct the nth fibonacci number
     @param: nth constrained to 92 because of maximum long
     */
    FibStream(int nth) {
        this.size = nth;
        this.list = new ArrayList<Long>();
        this.hash = new HashMap<>();

        Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
            .limit(nth)
            .forEach(f -> {
                hash.put(f[0],(ArrayList<Long>)list.clone());
                list.add(f[0]);
            });
    }

    long getNth() {
        return list.get(size - 1);
    }

    ArrayList<Long> getNthSeq() {
        return hash.get(this.getNth());
    }

    ArrayList<Long> getList() {
        return list;
    }

    HashMap<Long, ArrayList<Long>> getHash() {
        return hash;
    }

    public static void main(String[] args) {
        int num = 20;   //number of Fibs, 92 is max for long
        FibStream fibs = new FibStream(num);

        System.out.println(fibs.getNth());
        System.out.println(fibs.getNthSeq());

        //System.out.println(fibs.getList());
        //System.out.println(fibs.getHash());
    }
}
