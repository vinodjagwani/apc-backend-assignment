package nl.apc.legoland.constant;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ApplicationConstant {

    public static final Map<Integer, Integer> PROFITS = new HashMap<>() {{
        put(0, 0);
        put(1, -1);
        put(2, 3);
        put(3, 1);
    }};
}
