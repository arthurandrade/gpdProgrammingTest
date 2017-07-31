package com.gpd.utils;

import java.text.DecimalFormat;

public class DoubleUtils {

    public static double round2(double value) {
        DecimalFormat mask = new DecimalFormat("#.##");
        return Double.valueOf(mask.format(value).replace(",", "."));
    }

    public static double round3(double value) {
        DecimalFormat mask = new DecimalFormat("#.###");
        return Double.valueOf(mask.format(value).replace(",", "."));
    }

}
