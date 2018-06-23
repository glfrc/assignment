package com.masimo.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;


public class FunctionTest 
{

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToN()
    {
        Float[] data = {1.0f};
        int n = 0;
        int c = 0;

        new Function(data, n, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToC()
    {
        Float[] data = {1.0f};
        int n = 1;
        int c = -1;

        new Function(data, n, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToDataSize()
    {
        Float[] data = null;
        int n = 1;
        int c = 0;

        new Function(data, n, c);
    }

    @Test
    public void shouldAnswerMatrixSize1Value1()
    {
        Float[] data = {1.0f};
        int n = 1;
        int c = 0;

        Function function = new Function(data, n, c);
        Float[][] result = function.calc();

        Float[][] expected = {{1.0f}};
        assertTrue(Arrays.deepEquals(expected, result));
    }

    @Test
    public void shouldAnswerAsInTheProvidedExample() throws IOException
    {
        Stream<String> input = Files.lines(Paths.get("TEST.PRN"));
        Float[] data = input.map(Float::valueOf).toArray(Float[]::new);
        input.close();

        int n = 300;
        int c = 4;

        Function function = new Function(data, n, c);
        Float[][] result = function.calc();

        Float[][] expected = {{0.279525f, 0.276682f, 0.268098f, 0.254212f, 0.235722f},
                              {0.276682f, 0.280218f, 0.277855f, 0.269717f, 0.256231f},
                              {0.268098f, 0.277855f, 0.281863f, 0.279912f, 0.272113f},
                              {0.254212f, 0.269717f, 0.279912f, 0.284270f, 0.282571f},
                              {0.235722f, 0.256231f, 0.272113f, 0.282571f, 0.287076f}};

        for (int k = 0; k <= c; k++) {
            for (int j = 0; j <= c; j++) {
                assertEquals(expected[k][j], result[k][j], 1e-6f);
            }
        }
    }
}
