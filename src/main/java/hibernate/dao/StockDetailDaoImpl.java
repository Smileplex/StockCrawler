package hibernate.dao;

import hibernate.model.StockDetail;
import models.StockInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;

public class StockDetailDaoImpl extends AbstractDao<Integer, StockDetail> implements StockDetailDao {

	@Override
	public int save(StockInfo stockInfo) {
		int stockDetailId = 0;
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(StockDetail.class);
			int stockCode = 0;
			try{
				stockCode = Integer.parseInt(stockInfo.getStockCode());
			}catch(NumberFormatException e){
				System.out.println("Can't convert StockCode from string to integer.");
				//e.printStackTrace();
				return 0;
			}finally{
				crit.add(Restrictions.eq("code", stockCode));
				StockDetail entityStockDetail = (StockDetail) crit.setMaxResults(1).uniqueResult();
				if (entityStockDetail == null) {
					StockDetail stockDetail = new StockDetail();
					stockDetail.setName(stockInfo.getStockName());
					stockDetail.setCode(stockCode);
					stockDetail.setPrice(stockInfo.getPriceLast());
					stockDetail.setPricePrev(stockInfo.getPrevClose());
					stockDetail.setPriceMax(stockInfo.getHighVal());
					stockDetail.setPriceMin(stockInfo.getLowVal());
					stockDetail.setFluct(stockInfo.getPriceChange());
					stockDetail.setFluctRate(stockInfo.getPriceChangeRate());
					stockDetail.setRiseFall(stockInfo.getRisefall());
					stockDetail.setChartDaily(stockInfo.getChartDailyUrl());
					stockDetail.setChartWeekly(stockInfo.getChartWeeklyUrl());
					stockDetail.setChartMonthly(stockInfo.getChartMonthlyUrl());
					stockDetail.setDateCreated(new Timestamp(new Date().getTime()));
					stockDetail.setDateUpdated(new Timestamp(new Date().getTime()));
					stockDetailId = (Integer)session.save(stockDetail);
				}else{
					entityStockDetail.setPrice(stockInfo.getPriceLast());
					entityStockDetail.setPricePrev(stockInfo.getPrevClose());
					entityStockDetail.setPriceMax(stockInfo.getHighVal());
					entityStockDetail.setPriceMin(stockInfo.getLowVal());
					entityStockDetail.setFluct(stockInfo.getPriceChange());
					entityStockDetail.setFluctRate(stockInfo.getPriceChangeRate());
					entityStockDetail.setRiseFall(stockInfo.getRisefall());
					entityStockDetail.setChartDaily(stockInfo.getChartDailyUrl());
					entityStockDetail.setChartWeekly(stockInfo.getChartWeeklyUrl());
					entityStockDetail.setChartMonthly(stockInfo.getChartMonthlyUrl());
					entityStockDetail.setDateUpdated(new Timestamp(new Date().getTime()));
					stockDetailId = entityStockDetail.getId();
					session.update(entityStockDetail);
				}
				// TODO Auto-generated method stub
				tx.commit();
			}
		} catch (Exception e) {
			tx.rollback();
			//e.printStackTrace();
		}
		return stockDetailId;
	}
}
