package novare.com.hk.services;

import java.util.List;

import novare.com.hk.dao.AllocationDao;
import novare.com.hk.model.Allocation;
import novare.com.hk.repository.AllocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("allocationService")
public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Transactional
	public void insertData(Allocation allocation) {
		allocationRepository.save(allocation);
	
	}

	@Transactional
	public List<Allocation> getAllocationList() {
		return allocationRepository.findAll();
	}

	@Transactional
	public void updateData(Allocation allocation) {
		allocationRepository.save(allocation);
	}

	@Transactional
	public void deleteData(int id) {
		allocationRepository.delete(id);
	}

	@Transactional
	public Allocation getAllocation(int id) {
		return allocationRepository.findOne(id);
	}
	
	@Transactional
	public List<Allocation> getViewAlloc() {
		return allocationRepository.findAll();
	}
	
	@Transactional
	public List<Allocation> filterAllocation(String project_name){
		return null;
	}
	
	@Transactional
	public List<Allocation> searchAllocation(String searchquery){
		return null;
	}
}
