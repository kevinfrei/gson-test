package org.freik_gson.json;

import org.freik_gson.json.dto_interpolators.Interpolator;

import java.util.List;

// A Line is 2 points
public class DTOCurve {
    public List<RValue<DTOPose>> points;
    public RValue<Interpolator> interpolation; // nullable
}
