package nl.apc.legoland.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Value
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SwanWoodProfitData {

    List<Integer> trunkOrder;

    int profit;
}
