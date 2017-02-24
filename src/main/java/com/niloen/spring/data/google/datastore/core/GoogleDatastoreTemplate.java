package com.niloen.spring.data.google.datastore.core;

import com.google.api.client.googleapis.notifications.StoredChannel;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

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
}
