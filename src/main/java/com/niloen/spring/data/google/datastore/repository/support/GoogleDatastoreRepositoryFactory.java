package com.niloen.spring.data.google.datastore.repository.support;

import com.niloen.spring.data.google.datastore.repository.query.GoogleDatastoreQueryCreator;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.repository.query.KeyValuePartTreeQuery;
import org.springframework.data.keyvalue.repository.support.KeyValueRepositoryFactory;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;

/**
 * Created by marcus on 2017-02-23.
 */
public class GoogleDatastoreRepositoryFactory extends KeyValueRepositoryFactory {
    public GoogleDatastoreRepositoryFactory(KeyValueOperations keyValueOperations) {
        super(keyValueOperations, GoogleDatastoreQueryCreator.class);
    }

    public GoogleDatastoreRepositoryFactory(KeyValueOperations keyValueOperations, Class<? extends AbstractQueryCreator<?, ?>> queryCreator) {
        super(keyValueOperations, queryCreator, KeyValuePartTreeQuery.class);
    }

    public GoogleDatastoreRepositoryFactory(KeyValueOperations keyValueOperations, Class<? extends AbstractQueryCreator<?, ?>> queryCreator, Class<? extends RepositoryQuery> repositoryQueryType) {
        super(keyValueOperations, queryCreator, repositoryQueryType);
    }
}
