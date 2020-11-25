package department.controller;

import department.dao.DepartmentRepo;
import department.dto.DtoDepartment;
import department.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DtoDepartmentControllerTest {

    // @LocalServerPort
    // int randomServerPort;

    @MockBean
    @Qualifier("DtoDepartmentService")
    private DepartmentService service;

    @Autowired
    private WebTestClient webClient;

    @Test
    @SuppressWarnings("unchecked")
    void findAll() {
        DtoDepartment department1 = new DtoDepartment();
        department1.setId("first");
        department1.setName("firstName");

        DtoDepartment department2 = new DtoDepartment();
        department2.setId("second");
        department2.setName("secondName");

        DtoDepartment department3 = new DtoDepartment();
        department3.setId("third");
        department3.setName("thirdName");

        Mockito.when(service.findAll()).thenReturn(Flux.just(department1, department2, department3));
        webClient
                .get()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("[{\"id\":\"first\",\"name\":\"firstName\"},{\"id\":\"second\",\"name\":\"secondName\"},{\"id\":\"third\",\"name\":\"thirdName\"}]");


    }

    @Test
    @SuppressWarnings("unchecked")
    void findByName() {
        DtoDepartment department1 = new DtoDepartment();
        department1.setId("first");
        department1.setName("firstName");

        Mockito.when(service.findByName("firstName")).thenReturn(Mono.just(department1));

        webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/find")
                                .queryParam("name", "firstName")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("{\"id\":\"first\",\"name\":\"firstName\"}");

    }

    @Test
    @SuppressWarnings("unchecked")
    void findOne() {
        DtoDepartment department1 = new DtoDepartment();
        department1.setId("first");
        department1.setName("firstName");

        Mockito.when(service.findOne("first")).thenReturn(Mono.just(department1));

        webClient
                .get()
                .uri("/{id}", "first")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("{\"id\":\"first\",\"name\":\"firstName\"}");

    }

    @Test
    @SuppressWarnings("unchecked")
    void create() {
        DtoDepartment department1 = new DtoDepartment();
        department1.setId("first");
        department1.setName("firstName");

        Mockito.when(service.createDepartment(department1)).thenReturn(Mono.just(department1));

        webClient
                .post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(department1), DtoDepartment.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.name").isEqualTo("firstName");

    }

    @Test
    @SuppressWarnings("unchecked")
    void delete() {

        DtoDepartment department1 = new DtoDepartment();
        department1.setId("first");
        department1.setName("firstName");

        Mockito.when(service.delete("first")).thenReturn(Mono.just(Boolean.TRUE));

        webClient.
                delete()
                .uri("/{id}", "first")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .isEqualTo(true);

    }

    @Test
    @SuppressWarnings("unchecked")
    void updateDepartment() {

        DtoDepartment department1 = new DtoDepartment();
        department1.setId("first");
        department1.setName("firstName");

        Mockito.when(service.updateDepartment(department1, "first")).thenReturn(Mono.just(department1));
        webClient
                .put()
                .uri("/{id}","first")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(department1), DtoDepartment.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo("first")
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.name").isEqualTo("firstName");

    }
}