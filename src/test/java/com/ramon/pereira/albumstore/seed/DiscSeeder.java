package com.ramon.pereira.albumstore.seed;

import com.ramon.pereira.albumstore.model.Disc;
import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;
import java.util.Date;

import lombok.NonNull;

public class DiscSeeder extends SeederGeneric {

    public static Disc disc(@NonNull final Integer id) {
        return Disc.builder()
                .id(id)
                .genre(enDiscGenre.ROCK)
                .name(faker().bothify("##########"))
                .price(new BigDecimal(faker().number().randomNumber()))
                .createdAt(new Date())
                .build();
    }

    public static Disc disc() {
        return disc(faker().number().randomDigit());
    }
}
