package com.niloen.spring.data.google.datastore.repository.query;

import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StructuredQuery;
import org.springframework.data.domain.Sort;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;

import java.util.Iterator;

/**
 * Created by marcus on 2017-02-23.
 */
public class GoogleDatastoreQueryCreator extends AbstractQueryCreator<KeyValueQuery<EntityQuery.Builder>, StructuredQuery.Filter> {
    public GoogleDatastoreQueryCreator(PartTree tree, ParameterAccessor parameters) {
        super(tree, parameters);
    }

    @Override
    protected StructuredQuery.Filter create(Part part, Iterator<Object> iterator) {
        switch(part.getType()) {
            case SIMPLE_PROPERTY:
                Object value = iterator.next();
                // TODO: Handle non-string values
                return StructuredQuery.PropertyFilter.eq(part.getProperty().toDotPath(), value.toString());
            default:
                throw new RuntimeException("Operator not implemented: " + part.toString());
        }
    }
    @Override
    protected StructuredQuery.Filter and(Part part, StructuredQuery.Filter base, Iterator<Object> iterator) {
        return StructuredQuery.CompositeFilter.and(base, create(part, iterator));
    }

    @Override
    protected StructuredQuery.Filter or(StructuredQuery.Filter base, StructuredQuery.Filter criteria) {
        throw new RuntimeException("Google Datastore does not support OR queries");
    }

    @Override
    protected KeyValueQuery<EntityQuery.Builder> complete(StructuredQuery.Filter criteria, Sort sort) {
        EntityQuery.Builder builder = Query.newEntityQueryBuilder().setFilter(criteria);
        KeyValueQuery<EntityQuery.Builder> kvQuery = new KeyValueQuery<>(builder);
        if (sort != null) {
            kvQuery.setSort(sort);
        }

        return kvQuery;
    }
}
