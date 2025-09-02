package id.co.bintangindokarya.assembling.callout;

import id.co.bintangindokarya.erp.model.*;
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



@Callout(tableName = "MES_Barcode", columnName = { "SAP_ProdOrderMaster_ID", "POReference", "SAP_Sloc_ID",
		"MES_BarcodeType_ID" })
public class CalloutAssembling implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		String columnName = mField.getColumnName();
		if (columnName.equals("POReference") || columnName.equals("SAP_Sloc_ID")) {
			return listMasterProd(ctx, WindowNo, mTab, mField, value, oldValue);
		}
		if (columnName.equals("SAP_ProdOrderMaster_ID")) {
			return masterProductionOrder(ctx, WindowNo, mTab, mField, value, oldValue);
		}

		return "";
	}

	private String listMasterProd(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value,
			Object oldValue) {
		Integer SAP_Sloc_ID = (Integer) mTab.getValue("SAP_Sloc_ID");
		String poReference = (String) mTab.get_ValueAsString("POReference");
		int AD_Client_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "AD_Client_ID");
		Env.setContext(ctx, WindowNo, "IsOneRow", "N");

		if (SAP_Sloc_ID == null || SAP_Sloc_ID == 0) {
			System.out.println("Sloc Must be Fill");
		} else if (poReference == null || poReference.isEmpty()) {
			System.out.println("Order Reference Must be Fill");
		} else {
			final String sql = "SELECT sp2.sap_prodordermaster_id " + " FROM sap_prodorder sp "
					+ " INNER JOIN sap_salesorder ss ON sp.sap_salesorder_id  = ss.sap_salesorder_id AND ss.salesorderitem  = '10' "
					+ " INNER JOIN sap_prodordermaster sp2 ON sp2.masterproductionorder  = sp.masterproductionorder AND sp2.isActive = 'Y' "
					+ " INNER JOIN sap_sloc ss2 ON ss2.sap_sloc_id  = sp2.sap_sloc_id AND ss2.value  = ?  "
					+ " WHERE ss.poreference  = ? AND sp.AD_Client_ID = ?  " + " GROUP BY sp2.sap_prodordermaster_id ";

			int count = 0;
			Integer onlyId = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				MSAP_Sloc sloc = new MSAP_Sloc(ctx, SAP_Sloc_ID, null);
				pstmt = DB.prepareStatement(sql, null);
				DB.setParameters(pstmt, new Object[] { sloc.getValue(), poReference, AD_Client_ID });
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count++;
					if (count == 1) {
						onlyId = rs.getInt("sap_prodordermaster_id");
					}
					if (count > 1) {
						// nggak perlu lanjutkan baca; kita cuma butuh tahu >1
						break;
					}
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
			if (count == 1 && onlyId != null) {
				// set context agar field menjadi read-only via Read Only Logic
				Env.setContext(ctx, WindowNo, "IsOneRow", "Y");

				// set nilai dan trigger logika lanjutan
				mTab.setValue("SAP_ProdOrderMaster_ID", onlyId);
				masterProductionOrder(ctx, WindowNo, mTab, mTab.getField("SAP_ProdOrderMaster_ID"), onlyId, null);
			} else {
				// lebih dari 1 baris → biarkan editable (IsOneRow=N sudah diset diawal)
				// opsional: kosongkan field supaya user pilih manual
				// mTab.setValue("SAP_ProdOrderMaster_ID", null);
			}
		}

		return "";

	}

	private String masterProductionOrder(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value,
			Object oldValue) {
		Integer SAP_Sloc_ID = (Integer) mTab.getValue("SAP_Sloc_ID");
		String poReference = (String) mTab.get_ValueAsString("POReference");
		int AD_Client_ID = Env.getContextAsInt(ctx, WindowNo, mTab.getTabNo(), "AD_Client_ID");
		Integer SAP_ProdOrderMaster_ID = (Integer) mTab.getValue("SAP_ProdOrderMaster_ID");

		if (SAP_ProdOrderMaster_ID == null || SAP_ProdOrderMaster_ID == 0) {
			mTab.setValue("CustomerNo", null);
			mTab.setValue("QtyOrdered", Env.ZERO);
			mTab.setValue("M_Product_ID", null);
		} else {

			final String sql = "SELECT" + "    mp.m_product_id , " + "	mp.value, " + "    ss.customerno, "
					+ "    mp.unitsperpack, " + "    ss.lineaggregator, " + "    SUM(sp.qtyordered) AS qtyOrdered "
					+ "FROM" + "    sap_prodorder sp " + "INNER JOIN" + "    sap_salesorder ss "
					+ "    ON sp.sap_salesorder_id = ss.sap_salesorder_id " + "    AND ss.salesorderitem = '10' "
					+ "INNER JOIN " + "    m_product mp " + "    ON mp.m_product_id = ss.m_product_id " + "WHERE "
					+ "    sp.slocreceiving = ? " + "    AND ss.poreference = ? "
					+ "    AND sp.masterproductionorder = ? " + "	   AND sp.AD_Client_ID = ? " + "GROUP BY"
					+ "    mp.m_product_id ,mp.value, ss.customerno, mp.unitsperpack,ss.lineaggregator ";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				MSAP_ProdOrderMaster ProdOrderMaster = new MSAP_ProdOrderMaster(ctx, SAP_ProdOrderMaster_ID, null);
				MSAP_Sloc sloc = new MSAP_Sloc(ctx, SAP_Sloc_ID, null);
				pstmt = DB.prepareStatement(sql, null);
				DB.setParameters(pstmt, new Object[] { sloc.getValue(), poReference,
						ProdOrderMaster.getMasterProductionOrder(), AD_Client_ID });
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
