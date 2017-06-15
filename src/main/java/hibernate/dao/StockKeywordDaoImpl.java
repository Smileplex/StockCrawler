package hibernate.dao;

import com.google.inject.Singleton;
import hibernate.model.KeywordLinkQueue;
import hibernate.model.StockKeyword;
import models.KeywordInfo;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class StockKeywordDaoImpl extends AbstractDao<Integer, StockKeyword> implements StockKeywordDao {
		private static final Logger logger = Logger.getLogger(StockKeywordDaoImpl.class.getName());
	public StockKeyword fetchFirstRow() {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		
		StockKeyword stockKeyword = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(
					"from StockKeyword a where status = 1 or (DateUpdated < :time1 and status = 3) order by DateUpdated asc");
			query.setParameter("time1", new Timestamp(new Date().getTime() - 10 * 60 * 1000));
//			query.setLockMode("a", LockMode.PESSIMISTIC_WRITE);
			query.setMaxResults(1);
			List<StockKeyword> result = query.list();
			if (!result.isEmpty()) {
				stockKeyword = result.get(0);
				stockKeyword.setStatus(2);
				stockKeyword.setDateUpdated(new Timestamp(new Date().getTime()));
				session.update(stockKeyword);
			}else{
				stockKeyword = new EmptyStockKeyword();
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		return stockKeyword;
	}
	public int save(KeywordInfo keywordInfo, KeywordLinkQueue keywordLinkQueue, int keywordMainId) {
		int stockKeywordId = 0;

		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(StockKeyword.class);
			crit.add(Restrictions.eq("keywordMainId", keywordMainId));
			crit.add(Restrictions.eq("agentId", keywordLinkQueue.getAgentId()));

			StockKeyword entityKeyword = (StockKeyword) crit.setMaxResults(1).uniqueResult();

			if (entityKeyword == null) {
				entityKeyword = new StockKeyword();
				entityKeyword.setKeywordMainId(keywordMainId);
				entityKeyword.setAgentId(keywordLinkQueue.getAgentId());
				entityKeyword.setName(keywordInfo.getKeywordName());
				entityKeyword.setLink(keywordLinkQueue.getLink());
				entityKeyword.setDateCreated(new Timestamp(new Date().getTime()));
				entityKeyword.setDateUpdated(new Timestamp(new Date().getTime()));
				entityKeyword.setTypeId(keywordInfo.getKeywordType());
				entityKeyword.setStatus(1);
				if (!entityKeyword.getName().isEmpty()) {
					stockKeywordId = (Integer) session.save(entityKeyword);
				}
			} else {
				stockKeywordId = entityKeyword.getId();
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			logger.warning(String.format("Duplicated StockKeywordId at %d will rollback",stockKeywordId));
		}

		return stockKeywordId;
	}
	@Override
	public void update(StockKeyword entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}
	public boolean checkIfExist(String keywordName) {
		// TODO Auto-generated method stub
		boolean isExist = false;
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(StockKeyword.class);
			crit.add(Restrictions.eqOrIsNull("name", keywordName));
			StockKeyword entityKeyword = (StockKeyword) crit.uniqueResult();
			if (entityKeyword != null)
				isExist = true;
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		return isExist;
	}

}
