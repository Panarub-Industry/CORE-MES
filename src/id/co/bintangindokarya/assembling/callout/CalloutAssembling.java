package id.co.bintangindokarya.assembling.callout;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.adempiere.exceptions.DBException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

import id.co.bintangindokarya.assembling.model.MSAP_ProdOrderMaster;
import id.co.bintangindokarya.assembling.model.MSAP_Sloc;

@Callout(tableName = "MES_Barcode", columnName = { "SAP_ProdOrderMaster_ID" })
public class CalloutAssembling implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		String columnName = mField.getColumnName();
		if (columnName.equals("SAP_ProdOrderMaster_ID"))
			return MasterProductionOrder(ctx, WindowNo, mTab, mField, value, oldValue);
		return "";
	}

	private String MasterProductionOrder(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		Integer SAP_ProdOrderMaster_ID = (Integer) mTab.getValue("SAP_ProdOrderMaster_ID"); 
		Integer SAP_Sloc_ID = (Integer) mTab.getValue("SAP_Sloc_ID");
		String poReference = (String) mTab.get_ValueAsString("POReference");
		int AD_Client_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "AD_Client_ID");
		
		MSAP_ProdOrderMaster ProdOrderMaster = new MSAP_ProdOrderMaster(ctx, SAP_ProdOrderMaster_ID, null);
		MSAP_Sloc sloc = new MSAP_Sloc(ctx, SAP_Sloc_ID, null);
		
		if (SAP_ProdOrderMaster_ID == null || SAP_ProdOrderMaster_ID == 0) {
			mTab.setValue("CustomerNo", null);
			mTab.setValue("QtyOrdered", Env.ZERO);
		} else {

			final String sql = "SELECT"
					+ "    mp.m_product_id , "
					+ "	mp.value, "
					+ "    ss.customerno, "
					+ "    mp.unitsperpack, "
					+ "    ss.lineaggregator, "
					+ "    SUM(sp.qtyordered) AS qtyOrdered "
					+ "FROM"
					+ "    sap_prodorder sp "
					+ "INNER JOIN"
					+ "    sap_salesorder ss "
					+ "    ON sp.sap_salesorder_id = ss.sap_salesorder_id "
					+ "    AND ss.salesorderitem = '10' "
					+ "INNER JOIN "
					+ "    m_product mp "
					+ "    ON mp.m_product_id = ss.m_product_id "
					+ "WHERE "
					+ "    sp.slocreceiving = ? "
					+ "    AND ss.poreference = ? "
					+ "    AND sp.masterproductionorder = ? "
					+ "	   AND sp.AD_Client_ID = ? "
					+ "GROUP BY"
					+ "    mp.m_product_id ,mp.value, ss.customerno, mp.unitsperpack,ss.lineaggregator ";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, null);
				DB.setParameters(pstmt, new Object[] { sloc.getValue(), poReference, ProdOrderMaster.getMasterProductionOrder(), AD_Client_ID });
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Integer M_Product_ID = rs.getInt("m_product_id");
					String customerNo = rs.getString("customerno");
					BigDecimal qtyOrdered = rs.getBigDecimal("qtyOrdered");
					String lineAggregator = rs.getString("lineaggregator");

					mTab.setValue("M_Product_ID", M_Product_ID);
					mTab.setValue("CustomerNo", customerNo);
					mTab.setValue("QtyOrdered", qtyOrdered);
					mTab.setValue("lineAggregator", lineAggregator);
					mTab.setValue("BatchDocumentNo", 1);
				}
			}
			// If your method is not throwing Exception or SQLException you need this block
			// to catch SQLException
			// and convert them to unchecked DBException
			catch (SQLException e) {
				throw new DBException(e, sql);
			}
			// '''ALWAYS''' close your ResultSet in a finally statement
			finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

		}
		return "";
		
	}

}
