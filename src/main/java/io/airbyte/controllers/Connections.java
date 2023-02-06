package io.airbyte.controllers;

import io.airbyte.domain.Connection;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import java.util.Collection;
import java.util.List;

@Controller("/connections")
public class Connections {

    @View("getConnections")
    @Get("/")
    Connection getConnections() {

        return new Connection();
    }
}
