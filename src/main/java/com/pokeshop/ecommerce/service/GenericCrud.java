package com.pokeshop.ecommerce.service;

import com.pokeshop.ecommerce.util.EntityDto;

public interface GenericCrud<D extends EntityDto<E, D>, E, K> {

    D insert(D d);

    D update(D d);

    void delete(D d);

    D findById(K k, D d);

}