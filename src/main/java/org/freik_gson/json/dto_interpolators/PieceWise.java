package org.freik_gson.json.dto_interpolators;

import java.util.List;

public class PieceWise extends Interpolator {
    @Override
    public String getType() {
        return "PieceWise";
    }

    public List<PiecePortion> pieces;
}
