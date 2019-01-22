package com.andrelrs.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String nome){

        try {
            return URLDecoder.decode(nome,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String ids){
        /*
        String[] list = ids.split(",");
        List<Integer> listIds = new ArrayList<>();
        Arrays.asList(list).forEach(id -> listIds.add(Integer.parseInt(id)));
        */
        return Arrays.asList(ids.split(",")).stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList());
    }
}
