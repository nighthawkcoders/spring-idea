package com.example.lessons.algos.fibonacci;

public class FibRecurse extends _Fibonacci {

    public FibRecurse(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        //setup for recursion
        long limit = this.size;
        long[] f = new long[]{0, 1};
        initRecurse(limit,f);
    }

    private void initRecurse(long limit, long[] f) {
        if (limit == 0) return;                                 //exit condition
        super.setData(f[0]);
        initRecurse(--limit, new long[]{f[1], f[0] + f[1]});    //recursion requires pre-increment
    }
}
