package io.airbyte.dtos;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class BakeryCreate {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
