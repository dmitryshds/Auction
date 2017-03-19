package biz.bagira.auction.controller;

import biz.bagira.auction.configuration.ApplicationConfig;
import biz.bagira.auction.configuration.HibernateConfiguration;
import biz.bagira.auction.configuration.SecurityConfiguration;
import biz.bagira.auction.entities.Category;
import biz.bagira.auction.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Dmitriy on 17.03.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, HibernateConfiguration.class, SecurityConfiguration.class})
@WebAppConfiguration
public class MainControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private CategoryService categoryServiceMock = mock(CategoryService.class);

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(categoryServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void index() throws Exception {
        Category category = new Category();
        category.setIdCategory(1);
        category.setType("type1");

        Category category2 = new Category();
        category2.setIdCategory(2);
        category2.setType("type2");

        when(categoryServiceMock.getAll()).thenReturn(Arrays.asList(category,category2));

        mockMvc.perform(get("/"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("index"))
                        .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))

                        .andExpect(model().attribute("categories", notNullValue()));

                verifyZeroInteractions(categoryServiceMock);
    }

}