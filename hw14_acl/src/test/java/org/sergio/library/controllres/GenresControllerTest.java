package org.sergio.library.controllres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sergio.library.domain.Genre;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.repository.GenreRepo;
import org.sergio.library.validators.GenreDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;





@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class GenresControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepo authorRepo;

    @MockBean
    private GenreRepo genreRepo;

    @MockBean
    private GridFsTemplate gridFsTemplate;

    @MockBean
    private MongoTemplate mongoTemplate;


    @MockBean
    private GenreDTOValidator genreDTOValidator;



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
        Genre genre = new Genre("Sergio");
        Class genreClass = genre.getClass();
        Field id = genreClass.getDeclaredField("id");
        id.setAccessible(true);
        id.set(genre, "aaa");
        assertEquals(genre.getId(),"aaa");
        doNothing().when(genreRepo).deleteById("aaa");
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/genres/del/id", genre.getId())
                .contentType("application/json;charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
