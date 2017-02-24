package com.niloen.spring.data.google.datastore.repository.core.mapping;

import org.springframework.data.keyvalue.core.mapping.BasicKeyValuePersistentEntity;
import org.springframework.data.keyvalue.core.mapping.KeySpaceResolver;
import org.springframework.data.util.TypeInformation;

public class BasicGoogleDatastorePersistentEntity<T> extends BasicKeyValuePersistentEntity<T>
		implements GoogleDatastorePersistentEntity<T> {

	public BasicGoogleDatastorePersistentEntity(TypeInformation<T> information, KeySpaceResolver fallbackKeySpaceResolver) {
		super(information, fallbackKeySpaceResolver);
	}
}
