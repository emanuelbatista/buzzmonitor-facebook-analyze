
package com.buzzmonitor.facebook.analyze.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.buzzmonitor.facebook.analyze.dto.response.PostCountForDateDTO;
import com.buzzmonitor.facebook.analyze.model.Post;
import com.buzzmonitor.facebook.analyze.repository.PostRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookAnalyzeServiceTest {

    @Autowired
    private FacebookAnalyzeService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookAnalyzeServiceTest.class);

    private OffsetDateTime dataSince;
    private OffsetDateTime dataUntil;
    private static final String DATA_FORMAT_DEFAULT = "yyyyMMdd";
    private static final String DATA_SINCE_DEFAULT = "20170101";
    private static final String DATA_UNTIL_DEFAULT = "20171231";
    private static final String PAGE_ID = "998036520310119";

    @Before
    public void before() {
        this.dataSince = OffsetDateTime.of(
                LocalDate.parse(DATA_SINCE_DEFAULT,
                        DateTimeFormatter.ofPattern(DATA_FORMAT_DEFAULT)),
                LocalTime.MIN, OffsetDateTime.now().getOffset());
        this.dataUntil = OffsetDateTime.of(
                LocalDate.parse(DATA_UNTIL_DEFAULT,
                        DateTimeFormatter.ofPattern(DATA_FORMAT_DEFAULT)),
                LocalTime.MAX, OffsetDateTime.now().getOffset());
        //
    }

    /**
     * Test if the service isn't return none exception to consume the posts
     */
    @Test
    public void consumePostsSuccessful() {
        this.service.consumePosts(1400, PAGE_ID);
    }

    @Test(
            expected = IllegalStateException.class)
    public void consumePostsFailIllegalStateException() {
        this.service.consumePosts(-1400, PAGE_ID);
    }

    /**
     * Test to get the posts
     * 
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @Test
    public void getPostsBetweenDatesSucessful()
            throws JsonParseException, JsonMappingException, IOException {
        String result = this.service.getPosts(DATA_SINCE_DEFAULT, DATA_UNTIL_DEFAULT);
        List<Post> posts = objectMapper.readValue(result, new TypeReference<List<Post>>() {
        });
        assertNotNull(posts);
    }

    /**
     * Test that fail because the date since is greater than data until
     * 
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @Test(
            expected = IllegalStateException.class)
    public void getPostsBetweenDatesFailDateRule()
            throws JsonParseException, JsonMappingException, IOException {
        this.service.getPosts(DATA_UNTIL_DEFAULT, DATA_SINCE_DEFAULT);
    }

    /**
     * Test that fail because the date since is invalid format
     * 
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @Test(
            expected = DateTimeParseException.class)
    public void getPostsBetweenDatesFailDateFormat()
            throws JsonParseException, JsonMappingException, IOException {
        this.service.getPosts("2017-01-01", DATA_SINCE_DEFAULT);
    }

    /**
     * Test post amount from dates interval
     * 
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @Test
    public void getCountPostsBetweenDatesSucessful()
            throws JsonParseException, JsonMappingException, IOException {
        List<PostCountForDateDTO> postsRepository =
                this.postRepository.countPostsByDate(dataSince, dataUntil);
        LOGGER.info("POST REPOSITORY - " + postsRepository);
        String result = this.service.getVolume(DATA_SINCE_DEFAULT, DATA_UNTIL_DEFAULT);
        List<PostCountForDateDTO> posts =
                objectMapper.readValue(result, new TypeReference<List<PostCountForDateDTO>>() {
                });
        LOGGER.info("POST SERVICE - " + posts);
        assertTrue(posts.containsAll(postsRepository));
        LocalDate eachDate = dataSince.toLocalDate();
        int count = 0;
        while (eachDate.compareTo(dataUntil.toLocalDate()) <= 0) {
            PostCountForDateDTO post = posts.get(count++);
            assertNotNull(post);
            assertNotNull(post.getSum());
            assertNotNull(post.getDate());
            assertEquals(eachDate, post.getDate());
            assertTrue(post.getSum() >= 0);
            eachDate = eachDate.plusDays(1);
        }
    }

    /**
     * Test that fail because the date since is greater than data until
     * 
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @Test(
            expected = IllegalStateException.class)
    public void getCountPostsBetweenDatesFailDateRule()
            throws JsonParseException, JsonMappingException, IOException {
        this.service.getVolume(DATA_UNTIL_DEFAULT, DATA_SINCE_DEFAULT);
    }

}
