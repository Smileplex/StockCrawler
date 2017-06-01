package hibernate.dao;

import hibernate.model.Stock;
import models.StockInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;

public class StockDaoImpl extends AbstractDao<Integer, Stock> implements StockDao{

	@Override
	public void upsertStock(int keywordId, StockInfo stockInfo) {
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Stock.class);
			crit.add(Restrictions.eq("keywordId", keywordId));
			Stock entityStock = (Stock) crit.setMaxResults(1).uniqueResult();
			if (entityStock == null) {
				Stock stock = new Stock();
				stock.setKeywordId(keywordId);
				stock.setName(stockInfo.getStockName());
				try{
					stock.setCode(Integer.parseInt(stockInfo.getStockCode()));
				}catch(NumberFormatException e){
					//e.printStackTrace();
					System.out.println("StockCode에 문자포함("+stock.getName()+")");
				}
				stock.setPrice(stockInfo.getPriceLast());
				stock.setPricePrev(stockInfo.getPrevClose());
				stock.setPriceMax(stockInfo.getHighVal());
				stock.setPriceMin(stockInfo.getLowVal());
				stock.setFluct(stockInfo.getPriceChange());
				stock.setFluctRate(stockInfo.getPriceChangeRate());
				stock.setChartDaily(stockInfo.getChartDailyUrl());
				stock.setChartWeekly(stockInfo.getChartWeeklyUrl());
				stock.setChartMonthly(stockInfo.getChartMonthlyUrl());
				stock.setDateCreated(new Timestamp(new Date().getTime()));
				stock.setDateUpdated(new Timestamp(new Date().getTime()));
				session.save(stock);
			}else{
				entityStock.setPrice(stockInfo.getPriceLast());
				entityStock.setPricePrev(stockInfo.getPrevClose());
				entityStock.setPriceMax(stockInfo.getHighVal());
				entityStock.setPriceMin(stockInfo.getLowVal());
				entityStock.setFluct(stockInfo.getPriceChange());
				entityStock.setFluctRate(stockInfo.getPriceChangeRate());
				entityStock.setChartDaily(stockInfo.getChartDailyUrl());
				entityStock.setChartWeekly(stockInfo.getChartWeeklyUrl());
				entityStock.setChartMonthly(stockInfo.getChartMonthlyUrl());
				entityStock.setDateUpdated(new Timestamp(new Date().getTime()));
				session.save(entityStock);
			}
			// TODO Auto-generated method stub
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("rollback!!");
		}

	}
}
