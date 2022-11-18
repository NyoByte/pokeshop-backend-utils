package com.pokeshop.ecommerce.util;

import org.springframework.hateoas.Link;

public interface EntityDto<E, D> {

    D fromEntity(E entity);

    D fromEntity(D dto, E entity);

    E toEntity();

    D add(Link link);

}