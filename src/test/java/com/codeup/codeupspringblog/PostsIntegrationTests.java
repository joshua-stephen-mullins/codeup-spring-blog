package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupSpringBlogApplication.class)
@AutoConfigureMockMvc
public class PostsIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {

        testUser = userDao.findByUsername("testUser");

        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }

        httpSession = this.mvc.perform(post("/login").with(csrf())
                        .param("username", "testUser")
                        .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads() {
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        assertNotNull(httpSession);
    }

    @Test
    public void testCreatePost() throws Exception {
        this.mvc.perform(
                        post("/posts/create").with(csrf())
                                .session((MockHttpSession) httpSession)
                                .param("title", "test post")
                                .param("body", "lorem ipsum"))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    public void testShowPost() throws Exception {

        Post existingPost = postDao.findAll().get(0);

        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(existingPost.getBody())));
    }

    @Test
    public void testPostsIndex() throws Exception {
        Post existingPost = postDao.findAll().get(0);

        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test post")))
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }

}