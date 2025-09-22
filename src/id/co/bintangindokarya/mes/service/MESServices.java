package id.co.bintangindokarya.mes.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;

import id.co.bintangindokarya.mes.model.MMES_ProdOutputDetail;

public class MESServices {
	
	private static final Logger log = Logger.getLogger(MESServices.class.getName());
	
	    
	
	public static class Barcode {
		
		public static class BarcodeData {
	        public String barcodeTypeValue;
	        public String barcodeTypeName;
	        public String barcode;
	        public String sizeCustomer;
	        public String sizeFactory;
	        public BigDecimal qtyBarcode;
	    }
		
		/**
	     * Cek apakah barcode valid
	     */
	    public static boolean checkBarcode(Properties ctx, Map<String, Object> filters) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT 1 FROM mes_barcode barcodeheader ")
	           .append("INNER JOIN mes_barcodedetail barcodedetail ON barcodeheader.mes_barcode_id = barcodedetail.mes_barcode_id ")
	           .append("INNER JOIN mes_barcodetype barcodetype ON barcodeheader.mes_barcodetype_id = barcodetype.mes_barcodetype_id ")
	           .append("WHERE barcodedetail.isactive ='Y' ");

	        List<Object> params = new ArrayList<>();
	        if (filters != null) {
	            filters.forEach((key, value) -> {
	                if (value != null && (key.startsWith("barcodetype.") || key.startsWith("barcodedetail.") || key.startsWith("barcodeheader."))) {
	                    sql.append(" AND ").append(key).append(" = ?");
	                    params.add(value);
	                }
	            });
	        }

	        Integer result = DB.getSQLValueEx(null, sql.toString(), params.toArray());
	        return result != null && result > 0;
	    }

	    /**
	     * Ambil detail barcode lengkap (single record)
	     */
	    public static BarcodeData getBarcodeData(Properties ctx, Map<String, Object> filters) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT barcodetype.value AS barcodetypevalue, ")
	           .append("barcodetype.name AS barcodetypename, ")
	           .append("barcodedetail.barcode, ")
	           .append("barcodedetail.sizecustomer, ")
	           .append("barcodedetail.sizefactory, ")
	           .append("barcodedetail.qtybarcode ")
	           .append("FROM mes_barcode barcodeheader ")
	           .append("INNER JOIN mes_barcodedetail barcodedetail ON barcodeheader.mes_barcode_id = barcodedetail.mes_barcode_id ")
	           .append("INNER JOIN mes_barcodetype barcodetype ON barcodeheader.mes_barcodetype_id = barcodetype.mes_barcodetype_id ")
	           .append("WHERE barcodedetail.isactive = 'Y' ");

	        List<Object> params = new ArrayList<>();
	        if (filters != null) {
	            filters.forEach((key, value) -> {
	                if (value != null && (key.startsWith("barcodetype.") || key.startsWith("barcodedetail.") || key.startsWith("barcodeheader."))) {
	                    sql.append(" AND ").append(key).append(" = ?");
	                    params.add(value);
	                }
	            });
	        }

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = DB.prepareStatement(sql.toString(), null);
	            DB.setParameters(pstmt, params.toArray());
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                BarcodeData data = new BarcodeData();
	                data.barcodeTypeValue = rs.getString("barcodetypevalue");
	                data.barcodeTypeName = rs.getString("barcodetypename");
	                data.barcode = rs.getString("barcode");
	                data.sizeCustomer = rs.getString("sizecustomer");
	                data.sizeFactory = rs.getString("sizefactory");
	                data.qtyBarcode = rs.getBigDecimal("qtybarcode");
	                return data;
	            }
	            return null;

	        } catch (SQLException e) {
	            throw new DBException(e, sql.toString());
	        } finally {
	            DB.close(rs, pstmt);
	        }
	
	    }
	}
	
	
	public static class Stock {
		
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
		
	}
	
	
	public static class ProductionStatistic {
        
        public static class ProductionStatisticData {
            public int workHour;
            public BigDecimal totalPerHour;
            public BigDecimal totalToday;
            
            public ProductionStatisticData() {
                this.workHour = 0;
                this.totalPerHour = BigDecimal.ZERO;
                this.totalToday = BigDecimal.ZERO;
            }
            
            public ProductionStatisticData(int workHour, BigDecimal totalPerHour, BigDecimal totalToday) {
                this.workHour = workHour;
                this.totalPerHour = totalPerHour != null ? totalPerHour : BigDecimal.ZERO;
                this.totalToday = totalToday != null ? totalToday : BigDecimal.ZERO;
            }
        }
        
        public static ProductionStatisticData getProductionStatistic(Properties ctx, int clientId, int createdBy, String transId) {
        	String sql = "SELECT " +
                    "    EXTRACT(HOUR FROM CURRENT_TIMESTAMP) AS workhour, " +
                    "    COALESCE(SUM(CASE " +
                    "        WHEN mpod.created >= (CURRENT_TIMESTAMP - INTERVAL '1 hour') " +
                    "        THEN mpod.qtyentered ELSE 0 END), 0) AS total_perhour, " +
                    "    COALESCE(SUM(CASE " +
                    "        WHEN mpod.created >= (CURRENT_DATE + INTERVAL '7 hours') " +
                    "         AND mpod.created <= CURRENT_TIMESTAMP " +
                    "        THEN mpod.qtyentered ELSE 0 END), 0) AS total_today " +
                    "FROM mes_prodoutput mpo " +
                    "INNER JOIN mes_prodoutputdetail mpod ON mpo.mes_prodoutput_id = mpod.mes_prodoutput_id " +
                    "WHERE mpo.ad_client_id = ? " +
                    "  AND mpo.isactive = 'Y' " +
                    "  AND mpod.isactive = 'Y' " +
                    "  AND mpo.createdby = ? " +
                    "  AND mpo.mes_trans_id = ? ";


            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                pstmt = DB.prepareStatement(sql, null);
                pstmt.setInt(1, clientId);
                pstmt.setInt(2, createdBy);
                pstmt.setString(3, transId);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    return new ProductionStatisticData(
                        rs.getInt("workhour"),
                        rs.getBigDecimal("total_perhour"),
                        rs.getBigDecimal("total_today")
                    );
                }
                
                // Return default instance jika tidak ada data
                return new ProductionStatisticData();

            } catch (SQLException e) {
                throw new DBException(e, sql);
            } finally {
                DB.close(rs, pstmt);
            }
        }
    }
	
	
	
	
}
