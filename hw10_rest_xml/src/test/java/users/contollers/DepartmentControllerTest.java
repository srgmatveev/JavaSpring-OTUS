package users.contollers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import users.domain.Department;
import users.dto.DtoDepartment;
import users.test_controllers.TestDepartmentRepo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Departmen Controller Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DepartmentControllerTest {
    @LocalServerPort
    int randomServerPort;

    @Value("${rest.api.version1_0.path}")
    private String REST_API_URL;

    private final RestTemplate restTemplate;

    @Autowired
    TestDepartmentRepo departmentRepo;

    DepartmentControllerTest() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setUp() {
        departmentRepo.deleteAll();
    }

    @AfterEach
    void tearDown() {
        departmentRepo.deleteAll();
    }

    @Test
    @DisplayName("Post JSON method")
    void newDepartmentJSON() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/" + REST_API_URL + "/departments/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String request = "{\n" +
                "  \"name\":\"sergio1\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        ResponseEntity<DtoDepartment> resp = restTemplate.postForEntity(uri, entity, DtoDepartment.class);
        assertEquals(resp.getStatusCodeValue(), 200);
        assertEquals(resp.getBody().getName(), "sergio1");
        assertNotNull(resp.getBody().getId());
    }

    @Test
    @DisplayName("Post XML method")
    void newDepartmentXML() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/" + REST_API_URL + "/departments/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<dtoDepartment>\n" +
                "\t<name>Sergio</name>\n" +
                "</dtoDepartment>";

        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        ResponseEntity<DtoDepartment> resp = restTemplate.postForEntity(uri, entity, DtoDepartment.class);
        assertEquals(resp.getStatusCodeValue(), 200);
        assertEquals(resp.getBody().getName(), "Sergio");
        assertNotNull(resp.getBody().getId());
    }

    @Test
    @DisplayName("PUT JSON method")
    void updateDepartmentJSON() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/" + REST_API_URL + "/departments/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String request = "{\n" +
                "  \"name\":\"Sergio\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        ResponseEntity<DtoDepartment> resp = restTemplate.postForEntity(uri, entity, DtoDepartment.class);
        assertEquals(resp.getStatusCodeValue(), 200);
        assertEquals(resp.getBody().getName(), "Sergio");
        assertNotNull(resp.getBody().getId());

        String old_id = resp.getBody().getId();

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"id\":");
        builder.append("\"" + old_id + "\",");
        builder.append("\"name\":");
        builder.append("\"" + "Sergio18" + "\"");
        builder.append("}");

        entity = new HttpEntity<String>(builder.toString(), headers);
        resp = restTemplate.exchange(uri+"/"+resp.getBody().getId(), HttpMethod.PUT, entity, DtoDepartment.class);
        assertEquals(resp.getStatusCodeValue(), 200);
        assertEquals(resp.getBody().getName(), "Sergio18");
        assertEquals(resp.getBody().getId(), old_id);
    }


    @Test
    @DisplayName("PUT XML method")
    void updateDepartmentXML() throws URISyntaxException, JAXBException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/" + REST_API_URL + "/departments/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<dtoDepartment>\n" +
                "\t<name>Sergio</name>\n" +
                "</dtoDepartment>";

        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        ResponseEntity<DtoDepartment> resp = restTemplate.postForEntity(uri, entity, DtoDepartment.class);
        assertEquals(resp.getStatusCodeValue(), 200);
        assertEquals(resp.getBody().getName(), "Sergio");
        assertNotNull(resp.getBody().getId());

        String old_id = resp.getBody().getId();

        DtoDepartment tempoDtoDepartmnet = new DtoDepartment(resp.getBody().getId(),
                "Sergio18");

        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(DtoDepartment.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(tempoDtoDepartmnet, writer);

        String result = writer.toString();

        entity = new HttpEntity<String>(result, headers);
        resp = restTemplate.exchange(uri+"/"+resp.getBody().getId(), HttpMethod.PUT, entity, DtoDepartment.class);
        assertEquals(resp.getStatusCodeValue(), 200);
        assertEquals(resp.getBody().getName(), "Sergio18");
        assertEquals(resp.getBody().getId(), old_id);
    }

    @Test
    void deleteDepartment() throws URISyntaxException {
        assertEquals(departmentRepo.count(),0);
        Department department = new Department();
        department.setName("Michail");
        departmentRepo.insert(department);
        assertEquals(departmentRepo.count(),1);
        assertNotNull(department.getId());

        final String baseUrl = "http://localhost:" + randomServerPort + "/" + REST_API_URL + "/departments/";
        URI uri = new URI(baseUrl);
        restTemplate.delete(uri + "/" + department.getId());
        assertEquals(departmentRepo.count(),0);
    }
}