package com.niloen.spring.data.google.datastore.core;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.niloen.spring.data.google.datastore.core.mapping.GoogleDatastoreMappingContext;
import com.niloen.spring.data.google.datastore.core.mapping.GoogleDatastorePersistentEntity;
import org.springframework.data.keyvalue.core.AbstractKeyValueAdapter;
import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentProperty;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.data.mapping.PropertyHandler;
import org.springframework.data.util.CloseableIterator;

import java.io.Serializable;
import java.util.Map.Entry;

public class GoogleDatastoreKeyValueAdapter extends AbstractKeyValueAdapter {

	private GoogleDatastoreOperations ops;
	private GoogleDatastoreMappingContext mappingContext;

	public GoogleDatastoreKeyValueAdapter(GoogleDatastoreOperations ops, GoogleDatastoreMappingContext mappingContext) {
		super(new GoogleDatastoreQueryEngine());
		this.ops = ops;
		this.mappingContext = mappingContext;
	}

	private String asString(Serializable value) {
		return value.toString();
	}


	public Object put(Serializable id, Object item, Serializable keyspace) {
		Key key = ops.newKeyFactory().setKind(asString(keyspace)).newKey(asString(id));
		Entity.Builder builder = Entity.newBuilder(key);

		final GoogleDatastorePersistentEntity<?> persistentEntity = mappingContext.getPersistentEntity(item.getClass());

		final PersistentPropertyAccessor propertyAccessor = persistentEntity.getPropertyAccessor(item);

		persistentEntity.doWithProperties((PropertyHandler<KeyValuePersistentProperty>) persistentProperty -> {
			Object value = propertyAccessor.getProperty(persistentProperty);
			String name = persistentProperty.getName();
			builder.set(name, value == null ? "null": value.toString());
			}
		);

		ops.put(builder.build());

		return null;
	}

	public boolean contains(Serializable id, Serializable keyspace) {
		return ops.exists(ops.newKeyFactory().setKind(asString(keyspace)).newKey(asString(id)));
	}

	public Object get(Serializable id, Serializable keyspace) {
		throw new RuntimeException("Not implemented yet");
	}

	public Object delete(Serializable id, Serializable keyspace) {
		throw new RuntimeException("Not implemented yet");
	}

	public Iterable<?> getAllOf(Serializable keyspace) {
		throw new RuntimeException("Not implemented yet");
	}

	public CloseableIterator<Entry<Serializable, Object>> entries(Serializable keyspace) {
		throw new RuntimeException("Not implemented yet");
	}

	public void deleteAllOf(Serializable keyspace) {
	    ops.delete(asString(keyspace));
	}

	public void clear() {
		throw new RuntimeException("Not implemented yet");

	}

	public long count(Serializable keyspace) {
	    return ops.count(asString(keyspace));
	}

	public void destroy() throws Exception {
	}
}
