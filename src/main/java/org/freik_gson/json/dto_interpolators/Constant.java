package org.freik_gson.json.dto_interpolators;

import org.freik_gson.json.RValue;
import org.freik_gson.json.DTOValue;

public class Constant extends Interpolator {
    @Override
    public String getType() {
        return "Constant";
    }

    public RValue<DTOValue> heading;
}

