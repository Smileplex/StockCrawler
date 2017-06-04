package hibernate.dao;

import hibernate.model.StockDetailKeyword;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by DongwooSeo on 6/4/2017.
 */
public class StockDetailKeywordDaoImpl extends AbstractDao<Integer, StockDetailKeyword> implements StockDetailKeywordDao {

    @Override
    public int finyByStockKeywordId(int stockKeywordId) {
        return 0;
    }

    @Override
    public int save(int stockDetailId, int stockKeywordId) {
        // TODO Auto-generated method stub
        int stockDetailKeywordId = 0;
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(StockDetailKeyword.class);
            crit.add(Restrictions.eq("stockKeywordId", stockKeywordId));
            StockDetailKeyword entityStockDetailKeyword = (StockDetailKeyword) crit.setMaxResults(1).uniqueResult();
            if (entityStockDetailKeyword == null) {
                entityStockDetailKeyword = new StockDetailKeyword();
                entityStockDetailKeyword.setStockDetailId(stockDetailId);
                entityStockDetailKeyword.setStockKeywordId(stockKeywordId);
                stockDetailKeywordId = (Integer) session.save(entityStockDetailKeyword);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return stockDetailKeywordId;
    }
}
