package com.nonfallable.taskKnight.utils.converters;

import com.nonfallable.taskKnight.rest.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FromDomainConverter<DTO, DOMAIN> {

    public abstract DTO fromDomain(DOMAIN domain);

    public PageDTO<DTO> fromDomain(Page<DOMAIN> page) {
        Pageable pageable = page.getPageable();
        return new PageDTO<>(
                this.fromDomain(page.get()).collect(Collectors.toList()),
                pageable.getPageSize(),
                pageable.getPageNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public List<DTO> fromDomain(List<DOMAIN> domainList) {
        return this.fromDomain(domainList.stream()).collect(Collectors.toList());
    }

    public Stream<DTO> fromDomain(Stream<DOMAIN> domainStream) {
        return domainStream.map(this::fromDomain);
    }
}
