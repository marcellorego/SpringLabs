package br.com.splessons.mongoservice.controller;

import br.com.splessons.mongoservice.service.DocumentService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static br.com.splessons.mongoservice.repository.DocumentRepository.ID_FIELD;

@RestController
@RequestMapping("${api.base-path}${api.document-path}/{databaseName}/{collectionName}")
public class DocumentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);
    private static final String JSON = MediaType.APPLICATION_JSON_UTF8_VALUE;

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(consumes = JSON)
    public ResponseEntity<Document> storeDocument(
            @PathVariable("databaseName") String databaseName,
            @PathVariable("collectionName") String collectionName,
            @RequestBody Document document) {

    	documentService.storeDocument(databaseName, collectionName, document);
        HttpHeaders responseHeaders = createHeadersFromDocument(document);
        Document response = createResponseFromDocument(document);
        return new ResponseEntity<>(response, responseHeaders, HttpStatus.CREATED);
    }

    private HttpHeaders createHeadersFromDocument(Document document) {

        String newId = document.getObjectId(ID_FIELD).toHexString();
        URI newJsonDataUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(newJsonDataUri);
        responseHeaders.set(ID_FIELD, newId);

        return responseHeaders;
    }

    private Document createResponseFromDocument(Document document) {

        String newId = document.getObjectId(ID_FIELD).toHexString();
        URI newJsonDataUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        Document response = new Document();
        response.append(ID_FIELD, document.getObjectId(ID_FIELD).toHexString());
        response.append(HttpHeaders.LOCATION.toLowerCase(), newJsonDataUri.getPath());

        return response;
    }

}
