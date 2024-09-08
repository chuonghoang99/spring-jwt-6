package com.chuong.app.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface EntityMapper<D, E> {

    D toDto(E e);

    E toEntity(D e);

    List<D> toDto(List<E> e);

    List<E> toEntity(List<D> d);


    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E e, D dto);

}
