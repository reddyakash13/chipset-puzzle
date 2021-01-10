package com.chipset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class ChipsetProducer {

    private final int numberOfMachines;
    private final Vector<Integer> producingRates;
    private final int chipsetTargetRate;
    private final List<List<Integer>> possibleMachinesOnCombinations = new ArrayList<>();

    public ChipsetProducer(int numberOfMachines, Vector<Integer> producingRates, int chipsetTargetRate) {
        this.numberOfMachines = numberOfMachines;
        this.producingRates = producingRates;
        this.chipsetTargetRate = chipsetTargetRate;
    }

    public ChipsetMachineOutput machinesToBeTurnedOn() {
        findUniqueCombinations(0, 0, new Vector<>());
        return new ChipsetMachineOutput(possibleMachinesOnCombinations, chipsetTargetRate);
    }

    private void findUniqueCombinations(int startIndex, int sumTraversed, Vector<Integer> uniqueMachines) {
        if (sumTraversed == chipsetTargetRate) {
            possibleMachinesOnCombinations.add(new ArrayList<>(uniqueMachines));
        }

        for (int i = startIndex; i < producingRates.size(); i++) {
            if (sumTraversed + producingRates.get(i) > chipsetTargetRate) {
                continue;
            }
            uniqueMachines.add(producingRates.get(i));
            findUniqueCombinations(i + 1, sumTraversed + producingRates.get(i), uniqueMachines);
            uniqueMachines.remove(uniqueMachines.size() - 1);
        }
    }
}
