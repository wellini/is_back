package com.nonfallable.taskKnight.utils.converters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class RequestDtoConverter<DTO, DOMAIN> {

    public abstract DTO fromDomain(DOMAIN domain);



    public Page<DTO> fromDomain(Page<DOMAIN> page) {
        return new PageImpl<>(
                this.fromDomain(page.get()).collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public List<DTO> fromDomain(List<DOMAIN> domainList) {
        return this.fromDomain(domainList.stream()).collect(Collectors.toList());
    }

    public Stream<DTO> fromDomain(Stream<DOMAIN> domainStream) {
        return domainStream.map(this::fromDomain);
    }
}
