package com.chipset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChipsetProducerTest {

    @ParameterizedTest
    @MethodSource("inputOutputFilesToRead")
    void shouldTest(String inputFile, String outputFile) throws IOException {
        ChipsetMachineOutput chipsetMachineOutput = readInputFile(inputFile).machinesToBeTurnedOn();

        String output = readOutputFile(outputFile);
        Assertions.assertEquals(output, chipsetMachineOutput.toString());
    }

    private ChipsetProducer readInputFile(String inputFile) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/test/resources/" + inputFile));
        int numberOfMachines = Integer.parseInt(lines.get(0));
        Vector<Integer> machineRates = Arrays.stream(lines.get(1).split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(Vector::new));
        int chipsetTargetRate = Integer.parseInt(lines.get(2));
        return new ChipsetProducer(numberOfMachines, machineRates, chipsetTargetRate);
    }

    private String readOutputFile(String outputFile) throws IOException {
        List<String> outputLines = Files.readAllLines(Path.of("src/test/resources/" + outputFile));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < outputLines.size(); i++) {
            sb.append(outputLines.get(i));
            sb.append(i != outputLines.size() - 1 ? "\n" : "");
        }
        return sb.toString();
    }

    private static Stream<Arguments> inputOutputFilesToRead() {
        return Stream.of(
                Arguments.of("input_1.txt", "output_1.txt"),
                Arguments.of("input_2.txt", "output_2.txt")
        );
    }
}