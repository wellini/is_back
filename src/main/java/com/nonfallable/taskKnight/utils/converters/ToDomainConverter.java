package com.nonfallable.taskKnight.utils.converters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ToDomainConverter<DTO, DOMAIN>  {

    public abstract DOMAIN toDomain(DTO dto);

    public List<DOMAIN> toDomain(List<DTO> dtoList) {
        return this.toDomain(dtoList.stream()).collect(Collectors.toList());
    }

    public Stream<DOMAIN> toDomain(Stream<DTO> dtoStream) {
        return dtoStream.map(this::toDomain);
    }
}
