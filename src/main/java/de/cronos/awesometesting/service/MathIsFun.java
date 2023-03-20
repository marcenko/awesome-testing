package de.cronos.awesometesting.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MathIsFun {

    public int factorial(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("Factorial for negative numbers is not defined");
        }
        if(n == 0) {
            return 1;
        }
        return n * factorial(n-1);
    }

    public int getRandomNumber() {
        return new Random().nextInt();
    }
}
