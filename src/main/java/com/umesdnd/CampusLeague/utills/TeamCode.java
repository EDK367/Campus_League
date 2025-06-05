package com.umesdnd.CampusLeague.utills;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TeamCode {

    public String generateTeamCode(String nameTournament) {
        String code = "Default";
        code = firstCode(nameTournament) + "_";
        code = code + generate();
        return code;
    }

    private String generate () {

        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            if (random.nextInt(2) == 0) {
                code += random.nextInt(10);
            } else {
                code += (char) ('A' + random.nextInt(26));
            }
        }
        return code;
    }

    private String firstCode(String nameTournament) {

        String[] words = nameTournament.split(" ");
        StringBuilder firstsLetter = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                firstsLetter.append(word.charAt(0));
            }
        }

        return firstsLetter.toString();
    }



}
