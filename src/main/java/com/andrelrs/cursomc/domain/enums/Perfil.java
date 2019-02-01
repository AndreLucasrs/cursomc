package com.andrelrs.cursomc.domain.enums;

import static java.util.Objects.isNull;

public enum Perfil {

    //Role é colocado pq o spring security pede
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer cod;
    private String descricao;

    private Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {

        if (isNull(cod)) {
            return null;
        }

        for (Perfil x : Perfil.values()) {

            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id Invalido" + cod);
    }
}
