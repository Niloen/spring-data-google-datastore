package com.niloen.spring.data.google.datastore.core;

import com.google.cloud.datastore.*;

/**
 * Created by marcus on 2017-02-24.
 */
public interface GoogleDatastoreOperations {
    KeyFactory newKeyFactory();

    boolean exists(Key k);

    void put(Entity entity);

    long count(String kind);

    void delete(String kind);

    Entity get(Key key);
}
