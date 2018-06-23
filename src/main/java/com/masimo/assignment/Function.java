package com.masimo.assignment;

public class Function {

    private final Float[] data;
    private final int n;
    private final int c;

    /**
     * Initializes and sanitizes input arguments
     */
    public Function(Float[] data, int n, int c) {
        this.data = data;
        this.n = n;
        this.c = c;

        if (n < 1) throw new IllegalArgumentException("n must be greater than 0");
        else if (c < 0) throw new IllegalArgumentException("c must be 0 or greater");
        else if (data == null || data.length < n) throw new IllegalArgumentException("size of the data array must be equal or greater than n");
    }

    /**
     * Executes the function   SUM   (data[i-k] * data [i-j])
     *                       i=c->n-1
     * where:
     *  k = 0..c
     *  j = 0..c
     */
    public Float[][] calc() {

        Float[][] matrix = new Float[c + 1][c + 1];
        for (int k = 0; k <= c; k++) {
            
            Float[] row = new Float[c + 1];
            for (int j = 0; j <= c; j++) {

                Float sum = 0.0f;
                for (int i = c; i < n; i++) {

                    sum += data[i - k] * data[i - j];
                }
                row[j] = sum;
            }
            matrix[k] = row;
        }
        return matrix;
    }
}