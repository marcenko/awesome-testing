package de.cronos.awesometesting.service;

import org.springframework.stereotype.Service;


@Service
public class IdGenerator {

    private final MathIsFun mathIsFun;

    public IdGenerator(MathIsFun mathIsFun) {
        this.mathIsFun = mathIsFun;
    }

    public int getRandomIntegerId() {
        return mathIsFun.getRandomNumber();
    }
}
