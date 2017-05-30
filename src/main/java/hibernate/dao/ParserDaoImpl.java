package hibernate.dao;

import hibernate.model.Parsers;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ParserDaoImpl extends AbstractDao<Integer, Parsers> implements ParserDao{
	@SuppressWarnings("unchecked")
	public List<Parsers> findByAgentId(int agentId) {
		// TODO Auto-generated method stub
		Transaction tx = getSession().beginTransaction();
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("agentId", agentId));
		List<Parsers> parsers = (List<Parsers>)crit.list();
		tx.commit();
		return parsers;
	}
}
