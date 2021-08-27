package com.example.logandspeltest.spel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Inventor {

    private String name1;
    private String name2;

    public int num = 1;

    public int add() {
        return num + num;
    }
}
