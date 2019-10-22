package com.ramon.pereira.albumstore.model;

import lombok.Getter;

@Getter
public enum enDiscGenre {
    POP(1),
    MPB(2),
    CLASSIC(3),
    ROCK(4);

    private Integer id;

    enDiscGenre(Integer id) {
        this.id = id;
    }
}
