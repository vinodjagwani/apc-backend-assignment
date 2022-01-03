package nl.apc.legoland.service;

import nl.apc.legoland.dto.SwanWoodProfitData;

import java.util.List;
import java.util.Set;

public interface LegoLandService {

    int calculateMaxProfit(final int[][] lengths);

    Set<List<Integer>> permute(final int[] array);

    int calculateProfit(final List<Integer> blockLengths);

    List<SwanWoodProfitData> calculateMaxProfitsTrunkOrder(final int[] lengths);


}
