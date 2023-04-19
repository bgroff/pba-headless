package io.airbyte.clients;

import com.airbyte.api.Airbyte;
import com.airbyte.api.models.shared.Security;
import io.micronaut.context.annotation.Property;
import jakarta.inject.Singleton;

@Singleton
public class AirbyteApiFactory {
    final String airbyteServerUrl;
    final String apiKey;
    final Airbyte airbyteSdk;

    public AirbyteApiFactory(
            @Property(name = "airbyte.serverUrl") String airbyteServerUrl,
            @Property(name = "airbyte.apiKey") String apiKey) {
        this.airbyteServerUrl = airbyteServerUrl;
        this.apiKey = apiKey;
        try {
            this.airbyteSdk = Airbyte
                    .builder()
                    .setServerURL(airbyteServerUrl)
                    .setSecurity(
                            new Security()
                                    .withBearerAuth("Bearer " + apiKey)
                    )
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Airbyte getAirbyteSdk() {
        return airbyteSdk;
    }
}
