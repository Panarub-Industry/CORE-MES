package id.co.bintangindokarya.mes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

import id.co.bintangindokarya.erp.model.MSAP_WorkCenter;

public class MV_MES_ProductionInfo extends X_V_MES_ProductionInfo{

	private static final long serialVersionUID = -585923140539823593L;

	public MV_MES_ProductionInfo(Properties ctx, int V_MES_ProductionInfo_ID, String trxName) {
		super(ctx, V_MES_ProductionInfo_ID, trxName);
	}

	public MV_MES_ProductionInfo(Properties ctx, int V_MES_ProductionInfo_ID, String trxName, String[] virtualColumns) {
		super(ctx, V_MES_ProductionInfo_ID, trxName, virtualColumns);
	}

	public MV_MES_ProductionInfo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MV_MES_ProductionInfo(Properties ctx, String V_MES_ProductionInfo_UU, String trxName) {
		super(ctx, V_MES_ProductionInfo_UU, trxName);
	}

	public MV_MES_ProductionInfo(Properties ctx, String V_MES_ProductionInfo_UU, String trxName,
			String[] virtualColumns) {
		super(ctx, V_MES_ProductionInfo_UU, trxName, virtualColumns);
	}
	
	public static MV_MES_ProductionInfo findByPOAndSizeFactory(Properties ctx, String po, String sizeFactory, String transID, String trxName) {
	    try {
	        String whereClause = "AD_Org_ID=? AND AD_Client_ID=? AND productcategory=? AND poreference=? AND sizefactory=?";
	        
	        if ("MSS".equalsIgnoreCase(transID)) {
	            whereClause += " AND SUBSTR(workcenter, 1, 4) = 'CSAS'";
	        } else if ("MSA".equalsIgnoreCase(transID)) {
	            whereClause += " AND SUBSTR(workcenter, 1, 4) = 'CSAA'";
	        }

	        return new Query(ctx, Table_Name, whereClause, trxName)
	            .setParameters(
	                Env.getAD_Org_ID(ctx),
	                Env.getAD_Client_ID(ctx),
	                "ZFG",
	                po,
	                sizeFactory
	            )
	            .setOrderBy("masterproductionorder")
	            .firstOnly();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static MV_MES_ProductionInfo findByPOAndSizeFactory(Properties ctx, String po, String sizeFactory, String trxName) {
	    try {
	        return new Query(ctx, Table_Name,
	            "AD_Org_ID=? AND AD_Client_ID=? AND productcategory=? AND poreference=? AND sizefactory=?", trxName)
	            .setParameters(
	            		Env.getAD_Org_ID(ctx), 
	            		Env.getAD_Client_ID(ctx), 
	            		"ZFG", 
	            		po, 
	            		sizeFactory)
	            .setOrderBy("masterproductionorder")
	            .firstOnly();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return null;
	    }
	}

	public static List<String> getOutstandingPOSOList(Properties ctx, String productCategory, int slocReceiving, String transID, String trxName) {
	    try {
	    	
	        StringBuilder whereClause = new StringBuilder();
	        whereClause.append("AD_Org_ID=? ")
	                   .append("AND AD_Client_ID=? ")
	                   .append("AND productcategory=? ")
	                   .append("AND sap_workcenter_id=? ");
	        		

	        if ("MSS".equalsIgnoreCase(transID)) {
	            whereClause.append("AND SUBSTR(workcenter, 1, 4) = 'CSAS' ");
	        } else if ("MSA".equalsIgnoreCase(transID)) {
	            whereClause.append("AND SUBSTR(workcenter, 1, 4) = 'CSAA' ");
	        }

	        System.out.print(transID);
	        System.out.print(slocReceiving);
	        
	        List<MV_MES_ProductionInfo> list = new Query(ctx, Table_Name, whereClause.toString(), trxName)
	                .setParameters(
	                		Env.getAD_Org_ID(ctx),
	                		Env.getAD_Client_ID(ctx),
	                        productCategory,
	                        slocReceiving
	                )
	                .setOrderBy("poreference")
	                .list();

	        Set<String> distinctSet = new LinkedHashSet<>();
	        for (MV_MES_ProductionInfo item : list) {
	            distinctSet.add(item.get_ValueAsString("poreference"));
	        }

	        return new ArrayList<>(distinctSet);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}
	
	public static List<MV_MES_ProductionInfo> findBarcodeSizeAvailabilityList(Properties ctx, String productCategory, int slocReceiving, String poreference, String trxName) {

	    List<MV_MES_ProductionInfo> list = new ArrayList<>();

	    String sql =
	        "SELECT " +
	        "    oi.*, " +
	        "    COALESCE(binfo.qtybarcode, 0) AS qtybarcode, " +
	        "    (COALESCE(binfo.qtybarcode, 0) - oi.qtyoutput) AS qtybarcode_available " +
	        "FROM V_MES_ProductionInfo oi " +
	        "LEFT JOIN ( " +
	        "    SELECT " +
	        "        mb2.ad_client_id, " +
	        "        mb2.ad_org_id, " +
	        "        mb2.ordernumber, " +
	        "        mb2.sizefactory, " +
	        "        SUM(mb2.qtybarcode) AS qtybarcode " +
	        "    FROM mes_barcode mb " +
	        "    INNER JOIN mes_barcodedetail mb2 ON mb.mes_barcode_id = mb2.mes_barcode_id " +
	        "    INNER JOIN MES_BarcodeType bt ON bt.MES_BarcodeType_ID = mb.MES_BarcodeType_ID " +
	        "    WHERE mb.isactive = 'Y' AND bt.value = 'B2' AND mb.poreference = ? " +
	        "    GROUP BY mb2.ad_client_id, mb2.ad_org_id, mb2.ordernumber, mb2.sizefactory " +
	        ") binfo ON oi.ad_client_id = binfo.ad_client_id " +
	        "AND oi.ad_org_id = binfo.ad_org_id " +
	        "AND oi.ordernumber = binfo.ordernumber " +
	        "AND oi.sizefactory = binfo.sizefactory " +
	        "WHERE " +
	        "    oi.ad_client_id = ? " +
	        "    AND oi.ad_org_id = ? " +
	        "    AND oi.productcategory = ? " +
	        "    AND oi.sap_workcenter_id = ? " +
	        "    AND oi.poreference = ? " +
	        "    AND (COALESCE(binfo.qtybarcode, 0) - oi.qtyoutput) > 0";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        pstmt = DB.prepareStatement(sql, trxName);
	        int idx = 1;
	        pstmt.setString(idx++, poreference);   
	        pstmt.setInt(idx++, Env.getAD_Client_ID(ctx));  
	        pstmt.setInt(idx++, Env.getAD_Org_ID(ctx));  
	        pstmt.setString(idx++, productCategory);   
	        pstmt.setInt(idx++, slocReceiving);   
	        pstmt.setString(idx++, poreference);   

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            MV_MES_ProductionInfo infoBarcodeSize = new MV_MES_ProductionInfo(ctx, rs, trxName);

	            // Virtual columns
	            //infoBarcodeSize.set_Value("qtybarcode", rs.getBigDecimal("qtybarcode"));
	            //infoBarcodeSize.set_Value("qtybarcode_available", rs.getBigDecimal("qtybarcode_available"));

	            list.add(infoBarcodeSize);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DB.close(rs, pstmt);
	    }

	    return list;
	}
	
	

	
	

	




}
