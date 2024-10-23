package com.examples.lld.bms;

import org.modelmapper.ModelMapper;

public class DtoUtils {

    private static final ModelMapper modelMapper;
    static{
        modelMapper = new ModelMapper();
    }
    public static <T> T convertToDto(Object user, Class<T> classO){
        return modelMapper.map(user,classO);
    }
}
