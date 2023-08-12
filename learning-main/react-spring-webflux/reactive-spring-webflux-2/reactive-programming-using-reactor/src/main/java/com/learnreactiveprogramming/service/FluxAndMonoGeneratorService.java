package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {
    public Flux<String> nameFlux() {
        return Flux.fromIterable(List.of("Vishal", "Om", "shyam")).log();
    }

    public Mono<String> nameMono() {
        return Mono.just("Alex").log();
    }

    public Flux<String> nameFlux_map(int StringLength) {
        return Flux.fromIterable(List.of("Vishal", "Om", "shyam"))
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .map(t -> t.length() + "-" + t)
                .log();
    }

    public Flux<String> nameFlux_immutableMap() {
        var namesFlux = Flux.fromIterable(List.of("Vishal", "Om", "shyam"));
        namesFlux.map(t -> t.toLowerCase());
        return namesFlux;
    }

    public Flux<String> nameFlux_flatmap(int StringLength) {
        return Flux.fromIterable(List.of("Alex", "ben", "chole"))
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .flatMap(t -> splitstring(t));
    }

    public Flux<String> nameFlux_concatmap(int StringLength) {
        return Flux.fromIterable(List.of("Alex", "ben", "chole"))
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .flatMap(t -> splitstring(t));
    }

    public Flux<String> nameFlux_flatmap_async(int StringLength) {
        return Flux.fromIterable(List.of("Alex", "ben", "chole"))
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .flatMap(t -> splitstring_delay(t))
                .log();
    }

    public Flux<String> nameFlux_concatmap_async(int StringLength) {
        return Flux.fromIterable(List.of("Alex", "ben", "chole"))
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .concatMap(t -> splitstring_delay(t))
                .log();
    }

    public Flux<String> splitstring(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitstring_delay(String name) {
        var charArray = name.split("");
        int delay = 200;
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
//                .doOnNext(s -> System.out.println(s));
//                .flatMap(s -> {
//                    System.out.println(s);
//                    return Flux.just(s);
//                });

    }

    public Mono<List<String>> nameMono_flatmap(int StringLength) {
        return Mono.just("Alex")
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .flatMap(t -> splitStringMono(t))
                .log();
    }

    private Mono<List<String>> splitStringMono(String t) {
        var charArray = t.split("");
        var charList = List.of(charArray);
        return Mono.just(charList);
    }

    public Flux<String> nameMono_flatmapMany(int StringLength) {
        return Mono.just("Alex")
                .map(t -> t.toLowerCase())
                .filter(t -> t.length() > StringLength)
                .flatMapMany(this::splitstring)
                .log();
    }

    public Flux<String> nameFlux_transform(int StringLength) {
        Function<Flux<String>, Flux<String>> filtermap = name -> name.map(String::toLowerCase)
                .filter(t -> t.length() > StringLength);

        return Flux.fromIterable(List.of("Alex", "ben", "chole"))
                .transform(filtermap)
                .flatMap(t -> splitstring(t))
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> nameFlux_transform_swtichempty(int StringLength) {
        Function<Flux<String>, Flux<String>> filtermap = name -> name.map(String::toLowerCase)
                .filter(t -> t.length() > StringLength)
                .flatMap(t -> splitstring(t));

        var defaultFlux = Flux.just("default").transform(filtermap);

        return Flux.fromIterable(List.of("Alex", "ben", "chole"))
                .transform(filtermap)
//                .flatMap(t -> splitstring(t))
//                .defaultIfEmpty("default")
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> concatString() {

        var string1 = Flux.just("A", "B", "C");
        var string2 = Flux.just("D", "E", "F");
        return Flux.concat(string1, string2).log();
    }

    public Flux<String> concatwithString() {

        var string3 = Mono.just("A");
        var string4 = Mono.just("D");
        return string3.concatWith(string4).log();
    }

    public Flux<String> MergeString() {

        var string1 = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        var string2 = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
        return Flux.merge(string1, string2).log();
    }

    public Flux<String> MergeWithString() {
        var string1 = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        var string2 = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
        return string1.mergeWith(string2).log();
    }

    public Flux<String> MergeWithStringMono() {
        var string1 = Mono.just("A");
        var string2 = Mono.just("D");
        return string1.mergeWith(string2).log();
    }

    public Flux<String> MergeSequential() {
        var string1 = Flux.just("A", "B");
        var string2 = Flux.just("C", "D");
        return Flux.mergeSequential(string1, string2);
    }

    public Flux<String> zipString() {
        var string1 = Flux.just("A", "B", "C");
        var string2 = Flux.just("D", "E", "F");
        var string3 = Flux.just("G", "H");

//        return Flux.zip(string1, string2, (a, b) -> a + b);
        return Flux.zip(string1, string2, string3).map(t3 -> t3.getT1() + t3.getT2() + t3.getT3()).log();
    }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
//        fluxAndMonoGeneratorService.nameFlux().subscribe(
//                name -> {
//                    System.out.println("Name:" + name);
//                }
//        );
//
//        fluxAndMonoGeneratorService.nameMono().subscribe(
//                name -> {
//                    System.out.println("Mono Name:" + name);
//                }
//        );

//        int Stringlength = 3;
//        fluxAndMonoGeneratorService.nameFlux_flatmap(Stringlength).subscribe(
//                name -> {
//                    System.out.println("String:" + name);
//                }
//        );

//        int Stringlength = 3;
//        fluxAndMonoGeneratorService.nameFlux_flatmap_async(Stringlength).subscribe(
//                name -> {
//                    System.out.println("String:" + name);
//                }
//        );

//        int Stringlength = 3;
//        fluxAndMonoGeneratorService.nameFlux_flatmap_async(Stringlength).subscribe(
//                name -> {
//                    System.out.println("String:" + name);
//                }
//        );
//        try {
//            Thread.sleep(2000); // Sleep for 2 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        int Stringlength = 3;
//        fluxAndMonoGeneratorService.nameFlux_concatmap_async(Stringlength).subscribe(
//                name -> {
//                    System.out.println("String:" + name);
//                }
//        );
//        try {
//            Thread.sleep(2000); // Sleep for 2 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        fluxAndMonoGeneratorService.zipString().subscribe(
//                name -> {
//                    System.out.println("Name:" + name);
//                }
//        );

        fluxAndMonoGeneratorService.MergeSequential().subscribe(
                name -> {
                    System.out.println("Name:" + name);
                }
        );


    }
}
