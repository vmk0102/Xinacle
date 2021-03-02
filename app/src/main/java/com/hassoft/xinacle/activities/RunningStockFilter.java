package com.hassoft.xinacle.activities;

import com.google.common.base.Predicate;
import com.hassoft.xinacle.model.RunningStock;

import java.util.regex.Pattern;

public final class RunningStockFilter implements Predicate<RunningStock>{
    private final Pattern pattern;

    public RunningStockFilter(final String regex)
    {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean apply(final RunningStock input)
    {
        return pattern.matcher(input.getProductName()).find();
    }
}
