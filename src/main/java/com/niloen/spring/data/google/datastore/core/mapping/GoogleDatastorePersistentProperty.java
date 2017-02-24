package com.niloen.spring.data.google.datastore.core.mapping;

import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentProperty;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.model.SimpleTypeHolder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class GoogleDatastorePersistentProperty extends KeyValuePersistentProperty {

	public GoogleDatastorePersistentProperty(Field field, PropertyDescriptor propertyDescriptor,
                                             PersistentEntity<?, KeyValuePersistentProperty> owner, SimpleTypeHolder simpleTypeHolder) {
		super(field, propertyDescriptor, owner, simpleTypeHolder);
	}
}
