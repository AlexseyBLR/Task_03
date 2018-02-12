package entity;

public class EnumClass {
    public EnumClass() {
    }

    public static enum Type {
        OPEN,
        CLOSE,
        TEXT,
        WITHOUT_BODY;

        private Type() {
        }
    }
}