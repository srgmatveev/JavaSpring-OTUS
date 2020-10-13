package org.sergio.library.controllres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.sergio.library.domain.Genre;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.GenreRepo;
import org.sergio.library.validators.GenreDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenresControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private GenreRepo genreRepo;

    @MockBean
    private GridFsTemplate gridFsTemplate;

    @MockBean
    private MongoTemplate mongoTemplate;


    @MockBean
    private GenreDTOValidator genreDTOValidator;

    @Configuration
    @ComponentScan(basePackages = "org.sergio.library")
    static class InnerConfiguration {

        @Bean
        public MongoDatabaseFactory mongoDatabaseFactory() {
            return null;
        }

        @Bean
        public MappingMongoConverter mappingMongoConverter() {
            return null;
        }

        @Bean
        public MongoMappingContext mongoMappingContext() {
            return null;
        }
    }

    @Test
    @DisplayName("GET /genres success")
    void getGenres() throws Exception {
        Genre genre1 = new Genre("fantasy");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        Mockito.when(genreRepo.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(genres);
        ResultActions resultActions = mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre/genres"))
                .andExpect(model().attribute("genres", is(genres)))
                .andExpect(content().string(containsString("fantasy")));

        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
        assertEquals(mv.toString(), "ModelAndView [view=\"genre/genres\"; model={genres=[Genre(id=null, name=fantasy)]}]");
    }

    @Test
    @DisplayName("GET /genres/add success")
    void addGenre() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/genres/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre/add_genre"))
                .andExpect(model().attribute("genre", instanceOf(GenreDTO.class)));
        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
    }


    @Test
    @DisplayName("POST /genres/add success")
    void addGenrePost() throws Exception {
        mockMvc.perform(post("/genres/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/genres/del/id", "aaa")
                .contentType("application/json;charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
