package com.mesi.params;

public enum KeyMap {

    LEFT(37),
    RIGHT(39),
    UP(38),
    DOWN(40),
    ESCAPE(27);

    private final Integer keyCode;

    KeyMap(Integer keyCode) { this.keyCode = keyCode; }

    public Integer getKeyCode() {
        return keyCode;
    }
}
