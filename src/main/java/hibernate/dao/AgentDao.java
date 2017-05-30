package hibernate.dao;


import hibernate.model.Agent;

public interface AgentDao {
	Agent findById(int id);
	Agent findByName(String name);
	Agent fetchFirstRow();
	void update(Agent agent);
}
