package appraamlabs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appraamlabs.models.Configuration;
import appraamlabs.models.configuration.ConfigurationStatus;
import appraamlabs.repositories.ConfigurationRepository;
import appraamlabs.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Override
	public void delete(Configuration configuration) {
		// TODO Auto-generated method stub
		configurationRepository.delete(configuration);
	}

	@Override
	public List<Configuration> findAll() {
		// TODO Auto-generated method stub
		return configurationRepository.findAll();
	}

	@Override
	public Optional<Configuration> findOneById(String id) {
		// TODO Auto-generated method stub
		return configurationRepository.findOneById(id);
	}

	@Override
	public Configuration save(Configuration configuration) {
		// TODO Auto-generated method stub
		return configurationRepository.save(configuration);
	}

	@Override
	public List<ConfigurationStatus> getAllConfigurationStatus() {
		// TODO Auto-generated method stub
		List<Configuration> configurations = configurationRepository.findAll();
		List<ConfigurationStatus> configurationStatus = configurations.get(0).getConfigurationStatus();
		return configurationStatus;
	}

}
