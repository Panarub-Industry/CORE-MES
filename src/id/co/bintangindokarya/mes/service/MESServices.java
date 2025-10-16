package id.co.bintangindokarya.mes.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.Env;


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
	    
	    public static List<StockData> getOutstandingPOSOList(
	    		Properties ctx,
	            String productCategory,
	            String mesTransId, 
	            String slocReceiving  
	    ) {
	        List<StockData> results = new ArrayList<>();

	        String sql = 
	            "SELECT " +
	            " 	salesorder.salesordernumber, " +
	            " 	salesorder.poreference, " +
	            " 	SUM(COALESCE(prodorder.qtyordered, 0)) AS qtyordered, " +
	            " 	SUM(COALESCE(prodoutputdetail.qtyentered, 0)) AS qtyentered, " +
	            " 	SUM(COALESCE(prodorder.qtyordered, 0)) - SUM(COALESCE(prodoutputdetail.qtyentered, 0)) AS qtyoutstanding, " +
	            " 	MAX(prodoutputdetail.created) AS max_created " +
	            "FROM sap_prodorder prodorder " +
	            "INNER JOIN m_product product ON prodorder.m_product_id = product.m_product_id " +
	            "INNER JOIN m_product_category productcategory ON product.m_product_category_id = productcategory.m_product_category_id " +
	            "INNER JOIN sap_salesorder salesorder ON prodorder.sap_salesorder_id = salesorder.sap_salesorder_id " +
	            "LEFT JOIN mes_prodoutputdetail prodoutputdetail ON prodorder.ordernumber = prodoutputdetail.ordernumber " +
	            " AND prodorder.masterproductionorder = prodoutputdetail.masterproductionorder " +
	            " AND prodorder.ad_client_id = prodoutputdetail.ad_client_id " +
	            "LEFT JOIN mes_prodoutput prodoutput ON prodoutputdetail.mes_prodoutput_id = prodoutput.mes_prodoutput_id " +
	            " AND prodoutput.mes_trans_id = CASE WHEN ? = '' THEN prodoutput.mes_trans_id ELSE ? END " +
	            "WHERE prodorder.ad_org_id = ? " +
	            " AND prodorder.ad_client_id = ? " +
	            " AND productcategory.value = CASE WHEN ? = '' THEN productcategory.value ELSE ? END " +
	            " AND prodorder.slocreceiving = CASE WHEN ? = '' THEN prodorder.slocreceiving ELSE ? END " +
	            "GROUP BY salesorder.salesordernumber, salesorder.poreference " +
	            "HAVING (SUM(COALESCE(prodorder.qtyordered, 0)) - SUM(COALESCE(prodoutputdetail.qtyentered, 0))) > 0 " +
	            "ORDER BY max_created DESC";

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            pstmt = DB.prepareStatement(sql, null);
	            int idx = 1;
	            
	            pstmt.setString(idx++, mesTransId);
	            pstmt.setString(idx++, mesTransId);
	            pstmt.setInt(idx++, Env.getAD_Org_ID(ctx));
	            pstmt.setInt(idx++, Env.getAD_Client_ID(ctx));
	            pstmt.setString(idx++, productCategory);
	            pstmt.setString(idx++, productCategory);
	            pstmt.setString(idx++, slocReceiving);
	            pstmt.setString(idx++, slocReceiving);

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                StockData data = new StockData();
	                data.salesOrder = rs.getString("salesordernumber");
	                data.poReference = rs.getString("poreference");
	                data.qtyOrdered = rs.getBigDecimal("qtyordered");
	                data.qtyEntered = rs.getBigDecimal("qtyentered");
	                data.qtyOutstanding = rs.getBigDecimal("qtyoutstanding");

	                results.add(data);
	            }
	        } catch (SQLException e) {
	            log.log(Level.SEVERE, "Error fetching stock data", e);
	            throw new DBException(e, sql);
	        } finally {
	            DB.close(rs, pstmt);
	        }

	        return results;
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
	

	public class ProductionConfirmationFetcher {

	    public static class ProductionConfirmationData {
	        public String aufnr;
	        public String vornr;
	        public Date budat;
	        public String werks;
	        public BigDecimal lmnga;
	        public String gmein;
	        public String ile01;
	        public BigDecimal ism01;
	        public String ile02;
	        public BigDecimal ism02;
	        public String ile03;
	        public BigDecimal ism03;
	        public String ile04;
	        public BigDecimal ism04;
	        public String ile05;
	        public BigDecimal ism05;
	        public String ile06;
	        public BigDecimal ism06;
	        public Date isdd;
	        public Time isdz;
	        public Date iedd;
	        public Time iedz;
	        public String ltxa1;
	        public int id;
	    }

	    public static List<ProductionConfirmationData> getProductionConfirmations(int adOrgId, int adClientId, int createdBy,String documentNo) {
	        List<ProductionConfirmationData> list = new ArrayList<>();

	        String sql = """
	            SELECT
	                LPAD(mes_prodoutputdetail.ordernumber, 12, '0') AS aufnr,
	                LPAD(sap_operation.operationphasenumber, 4, '0') AS vornr,
	                CURRENT_DATE AS budat,
	                '1101' AS werks,
	                sap_prodorder.qtyordered AS lmnga,
	                'PAA' AS gmein,
	                'H' AS ile01,
	                ROUND((1 / sap_operation.basequantity)::NUMERIC, 3) AS ism01,
	                'H' AS ile02,
	                ROUND((1 / sap_operation.basequantity)::NUMERIC, 3) AS ism02,
	                'H' AS ile03,
	                ROUND((1 / sap_operation.basequantity)::NUMERIC, 3) AS ism03,
	                'AU' AS ile04,
	                ROUND(sap_prodorder.qtyordered::NUMERIC, 3) AS ism04,
	                'H' AS ile05,
	                0 AS ism05,
	                'H' AS ile06,
	                0 AS ism06,
	                CURRENT_DATE AS isdd,
	                CURRENT_TIME AS isdz,
	                CURRENT_DATE AS iedd,
	                CURRENT_TIME AS iedz,
	                mes_prodoutputdetail.barcode AS ltxa1,
	                mes_prodoutputdetail.mes_prodoutputdetail_id AS id
	            FROM
	                mes_prodoutput
	            INNER JOIN
	                mes_prodoutputdetail ON mes_prodoutput.mes_prodoutput_id = mes_prodoutputdetail.mes_prodoutput_id
	            LEFT JOIN
	                sap_prodorder ON mes_prodoutputdetail.ordernumber = sap_prodorder.ordernumber
	            INNER JOIN
	                sap_operation ON sap_prodorder.sap_prodorder_id = sap_operation.sap_prodorder_id
	            WHERE
	                mes_prodoutput.ad_org_id = ?
	                AND mes_prodoutput.ad_client_id = ?
	                ADN mes_prodoutput.documentno = ?
	                AND mes_prodoutput.mes_trans_id = 'MSA'
	                AND mes_prodoutput.docstatus = 'DR'
	                AND mes_prodoutput.isactive = 'Y'
	                AND mes_prodoutputdetail.isactive = 'Y'
	                AND mes_prodoutputdetail.createdby = ?
	                AND (mes_prodoutputdetail.poststatus = 'N' OR mes_prodoutputdetail.postno IS NULL)
	            """;

	        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
	            pstmt.setInt(1, adOrgId);
	            pstmt.setInt(2, adClientId);
	            pstmt.setString(3, documentNo);
	            pstmt.setInt(4, createdBy);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    ProductionConfirmationData data = new ProductionConfirmationData();
	                    data.aufnr = rs.getString("aufnr");
	                    data.vornr = rs.getString("vornr");
	                    data.budat = rs.getDate("budat");
	                    data.werks = rs.getString("werks");
	                    data.lmnga = rs.getBigDecimal("lmnga");
	                    data.gmein = rs.getString("gmein");
	                    data.ile01 = rs.getString("ile01");
	                    data.ism01 = rs.getBigDecimal("ism01");
	                    data.ile02 = rs.getString("ile02");
	                    data.ism02 = rs.getBigDecimal("ism02");
	                    data.ile03 = rs.getString("ile03");
	                    data.ism03 = rs.getBigDecimal("ism03");
	                    data.ile04 = rs.getString("ile04");
	                    data.ism04 = rs.getBigDecimal("ism04");
	                    data.ile05 = rs.getString("ile05");
	                    data.ism05 = rs.getBigDecimal("ism05");
	                    data.ile06 = rs.getString("ile06");
	                    data.ism06 = rs.getBigDecimal("ism06");
	                    data.isdd = rs.getDate("isdd");
	                    data.isdz = rs.getTime("isdz");
	                    data.iedd = rs.getDate("iedd");
	                    data.iedz = rs.getTime("iedz");
	                    data.ltxa1 = rs.getString("ltxa1");
	                    data.id = rs.getInt("id");

	                    list.add(data);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	}

	public class ProductionReport {
		
		public static class ProductionReportData {
			public String poNumber;
			public int total;
			public int[] hourlyData;
		    
		    public ProductionReportData() {
		        this.hourlyData = new int[8];
		    }
		    
		    public ProductionReportData(String poNumber, int total, int[] hourlyData) {
		        this.poNumber = poNumber;
		        this.total = total;
		        this.hourlyData = hourlyData;
		    }
		}
		
		public List<ProductionReportData> getReportDataFromDatabase(Properties ctx, String transID,int craetedby) {
	        List<ProductionReportData> reportData = new ArrayList<>();

	        String sql = """
	            SELECT 
	                mpd.poreference,
	                COUNT(*) as total,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 8 THEN 1 ELSE 0 END) as hour1,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 9 THEN 1 ELSE 0 END) as hour2,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 10 THEN 1 ELSE 0 END) as hour3,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 11 THEN 1 ELSE 0 END) as hour4,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 12 THEN 1 ELSE 0 END) as hour5,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 13 THEN 1 ELSE 0 END) as hour6,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 14 THEN 1 ELSE 0 END) as hour7,
	                SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 15 THEN 1 ELSE 0 END) as hour8
	            FROM mes_prodoutput mp
	            JOIN mes_prodoutputdetail mpd ON mp.mes_prodoutput_id = mpd.mes_prodoutput_id
	            WHERE mp.ad_client_id = ?
	            AND mp.mes_trans_id = ?
	            AND DATE(mpd.created) = CURRENT_DATE
	            GROUP BY mpd.poreference
	            ORDER BY mpd.poreference
	            """;

	        try (
	            PreparedStatement pstmt = DB.prepareStatement(sql, null);
	        ) {
	            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
	            pstmt.setString(2, transID);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    ProductionReportData data = new ProductionReportData();
	                    data.poNumber = rs.getString("poreference");
	                    data.total = rs.getInt("total");
	                    data.hourlyData = new int[]{
	                        rs.getInt("hour1"), rs.getInt("hour2"), rs.getInt("hour3"), rs.getInt("hour4"),
	                        rs.getInt("hour5"), rs.getInt("hour6"), rs.getInt("hour7"), rs.getInt("hour8")
	                    };
	                    reportData.add(data);
	                }
	            }

	        } catch (SQLException e) {
	            log.log(Level.SEVERE, "Database error getting report data", e);
	        }

	        return reportData;
	    }
		
	}
	
	
}
