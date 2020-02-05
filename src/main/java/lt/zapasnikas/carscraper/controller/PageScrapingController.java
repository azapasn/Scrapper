package lt.zapasnikas.carscraper.controller;

import lt.zapasnikas.carscraper.model.Seller;
import lt.zapasnikas.carscraper.service.PageScrapingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
public class PageScrapingController {
    private PageScrapingService pageScrapingService;

    public PageScrapingController(PageScrapingService pageScrapingService) {
        this.pageScrapingService = pageScrapingService;
    }

    @PostMapping("/scrap/single")
    public ResponseEntity<Seller> scrapSinglePage(@PathParam("link") String link) {
        Seller seller = pageScrapingService.scrapSinglePage(link);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping("/scrap/all")
    public ResponseEntity<Seller> scrapBySearchEngine(@PathParam("link") String link) throws IOException {
        pageScrapingService.scrapAllAdvertisementsBySearchLink(link);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<Seller>> getSellers() {
        List<Seller> sellers = pageScrapingService.getData();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

}

