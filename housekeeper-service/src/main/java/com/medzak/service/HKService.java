package com.medzak.service;

import com.medzak.models.HousekeeperEntity;
import com.medzak.repository.HousekeeperRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HKService {
    private final HousekeeperRepository housekeeperRepository;

    public HKService(HousekeeperRepository housekeeperRepository) {
        this.housekeeperRepository = housekeeperRepository;
    }

    @Cacheable(value = "user", key = "#id")
    public Mono<String> getHK(String lastName) {
        return housekeeperRepository.findByOnlyLastName(lastName);
    }

    @Cacheable(value = "users", key = "#id")
    public Flux<HousekeeperEntity> getHKs(String lastName) {
        return housekeeperRepository.findByLastName(lastName);
    }

    public Mono<HousekeeperEntity> createHK(HousekeeperEntity housekeeperEntity) {
        return housekeeperRepository.save(housekeeperEntity);
    }

}