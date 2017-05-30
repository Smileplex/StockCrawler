package hibernate.dao;


import hibernate.model.Parsers;

import java.util.List;

public interface ParserDao {
	List<Parsers>findByAgentId(int agentId);

}
