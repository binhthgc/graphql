package org.graphql.api.controller.cud;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class CreateDictItemMutation implements DataFetcher<DictItem> {

	@Override
	public DictItem get(DataFetchingEnvironment dataFetchingEnvironment) {

		System.out.println("MutationWiring.CreateDictItem()");
		
		String itemCode = dataFetchingEnvironment.getArgument("itemCode");

		System.out.println("CreateDictItem.get(itemCode)" + itemCode);

		DictItem results = DictItemLocalServiceUtil.fetchDictItem(4);

		return results;

	}
}
