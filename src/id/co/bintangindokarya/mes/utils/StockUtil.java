package id.co.bintangindokarya.mes.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class StockUtil {

    private static final CLogger log = CLogger.getCLogger(StockUtil.class);

    /**
     * Cek qty outstanding secara dinamis.
     * Semua filter ditaruh di parameter `filters` dengan format: alias.column = value
     *
     * Contoh filter:
     * filters.put("mpc.value", "ZFG");
     * filters.put("mpo.mes_trans_id", "MSA");
     * filters.put("ss.poreference", "901012206");
     */
	public static BigDecimal checkStock(Properties ctx, Map<String, Object> filters) {
	    // UPDATED: Using string concatenation instead of StringBuilder
	    String sql = "SELECT (COALESCE(prodorder.qtyordered, 0) - COALESCE(SUM(prodoutputdetail.qtyentered), 0)) AS qtyoutstanding"
	            + " FROM sap_prodorder prodorder"
	            + " INNER JOIN m_product product ON prodorder.m_product_id = product.m_product_id"
	            + " INNER JOIN m_product_category productcategory ON product.m_product_category_id = productcategory.m_product_category_id"
	            + " INNER JOIN sap_salesorder salesorder ON prodorder.sap_salesorder_id = salesorder.sap_salesorder_id"
	            + " LEFT JOIN mes_prodoutputdetail prodoutputdetail"
	            + "     ON prodorder.ordernumber = prodoutputdetail.ordernumber"
	            + "    AND prodorder.masterproductionorder = prodoutputdetail.masterproductionorder"
	            + "    AND prodorder.ad_client_id = prodoutputdetail.ad_client_id"
	            + " LEFT JOIN mes_prodoutput prodoutput ON prodoutputdetail.mes_prodoutput_id = prodoutput.mes_prodoutput_id"
	            + " WHERE 1=1";

	    List<Object> params = new ArrayList<>();
	    StringBuilder whereClause = new StringBuilder();

	    // Apply dynamic filters
	    if (filters != null) {
	        filters.forEach((key, value) -> {
	            if (value != null) {
	                // Filter for all tables with descriptive aliases
	                if (key.startsWith("prodorder.") || key.startsWith("product.") || key.startsWith("productcategory.") || 
	                    key.startsWith("salesorder.") || key.startsWith("prodoutputdetail.") || key.startsWith("prodoutput.")) {
	                    whereClause.append(" AND ").append(key).append(" = ?");
	                    params.add(value);
	                }
	            }
	        });
	    }

	    // Complete the SQL with GROUP BY, HAVING, ORDER BY, and LIMIT
	    sql += whereClause.toString()
	        + " GROUP BY salesorder.poreference, prodorder.ordernumber, prodorder.masterproductionorder,"
	        + "          product.genericvalue, product.sizefactory, prodorder.qtyordered"
	        + " HAVING (COALESCE(prodorder.qtyordered, 0) - COALESCE(SUM(prodoutputdetail.qtyentered), 0)) > 0"
	        + " ORDER BY prodorder.masterproductionorder ASC"
	        + " LIMIT 1";

	    BigDecimal result = DB.getSQLValueBDEx(null, sql, params.toArray());
	    return result != null ? result : BigDecimal.ZERO;
	}


	/**
     * NEW METHOD: Get stock data dengan semua field yang dibutuhkan untuk MMES_ProdOutputDetail
     * 
     * @param ctx Properties context
     * @param filters Map filter dengan format alias.column = value
     * @return StockData object dengan semua field atau null jika tidak ditemukan
     */
    public static StockData getStockData(Properties ctx, Map<String, Object> filters) {
        String sql = "SELECT "
        		+ "	   salesorder.salesordernumber, "		
                + "    salesorder.poreference, "
                + "    prodorder.ordernumber, "
                + "    prodorder.masterproductionorder, "
                + "    product.genericvalue, "
                + "    product.sizefactory, "
                + "    prodorder.qtyordered, "
                + "    COALESCE(SUM(prodoutputdetail.qtyentered), 0) AS qtyentered, "
                + "    (COALESCE(prodorder.qtyordered, 0) - COALESCE(SUM(prodoutputdetail.qtyentered), 0)) AS qtyoutstanding "
                + "FROM sap_prodorder prodorder "
                + "INNER JOIN m_product product ON prodorder.m_product_id = product.m_product_id "
                + "INNER JOIN m_product_category productcategory ON product.m_product_category_id = productcategory.m_product_category_id "
                + "INNER JOIN sap_salesorder salesorder ON prodorder.sap_salesorder_id = salesorder.sap_salesorder_id "
                + "LEFT JOIN mes_prodoutputdetail prodoutputdetail "
                + "    ON prodorder.ordernumber = prodoutputdetail.ordernumber "
                + "   AND prodorder.masterproductionorder = prodoutputdetail.masterproductionorder "
                + "   AND prodorder.ad_client_id = prodoutputdetail.ad_client_id "
                + "LEFT JOIN mes_prodoutput prodoutput ON prodoutputdetail.mes_prodoutput_id = prodoutput.mes_prodoutput_id "
                + "WHERE 1=1";

        List<Object> params = new ArrayList<>();
        StringBuilder whereClause = new StringBuilder();

        // Apply dynamic filters
        if (filters != null) {
            filters.forEach((key, value) -> {
                if (value != null) {
                    // Filter for all tables with descriptive aliases
                    if (key.startsWith("prodorder.") || key.startsWith("product.") || key.startsWith("productcategory.") || 
                        key.startsWith("salesorder.") || key.startsWith("prodoutputdetail.") || key.startsWith("prodoutput.")) {
                        whereClause.append(" AND ").append(key).append(" = ?");
                        params.add(value);
                    }
                }
            });
        }

        // Complete the SQL with GROUP BY, HAVING, ORDER BY, and LIMIT
        sql += whereClause.toString()
            + " GROUP BY salesorder.salesordernumber, salesorder.poreference, prodorder.ordernumber, prodorder.masterproductionorder, "
            + "          product.genericvalue, product.sizefactory, prodorder.qtyordered "
            + " HAVING (COALESCE(prodorder.qtyordered, 0) - COALESCE(SUM(prodoutputdetail.qtyentered), 0)) > 0 "
            + " ORDER BY prodorder.masterproductionorder ASC "
            + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql, null);
            DB.setParameters(pstmt, params.toArray());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                StockData stockData = new StockData();
                stockData.salesOrder = rs.getString("salesordernumber");
                stockData.poReference = rs.getString("poreference");
                stockData.orderNumber = rs.getString("ordernumber");
                stockData.masterProductionOrder = rs.getString("masterproductionorder");
                stockData.article = rs.getString("genericvalue");
                stockData.sizeFactory = rs.getString("sizefactory");
                stockData.qtyOrdered = rs.getBigDecimal("qtyordered");
                stockData.qtyEntered = rs.getBigDecimal("qtyentered");
                stockData.qtyOutstanding = rs.getBigDecimal("qtyoutstanding");

                log.info("Stock data found: " + stockData.toString());
                return stockData;
            } else {
                log.info("No stock data found with filters: " + filters);
                return null;
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database error in getStockData", e);
            throw new DBException(e, sql);
        } finally {
            DB.close(rs, pstmt);
        }
    }
    
    /**
     * Simple data class untuk hasil query stock
     */
    public static class StockData {
    	public String salesOrder;
        public String poReference;           
        public String orderNumber;           
        public String masterProductionOrder;
        public String article;
        public String sizeFactory;          
        public BigDecimal qtyOrdered;       
        public BigDecimal qtyEntered;        
        public BigDecimal qtyOutstanding;   
    }
}

