package ab180.assignments.link.domain;

import ab180.assignments.link.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "link")
public class Link extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String shortId;

    @Builder
    private Link(String url, String shortId) {
        this.url = url;
        this.shortId = shortId;
    }

    public static Link createLink(String url, String shortId) {
        return new Link(url, shortId);
    }
}
