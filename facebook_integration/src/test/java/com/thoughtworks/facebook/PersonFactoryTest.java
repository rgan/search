package com.thoughtworks.facebook;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonFactoryTest {

    @Test
    public void shouldParseLikes() {
        String json = "{\"data\":[{\"name\":\"To Kill a Mockingbird\",\n" +
                "\"category\":\"Book\",\"id\":\"105709882797285\",\n" +
                "\"created_time\":\"2010-09-22T22:19:18+0000\"},\n" +
                "{\"name\":\"Cricket\",\"category\":\"Sport\",\n" +
                "\"id\":\"103992339636529\",\"created_time\":\"2010-09-22T22:19:18+0000\"}]}";
        final PersonFactory personFactory = new PersonFactory(null);
        final List<Like> result = personFactory.parseLikes(JSONObject.fromObject(json));
        assertTrue(result.contains(new Like("To Kill a Mockingbird", "Book")));
        assertTrue(result.contains(new Like("Cricket", "Sport")));
        assertTrue(personFactory.parseLikes(JSONObject.fromObject("{}")).isEmpty());
    }

    @Test
    public void shouldParsePerson() throws Exception {

        FacebookClient client = mock(FacebookClient.class);
        when(client.getPersonData("758702630", false)).thenReturn("{\"id\": \"758702630\", \"name\": \"foo\", \"likes\": {}}");
        final PersonFactory personFactory = new PersonFactory(client);
        String json = "{\n" +
                "   \"id\": \"100001569193092\",\n" +
                "   \"name\": \"someone\",\n" +
                "   \"likes\": {\n" +
                "      \"data\": [\n" +
                "         {\n" +
                "            \"name\": \"Toys ''R'' Us\",\n" +
                "            \"category\": \"Retail\",\n" +
                "            \"id\": \"40155181493\",\n" +
                "            \"created_time\": \"2010-09-24T10:37:58+0000\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\": \"Gandhi\",\n" +
                "            \"category\": \"Movie\",\n" +
                "            \"id\": \"109466732412595\",\n" +
                "            \"created_time\": \"2010-09-24T03:11:54+0000\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\": \"Hiking\",\n" +
                "            \"category\": \"Interest\",\n" +
                "            \"id\": \"105525412814559\",\n" +
                "            \"created_time\": \"2010-09-24T03:11:54+0000\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\": \"Cheers\",\n" +
                "            \"category\": \"Tv show\",\n" +
                "            \"id\": \"106021302763133\",\n" +
                "            \"created_time\": \"2010-09-24T03:11:54+0000\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\": \"To Kill a Mockingbird\",\n" +
                "            \"category\": \"Book\",\n" +
                "            \"id\": \"105709882797285\",\n" +
                "            \"created_time\": \"2010-09-22T22:19:18+0000\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\": \"Cricket\",\n" +
                "            \"category\": \"Sport\",\n" +
                "            \"id\": \"103992339636529\",\n" +
                "            \"created_time\": \"2010-09-22T22:19:18+0000\"\n" +
                "         }\n" +
                "      ]\n" +
                "   },\n" +
                "   \"friends\": {\n" +
                "      \"data\": [\n" +
                "         {\n" +
                "            \"name\": \"Foo\",\n" +
                "            \"id\": \"758702630\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";
        Person person = personFactory.from("100001569193092",json);
        assertEquals("100001569193092", person.getId());
        assertEquals("someone", person.getName());
        assertTrue(person.getLikes().contains(new Like("Gandhi", "Movie")));
        assertEquals(1, person.getFriends().size());
        assertEquals("758702630", person.getFriends().iterator().next().getId());
    }

}
