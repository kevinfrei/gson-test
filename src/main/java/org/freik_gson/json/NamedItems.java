package org.freik_gson.json;

import org.freik_gson.json.dto_interpolators.Interpolator;
import java.util.Map;

public class NamedItems {
    public Map<String, DTOValue> values;
    public Map<String, DTOPose> poses;
    public Map<String, DTOCurve> curves;
    public Map<String, Interpolator> interpolations;
    public Map<String, DTOPath> paths;
}
