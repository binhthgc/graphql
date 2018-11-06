package org.graphql.api.controller.fetcher;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class ItemDictCollectionFetcher implements DataFetcher<DictCollection> {

	@Override
	public DictCollection get(DataFetchingEnvironment dataFetchingEnvironment) {

		long dictCollectionId = dataFetchingEnvironment.getArgument("dictCollectionId");

		System.out.println("ItemDictCollectionFetcher.get(dictCollectionId)" + dictCollectionId);

		DictCollection results = DictCollectionLocalServiceUtil.fetchDictCollection(dictCollectionId);

		return results;

	}
}
