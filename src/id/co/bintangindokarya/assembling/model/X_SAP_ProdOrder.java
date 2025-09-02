/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package id.co.bintangindokarya.assembling.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for SAP_ProdOrder
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_ProdOrder")
public class X_SAP_ProdOrder extends PO implements I_SAP_ProdOrder, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250901L;

    /** Standard Constructor */
    public X_SAP_ProdOrder (Properties ctx, int SAP_ProdOrder_ID, String trxName)
    {
      super (ctx, SAP_ProdOrder_ID, trxName);
      /** if (SAP_ProdOrder_ID == 0)
        {
			setActualRelease (new Timestamp( System.currentTimeMillis() ));
			setBasicFinish (new Timestamp( System.currentTimeMillis() ));
			setBasicStart (new Timestamp( System.currentTimeMillis() ));
			setC_UOM_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrder_ID (0);
			setSAP_SalesOrder_ID (0);
			setSlocIssuing (null);
			setSlocReceiving (null);
			setSystemStatus (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrder (Properties ctx, int SAP_ProdOrder_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProdOrder_ID, trxName, virtualColumns);
      /** if (SAP_ProdOrder_ID == 0)
        {
			setActualRelease (new Timestamp( System.currentTimeMillis() ));
			setBasicFinish (new Timestamp( System.currentTimeMillis() ));
			setBasicStart (new Timestamp( System.currentTimeMillis() ));
			setC_UOM_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrder_ID (0);
			setSAP_SalesOrder_ID (0);
			setSlocIssuing (null);
			setSlocReceiving (null);
			setSystemStatus (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrder (Properties ctx, String SAP_ProdOrder_UU, String trxName)
    {
      super (ctx, SAP_ProdOrder_UU, trxName);
      /** if (SAP_ProdOrder_UU == null)
        {
			setActualRelease (new Timestamp( System.currentTimeMillis() ));
			setBasicFinish (new Timestamp( System.currentTimeMillis() ));
			setBasicStart (new Timestamp( System.currentTimeMillis() ));
			setC_UOM_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrder_ID (0);
			setSAP_SalesOrder_ID (0);
			setSlocIssuing (null);
			setSlocReceiving (null);
			setSystemStatus (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrder (Properties ctx, String SAP_ProdOrder_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProdOrder_UU, trxName, virtualColumns);
      /** if (SAP_ProdOrder_UU == null)
        {
			setActualRelease (new Timestamp( System.currentTimeMillis() ));
			setBasicFinish (new Timestamp( System.currentTimeMillis() ));
			setBasicStart (new Timestamp( System.currentTimeMillis() ));
			setC_UOM_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrder_ID (0);
			setSAP_SalesOrder_ID (0);
			setSlocIssuing (null);
			setSlocReceiving (null);
			setSystemStatus (null);
        } */
    }

    /** Load Constructor */
    public X_SAP_ProdOrder (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_SAP_ProdOrder[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Actual Release.
		@param ActualRelease Actual Release
	*/
	public void setActualRelease (Timestamp ActualRelease)
	{
		set_Value (COLUMNNAME_ActualRelease, ActualRelease);
	}

	/** Get Actual Release.
		@return Actual Release	  */
	public Timestamp getActualRelease()
	{
		return (Timestamp)get_Value(COLUMNNAME_ActualRelease);
	}

	/** Set Basic Finish.
		@param BasicFinish Basic Finish
	*/
	public void setBasicFinish (Timestamp BasicFinish)
	{
		set_Value (COLUMNNAME_BasicFinish, BasicFinish);
	}

	/** Get Basic Finish.
		@return Basic Finish	  */
	public Timestamp getBasicFinish()
	{
		return (Timestamp)get_Value(COLUMNNAME_BasicFinish);
	}

	/** Set Basic Start.
		@param BasicStart Basic Start
	*/
	public void setBasicStart (Timestamp BasicStart)
	{
		set_Value (COLUMNNAME_BasicStart, BasicStart);
	}

	/** Get Basic Start.
		@return Basic Start	  */
	public Timestamp getBasicStart()
	{
		return (Timestamp)get_Value(COLUMNNAME_BasicStart);
	}

	/** Set Batch Document No.
		@param BatchDocumentNo Document Number of the Batch
	*/
	public void setBatchDocumentNo (String BatchDocumentNo)
	{
		set_Value (COLUMNNAME_BatchDocumentNo, BatchDocumentNo);
	}

	/** Get Batch Document No.
		@return Document Number of the Batch
	  */
	public String getBatchDocumentNo()
	{
		return (String)get_Value(COLUMNNAME_BatchDocumentNo);
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
	{
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_ID)
			.getPO(getC_UOM_ID(), get_TrxName());
	}

	/** Set UOM.
		@param C_UOM_ID Unit of Measure
	*/
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Change Date Order Master.
		@param ChangeDateOrderMaster Change Date Order Master
	*/
	public void setChangeDateOrderMaster (Timestamp ChangeDateOrderMaster)
	{
		set_Value (COLUMNNAME_ChangeDateOrderMaster, ChangeDateOrderMaster);
	}

	/** Get Change Date Order Master.
		@return Change Date Order Master	  */
	public Timestamp getChangeDateOrderMaster()
	{
		return (Timestamp)get_Value(COLUMNNAME_ChangeDateOrderMaster);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_ID)
			.getPO(getM_Product_ID(), get_TrxName());
	}

	/** Set Product.
		@param M_Product_ID Product, Service, Item
	*/
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			set_Value (COLUMNNAME_M_Product_ID, null);
		else
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Master Production Order.
		@param MasterProductionOrder Master Production Order
	*/
	public void setMasterProductionOrder (String MasterProductionOrder)
	{
		set_Value (COLUMNNAME_MasterProductionOrder, MasterProductionOrder);
	}

	/** Get Master Production Order.
		@return Master Production Order	  */
	public String getMasterProductionOrder()
	{
		return (String)get_Value(COLUMNNAME_MasterProductionOrder);
	}

	/** Set Order Number.
		@param OrderNumber Order Number
	*/
	public void setOrderNumber (String OrderNumber)
	{
		set_Value (COLUMNNAME_OrderNumber, OrderNumber);
	}

	/** Get Order Number.
		@return Order Number	  */
	public String getOrderNumber()
	{
		return (String)get_Value(COLUMNNAME_OrderNumber);
	}

	/** Set Ordered Quantity.
		@param QtyOrdered Ordered Quantity
	*/
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SAP_ProdOrder.
		@param SAP_ProdOrder_ID SAP_ProdOrder
	*/
	public void setSAP_ProdOrder_ID (int SAP_ProdOrder_ID)
	{
		if (SAP_ProdOrder_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_ProdOrder_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_ProdOrder_ID, Integer.valueOf(SAP_ProdOrder_ID));
	}

	/** Get SAP_ProdOrder.
		@return SAP_ProdOrder	  */
	public int getSAP_ProdOrder_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_ProdOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SAP_ProdOrder_UU.
		@param SAP_ProdOrder_UU SAP_ProdOrder_UU
	*/
	public void setSAP_ProdOrder_UU (String SAP_ProdOrder_UU)
	{
		set_Value (COLUMNNAME_SAP_ProdOrder_UU, SAP_ProdOrder_UU);
	}

	/** Get SAP_ProdOrder_UU.
		@return SAP_ProdOrder_UU	  */
	public String getSAP_ProdOrder_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_ProdOrder_UU);
	}

	public I_SAP_SalesOrder getSAP_SalesOrder() throws RuntimeException
	{
		return (I_SAP_SalesOrder)MTable.get(getCtx(), I_SAP_SalesOrder.Table_ID)
			.getPO(getSAP_SalesOrder_ID(), get_TrxName());
	}

	/** Set SAP_SalesOrder.
		@param SAP_SalesOrder_ID SAP_SalesOrder
	*/
	public void setSAP_SalesOrder_ID (int SAP_SalesOrder_ID)
	{
		if (SAP_SalesOrder_ID < 1)
			set_Value (COLUMNNAME_SAP_SalesOrder_ID, null);
		else
			set_Value (COLUMNNAME_SAP_SalesOrder_ID, Integer.valueOf(SAP_SalesOrder_ID));
	}

	/** Get SAP_SalesOrder.
		@return SAP_SalesOrder	  */
	public int getSAP_SalesOrder_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_SalesOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sloc Issuing.
		@param SlocIssuing Sloc Issuing
	*/
	public void setSlocIssuing (String SlocIssuing)
	{
		set_Value (COLUMNNAME_SlocIssuing, SlocIssuing);
	}

	/** Get Sloc Issuing.
		@return Sloc Issuing	  */
	public String getSlocIssuing()
	{
		return (String)get_Value(COLUMNNAME_SlocIssuing);
	}

	/** Set Sloc Receiving.
		@param SlocReceiving Sloc Receiving
	*/
	public void setSlocReceiving (String SlocReceiving)
	{
		set_Value (COLUMNNAME_SlocReceiving, SlocReceiving);
	}

	/** Get Sloc Receiving.
		@return Sloc Receiving	  */
	public String getSlocReceiving()
	{
		return (String)get_Value(COLUMNNAME_SlocReceiving);
	}

	/** Set System Status.
		@param SystemStatus Status of the system - Support priority depends on system status
	*/
	public void setSystemStatus (String SystemStatus)
	{
		set_Value (COLUMNNAME_SystemStatus, SystemStatus);
	}

	/** Get System Status.
		@return Status of the system - Support priority depends on system status
	  */
	public String getSystemStatus()
	{
		return (String)get_Value(COLUMNNAME_SystemStatus);
	}
}