package com.example.demo.technicals;

public class FibWhile extends _Fibonacci {
    public FibWhile(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        long limit = this.size;
        long[] f = new long[]{0, 1};
        while (limit-- > 0) {
            super.setData(f[0]);
            f = new long[]{f[1], f[0] + f[1]};
        }
    }
}
