package ru.egartech.profile.model.task.deserializer;

import java.util.Locale;
import java.util.Map;

public enum FieldType {
    EMPTY,
    LIST_RELATIONSHIP,
    DROP_DOWN,
    LABELS,
    SHORT_TEXT,
    DATE,
    PHONE,
    EMAIL,
    URL,
    ATTACHMENT,
    ;

    public static FieldType of(String name) {
        return FieldType.valueOf(name.toUpperCase(Locale.ROOT));
    }
}
