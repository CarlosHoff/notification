package br.com.hoffmann.notification.domain.enums;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public enum TemplateType {

    NONE(0, "none"),
    EMAIL(1, "Email"),
    SMS(2, "Sms"),
    PUSH(3, "Push"),
    INAPP(4, "INAPP");

    private static final Map<Integer, TemplateType> TIPO_DE_TEMPLATE_MAP = new HashMap<>(values().length);

    static {
        TIPO_DE_TEMPLATE_MAP.putAll(stream(values()).collect(toMap(TemplateType::code, identity())));
    }

    private final Integer code;
    private final String description;

    TemplateType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer code() {
        return this.code;
    }

    public Long getCode() {
        return code.longValue();
    }

    public String description() {
        return this.description;
    }

    public static TemplateType of(Long enumCode) {

        if (enumCode.equals(EMAIL.getCode())) {
            return EMAIL;
        } else if (enumCode.equals(SMS.getCode())) {
            return SMS;
        } else if (enumCode.equals(PUSH.getCode())) {
            return PUSH;
        } else if (enumCode.equals(INAPP.getCode())) {
            return INAPP;
        } else
            return null;
    }
}
