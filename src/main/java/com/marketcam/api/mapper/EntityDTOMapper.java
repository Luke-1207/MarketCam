package com.marketcam.api.mapper;

import org.modelmapper.ModelMapper;

public class EntityDTOMapper<T, D> {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, D> D convertToDTO(T entity, Class<D> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    public static <T, D> T convertToEntity(D dto, Class<T> entityClass){
        return modelMapper.map(dto, entityClass);
    }

}