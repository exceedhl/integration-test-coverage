package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.Story;
import com.thoughtworks.itcoverage.domain.StoryList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class XStreamTest {
    @Test
    public void testSerialization() {
        ArrayList<Story> stories = new ArrayList<Story>();
        stories.add(new Story(1, "a", "a desc"));
        stories.add(new Story(2, "b", "b desc"));
        StoryList storyList = new StoryList(stories);
        XStream xs = new XStream();
        xs.alias("cards", StoryList.class);
        xs.alias("card", Story.class);
        xs.addImplicitCollection(StoryList.class, "stories");
        xs.omitField(Story.class, "acceptanceCriteria");
        assertEquals("<cards>\n" +
                "  <card>\n" +
                "    <number>1</number>\n" +
                "    <name>a</name>\n" +
                "    <description>a desc</description>\n" +
                "  </card>\n" +
                "  <card>\n" +
                "    <number>2</number>\n" +
                "    <name>b</name>\n" +
                "    <description>b desc</description>\n" +
                "  </card>\n" +
                "</cards>", xs.toXML(storyList));
    }

    @Test
    public void testDeserializingMingleCardsXmlRepresentation() {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<cards type=\"array\">\n" +
                "  <card>\n" +
                "    <name>a</name>\n" +
                "    <description>a desc</description>\n" +
                "    <card_type url=\"http://localhost:8080/api/v2/projects/test/card_types/34.xml\">\n" +
                "      <name>Story</name>\n" +
                "    </card_type>\n" +
                "    <id type=\"integer\">371</id>\n" +
                "    <number type=\"integer\">12</number>\n" +
                "    <project url=\"http://localhost:8080/api/v2/projects/test.xml\">\n" +
                "      <name>Test</name>\n" +
                "      <identifier>test</identifier>\n" +
                "    </project>\n" +
                "    <version type=\"integer\">2</version>\n" +
                "    <project_card_rank type=\"integer\">105</project_card_rank>\n" +
                "    <created_on type=\"datetime\">2011-03-30T03:42:18Z</created_on>\n" +
                "    <modified_on type=\"datetime\">2011-04-22T01:32:38Z</modified_on>\n" +
                "    <modified_by url=\"http://localhost:8080/api/v2/users/1.xml\">\n" +
                "      <name>&#39033;&#30446;&#32463;&#29702;</name>\n" +
                "      <login>pm</login>\n" +
                "    </modified_by>\n" +
                "    <created_by url=\"http://localhost:8080/api/v2/users/1.xml\">\n" +
                "      <name>&#39033;&#30446;&#32463;&#29702;</name>\n" +
                "      <login>pm</login>\n" +
                "    </created_by>\n" +
                "    <properties type=\"array\">\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Priority</name>\n" +
                "        <value>Critical</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Story Status</name>\n" +
                "        <value>Open</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Automatically generated from the team list\" hidden=\"false\">\n" +
                "        <name>Owner</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed number list\" hidden=\"false\">\n" +
                "        <name>Planning Estimate</name>\n" +
                "        <value>5</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Formula\" hidden=\"false\">\n" +
                "        <name>Actual Effort</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Type of test</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Testing Status</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any number\" hidden=\"false\">\n" +
                "        <name>Build completed</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Formula\" hidden=\"false\">\n" +
                "        <name>Story Time-To-Life</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Added to Scope On</name>\n" +
                "        <value type=\"date\">2008-01-02</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Development Started On</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Development Completed On</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Accepted On</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Accepted in Iteration</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Added to Scope in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/44.xml\">\n" +
                "          <number type=\"integer\">44</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Development Completed in Iteration</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Development Started in Iteration</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>QA Completed in Iteration</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Analysis Completed in Iteration</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any card used in tree\" hidden=\"false\">\n" +
                "        <name>Release</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/31.xml\">\n" +
                "          <number type=\"integer\">31</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any card used in tree\" hidden=\"false\">\n" +
                "        <name>Feature - Story</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/46.xml\">\n" +
                "          <number type=\"integer\">46</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any card used in tree\" hidden=\"false\">\n" +
                "        <name>Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/38.xml\">\n" +
                "          <number type=\"integer\">38</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "    </properties>\n" +
                "    <tags>\n" +
                "    </tags>\n" +
                "  </card>\n" +
                "  <card>\n" +
                "    <name>Login to the e-mail application</name>\n" +
                "    <description>h1. &#25551;&#36848;&#65306;\n" +
                "    </description>\n" +
                "    <card_type url=\"http://localhost:8080/api/v2/projects/test/card_types/34.xml\">\n" +
                "      <name>Story</name>\n" +
                "    </card_type>\n" +
                "    <id type=\"integer\">360</id>\n" +
                "    <number type=\"integer\">1</number>\n" +
                "    <project url=\"http://localhost:8080/api/v2/projects/test.xml\">\n" +
                "      <name>Test</name>\n" +
                "      <identifier>test</identifier>\n" +
                "    </project>\n" +
                "    <version type=\"integer\">2</version>\n" +
                "    <project_card_rank type=\"integer\">116</project_card_rank>\n" +
                "    <created_on type=\"datetime\">2011-03-30T03:42:18Z</created_on>\n" +
                "    <modified_on type=\"datetime\">2011-04-22T01:29:36Z</modified_on>\n" +
                "    <modified_by url=\"http://localhost:8080/api/v2/users/1.xml\">\n" +
                "      <name>&#39033;&#30446;&#32463;&#29702;</name>\n" +
                "      <login>pm</login>\n" +
                "    </modified_by>\n" +
                "    <created_by url=\"http://localhost:8080/api/v2/users/1.xml\">\n" +
                "      <name>&#39033;&#30446;&#32463;&#29702;</name>\n" +
                "      <login>pm</login>\n" +
                "    </created_by>\n" +
                "    <properties type=\"array\">\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Priority</name>\n" +
                "        <value>Critical</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Story Status</name>\n" +
                "        <value>Accepted</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Automatically generated from the team list\" hidden=\"false\">\n" +
                "        <name>Owner</name>\n" +
                "        <value nil=\"true\"></value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed number list\" hidden=\"false\">\n" +
                "        <name>Planning Estimate</name>\n" +
                "        <value>1</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Formula\" hidden=\"false\">\n" +
                "        <name>Actual Effort</name>\n" +
                "        <value>1.00</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Type of test</name>\n" +
                "        <value>None</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Managed text list\" hidden=\"false\">\n" +
                "        <name>Testing Status</name>\n" +
                "        <value>Testing Complete</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any number\" hidden=\"false\">\n" +
                "        <name>Build completed</name>\n" +
                "        <value>5</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Formula\" hidden=\"false\">\n" +
                "        <name>Story Time-To-Life</name>\n" +
                "        <value>794</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Added to Scope On</name>\n" +
                "        <value type=\"date\">2008-01-02</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Development Started On</name>\n" +
                "        <value type=\"date\">2008-01-16</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Development Completed On</name>\n" +
                "        <value type=\"date\">2008-01-17</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Date\" hidden=\"true\">\n" +
                "        <name>Accepted On</name>\n" +
                "        <value type=\"date\">2010-03-06</value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Accepted in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/37.xml\">\n" +
                "          <number type=\"integer\">37</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Added to Scope in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/44.xml\">\n" +
                "          <number type=\"integer\">44</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Development Completed in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/34.xml\">\n" +
                "          <number type=\"integer\">34</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Development Started in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/34.xml\">\n" +
                "          <number type=\"integer\">34</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>QA Completed in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/36.xml\">\n" +
                "          <number type=\"integer\">36</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Card\" hidden=\"false\">\n" +
                "        <name>Analysis Completed in Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/44.xml\">\n" +
                "          <number type=\"integer\">44</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any card used in tree\" hidden=\"false\">\n" +
                "        <name>Release</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/31.xml\">\n" +
                "          <number type=\"integer\">31</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any card used in tree\" hidden=\"false\">\n" +
                "        <name>Feature - Story</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/49.xml\">\n" +
                "          <number type=\"integer\">49</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "      <property type_description=\"Any card used in tree\" hidden=\"false\">\n" +
                "        <name>Iteration</name>\n" +
                "        <value url=\"http://localhost:8080/api/v2/projects/test/cards/34.xml\">\n" +
                "          <number type=\"integer\">34</number>\n" +
                "        </value>\n" +
                "      </property>\n" +
                "    </properties>\n" +
                "    <tags>\n" +
                "    </tags>\n" +
                "  </card>\n" +
                "</cards>";

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

        StoryList stories = (StoryList) xs.fromXML(s);
        assertEquals(2, stories.size());
        assertEquals("a", stories.get(0).getName());
        assertEquals(12, stories.get(0).getNumber());
        assertEquals("a desc", stories.get(0).getDescription());
    }
}
