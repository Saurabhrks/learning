package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();


    @Test
    void namesFlux() {
        var nameFlux = fluxAndMonoGeneratorService.nameFlux();
        StepVerifier.create(nameFlux)
//                .expectNext("Vishal","Om","shyam")
                .expectNext("Vishal")
                .expectNext("Om")
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    void namesFlux_map() {
        int StringLength = 2;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_map(StringLength);
        StepVerifier.create(namesFlux)
                .expectNext("6-vishal", "5-shyam")
                .verifyComplete();

    }


    @Test
    void namesFlux_immutablemap() {
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_immutableMap();
        StepVerifier.create(namesFlux)
                .expectNext("Alex", "ben", "chole")
                .verifyComplete();

    }

    @Test
    void nameFlux_flatmap() {
        int StringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_flatmap(StringLength);
        StepVerifier.create(namesFlux)
                .expectNext("a", "l", "e", "x", "c", "h", "o", "l", "e").verifyComplete();
    }

    @Test
    void nameFlux_flatmap_async() {
        int StringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_flatmap_async(StringLength);
        StepVerifier.create(namesFlux)
//                .expectNext("a", "l", "e", "x", "c", "h", "o", "l", "e")
                .expectNextCount(9).verifyComplete();
    }

    @Test
    void nameFlux_concatmap_async() {
        int StringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_concatmap_async(StringLength);
        StepVerifier.create(namesFlux)
                .expectNext("a", "l", "e", "x", "c", "h", "o", "l", "e")
                .verifyComplete();
    }

    @Test
    void nameMono_flatmap() {
        int StringLength = 3;
        var value = fluxAndMonoGeneratorService.nameMono_flatmap(StringLength);
        StepVerifier.create(value)
                .expectNext(List.of("a", "l", "e", "x"))
                .verifyComplete();

    }

    @Test
    void nameMono_flatmapMany() {
        int StringLength = 3;
        var value = fluxAndMonoGeneratorService.nameMono_flatmapMany(StringLength);
        StepVerifier.create(value)
                .expectNext("a", "l", "e", "x")
                .verifyComplete();

    }

    @Test
    void nameFlux_transform() {
        int StringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_transform(StringLength);
        StepVerifier.create(namesFlux)
                .expectNext("a", "l", "e", "x", "c", "h", "o", "l", "e").verifyComplete();
    }

    @Test
    void nameFlux_transform_1() {
        int StringLength = 6;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_transform(StringLength);
        StepVerifier.create(namesFlux)
                .expectNext("default").verifyComplete();
    }

    @Test
    void nameFlux_transform_swtichempty() {
        int StringLength = 6;
        var namesFlux = fluxAndMonoGeneratorService.nameFlux_transform_swtichempty(StringLength);
        StepVerifier.create(namesFlux)
                .expectNext("d", "e", "f", "a", "u", "l", "t").verifyComplete();
    }

    @Test
    void concatString() {
        var stringcon = fluxAndMonoGeneratorService.concatString();
        StepVerifier.create(stringcon).expectNext("A", "B", "C", "D", "E", "F").verifyComplete();
    }

    @Test
    void concatwithString() {
        var stringcon = fluxAndMonoGeneratorService.concatwithString();
        StepVerifier.create(stringcon).expectNext("A", "D").verifyComplete();
    }

    @Test
    void MergeString() {
        var stringcon = fluxAndMonoGeneratorService.MergeString();
        StepVerifier.create(stringcon).expectNextCount(6).verifyComplete();
    }

    @Test
    void MergeWithString() {
        var stringcon = fluxAndMonoGeneratorService.MergeWithString();
        StepVerifier.create(stringcon).expectNextCount(6).verifyComplete();
    }

    @Test
    void MergeWithStringMono() {
        var stringcon = fluxAndMonoGeneratorService.MergeWithStringMono();
        StepVerifier.create(stringcon).expectNext("A", "D").verifyComplete();
    }

    @Test
    void zipString() {
        var zipnames = fluxAndMonoGeneratorService.zipString();
        StepVerifier.create(zipnames).expectNext("ADG", "BEH").verifyComplete();
    }
}