package com.niloen.spring.data.google.datastore.repository.core;

import com.niloen.spring.data.google.datastore.repository.query.GoogleDatastoreOperationChain;
import org.springframework.data.keyvalue.core.QueryEngine;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;

/**
 * GoogleDatastore specific {@link QueryEngine} implementation.
 * 
 * @author Christoph Strobl
 * @author Mark Paluch
 * @since 1.7
 */
class GoogleDatastoreQueryEngine extends QueryEngine<GoogleDatastoreKeyValueAdapter, GoogleDatastoreOperationChain, Comparator<?>> {

	/**
	 * Creates new {@link GoogleDatastoreQueryEngine} with defaults.
	 */
	public GoogleDatastoreQueryEngine() {
		super(null, null);
	}

	@Override
	public Collection<?> execute(GoogleDatastoreOperationChain googleDatastoreOperationChain, Comparator<?> comparator, int offset, int rows, Serializable keyspace) {
		throw new RuntimeException("Not implementeted yet");
	}

	@Override
	public long count(GoogleDatastoreOperationChain googleDatastoreOperationChain, Serializable keyspace) {
		throw new RuntimeException("Not implementeted yet");
	}
}
