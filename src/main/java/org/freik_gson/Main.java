package org.freik_gson;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.freik_gson.json.*;
import org.freik_gson.json.dto_interpolators.FacingPoint;
import org.freik_gson.json.dto_interpolators.Interpolator;

public class Main {

    //  public NamedItems loadPathFromJson(Context context, String fileName) {
    //      try (Reader reader = new InputStreamReader(context.getAssets().open(fileName))) {
    public static NamedItems loadPathFromJson(String fileName) {
        try (Reader reader = new InputStreamReader(Files.newInputStream(Paths.get(fileName)))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, NamedItems.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        DTOValue dtoValue = new DTOValue(Math.PI);
        DTOValue dtoValue2 = new DTOValue(Math.E);
        DTOValue zero = new DTOValue(0.0);
        DTOPose dtoPose = new DTOPose(dtoValue, dtoValue2, zero, true);
        DTOPose dtoOther = new DTOPose("v1", "v2", "v3", true);
        List<RValue<DTOPose>> points = new ArrayList<>();
        points.add(new RValue<>(dtoPose));
        points.add(new RValue<DTOPose>("pose"));
        points.add(new RValue<>(dtoOther));
        FacingPoint facingPoint = new FacingPoint("pose");
        RValue<Interpolator> interpolation = new RValue<Interpolator>("pose");
        RValue<Interpolator> interpolation2 = new RValue<Interpolator>(facingPoint);
        DTOCurve dtoCurve = new DTOCurve(points, interpolation);
        dtoCurve.interpolation = interpolation;
        DTOPath dtoPath = new DTOPath();
        dtoPath.curves = new ArrayList<>();
        dtoPath.curves.add(new RValue<>(dtoCurve));
        dtoPath.globalInterpolator = interpolation;
        NamedItems items = new NamedItems();
        items.values = new HashMap<>();
        items.values.put("v1", dtoValue);
        items.values.put("v2", dtoValue2);
        items.values.put("v3", zero);
        items.poses = new HashMap<>();
        items.poses.put("pose", dtoPose);
        items.poses.put("otherPose", dtoOther);
        items.curves = new HashMap<>();
        items.curves.put("curve", dtoCurve);
        items.interpolations = new HashMap<>();
        items.interpolations.put("facing", facingPoint);
        items.paths = new HashMap<>();
        items.paths.put("aPath", dtoPath);
        Gson gson = new Gson();
        String vals = gson.toJson(items);
        System.out.println(vals);
        NamedItems roundTrip =gson.fromJson(vals, NamedItems.class);
        System.out.printf("%d%n", roundTrip.paths.size());
        System.out.println("Round trip");
    }
}