package io.airbyte.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Serdeable
@Entity
public class Connection {
    @Id
    @GeneratedValue
    private Long id;

    private String remoteConnectionID;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRemoteConnectionID() {
        return remoteConnectionID;
    }

    public void setRemoteConnectionID(String remoteConnectionID) {
        this.remoteConnectionID = remoteConnectionID;
    }
}
