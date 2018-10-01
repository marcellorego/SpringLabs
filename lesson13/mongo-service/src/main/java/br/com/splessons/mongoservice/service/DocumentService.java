package br.com.splessons.mongoservice.service;

import br.com.splessons.mongoservice.repository.DocumentRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Date;

@Service
public class DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);

    public static final String CREATE_TIMESTAMP_FIELD = "createTimestamp";
    public static final String UPDATE_TIMESTAMP_FIELD = "updateTimestamp";
    public static final String UPDATED_BY_FIELD = "updatedBy";
    public static final String CREATED_BY_FIELD = "createdBy";
    public static final String OWNER_ID_FIELD = "ownerId";
    public static final String OWNED_BY_FIELD = "ownedBy";
    public static final String HOST_FIELD = "host";

    private final DocumentRepository DocumentRepository;
    private final Clock defaultClock;

    public DocumentService(DocumentRepository DocumentRepository, Clock defaultClock) {
        this.DocumentRepository = DocumentRepository;
        this.defaultClock = defaultClock;
    }

    public Document storeDocument(String databaseName, String collectionName, Document document) {
        setDocumentMetadata(document);
        return DocumentRepository.storeDocument(databaseName, collectionName, document);
    }

    private void setDocumentMetadata(Document document) {
        updateTimestamps(document);
    }

    private void updateTimestamps(Document document) {
        Date now = Date.from(defaultClock.instant());
        document.putIfAbsent(CREATE_TIMESTAMP_FIELD, now);
        document.put(UPDATE_TIMESTAMP_FIELD, now);
    }

}
