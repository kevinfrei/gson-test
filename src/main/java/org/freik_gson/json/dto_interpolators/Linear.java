package org.freik_gson.json.dto_interpolators;

import org.freik_gson.json.RValue;
import org.freik_gson.json.DTOValue;

public class Linear extends Interpolator {
    @Override
    public String getType() {
        return "Linear";
    }

    public RValue<DTOValue> startHeading;
    public RValue<DTOValue> endHeading;
    public boolean longWay = false;
}
