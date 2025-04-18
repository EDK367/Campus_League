package com.umesdnd.CampusLeague.utills;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DuplicateData {

    public <T> List<T> duplicate(List<T> list) {

        Set<T> duplicate = new HashSet<>();
        List<T> repeated = list.stream()
                .filter(n -> !duplicate.add(n))
                .collect(Collectors.toList());

        return repeated;
    }
}
