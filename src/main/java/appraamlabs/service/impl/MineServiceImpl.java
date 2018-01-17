package appraamlabs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.MineModel;
import appraamlabs.repositories.MineRepository;
import appraamlabs.service.MineService;


@Service
public class MineServiceImpl implements MineService {
	
	@Autowired
	private MineRepository mineRepository;

	@Override
	public List<MineModel> getAllMines() {
		// TODO Auto-generated method stub
		return mineRepository.findAll();
		
	}

}
