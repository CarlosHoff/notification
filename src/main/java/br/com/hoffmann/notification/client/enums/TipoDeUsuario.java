package br.com.hoffmann.notification.client.enums;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public enum TipoDeUsuario {

    NONE(0, "none"),
    ADMIN(1, "Admin"),
    SUPORTE(2, "Suport"),
    USUARIO(3, "User");

    private static final Map<Integer, TipoDeUsuario> TIPO_DE_USUARIO_MAP = new HashMap<>(values().length);

    static {
        TIPO_DE_USUARIO_MAP.putAll(stream(values()).collect(toMap(TipoDeUsuario::code, identity())));
    }

    private final Integer code;
    private final String description;

    TipoDeUsuario(Integer code, String description) {
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

    public static TipoDeUsuario of(Long enumCode) {

        if (enumCode.equals(ADMIN.getCode())) {
            return ADMIN;
        } else if (enumCode.equals(SUPORTE.getCode())) {
            return SUPORTE;
        } else if (enumCode.equals(USUARIO.getCode())) {
            return USUARIO;
        } else
            return null;
    }

}
