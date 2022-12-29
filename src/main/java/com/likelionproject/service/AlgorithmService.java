package com.likelionproject.service;

import org.springframework.stereotype.Service;

@Service
public class AlgorithmService {
    public int sumOfDigit(int num) {
        int sum = 1;
        for (int i = 1; i < num; i++) {
            sum +=sum;
            sum++;
        }
        return sum;
    }
}
