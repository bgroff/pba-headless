package io.airbyte.controllers;

import com.airbyte.api.Airbyte;
import com.airbyte.api.models.operations.InitiateOAuthResponse;
import com.airbyte.api.models.operations.ListWorkspacesRequest;
import com.airbyte.api.models.shared.*;
import io.airbyte.clients.AirbyteApiFactory;
import io.airbyte.domain.Bakery;
import io.airbyte.domain.ConnectionKey;
import io.airbyte.dtos.BakeryCreate;
import io.airbyte.repositories.BakeriesRepository;
import io.airbyte.repositories.ConnectionKeyRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller("/bakeries")
public class Bakeries {
    final BakeriesRepository bakeriesRepository;
    final ConnectionKeyRepository connectionKeyRepository;
    final Airbyte airbyteSdk;

    public Bakeries(
            BakeriesRepository bakeriesRepository,
            ConnectionKeyRepository connectionKeyRepository,
            AirbyteApiFactory airbyteApiFactory
    ) {
        this.bakeriesRepository = bakeriesRepository;
        this.connectionKeyRepository = connectionKeyRepository;
        airbyteSdk = airbyteApiFactory.getAirbyteSdk();
    }

    @Get("/")
    public Mono<List<Bakery>> listBakeries() throws Exception {
        var workspaces = airbyteSdk.workspaces.listWorkspaces(new ListWorkspacesRequest().withLimit(1));
        return bakeriesRepository.findAll().collectList();
    }

    @Post("/")
    public Mono<Bakery> createBakery(@Valid BakeryCreate bakeryCreate) throws Exception {
        // Make call to the Airbyte API to create the Workspace

        var workspaceResponse = airbyteSdk.workspaces.createWorkspace(
                new WorkspaceCreateRequest()
                        .withName(bakeryCreate.getName()
                        )
        );

        var bakery = new Bakery();
        bakery.setName(bakeryCreate.getName());
        bakery.setWorkspaceId(UUID.fromString(workspaceResponse.workspaceResponse.workspaceId));
        bakery.setNumberOfMembers(new Random().nextInt(14));
        bakery.setCreatedOn(OffsetDateTime.now());
        bakery.setUpdatedOn(OffsetDateTime.now());
        return bakeriesRepository.save(bakery);

    }

    @Post("initiateConnection/{bakeryId}")
    public String createConnection(@PathVariable Long bakeryId) throws Exception {
        final var bakery = bakeriesRepository.findById(bakeryId).block();

        ConnectionKey connectionKey = new ConnectionKey();
        connectionKey.setKey(generateRandomString());
        connectionKey.setBakeryId(bakeryId);
        connectionKey.setUsername("testuser"); // Note: In a real production environment this would be filled in correctly
        connectionKeyRepository.save(connectionKey).block();


        InitiateOauthRequest initiateOauthRequest = new InitiateOauthRequest();
        initiateOauthRequest.withName("google-ads");
        initiateOauthRequest.withWorkspaceId(String.valueOf(bakery.getWorkspaceId()));
        initiateOauthRequest.withRedirectUrl("https://localhost:8008/bakeries/" + bakeryId + "/" + connectionKey.getKey());

        final InitiateOAuthResponse initiateOAuthResponse = airbyteSdk.sources.initiateOAuth(initiateOauthRequest);

        return new String(initiateOAuthResponse.rawResponse.body());
    }

    @Get("createConnection/{connectionKey}")
    public String createConnection(@PathVariable String connectionKey, @QueryValue("secret_id") String secretId) throws Exception {
        final ConnectionKey key = connectionKeyRepository.findByKey(connectionKey).block();
        final Bakery bakery = bakeriesRepository.findById(key.getBakeryId()).block();

        SourceGoogleAds googleAds = new SourceGoogleAds();
        googleAds.withCustomerId("1234567890"); // TODO: This should be real
        googleAds.withStartDate(LocalDate.now());
        googleAds.withSourceType(SourceGoogleAdsGoogleAdsEnum.GOOGLE_ADS);

        SourceCreateRequest sourceCreateRequest = new SourceCreateRequest();
        sourceCreateRequest.withSecretId(secretId);
        sourceCreateRequest.withWorkspaceId(String.valueOf(bakery.getWorkspaceId()));
        sourceCreateRequest.withConfiguration(googleAds);
        sourceCreateRequest.withName(bakery.getName() + " - Google Ads");

        var response = airbyteSdk.sources.createSource(sourceCreateRequest);
        var text = new String(response.rawResponse.body());
        return text;
    }

    private String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
