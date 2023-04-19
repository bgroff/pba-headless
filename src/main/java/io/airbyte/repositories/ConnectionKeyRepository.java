package io.airbyte.repositories;

import io.airbyte.domain.ConnectionKey;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

@Repository
public interface ConnectionKeyRepository extends ReactorCrudRepository<ConnectionKey, Long> {
    Mono<ConnectionKey> findByKey(String key);
}
