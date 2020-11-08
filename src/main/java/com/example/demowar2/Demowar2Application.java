package com.example.demowar2;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Demowar2Application {


	@Value("classpath:scehma.graphql")
	Resource resource;

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {

		SpringApplication.run(Demowar2Application.class, args);
	}


	@Bean
	public GraphQL graphQL() throws IOException{
		File schemaFile = resource.getFile();
		SchemaParser schemaParser = new SchemaParser();
		ClassPathResource schema = new ClassPathResource("scehma.graphql");
		//ClassPathResource schema = new ClassPathResource("schema.graphql");
		//TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(String.valueOf(schema.getInputStream()));
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schemaFile);
		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBooks", bookService.getBooks()))


				.build();
		//.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("createBook", bookService.createBook()))
		//.type(TypeRuntimeWiring.newTypeWiring("Book").dataFetcher("author", authorService.authorDataFetcher()))


		SchemaGenerator generator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = generator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		return GraphQL.newGraphQL(graphQLSchema).build();
	}









}
