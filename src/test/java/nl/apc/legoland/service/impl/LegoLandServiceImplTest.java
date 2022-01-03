package nl.apc.legoland.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import nl.apc.legoland.service.LegoLandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE)
class LegoLandServiceImplTest  {

    LegoLandService legoLandService;

    @BeforeEach
    void setUp() {
        legoLandService = new LegoLandServiceImpl();
    }

    @Test
    @DisplayName("Calculate Profit")
    void testCalculateProfit() {
        assertAll(() -> assertEquals(legoLandService.calculateProfit(new ArrayList<>(Arrays.asList(2,3,1))),4)
                ,() -> assertEquals(legoLandService.calculateProfit(new ArrayList<>(Arrays.asList(1,2,1))),1));
    }

    @Test
    @DisplayName("Permutation")
    void testPermutation() {
        assertAll(() -> assertEquals(legoLandService.permute(new int[]{1,2,1}).size(),3));
    }

    @Test
    @DisplayName("Calculate Max Profits with TrunkOrder")
    void testCalculateMaxProfitsTrunkOrder() {
        assertAll(() -> assertEquals(legoLandService.calculateMaxProfitsTrunkOrder(new int[]{1,3,2}).size(),4));
    }

    @Test
    @DisplayName("Calculate Total Profit")
    void testCalculateTotalProfits() {
        assertAll(() -> assertEquals(legoLandService.calculateMaxProfit(new int[][]{{1,2,1},{1,2},{1,4}}),8));
    }
}
