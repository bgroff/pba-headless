package io.airbyte.repositories;

import io.airbyte.domain.Bakery;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

@Repository
public interface BakeriesRepository extends ReactorCrudRepository<Bakery, Long> {
}
