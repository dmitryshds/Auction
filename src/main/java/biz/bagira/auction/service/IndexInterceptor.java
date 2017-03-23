package biz.bagira.auction.service;

import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.State;
import org.hibernate.search.indexes.interceptor.EntityIndexingInterceptor;
import org.hibernate.search.indexes.interceptor.IndexingOverride;

/**
 * Created by Dmitriy on 22.03.2017.
 */

/**
 * This class allows the Hibernate search make index only ACTIVE items
 */
public class IndexInterceptor implements EntityIndexingInterceptor<Item> {
    @Override
    public IndexingOverride onAdd(Item item) {
        if (item.getState().equals(State.ACTIVE.getState())) {
            return IndexingOverride.APPLY_DEFAULT;
        }
        return IndexingOverride.SKIP;
    }

    @Override
    public IndexingOverride onUpdate(Item item) {
        if (item.getState().equals(State.ACTIVE.getState())) {
            return IndexingOverride.UPDATE;
        }
        return IndexingOverride.REMOVE;
    }

    @Override
    public IndexingOverride onDelete(Item item) {
        return IndexingOverride.APPLY_DEFAULT;
    }

    @Override
    public IndexingOverride onCollectionUpdate(Item item) {
        return onUpdate(item);
    }
}
