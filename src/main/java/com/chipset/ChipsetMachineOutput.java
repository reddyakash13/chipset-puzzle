package com.chipset;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChipsetMachineOutput {

    private final List<List<Integer>> possibleMachinesOnCombinations;
    private final int targetRate;
    private int waste = 0;

    public ChipsetMachineOutput(List<List<Integer>> possibleMachinesOnCombinations, int targetRate) {
        this.possibleMachinesOnCombinations = possibleMachinesOnCombinations;
        this.targetRate = targetRate;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Nr solutions=" + possibleMachinesOnCombinations.size());
        stringBuffer.append("\n");
        possibleMachinesOnCombinations.forEach(
                line -> {
                    stringBuffer.append(line.stream().map(Objects::toString).collect(Collectors.joining(" ")));
                    stringBuffer.append("\n");
                    addWastage(line.stream().mapToInt(Integer::intValue).sum() - targetRate);
                }
        );

        stringBuffer.append("Waste="+waste);
        return stringBuffer.toString();
    }

    private void addWastage(int wastage) {
        waste += wastage;
    }
}
