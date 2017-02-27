package com.niloen.spring.data.google.datastore.core;

import com.google.cloud.datastore.*;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public long count(String kind) {

        QueryResults<Entity> entities = datastore.run(queryByKind(kind).build());

        long c = 0;
        while(entities.hasNext()) {
            entities.next();
            c++;
        }

        return c;
    }

    @Override
    public void delete(String kind) {
        //( No delete operation in Google Datastore
        QueryResults<Entity> entities = datastore.run(queryByKind(kind).build());

        List<Key> keysToDelete = new ArrayList<>();
        entities.forEachRemaining(entity -> keysToDelete.add(entity.getKey()));
        datastore.delete((keysToDelete.toArray(new Key[0])));
    }

    private EntityQuery.Builder queryByKind(String kind) {
        return Query.newEntityQueryBuilder().setKind(kind);
    }

    @Override
    public Entity get(Key key) {
        return datastore.get(key);
    }
}
