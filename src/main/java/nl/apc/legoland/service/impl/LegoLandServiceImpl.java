package nl.apc.legoland.service.impl;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import nl.apc.legoland.dto.SwanWoodProfitData;
import nl.apc.legoland.service.LegoLandService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nl.apc.legoland.constant.ApplicationConstant.PROFITS;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LegoLandServiceImpl implements LegoLandService {

    @Override
    public int calculateMaxProfit(final int[][] lengths) {
        return Arrays.stream(lengths).mapToInt(value -> calculateMaxProfitsTrunkOrder(value).stream().findFirst().orElse(SwanWoodProfitData.builder().build()).getProfit()).sum();
    }

    public List<SwanWoodProfitData> calculateMaxProfitsTrunkOrder(final int[] lengths) {
        final List<SwanWoodProfitData> result = Lists.newArrayList();
        int maxProfit = Integer.MIN_VALUE;
        final Set<List<Integer>> allPermutations = permute(lengths);
        for (List<Integer> blockLengths : allPermutations) {
            final int profit = calculateProfit(blockLengths);
            if (profit > maxProfit)
                maxProfit = profit;
            result.add(SwanWoodProfitData.builder().trunkOrder(blockLengths).profit(profit).build());
        }
        final int maxProfitFinal = maxProfit;
        return result.stream().filter(f -> f.getProfit() == maxProfitFinal).collect(Collectors.toList());
    }

    public int calculateProfit(final List<Integer> blockLengths) {
        int profit = 0;
        int remainder = 0;
        int tempLength;
        for (Integer blockLength : blockLengths) {
            profit += PROFITS.get(remainder);
            if (blockLength < 3) {
                remainder = blockLength;
                continue;
            }
            tempLength = blockLength;
            if (remainder > 0) {
                profit += PROFITS.get(3 - remainder);
                tempLength = tempLength - (3 - remainder);
            }
            remainder = tempLength % 3;
            profit += PROFITS.get(3) * ((tempLength - remainder) / 3);
        }
        profit += PROFITS.get(remainder);
        return profit;
    }

    public Set<List<Integer>> permute(final int[] array) {
        final List<List<Integer>> list = Lists.newArrayList();
        final int[] indexes = new int[array.length];
        IntStream.range(1, array.length + 1).forEach(val -> indexes[val - 1] = val - 1);
        getPermutation(list, Lists.newArrayList(), indexes);
        return list.stream().map(l -> l.stream().map(i -> array[indexes[i]]).collect(Collectors.toCollection(() -> new ArrayList<>(l.size())))).collect(Collectors.toSet());
    }

    private void getPermutation(final List<List<Integer>> list, final List<Integer> result, final int[] array) {
        if (result.size() == array.length) {
            list.add(Lists.newArrayList(result));
        } else {
            Arrays.stream(array).filter(j -> !result.contains(j)).forEach(j -> {
                result.add(j);
                getPermutation(list, result, array);
                result.remove(result.size() - 1);
            });
        }
    }
}
