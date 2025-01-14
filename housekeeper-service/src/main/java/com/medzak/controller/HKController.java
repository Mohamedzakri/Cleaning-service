package com.medzak.controller;


import com.medzak.models.HousekeeperEntity;
import com.medzak.repository.HousekeeperRepository;
import com.medzak.service.HKService;
import com.medzak.utils.TimeReactive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
@Slf4j
public class HKController {
    private final HKService housekeeperService;
    private final HousekeeperRepository housekeeperRepository;

    public HKController(HKService housekeeperService, HousekeeperRepository housekeeperRepository) {
        this.housekeeperService = housekeeperService;
        this.housekeeperRepository = housekeeperRepository;
    }

    @PostMapping("/create")
    public Mono<HousekeeperEntity> saveHouseKeeper(@RequestBody HousekeeperEntity housekeeperEntity) {
        return housekeeperRepository.save(housekeeperEntity);
    }

    @GetMapping(value = "/{lastName}")
    public Mono<String> getHK(@PathVariable String lastName) {
        return housekeeperService.getHK(lastName);
    }

    @TimeReactive
    @GetMapping(value = "/hks/{lastName}")
    public Flux<HousekeeperEntity> getHKs(@PathVariable String lastName) {
        return housekeeperService.getHKs(lastName);
    }

    @GetMapping(value = "/date/{createDate}")
    public Flux<HousekeeperEntity> getHKByDate(@PathVariable String createDate) {
        return housekeeperRepository.getByCreationDate(createDate);
    }
}