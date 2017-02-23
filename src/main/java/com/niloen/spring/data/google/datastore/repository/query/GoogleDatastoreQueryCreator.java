package com.niloen.spring.data.google.datastore.repository.query;

import org.springframework.data.domain.Sort;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;

import java.util.Iterator;

/**
 * Created by marcus on 2017-02-23.
 */
public class GoogleDatastoreQueryCreator extends AbstractQueryCreator<KeyValueQuery<GoogleDatastoreOperationChain>, GoogleDatastoreOperationChain> {
    public GoogleDatastoreQueryCreator(PartTree tree, ParameterAccessor parameters) {
        super(tree, parameters);
    }

    @Override
    protected GoogleDatastoreOperationChain create(Part part, Iterator<Object> iterator) {
        return null;
    }

    @Override
    protected GoogleDatastoreOperationChain and(Part part, GoogleDatastoreOperationChain googleDatastoreOperationChain, Iterator<Object> iterator) {
        return null;
    }

    @Override
    protected GoogleDatastoreOperationChain or(GoogleDatastoreOperationChain googleDatastoreOperationChain, GoogleDatastoreOperationChain s1) {
        return null;
    }

    @Override
    protected KeyValueQuery<GoogleDatastoreOperationChain> complete(GoogleDatastoreOperationChain googleDatastoreOperationChain, Sort sort) {
        return null;
    }
}
