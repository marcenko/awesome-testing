package de.cronos.awesometesting.container;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertTrue;

@Testcontainers
@SpringBootTest
public class ContainerITest {

    @Test
    void test() throws Exception {
        try (var container = new GenericContainer<>("nginx").withExposedPorts(80)) {
            container.start();

            var client = HttpClient.newHttpClient();
            var uri = "http://" + container.getHost() + ":" + container.getFirstMappedPort();

            var request = HttpRequest.newBuilder(URI.create(uri)).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            assertTrue(response.body().contains("Thank you for using nginx."));
        }
    }
}
