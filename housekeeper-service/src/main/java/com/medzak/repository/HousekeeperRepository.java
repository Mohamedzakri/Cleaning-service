package com.medzak.repository;

import com.medzak.models.HousekeeperEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HousekeeperRepository extends R2dbcRepository<HousekeeperEntity, Long> {
    @Query("SELECT * FROM housekeepers WHERE last_name = :lastname")
    Flux<HousekeeperEntity> findByLastName(String lastName);

    @Query("SELECT count(*) FROM housekeepers WHERE last_name = :lastname")
    Mono<String> findByOnlyLastName(String lastName);

    @Query("SELECT * FROM housekeepers WHERE created_at = :createDate")
    Flux<HousekeeperEntity> getByCreationDate(String createDate);
}
