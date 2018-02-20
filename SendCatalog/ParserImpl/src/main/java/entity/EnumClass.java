package entity;

public class EnumClass {// да чтоб тебя так всю жизнь называли, ну неужели совсем мозни отключаются?
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
