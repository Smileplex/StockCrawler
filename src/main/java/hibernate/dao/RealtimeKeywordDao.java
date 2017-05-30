package hibernate.dao;


import hibernate.model.RealtimeKeyword;

import java.util.List;

public interface RealtimeKeywordDao {
	RealtimeKeyword findById(int id);
	RealtimeKeyword findByName(String name);
	RealtimeKeyword findByNameAndAgentId(String name, int agentId);
	void save(RealtimeKeyword realtimeKeyword);
	void saveAll(List<RealtimeKeyword> realtimeKeywords);
	void saveOrUpdate(RealtimeKeyword realtimeKeyword);
	void update(RealtimeKeyword realtimeKeyword);
	void deleteAllByAgentId(int agentId);
	
}
