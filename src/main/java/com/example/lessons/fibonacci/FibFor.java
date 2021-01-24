package com.example.lessons.fibonacci;

public class FibFor extends _Fibonacci {
    public FibFor(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        long limit = this.size;
        for (long[] f = new long[]{0, 1}; limit-- > 0; f = new long[]{f[1], f[0] + f[1]})
            super.setData(f[0]);
    }
}
