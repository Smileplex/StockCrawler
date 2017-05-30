package hibernate.dao;

import hibernate.model.Agent;
import org.hibernate.Query;
import org.hibernate.Transaction;

import java.util.List;

public class AgentDaoImpl extends AbstractDao<Integer, Agent> implements AgentDao {

	public Agent findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	public Agent findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}	
	
    @Override
    public void update(Agent entity) {
    	// TODO Auto-generated method stub
    	super.update(entity);
    }
	
	public Agent fetchFirstRow() {
		// TODO Auto-generated method stub
		Transaction tx = getSession().beginTransaction();
		Query query = getSession().createSQLQuery("select * from Agent where (Status = 1 or (DateFinished + INTERVAL Minutes MINUTE < now() and Status = 3)) and isUsed order by DateFinished").addEntity(Agent.class);
		
		List<Agent> result = query.list();
		Agent agent = null;
		
		if(!result.isEmpty()){
			agent = result.get(0);
			//System.out.println(agent.getName());	
		}
		
		tx.commit();
		//System.out.println(agent.getName());
		return agent;
	}
}
