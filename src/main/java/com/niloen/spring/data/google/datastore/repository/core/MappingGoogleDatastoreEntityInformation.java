package com.niloen.spring.data.google.datastore.repository.core;

import com.niloen.spring.data.google.datastore.core.mapping.GoogleDatastorePersistentEntity;
import org.springframework.data.repository.core.support.PersistentEntityInformation;

import java.io.Serializable;

/**
 * {@link GoogleDatastoreEntityInformation} implementation
 * 
 * @author Marcus Nilsson
 * @param <T>
 * @param <ID>
 */
public class MappingGoogleDatastoreEntityInformation<T, ID extends Serializable>
		extends PersistentEntityInformation<T, Serializable> implements GoogleDatastoreEntityInformation<T, Serializable> {

	private final GoogleDatastorePersistentEntity<T> entityMetadata;

	/**
	 * @param entity
	 */
	public MappingGoogleDatastoreEntityInformation(GoogleDatastorePersistentEntity<T> entity) {
		super(entity);

		this.entityMetadata = entity;
	}
}
