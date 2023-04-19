package io.airbyte.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Serdeable
@Entity
public class ConnectionKey {
    @Id
    @GeneratedValue
    Long id;

    String username;

    String key;

    Long bakeryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getBakeryId() {
        return bakeryId;
    }

    public void setBakeryId(Long bakeryId) {
        this.bakeryId = bakeryId;
    }
}
