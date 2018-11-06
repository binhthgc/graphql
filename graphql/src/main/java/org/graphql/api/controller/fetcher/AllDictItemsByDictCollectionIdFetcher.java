package org.graphql.api.controller.fetcher;

import java.util.List;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class AllDictItemsByDictCollectionIdFetcher implements DataFetcher<List<DictItem>> {

	@Override
	public List<DictItem> get(DataFetchingEnvironment dataFetchingEnvironment) {

		int start = dataFetchingEnvironment.getArgument("start");
		int end = dataFetchingEnvironment.getArgument("end");
		long dictCollectionId = dataFetchingEnvironment.getArgument("dictCollectionId");

		System.out.println("AllDictItemsByDictCollectionIdFetcher.get(dictCollectionId)" + dictCollectionId);
		System.out.println("AllDictItemsByDictCollectionIdFetcher.get(start)" + start);
		System.out.println("AllDictItemsByDictCollectionIdFetcher.get(end)" + end);

		List<DictItem> results = DictItemLocalServiceUtil.findByF_dictCollectionId(dictCollectionId, start, end, null);

		return results;

	}
}
