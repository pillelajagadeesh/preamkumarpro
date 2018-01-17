package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.Configuration;
import appraamlabs.models.configuration.ConfigurationStatus;

public interface ConfigurationService {

	public void delete(Configuration configuration);
	
    public List<Configuration> findAll();
 
    public Optional<Configuration> findOneById(String id);

 	public Configuration save(Configuration configuration);
 	
 	public List<ConfigurationStatus> getAllConfigurationStatus();
 	 	
}
