package com.niloen.spring.data.google.datastore.core;

import com.google.cloud.datastore.*;
import com.niloen.spring.data.google.datastore.core.mapping.GoogleDatastoreMappingContext;
import com.niloen.spring.data.google.datastore.core.mapping.GoogleDatastorePersistentEntity;
import org.springframework.data.keyvalue.core.AbstractKeyValueAdapter;
import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentProperty;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.data.mapping.PropertyHandler;
import org.springframework.data.util.CloseableIterator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
		Key key = keyFactoryForKeyspace(keyspace).newKey(asString(id));
		Entity.Builder builder = Entity.newBuilder(key);

		final GoogleDatastorePersistentEntity<?> persistentEntity = mappingContext.getPersistentEntity(item.getClass());

		final PersistentPropertyAccessor propertyAccessor = persistentEntity.getPropertyAccessor(item);

		persistentEntity.doWithProperties((PropertyHandler<KeyValuePersistentProperty>) persistentProperty -> {
				Object value = propertyAccessor.getProperty(persistentProperty);
				String name = persistentProperty.getName();
				if (value == null) {
					builder.setNull(name);
				} else {
					builder.set(name, value.toString());
				}
			}
		);

		ops.put(builder.build());

		return null;
	}

	public boolean contains(Serializable id, Serializable keyspace) {
		return ops.exists(keyFactoryForKeyspace(keyspace).newKey(asString(id)));
	}

	private Class classForKeyspace(Serializable keyspace) {
		try {
			return getClass().getClassLoader().loadClass(asString(keyspace));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Object get(Serializable id, Serializable keyspace) {
		Entity entity = ops.get(keyFactoryForKeyspace(keyspace).newKey(asString(id)));

		return entityToObject(entity, keyspace);
	}

	private Object entityToObject(Entity entity, Serializable keyspace) {
		if (entity == null) return null;

		Object obj = newObjectForKeyspace(keyspace);

		GoogleDatastorePersistentEntity<?> persistentEntity = mappingContext.getPersistentEntity(obj.getClass());

		final PersistentPropertyAccessor propertyAccessor = persistentEntity.getPropertyAccessor(obj);

		persistentEntity.doWithProperties((PropertyHandler<KeyValuePersistentProperty>) persistentProperty -> {
			Value<?> entityValue = entity.getValue(persistentProperty.getName());
			propertyAccessor.setProperty(persistentProperty, entityValue.get());
		});

		return obj;
	}

	private Object newObjectForKeyspace(Serializable keyspace) {
		Class entityClass = classForKeyspace(keyspace);

		try {
			return entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private KeyFactory keyFactoryForKeyspace(Serializable keyspace) {
		return ops.newKeyFactory().setKind(asString(keyspace));
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

	public Collection<Object> execute(EntityQuery query, Serializable keyspace) {
		return ops.execute(query.toBuilder().setKind(asString(keyspace)).build()).stream()
				.map(e -> entityToObject(e, keyspace))
				.collect(Collectors.toList());
	}
}
