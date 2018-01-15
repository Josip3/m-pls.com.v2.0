package com.mpls.v2.dto.utils;

public interface DtoMapper {
    Object parseFromDTOtoObject(Object dtoObject, Class... parsingClasses);
}
