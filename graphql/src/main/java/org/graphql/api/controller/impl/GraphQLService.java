package org.graphql.api.controller.impl;

import com.liferay.petra.string.StringPool;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.graphql.api.controller.cud.CreateDictCollectionMutation;
import org.graphql.api.controller.cud.CreateDictCollectionMutationDD;
import org.graphql.api.controller.cud.CreateDictItemMutation;
import org.graphql.api.controller.cud.DeleteDictCollectionMutation;
import org.graphql.api.controller.cud.DeleteDictItemMutation;
import org.graphql.api.controller.fetcher.AllDictCollectionsFetcher;
import org.graphql.api.controller.fetcher.AllDictItemsByDictCollectionIdFetcher;
import org.graphql.api.controller.fetcher.ItemDictCollectionFetcher;
import org.graphql.api.controller.fetcher.ItemDictItemFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * Created binhth
 */
@Service
public class GraphQLService {

	@Autowired
	private AllDictCollectionsFetcher allDictCollectionsFetcher;
	@Autowired
	private ItemDictCollectionFetcher itemDictCollectionFetcher;
	@Autowired
	private AllDictItemsByDictCollectionIdFetcher allDictItemsByDictCollectionIdFetcher;
	@Autowired
	private ItemDictItemFetcher itemDictItemFetcher;
	
	private GraphQL graphQL;

	@Autowired
	private CreateDictCollectionMutation createDictCollection;
	@Autowired
	private CreateDictItemMutation createDictItem;
	@Autowired
	private DeleteDictCollectionMutation deleteDictCollection;
	@Autowired
	private DeleteDictItemMutation deleteDictItem;
	
	@Autowired
	
	@PostConstruct
	public void loadGraphQLSchema() throws IOException {

		String schema = StringPool.BLANK;

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			schema = IOUtils.toString(classLoader.getResourceAsStream("structs/dataitems.graphql"));
		} catch (IOException e) {
			schema = StringPool.BLANK;
		}

		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);
		RuntimeWiring runtimeWiring = initWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private RuntimeWiring initWiring() {

//		MutationWiring mutationWiring = new MutationWiring();
		
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",
						typeWiring -> typeWiring.dataFetcher("allDictCollections", allDictCollectionsFetcher)
								.dataFetcher("itemDictCollection", itemDictCollectionFetcher)
								.dataFetcher("allDictItemsByDictCollectionId", allDictItemsByDictCollectionIdFetcher)
								.dataFetcher("itemDictItem", itemDictItemFetcher)
								.dataFetcher("createDictCollectionTest", createDictCollection)
				)
				.type("Mutation", 
						typeWiring -> typeWiring.dataFetcher("createDictCollection", createDictCollection)
								.dataFetcher("createDictItem", createDictItem)
								.dataFetcher("deleteDictCollection", deleteDictCollection)
								.dataFetcher("deleteDictItem", deleteDictItem)
                )
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
