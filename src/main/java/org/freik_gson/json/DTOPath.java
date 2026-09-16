package org.freik_gson.json;

import org.freik_gson.json.dto_interpolators.Interpolator;

import java.util.List;

public class DTOPath {
    public List<RValue<DTOCurve>> curves;
    public RValue<Interpolator> globalInterpolator; // nullable
}
