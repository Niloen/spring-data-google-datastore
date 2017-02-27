package com.niloen.spring.data.google.datastore.core;

import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StructuredQuery;
import org.springframework.data.domain.Sort;
import org.springframework.data.keyvalue.core.CriteriaAccessor;
import org.springframework.data.keyvalue.core.QueryEngine;
import org.springframework.data.keyvalue.core.SortAccessor;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GoogleDatastore specific {@link QueryEngine} implementation.
 * 
 * @author Christoph Strobl
 * @author Mark Paluch
 * @since 1.7
 */
class GoogleDatastoreQueryEngine extends QueryEngine<GoogleDatastoreKeyValueAdapter, EntityQuery.Builder, StructuredQuery.OrderBy[]> {

	/**
	 * Creates new {@link GoogleDatastoreQueryEngine} with defaults.
	 */
	public GoogleDatastoreQueryEngine() {
		super(new GoogleDatastoreCriteriaAccessor(), new GoogleDatastoreSortAccessor());
	}

	@Override
	public Collection<?> execute(EntityQuery.Builder queryBuilder, StructuredQuery.OrderBy[] orderBys, int offset, int rows, Serializable keyspace) {
		for (StructuredQuery.OrderBy orderBy : orderBys) {
			queryBuilder.addOrderBy(orderBy);
		}

		return getAdapter().execute(queryBuilder.build(), keyspace);
	}

	@Override
	public long count(EntityQuery.Builder queryBuilder, Serializable keyspace) {
		return 0;
	}

	private static class GoogleDatastoreSortAccessor implements SortAccessor<StructuredQuery.OrderBy[]> {
		@Override
        public StructuredQuery.OrderBy[] resolve(KeyValueQuery<?> query) {
            List<StructuredQuery.OrderBy> res = new ArrayList<>();
            Sort sort = query.getSort();
            if (sort != null) {
				sort.forEach(order -> {
					if (order.getDirection() == Sort.Direction.ASC) {
						res.add(StructuredQuery.OrderBy.asc(order.getProperty()));
					} else {
						res.add(StructuredQuery.OrderBy.desc(order.getProperty()));
					}
				});
			}

            return res.toArray(new StructuredQuery.OrderBy[0]);
        }
	}

	private static class GoogleDatastoreCriteriaAccessor implements CriteriaAccessor<EntityQuery.Builder> {
		@Override
        public EntityQuery.Builder resolve(KeyValueQuery<?> query) {
            return (EntityQuery.Builder) query.getCritieria();
        }
	}
}
