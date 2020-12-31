package com.example.demo.fibonacci;
import java.util.stream.*;

public class FibStream extends _Fibonacci {
    public FibStream(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
            .limit(this.size)
            .forEach(f -> super.setData(f[0]) );
    }
}
