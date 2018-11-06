package org.graphql.api.controller.cud;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class DeleteDictItemMutation implements DataFetcher<DictItem> {

	@Override
	public DictItem get(DataFetchingEnvironment dataFetchingEnvironment) {

		System.out.println("MutationWiring.deleteDictItem()");
		
		Long dictItemId = dataFetchingEnvironment.getArgument("dictItemId");

		System.out.println("deleteDictItem.get(dictItemId)" + dictItemId);

		DictItem results = DictItemLocalServiceUtil.fetchDictItem(dictItemId);

		return results;

	}
}
