package com.niloen.spring.data.google.datastore.repository.core;

import org.springframework.data.repository.core.EntityInformation;

import java.io.Serializable;

/**
 * @author Marcus Nilsson
 * @param <T>
 * @param <ID>
 */
public interface GoogleDatastoreEntityInformation<T, ID extends Serializable> extends EntityInformation<T, ID> {

}
