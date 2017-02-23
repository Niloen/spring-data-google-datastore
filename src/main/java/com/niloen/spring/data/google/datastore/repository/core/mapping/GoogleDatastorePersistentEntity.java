package com.niloen.spring.data.google.datastore.repository.core.mapping;

import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentEntity;
import org.springframework.data.mapping.PersistentEntity;

/**
 * Google datastore specific {@link PersistentEntity}.
 * 
 * @param <T>
 */
public interface GoogleDatastorePersistentEntity<T> extends KeyValuePersistentEntity<T> {
}
