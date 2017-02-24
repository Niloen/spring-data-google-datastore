package com.niloen.spring.data.google.datastore.repository.configuration;

import com.niloen.spring.data.google.datastore.core.GoogleDatastoreKeyValueAdapter;
import com.niloen.spring.data.google.datastore.core.GoogleDatastoreTemplate;
import com.niloen.spring.data.google.datastore.core.mapping.GoogleDatastoreMappingContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.data.config.ParsingUtils;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.util.StringUtils;

/**
 * {@link RepositoryConfigurationExtension} for GoogleDatastore.
 *
 * @author Christoph Strobl
 * @since 1.7
 */
public class GoogleDatastoreRepositoryConfigurationExtension extends KeyValueRepositoryConfigurationExtension {

	private static final String GOOGLE_DATASTORE_ADAPTER_BEAN_NAME = "googleDatastoreKeyValueAdapter";


	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "googleDatastore";
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension#getModulePrefix()
	 */
	@Override
	protected String getModulePrefix() {
		return "googleDatastore";
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension#getDefaultKeyValueTemplateRef()
	 */
	@Override
	protected String getDefaultKeyValueTemplateRef() {
		return "googleDatastoreKeyValueTemplate";
	}

	@Override
	public void registerBeansForRoot(BeanDefinitionRegistry registry, RepositoryConfigurationSource configurationSource) {
		String googleDatastoreTemplateRef = configurationSource.getAttribute("googleDatastoreTemplateRef");

		RootBeanDefinition googleDatastoreTemplateDefinition = new RootBeanDefinition(GoogleDatastoreTemplate.class);
		registerIfNotAlreadyRegistered(googleDatastoreTemplateDefinition, registry, googleDatastoreTemplateRef, configurationSource);

		RootBeanDefinition mappingContextDefinition = createGoogleDatastoreMappingContext(configurationSource);
		mappingContextDefinition.setSource(configurationSource.getSource());

		registerIfNotAlreadyRegistered(mappingContextDefinition, registry, MAPPING_CONTEXT_BEAN_NAME, configurationSource);

		// register Adapter
		RootBeanDefinition googleDatastoreKeyValueAdapterDefinition = new RootBeanDefinition(GoogleDatastoreKeyValueAdapter.class);

		ConstructorArgumentValues constructorArgumentValuesForGoogleDatastoreKeyValueAdapter = new ConstructorArgumentValues();
		constructorArgumentValuesForGoogleDatastoreKeyValueAdapter.addIndexedArgumentValue(0,
				new RuntimeBeanReference(googleDatastoreTemplateRef));

		constructorArgumentValuesForGoogleDatastoreKeyValueAdapter.addIndexedArgumentValue(1,
				new RuntimeBeanReference(MAPPING_CONTEXT_BEAN_NAME));

		googleDatastoreKeyValueAdapterDefinition.setConstructorArgumentValues(constructorArgumentValuesForGoogleDatastoreKeyValueAdapter);

		registerIfNotAlreadyRegistered(googleDatastoreKeyValueAdapterDefinition, registry, GOOGLE_DATASTORE_ADAPTER_BEAN_NAME,
				configurationSource);


		super.registerBeansForRoot(registry, configurationSource);
	}


	private RootBeanDefinition createGoogleDatastoreMappingContext(RepositoryConfigurationSource configurationSource) {

		RootBeanDefinition mappingContextBeanDef = new RootBeanDefinition(GoogleDatastoreMappingContext.class);

		return mappingContextBeanDef;
	}

	@Override
	protected AbstractBeanDefinition getDefaultKeyValueTemplateBeanDefinition(
			RepositoryConfigurationSource configurationSource) {

		RootBeanDefinition keyValueTemplateDefinition = new RootBeanDefinition(KeyValueTemplate.class);

		ConstructorArgumentValues constructorArgumentValuesForKeyValueTemplate = new ConstructorArgumentValues();
		constructorArgumentValuesForKeyValueTemplate.addIndexedArgumentValue(0,
				new RuntimeBeanReference(GOOGLE_DATASTORE_ADAPTER_BEAN_NAME));
		constructorArgumentValuesForKeyValueTemplate.addIndexedArgumentValue(1,
				new RuntimeBeanReference(MAPPING_CONTEXT_BEAN_NAME));

		keyValueTemplateDefinition.setConstructorArgumentValues(constructorArgumentValuesForKeyValueTemplate);

		return keyValueTemplateDefinition;
	}
}
