package com.niloen.spring.data.google.datastore.core;

import com.google.cloud.datastore.*;

/**
 * Created by marcus on 2017-02-24.
 */
public class GoogleDatastoreTemplate implements GoogleDatastoreOperations {
    private Datastore datastore;

    public GoogleDatastoreTemplate(Datastore datastore) {
        this.datastore = datastore;
    }

    public GoogleDatastoreTemplate() {
        this(DatastoreOptions.getDefaultInstance().getService());
    }

    public KeyFactory newKeyFactory() {
        return datastore.newKeyFactory();
    }

    public boolean exists(Key key) {
        return datastore.get(key) != null;
    }

    public void put(Entity entity) {
        datastore.put(entity);
    }
}
