package com.example.demo.technicals;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

abstract class _Fibonacci {
    int size;
    long timeElapsed;
    ArrayList<Long> list;
    HashMap<Long, Object> hash;

    /*
     Construct the nth fibonacci number
     @param: nth constrained to 92 because of maximum long
     */
    public _Fibonacci(int nth) {
        this.size = nth;
        this.list = new ArrayList<>();
        this.hash = new HashMap<Long, Object>();

        long startTime = Instant.now().toEpochMilli();
        this.init();
        long endTime = Instant.now().toEpochMilli();
        this.timeElapsed = endTime - startTime;
    }

    protected abstract void init();

    public void setData(long num) {
        list.add(num);
        hash.put(num, list.clone());
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public long getNth() {
        return list.get(size - 1);
    }

    public Object getNthSeq() {
        return hash.get(this.getNth());
    }

    public ArrayList<Long> getList() {
        return list;
    }

    public HashMap<Long, Object> getHash() {
        return hash;
    }

    public static void main(String[] args) {
        int num = 20;   //number of Fibs, 92 is max for long

        FibFor fibf = new FibFor(num);
        System.out.println("For");
        System.out.println(fibf.getNth());
        System.out.println(fibf.getNthSeq());
        System.out.println(fibf.getList());
        System.out.println(fibf.getHash());

        FibRecurse fibr = new FibRecurse(num);
        System.out.println("Recurse");
        System.out.println(fibr.getNth());
        System.out.println(fibr.getNthSeq());
        System.out.println(fibr.getList());
        System.out.println(fibr.getHash());

        FibStream fibs = new FibStream(num);
        System.out.println("Stream");
        System.out.println(fibs.getNth());
        System.out.println(fibs.getNthSeq());
        System.out.println(fibs.getList());
        System.out.println(fibs.getHash());

        FibWhile fibw = new FibWhile(num);
        System.out.println("While");
        System.out.println(fibw.getNth());
        System.out.println(fibw.getNthSeq());
        System.out.println(fibw.getList());
        System.out.println(fibw.getHash());
    }
}