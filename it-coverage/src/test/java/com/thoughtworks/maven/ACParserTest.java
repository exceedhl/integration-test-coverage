package com.thoughtworks.maven;

import com.thoughtworks.maven.domain.Story;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ACParserTest {
    @Test
    public void shouldParseOutAllACFromStoryDescription() {
        String description = "h1. 描述：\n" +
                "\n" +
                "业务系统，希望通过动态信息份分发，以便将消息发送到指定的好友。\n" +
                " \n" +
                "应用系统 在UCenter注册的时候，就创建了发送队列，后续该应用系统发送用户动态或者站内消息，都是通过向属于该应用系统的队列中send消息。\n" +
                "\n" +
                "消息调度进程已经提前在消息队列注册回调方法，注册成功以后，应用1想队列里面发布一条动态，队列会触发消息调度进程，消息调度进程把动态消息插入mongodb，查询该用户的所有应用的好友数据，把该动态按照好友id，用户id，应用id，动态信息的方式存放到消息分发表中，一个好友增加一条记录。\n" +
                "\n" +
                "其他应用系统或者本系统的好友上线以后，应用系统根据需要直接在消息分发表中查询好友动态。\n" +
                "\n" +
                "应用系统上需要和UCenter的队列交互，包括注册回调方法和创建队列，需要安装UCenter的客户端。\n" +
                "\n" +
                "h1. 验收策略\n" +
                "\n" +
                "h2. 正常路径\n" +
                "\n" +
                "# 当用户发布动态信息时，用户的好友收到该用户的动态信息。\n" +
                "# 当用户发布动态信息后，用户的好友由离线变为在线时收到该用户的动态信息。";
        Story story = new Story(1, "name", description);
        ACParser parser = new ACParser();
        parser.parseAC(story);
        assertEquals(2, story.getAcceptanceCriteria().size());
        assertEquals("当用户发布动态信息时，用户的好友收到该用户的动态信息。", story.getAcceptanceCriteria().get(0));
        assertEquals("当用户发布动态信息后，用户的好友由离线变为在线时收到该用户的动态信息。", story.getAcceptanceCriteria().get(1));
    }

    @Test
    public void shouldReturnEmptyACListWhenNoACTextFound() {
        String description = "text with no AC section";
        Story story = new Story(1, "name", description);
        ACParser parser = new ACParser();
        parser.parseAC(story);
        assertEquals(0, story.getAcceptanceCriteria().size());
    }
}
