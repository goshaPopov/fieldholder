package ru.popov.sixthgrain.fieldholder.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import ru.popov.sixthgrain.fieldholder.AbstractIntegrationTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountTest extends AbstractIntegrationTest {

    private final static String URL = "/api/account";

    @Autowired
    private MockMvc mvc;

    @Test
    public void tryToGetAllAccounts() throws Exception {
        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    @Test
    public void tryToGetOneAccounts() throws Exception {
        mvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.AccountName", is("Goges")))
                .andExpect(jsonPath("$.AccountEmail", is("goge39@gmail.com")));
    }

    @Test
    public void tryToGetNonExistedAccounts() throws Exception {
        mvc.perform(get(URL + "/5"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(5)));
    }

}
