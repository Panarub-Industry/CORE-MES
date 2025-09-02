/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package id.co.bintangindokarya.assembling.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.sql.SQLException;

import org.compiere.model.MProcessPara;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;
import org.adempiere.exceptions.DBException;

import id.co.bintangindokarya.assembling.model.MMes_Barcode;
import id.co.bintangindokarya.assembling.model.MMes_BarcodeDetail;
import id.co.bintangindokarya.assembling.model.MSAP_ProdOrderMaster;
 
/**
 *  @author Erick
 */
@org.adempiere.base.annotation.Process
public class ProcessGenerateBarcode extends SvrProcess
{
	private int p_MES_Barcode_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("MES_Barcode_ID"))
				p_MES_Barcode_ID = para[i].getParameterAsInt();
			else
				MProcessPara.validateUnknownParameter(getProcessInfo().getAD_Process_ID(), para[i]);
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws AdempiereSystemError
	{
		if (log.isLoggable(Level.INFO)) log.info("doIt - Process IT_Issue_ID=" + p_MES_Barcode_ID);
		if (p_MES_Barcode_ID == 0)
			return "";
		// search MES_Barcode_ID
		MMes_Barcode barcode =new MMes_Barcode(getCtx(), p_MES_Barcode_ID, get_TrxName());
		Integer SAP_ProdOrderMaster_ID = barcode.getSAP_ProdOrderMaster_ID();		
		MSAP_ProdOrderMaster ProdOrderMaster = new MSAP_ProdOrderMaster(getCtx(), SAP_ProdOrderMaster_ID, get_TrxName());				
		
		final String sql = "WITH cte AS ( "
				+ "    SELECT "
				+ "        a.ordernumber, "
				+ "        a.qtyordered, "
				+ "		   mp.sizefactory, "
				+ "        g.n AS package_no, "
				+ "        CASE "
				+ "            WHEN a.qtyordered - (g.n - 1) * ? >= ? THEN ? "
				+ "            ELSE a.qtyordered - (g.n - 1) * ? "
				+ "        END AS qtyBarcode "
				+ "    FROM sap_prodorder a "
				+ "	   INNER JOIN m_product mp on a.m_product_id = mp.m_product_id "
				+ "    CROSS JOIN LATERAL generate_series( "
				+ "        1, "
				+ "        (a.qtyordered / ? ) + CASE WHEN a.qtyordered % ? > 0 THEN 1 ELSE 0 END "
				+ "    ) g(n) "
				+ "    WHERE a.masterproductionorder = ? "
				+ ") "
				+ ", cte2 AS ( "
				+ "    SELECT "
				+ "        cte.ordernumber, "
				+ "        cte.qtyBarcode,"
				+ "		   cte.sizefactory, "
				+ "        ROW_NUMBER() OVER ( "
				+ "            PARTITION BY cte.ordernumber "
				+ "            ORDER BY cte.package_no "
				+ "        ) AS seq_per_order "
				+ "    FROM cte "
				+ ")"
				+ "SELECT "
				+ "    cte2.ordernumber,"
				+ "	   cte2.sizefactory, "
				+ "    cte2.qtyBarcode,"
				+ "    to_char(current_date, 'YY') "
				+ "        || cte2.ordernumber "
				+ "        || lpad(seq_per_order::text, 4, '0') AS barcode "
				+ "FROM cte2 "
				+ "ORDER BY cte2.ordernumber, seq_per_order ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameters(pstmt, new Object[]{ barcode.getQtyPack(), barcode.getQtyPack(),barcode.getQtyPack(),barcode.getQtyPack(),barcode.getQtyPack(),barcode.getQtyPack(),ProdOrderMaster.getMasterProductionOrder(),});
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String orderNumber = rs.getString("ordernumber");
				BigDecimal qtyBarcode = rs.getBigDecimal("qtyBarcode");
				String barcodeGenerate = rs.getString("barcode");
				String sizeFactory = rs.getString("sizefactory");
				
				MMes_BarcodeDetail barcodeDetail = new MMes_BarcodeDetail(getCtx(), 0, get_TrxName());				
				barcodeDetail.setMES_Barcode_ID(p_MES_Barcode_ID);
				barcodeDetail.setOrderNumber(orderNumber);
				barcodeDetail.setBarcode(barcodeGenerate);
				barcodeDetail.setQtyBarcode(qtyBarcode);
				barcodeDetail.setSizeCustomer(sizeFactory);
				barcodeDetail.setSizeFactory(sizeFactory);
				
				System.out.println(orderNumber + " | " + barcodeGenerate + " | " + qtyBarcode);
				
				barcodeDetail.saveEx();
			}
		     return "@OK@";
		}
		// If your method is not throwing Exception or SQLException you need this block to catch SQLException
		// and convert them to unchecked DBException
		
		catch (SQLException e)
		{			
			throw new DBException(e, sql);
		}		
		// '''ALWAYS''' close your ResultSet in a finally statement
		finally
		{			
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
			return "@OK@";
		}
		
		
	}	//	doIt
	
}	//	OrderOpen
