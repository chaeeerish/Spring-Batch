package ab180.assignments.link.controller;

import ab180.assignments.link.dto.request.LinkCreateRequest;
import ab180.assignments.link.dto.response.LinkResponse;
import ab180.assignments.link.global.base.BaseResponse;
import ab180.assignments.link.service.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/short-links")
    public ResponseEntity<BaseResponse<LinkResponse>> create(@RequestBody @Valid LinkCreateRequest request) {
        LinkResponse response = linkService.saveLink(request.url());
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("/short-links/{short_id}")
    public ResponseEntity<BaseResponse<LinkResponse>> get(@PathVariable(name = "short_id") String shortId) {
        LinkResponse response = linkService.getLinkByShortId(shortId);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("/r/{short_id}")
    public ResponseEntity<Object> redirect(@PathVariable(name = "short_id") String shortId) throws URISyntaxException {
        LinkResponse response = linkService.getLinkByShortId(shortId);
        URI redirectURI = new URI(response.url());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectURI);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
