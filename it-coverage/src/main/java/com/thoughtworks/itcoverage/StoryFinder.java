package com.thoughtworks.itcoverage;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.itcoverage.domain.Properties;
import com.thoughtworks.itcoverage.domain.Story;
import com.thoughtworks.itcoverage.domain.StoryList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import sun.misc.BASE64Encoder;

public class StoryFinder {
    private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
    private static final String RESOURCE_URL = "http://localhost:8080/api/v2/projects/test/cards.xml?filters[]=[Type][is][story]&sort=number&order=ASC";
    private static final String USERNAME = "pm";
    private static final String PASSWORD = "111111";
    private String resourceUrl;
    private String username;
    private String password;
    private ACParser acParser;

    public StoryFinder(String resourceUrl, String username, String password, ACParser acParser) {
        this.resourceUrl = resourceUrl;
        this.username = username;
        this.password = password;
        this.acParser = acParser;
    }

    public StoryFinder(ACParser acParser) {
        this.resourceUrl = RESOURCE_URL;
        this.username = USERNAME;
        this.password = PASSWORD;
    }

    public StoryList findAll() {
        Client client = Client.create();
        WebResource resource = client.resource(resourceUrl);
        String s = resource.header("Authorization", "Basic " + getBasicAuthorization()).
                header("Content-Type", CONTENT_TYPE).get(String.class);
        XStream xs = getXStream();
        StoryList stories = (StoryList) xs.fromXML(s);
        acParser.parseAC(stories);
        return stories;
    }

    private String getBasicAuthorization() {
        return new BASE64Encoder().encode((username + ":" + password).getBytes());
    }

    private XStream getXStream() {
        XStream xs = new XStream() {
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        try {
                            return definedIn != Object.class || realClass(fieldName) != null;
                        } catch (CannotResolveClassException cnrce) {
                            return false;
                        }
                    }
                };
            }
        };

        xs.alias("cards", StoryList.class);
        xs.alias("card", Story.class);
        xs.addImplicitCollection(StoryList.class, "stories");
        xs.addImplicitCollection(Properties.class, "properties");
        return xs;
    }
}
