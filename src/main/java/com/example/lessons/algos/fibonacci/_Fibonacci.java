package com.example.lessons.algos.fibonacci;
import com.example.lessons.consoleUI.ConsoleMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.Instant;
import java.time.Duration;


abstract class _Fibonacci {
    int size;
    Duration timeElapsed;
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

        Instant start = Instant.now();
        this.init();
        Instant end = Instant.now();
        this.timeElapsed = Duration.between(start, end);
    }

    protected abstract void init();

    public void setData(long num) {
        list.add(num);
        hash.put(num, list.clone());
    }

    public int getTimeElapsed() {
        return timeElapsed.getNano();
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
        ConsoleMethods.println("For");
        ConsoleMethods.println(fibf.getNth());
        ConsoleMethods.println(fibf.getNthSeq());
        ConsoleMethods.println(fibf.getList());
        ConsoleMethods.println(fibf.getHash());

        FibRecurse fibr = new FibRecurse(num);
        ConsoleMethods.println("Recurse");
        ConsoleMethods.println(fibr.getNth());
        ConsoleMethods.println(fibr.getNthSeq());
        ConsoleMethods.println(fibr.getList());
        ConsoleMethods.println(fibr.getHash());

        FibStream fibs = new FibStream(num);
        ConsoleMethods.println("Stream");
        ConsoleMethods.println(fibs.getNth());
        ConsoleMethods.println(fibs.getNthSeq());
        ConsoleMethods.println(fibs.getList());
        ConsoleMethods.println(fibs.getHash());

        FibWhile fibw = new FibWhile(num);
        ConsoleMethods.println("While");
        ConsoleMethods.println(fibw.getNth());
        ConsoleMethods.println(fibw.getNthSeq());
        ConsoleMethods.println(fibw.getList());
        ConsoleMethods.println(fibw.getHash());
    }
}