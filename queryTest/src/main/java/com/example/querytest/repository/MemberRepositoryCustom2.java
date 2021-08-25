package com.example.querytest.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryCustom2 extends QuerydslRepositorySupport {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public MemberRepositoryCustom2(Class<?> domainClass) {
        super(domainClass);
    }
}
