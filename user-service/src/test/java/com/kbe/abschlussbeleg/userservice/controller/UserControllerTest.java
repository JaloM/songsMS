package com.kbe.abschlussbeleg.userservice.controller;

import com.kbe.abschlussbeleg.userservice.model.User;
import com.kbe.abschlussbeleg.userservice.repository.UserRepository;
import com.kbe.abschlussbeleg.userservice.service.UserService;
import com.kbe.abschlussbeleg.userservice.util.JwtUtil;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "/test.properties")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
/*
    @Autowired
    private UserController userController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }


    @Autowired
    private UserRepository uRepo;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }


    @Test
    @Order(1)
    public void postUserShouldSaveUserAndReturnNewId() throws Exception {
        String userToSave = "{\"userId\":\"mmuster\","
                + "\"firstName\":\"Maxime\","
                + "\"lastName\":\"Muster\","
                + "\"password\":\"pass123\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userToSave))
                .andExpect(status().isOk());

        User savedUser = uRepo.findByUserId("mmuster");
        assertEquals("mmuster", savedUser.getUserId());
        assertEquals("Maxime", savedUser.getFirstname());
        assertEquals("Muster", savedUser.getLastname());
        assertEquals("pass1234", savedUser.getPassword());
    }

    @Test
    @Order(2)
    public void getUserByIdShouldReturnUser() throws Exception {
        uRepo.findAll().forEach( u -> System.out.println("EEEEEE: " + u.toString()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Bobby"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.email").value("bs@gmx.de"))
                .andExpect(jsonPath("$.userId").value("bsmith"));
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        User muster = new User();
        muster.setUserId("mmuster");
        muster.setPassword("pass1234");
        this.mockMvc.perform(get("/{token}")).andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(containsString("muster")));
    }

    @Test
    void authUser() throws Exception {
        User muster = new User();
        muster.setUserId("mmuster");
        muster.setPassword("pass1234");

        mockMvc.perform(post("/songsWS-Moroz_KBE/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(muster)))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/")).andDo(print()).andExpect(status().isOk());
            //    .andExpect((ResultMatcher) content().string(containsString(jwtUtil.generateToken(muster))));
    }

    @Test
    void testAuthToken() {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

 */
}