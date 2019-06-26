package com.dawn.springbootmatricsdemo.rest;

import com.dawn.springbootmatricsdemo.rest.error.AgeNotInRangeException;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class DemoResource {

    private static final int MINIMUM_AGE = 0;
    private static final int MAXIMUM_AGE = 20;
    private Set<String> DogsNames;
    private Map<String, Integer> map;
    private final MeterRegistry registry;

    @PostConstruct
    public void initData(){
        this.DogsNames =
                registry.gauge(
                        "dog.DogsNames.set.size",
                        Collections.emptyList(),
                        new HashSet<>(),
                        Set::size);

        this.map =
                registry.gaugeMapSize(
                        "dog.DogsNames.map.size",
                        Tags.empty(),
                        new HashMap<>());
    }

    /**
     * Simple method to demonstrate custom prometheus metric
     * This method gets age (in years) and will calculate
     * the age of a dog.
     * If the age not between 0 to 20 years, it will count to
     * prometheus custom metric and an exception will throw.
     */
    @GetMapping("/dog/age")
    @Timed("dog.age.calculation")
    public Double calculateDogAge(@RequestParam Double age) {

        if(age > MINIMUM_AGE && age <= MAXIMUM_AGE) {
            return age * 7;
        }
        else {

//            Timer timer = registry.timer("", "");
//
//            registry.counter("","").increment(-1);


            // Custom metric, counter that counts number of business exception
            registry
                    .counter(
                            "custom.metrics.age.exception",
                            "value",
                            age.toString())
                    .increment();
            throw new AgeNotInRangeException(
                    "1",
                    "Dog age must be between " +
                            MINIMUM_AGE + "-" + MAXIMUM_AGE +
                            " (in human years)");
        }
    }

    @PostMapping("/dog-name")
    public Set<String> saveDogName(@RequestBody String name){
        DogsNames.add(name);
        return DogsNames;
    }
}
