package ab180.assignments.link.service;

import ab180.assignments.link.domain.Link;
import ab180.assignments.link.domain.LinkRepository;
import ab180.assignments.link.dto.response.LinkResponse;
import ab180.assignments.link.exception.LinkErrorCode;
import ab180.assignments.link.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;

    @Transactional
    public LinkResponse saveLink(String url) {
        String shortId = createShortId();
        Link link = linkRepository.save(Link.createLink(url, shortId));

        return new LinkResponse(link.getShortId(), link.getUrl(), link.getCreatedDateAt());
    }

    private String createShortId() {
        return "abcde";
    }

    public LinkResponse getLinkByShortId(String shortId) {
        Link link = linkRepository.findByShortId(shortId)
                .orElseThrow(() -> BaseException.type(LinkErrorCode.NOT_FOUND_LINK_BY_SHORT_ID));
        return new LinkResponse(link.getShortId(), link.getUrl(), link.getCreatedDateAt());
    }
}
