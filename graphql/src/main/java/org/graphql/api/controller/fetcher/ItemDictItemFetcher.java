package org.graphql.api.controller.fetcher;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class ItemDictItemFetcher implements DataFetcher<DictItem> {

	@Override
	public DictItem get(DataFetchingEnvironment dataFetchingEnvironment) {

		long dictItemId = dataFetchingEnvironment.getArgument("dictItemId");

		System.out.println("ItemDictItemFetcher.get(dictItemId)" + dictItemId);

		DictItem results = DictItemLocalServiceUtil.fetchDictItem(dictItemId);

		return results;

	}
}
