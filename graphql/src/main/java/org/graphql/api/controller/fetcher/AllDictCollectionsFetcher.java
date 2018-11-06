package org.graphql.api.controller.fetcher;

import java.util.List;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class AllDictCollectionsFetcher implements DataFetcher<List<DictCollection>> {

    @Override
    public List<DictCollection> get(DataFetchingEnvironment dataFetchingEnvironment) {
    	
    	int start = dataFetchingEnvironment.getArgument("start");
    	int end = dataFetchingEnvironment.getArgument("end");
    	
    	System.out.println("AllDictCollectionsFetcher.get(start)" + start);
    	System.out.println("AllDictCollectionsFetcher.get(end)" + end);
    	
    	List<DictCollection> results = DictCollectionLocalServiceUtil.getDictCollections(start, end);
    	
        return results;
        
    }
}
