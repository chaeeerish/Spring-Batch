package ab180.assignments.domain;

import ab180.assignments.link.domain.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("[Domain Test] Link")
public class LinkTest {
    private String url = "https://airbridge.io";
    private String shortId = "abcde";

    @Test
    void Link_생성에_성공하다() {
        Link link = Link.createLink(url, shortId);

        assertAll(
                () -> assertThat(link.getUrl()).isEqualTo(url),
                () -> assertThat(link.getShortId()).isEqualTo(shortId)
        );
    }
}