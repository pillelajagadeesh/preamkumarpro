package appraamlabs.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

import appraamlabs.models.MineModel;


@Component
public interface MineRepository extends MongoRepository<MineModel, Long>{

	public List<MineModel> findAll();
	
}
