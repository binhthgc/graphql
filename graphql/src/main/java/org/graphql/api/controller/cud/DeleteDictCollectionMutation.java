package org.graphql.api.controller.cud;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class DeleteDictCollectionMutation implements DataFetcher<DictCollection> {

	@Override
	public DictCollection get(DataFetchingEnvironment dataFetchingEnvironment) {

		System.out.println("MutationWiring.deleteDictCollection()");
		
		Long dictCollectionId = dataFetchingEnvironment.getArgument("dictCollectionId");

		System.out.println("deleteDictCollection.get(dictCollectionId)" + dictCollectionId);

		DictCollection results = DictCollectionLocalServiceUtil.fetchDictCollection(dictCollectionId);

		return results;

	}
}
