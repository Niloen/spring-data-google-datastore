package com.niloen.spring.data.google.datastore.repository.support;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.repository.support.KeyValueRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;

import java.io.Serializable;

/**
 * Adapter for Springs {@link FactoryBean} interface to allow easy setup of {@link GoogleDatastoreRepositoryFactory} via Spring
 * configuration.
 * 
 */
public class GoogleDatastoreRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends KeyValueRepositoryFactoryBean<T, S, ID> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.keyvalue.repository.support.KeyValueRepositoryFactoryBean#createRepositoryFactory(org.springframework.data.keyvalue.core.KeyValueOperations, java.lang.Class, java.lang.Class)
	 */
	@Override
	protected GoogleDatastoreRepositoryFactory createRepositoryFactory(KeyValueOperations operations,
                                                             Class<? extends AbstractQueryCreator<?, ?>> queryCreator, Class<? extends RepositoryQuery> repositoryQueryType) {
		return new GoogleDatastoreRepositoryFactory(operations, queryCreator, repositoryQueryType);
	}
}
