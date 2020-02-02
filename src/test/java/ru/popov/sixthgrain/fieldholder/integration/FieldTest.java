package ru.popov.sixthgrain.fieldholder.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.popov.sixthgrain.fieldholder.AbstractIntegrationTest;
import ru.popov.sixthgrain.fieldholder.dto.requests.FieldRequest;

import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FieldTest extends AbstractIntegrationTest {


    private final static String URL = "/api/field";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;


    @Test
    public void tryToGetAllFields() throws Exception {
        mvc.perform(
                get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    public void tryToGetOneFields() throws Exception {
        mvc.perform(
                get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.FieldName", is("Field 1")));
    }

    @Test
    public void tryToGetNonExistFields() throws Exception {
        mvc.perform(
                get(URL + "/123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(5)));
    }

    @Test
    public void tryToCreateNewField() throws Exception {
        FieldRequest fieldRequest = new FieldRequest();
        fieldRequest.setFieldName("Field1");
        fieldRequest.setLat(20.20);
        fieldRequest.setLon(20.20);
        fieldRequest.setAccountName("New User");
        fieldRequest.setAccountEmail("newUser@gmail.com");
        String json = mapper.writeValueAsString(fieldRequest);

        MvcResult mvcResult = mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Long> res = mapper.readValue(mvcResult.getResponse().getContentAsString(), Map.class);

        mvc.perform(
                get(URL + "/" + res.get("FieldId")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.FieldName", is("Field1")));
    }

}
