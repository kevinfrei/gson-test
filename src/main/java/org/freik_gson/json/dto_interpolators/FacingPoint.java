package org.freik_gson.json.dto_interpolators;

import org.freik_gson.json.DTOPose;
import org.freik_gson.json.RValue;

public class FacingPoint extends Interpolator {
    @Override
    public String getType() {
        return "FacingPoint";
    }

    public RValue<DTOPose> point;
    public FacingPoint(DTOPose point) {
        this.point = new RValue<>(point);
    }
    public FacingPoint(String ref) {
        this.point = new RValue<>(ref);
    }
    public FacingPoint() {}
}
