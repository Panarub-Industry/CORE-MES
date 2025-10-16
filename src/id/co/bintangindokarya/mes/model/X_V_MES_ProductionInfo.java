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
package id.co.bintangindokarya.mes.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for V_MES_ProductionInfo
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="V_MES_ProductionInfo")
public class X_V_MES_ProductionInfo extends PO implements I_V_MES_ProductionInfo, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250929L;

    /** Standard Constructor */
    public X_V_MES_ProductionInfo (Properties ctx, int V_MES_ProductionInfo_ID, String trxName)
    {
      super (ctx, V_MES_ProductionInfo_ID, trxName);
      /** if (V_MES_ProductionInfo_ID == 0)
        {
        } */
    }

    /** Standard Constructor */
    public X_V_MES_ProductionInfo (Properties ctx, int V_MES_ProductionInfo_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, V_MES_ProductionInfo_ID, trxName, virtualColumns);
      /** if (V_MES_ProductionInfo_ID == 0)
        {
        } */
    }

    /** Standard Constructor */
    public X_V_MES_ProductionInfo (Properties ctx, String V_MES_ProductionInfo_UU, String trxName)
    {
      super (ctx, V_MES_ProductionInfo_UU, trxName);
      /** if (V_MES_ProductionInfo_UU == null)
        {
        } */
    }

    /** Standard Constructor */
    public X_V_MES_ProductionInfo (Properties ctx, String V_MES_ProductionInfo_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, V_MES_ProductionInfo_UU, trxName, virtualColumns);
      /** if (V_MES_ProductionInfo_UU == null)
        {
        } */
    }

    /** Load Constructor */
    public X_V_MES_ProductionInfo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_V_MES_ProductionInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Last_Trans_Created.
		@param Last_Trans_Created Last_Trans_Created
	*/
	public void setLast_Trans_Created (Timestamp Last_Trans_Created)
	{
		set_ValueNoCheck (COLUMNNAME_Last_Trans_Created, Last_Trans_Created);
	}

	/** Get Last_Trans_Created.
		@return Last_Trans_Created	  */
	public Timestamp getLast_Trans_Created()
	{
		return (Timestamp)get_Value(COLUMNNAME_Last_Trans_Created);
	}

	/** Set Master Production Order.
		@param MasterProductionOrder Master Production Order
	*/
	public void setMasterProductionOrder (String MasterProductionOrder)
	{
		set_ValueNoCheck (COLUMNNAME_MasterProductionOrder, MasterProductionOrder);
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
		set_ValueNoCheck (COLUMNNAME_OrderNumber, OrderNumber);
	}

	/** Get Order Number.
		@return Order Number	  */
	public String getOrderNumber()
	{
		return (String)get_Value(COLUMNNAME_OrderNumber);
	}

	/** Set Order Reference.
		@param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	*/
	public void setPOReference (String POReference)
	{
		set_ValueNoCheck (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference()
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	/** Set Product.
		@param Product Product
	*/
	public void setProduct (String Product)
	{
		set_ValueNoCheck (COLUMNNAME_Product, Product);
	}

	/** Get Product.
		@return Product	  */
	public String getProduct()
	{
		return (String)get_Value(COLUMNNAME_Product);
	}

	/** Set ProductCategory.
		@param ProductCategory ProductCategory
	*/
	public void setProductCategory (String ProductCategory)
	{
		set_ValueNoCheck (COLUMNNAME_ProductCategory, ProductCategory);
	}

	/** Get ProductCategory.
		@return ProductCategory	  */
	public String getProductCategory()
	{
		return (String)get_Value(COLUMNNAME_ProductCategory);
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

	/** Set QtyOutput.
		@param QtyOutput QtyOutput
	*/
	public void setQtyOutput (BigDecimal QtyOutput)
	{
		set_ValueNoCheck (COLUMNNAME_QtyOutput, QtyOutput);
	}

	/** Get QtyOutput.
		@return QtyOutput	  */
	public BigDecimal getQtyOutput()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOutput);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyOutstanding.
		@param QtyOutstanding Remaining quantity to be produced
	*/
	public void setQtyOutstanding (BigDecimal QtyOutstanding)
	{
		set_ValueNoCheck (COLUMNNAME_QtyOutstanding, QtyOutstanding);
	}

	/** Get QtyOutstanding.
		@return Remaining quantity to be produced
	  */
	public BigDecimal getQtyOutstanding()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOutstanding);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyPlanned.
		@param QtyPlanned QtyPlanned
	*/
	public void setQtyPlanned (BigDecimal QtyPlanned)
	{
		set_ValueNoCheck (COLUMNNAME_QtyPlanned, QtyPlanned);
	}

	/** Get QtyPlanned.
		@return QtyPlanned	  */
	public BigDecimal getQtyPlanned()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPlanned);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SAP_WorkCenter.
		@param SAP_WorkCenter_ID SAP_WorkCenter
	*/
	public void setSAP_WorkCenter_ID (int SAP_WorkCenter_ID)
	{
		if (SAP_WorkCenter_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_WorkCenter_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_WorkCenter_ID, Integer.valueOf(SAP_WorkCenter_ID));
	}

	/** Get SAP_WorkCenter.
		@return SAP_WorkCenter	  */
	public int getSAP_WorkCenter_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_WorkCenter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Order Number.
		@param SalesOrderNumber Sales Order Number
	*/
	public void setSalesOrderNumber (String SalesOrderNumber)
	{
		set_ValueNoCheck (COLUMNNAME_SalesOrderNumber, SalesOrderNumber);
	}

	/** Get Sales Order Number.
		@return Sales Order Number	  */
	public String getSalesOrderNumber()
	{
		return (String)get_Value(COLUMNNAME_SalesOrderNumber);
	}

	/** Set Size Factory.
		@param SizeFactory Size Factory
	*/
	public void setSizeFactory (String SizeFactory)
	{
		set_ValueNoCheck (COLUMNNAME_SizeFactory, SizeFactory);
	}

	/** Get Size Factory.
		@return Size Factory	  */
	public String getSizeFactory()
	{
		return (String)get_Value(COLUMNNAME_SizeFactory);
	}

	/** Set Sloc.
		@param Sloc Sloc
	*/
	public void setSloc (String Sloc)
	{
		set_ValueNoCheck (COLUMNNAME_Sloc, Sloc);
	}

	/** Get Sloc.
		@return Sloc	  */
	public String getSloc()
	{
		return (String)get_Value(COLUMNNAME_Sloc);
	}

	/** Set SlocName.
		@param SlocName SlocName
	*/
	public void setSlocName (String SlocName)
	{
		set_ValueNoCheck (COLUMNNAME_SlocName, SlocName);
	}

	/** Get SlocName.
		@return SlocName	  */
	public String getSlocName()
	{
		return (String)get_Value(COLUMNNAME_SlocName);
	}

	/** Set Sloc Receiving.
		@param SlocReceiving Sloc Receiving
	*/
	public void setSlocReceiving (String SlocReceiving)
	{
		set_ValueNoCheck (COLUMNNAME_SlocReceiving, SlocReceiving);
	}

	/** Get Sloc Receiving.
		@return Sloc Receiving	  */
	public String getSlocReceiving()
	{
		return (String)get_Value(COLUMNNAME_SlocReceiving);
	}

	/** Set MES Production Info.
		@param V_MES_ProductionInfo_ID MES Production Info
	*/
	public void setV_MES_ProductionInfo_ID (int V_MES_ProductionInfo_ID)
	{
		if (V_MES_ProductionInfo_ID < 1)
			set_ValueNoCheck (COLUMNNAME_V_MES_ProductionInfo_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_V_MES_ProductionInfo_ID, Integer.valueOf(V_MES_ProductionInfo_ID));
	}

	/** Get MES Production Info.
		@return MES Production Info	  */
	public int getV_MES_ProductionInfo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_V_MES_ProductionInfo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Variant.
		@param Variant Variant
	*/
	public void setVariant (String Variant)
	{
		set_ValueNoCheck (COLUMNNAME_Variant, Variant);
	}

	/** Get Variant.
		@return Variant	  */
	public String getVariant()
	{
		return (String)get_Value(COLUMNNAME_Variant);
	}

	/** Set WorkCenter.
		@param WorkCenter WorkCenter
	*/
	public void setWorkCenter (String WorkCenter)
	{
		set_ValueNoCheck (COLUMNNAME_WorkCenter, WorkCenter);
	}

	/** Get WorkCenter.
		@return WorkCenter	  */
	public String getWorkCenter()
	{
		return (String)get_Value(COLUMNNAME_WorkCenter);
	}

	/** Set WorkCenterName.
		@param WorkCenterName WorkCenterName
	*/
	public void setWorkCenterName (String WorkCenterName)
	{
		set_ValueNoCheck (COLUMNNAME_WorkCenterName, WorkCenterName);
	}

	/** Get WorkCenterName.
		@return WorkCenterName	  */
	public String getWorkCenterName()
	{
		return (String)get_Value(COLUMNNAME_WorkCenterName);
	}
}