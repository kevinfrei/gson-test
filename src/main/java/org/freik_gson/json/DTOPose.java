package org.freik_gson.json;

public class DTOPose {
    public RValue<DTOValue> X;
    public RValue<DTOValue> Y;
    public RValue<DTOValue> Heading; // Nullable
    public boolean inRadians;

    public DTOPose() {}
    public DTOPose(DTOValue X, DTOValue Y, DTOValue Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(String X, DTOValue Y, DTOValue Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(DTOValue X, String Y, DTOValue Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(String X, String Y, DTOValue Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(DTOValue X, DTOValue Y, String Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(String X, DTOValue Y, String Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(DTOValue X, String Y, String Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
    public DTOPose(String X, String Y, String Heading, boolean inRadians) {
        this.X = new RValue<>(X);
        this.Y = new RValue<>(Y);
        this.Heading = new RValue<>(Heading);
        this.inRadians = inRadians;
    }
}
