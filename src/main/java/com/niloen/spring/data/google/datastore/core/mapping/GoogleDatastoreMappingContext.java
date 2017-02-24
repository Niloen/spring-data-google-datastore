package com.niloen.spring.data.google.datastore.core.mapping;

import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentEntity;
import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentProperty;
import org.springframework.data.keyvalue.core.mapping.context.KeyValueMappingContext;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class GoogleDatastoreMappingContext extends KeyValueMappingContext {

	public GoogleDatastoreMappingContext() {
	}

	@Override
	protected <T> GoogleDatastorePersistentEntity<T> createPersistentEntity(TypeInformation<T> typeInformation) {
		return new BasicGoogleDatastorePersistentEntity<T>(typeInformation, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.mapping.context.AbstractMappingContext#getPersistentEntity(java.lang.Class)
	 */
	@Override
	public GoogleDatastorePersistentEntity<?> getPersistentEntity(Class<?> type) {
		return (GoogleDatastorePersistentEntity<?>) super.getPersistentEntity(type);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.mapping.context.AbstractMappingContext#getPersistentEntity(org.springframework.data.mapping.PersistentProperty)
	 */
	@Override
	public GoogleDatastorePersistentEntity<?> getPersistentEntity(KeyValuePersistentProperty persistentProperty) {
		return (GoogleDatastorePersistentEntity<?>) super.getPersistentEntity(persistentProperty);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.mapping.context.AbstractMappingContext#getPersistentEntity(org.springframework.data.util.TypeInformation)
	 */
	@Override
	public GoogleDatastorePersistentEntity<?> getPersistentEntity(TypeInformation<?> type) {
		return (GoogleDatastorePersistentEntity<?>) super.getPersistentEntity(type);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.keyvalue.core.mapping.context.KeyValueMappingContext#createPersistentProperty(java.lang.reflect.Field, java.beans.PropertyDescriptor, org.springframework.data.keyvalue.core.mapping.KeyValuePersistentEntity, org.springframework.data.mapping.model.SimpleTypeHolder)
	 */
	@Override
	protected KeyValuePersistentProperty createPersistentProperty(Field field, PropertyDescriptor descriptor,
                                                                  KeyValuePersistentEntity<?> owner, SimpleTypeHolder simpleTypeHolder) {
		return new GoogleDatastorePersistentProperty(field, descriptor, owner, simpleTypeHolder);
	}
}
