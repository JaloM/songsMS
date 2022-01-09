package com.kbe.abschlussbeleg.songsWS.controller;

import com.kbe.abschlussbeleg.songsWS.SongsWsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


//@Sql({"/import.sql"})
//@SpringBootTest(classes = {SongsWsApplication.class})
//@TestPropertySource(locations = "/test.properties")
//@Sql(scripts = "/import.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = {"/drop.sql","/schema.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SongListControllerTest {

    @Test
    void getUserById() {
    }

    @Test
    void getSongListById() {
    }

    @Test
    void createSongsList() {
    }

    @Test
    void updateSongList() {
    }

    @Test
    void deleteSongList() {
    }
    /*
     @BeforeEach
    public void setupMockMvc() {
        userDetailsService = new MyUserDetailsService(userRepo);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("mmuster", "pass1234"));

        UserDetails userDetails = userDetailsService.loadUserByUsername("mmuster");
        jwt = jwtToken.generateToken(userDetails);

        mockMvc = MockMvcBuilders.standaloneSetup(new SongListController(songListRepo,userRepo,songRepo)).build();
    }

    @Test
    void postUserShouldReturn401ForNonIdentity() throws Exception {
        String userTo= "{\"userId\":\"mmuster\","
                + "\"password\":\"pass1234\", "
                + "\"firstName\":\"Maxime\","
                + "\"lastName\":\"Muster\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/songsWS-MorozNebi_KBE/rest/auth")
                .content(userTo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.userId").doesNotExist());
    }


    @Test
    public void getAllSonglistsJSONReturn200AndOnlyPublicSongLists() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/songsWS-MorozNebi_KBE/rest/songLists?userId=eschuler").accept(MediaType.APPLICATION_JSON).header("Authorization",jwt))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].ownerId").value("eschuler"))
                .andExpect(jsonPath("$[0].private").value(false))
                .andExpect(jsonPath("$[0].name").value("ElenaPublicList"))
                .andExpect(jsonPath("$[0].songList.size()").value(2))
                .andReturn();
    }

    @Test
    void createSongsList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/songsWS-MorozNebi_KBE/rest/songLists?userId=mmuster")
                .accept(MediaType.APPLICATION_JSON).header(jwt))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[ownerId]").value("mmuster"))
                .andReturn();
    }
     */
}