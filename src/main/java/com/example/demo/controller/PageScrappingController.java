package com.example.demo.controller;

import com.example.demo.model.Seller;
import com.example.demo.service.PageScrappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
public class PageScrappingController {
    private PageScrappingService pageScrappingService;

    public PageScrappingController(PageScrappingService pageScrappingService) {
        this.pageScrappingService = pageScrappingService;
    }

    @PostMapping("/page")
    public ResponseEntity<Seller> scrap(@PathParam("link") String link) throws IOException {
        Seller seller = pageScrappingService.scrap(link);
        return new ResponseEntity<Seller>(seller, HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Seller>> getSellers(){
        List<Seller> sellers = pageScrappingService.getData();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

}
