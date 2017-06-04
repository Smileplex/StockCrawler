package hibernate.dao;

import com.google.inject.Singleton;
import hibernate.model.RelatedKeywordLink;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@Singleton
public class RelatedKeywordLinkDaoImpl extends AbstractDao<Integer, RelatedKeywordLink>
		implements RelatedKeywordLinkDao {


	public void save(int keywordId, int relatedId, int correl) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(RelatedKeywordLink.class);
			crit.add(Restrictions.eq("keywordId", keywordId));
			crit.add(Restrictions.eq("relatedId", relatedId));

			RelatedKeywordLink entityRelatedKeywordLink = (RelatedKeywordLink) crit.setMaxResults(1).uniqueResult();

			if (entityRelatedKeywordLink == null) {
				entityRelatedKeywordLink = new RelatedKeywordLink();
				entityRelatedKeywordLink.setKeywordId(keywordId);
				entityRelatedKeywordLink.setRelatedId(relatedId);
				entityRelatedKeywordLink.setCorrel(correl);
				session.save(entityRelatedKeywordLink);
			}else{
				entityRelatedKeywordLink.setCorrel(correl);
				session.update(entityRelatedKeywordLink);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}

}
