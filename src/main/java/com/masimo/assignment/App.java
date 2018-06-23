package com.masimo.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {

    private static String fileName;
    private static int n;
    private static int c;

    /**
     * usage: assignment -c <c> -inputfile <file> -n <n>
     *  -c <c>              value c as defined in the assignment
     *  -inputfile <file>   input data file of floating point numbers
     *  -n <n>              size of the input data array
     */
    public static void main(String[] args) {

        App.parseArguments(args);
        Float[] data = App.parseInputData();
        
        Function function = null;
        try {
            function = new Function(data, n, c);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.exit(3);
        }
        Float[][] result = function.calc();

        App.printResult(result);
    }

    /**
     * Parses command line arguments
     */
    private static void parseArguments(final String[] args) {

        Options options = new Options();

        Option inputOption = Option.builder("inputfile").argName("file").hasArg().desc("input data file of floating point numbers").required().build();
        Option nOption = Option.builder("n").argName("n").hasArg().desc("size of the input data array").required().type(Number.class).build();
        Option cOption = Option.builder("c").argName("c").hasArg().desc("value c as defined in the assignment").required().type(Number.class).build();
        options.addOption(inputOption);
        options.addOption(nOption);
        options.addOption(cOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            fileName = line.getOptionValue(inputOption.getOpt());
            n = ((Number)line.getParsedOptionValue(nOption.getOpt())).intValue();
            c = ((Number)line.getParsedOptionValue(cOption.getOpt())).intValue();
        } catch (ParseException e) {
            System.err.println(e);
            
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("assignment", options, true);
            System.exit(1);
        }
    }

    private static Float[] parseInputData() {

        Stream<String> input = null;
        try {
            input = Files.lines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(2);
        }
        
        Float[] data = input.map(Float::valueOf).toArray(Float[]::new);
        input.close();

        return data;
    }

    private static void printResult(final Float[][] result) {

        for (Float[] row : result) {
            for (Float item : row) {
                System.out.format("%f ", item);
            }
            System.out.println();
        }
    }
}
