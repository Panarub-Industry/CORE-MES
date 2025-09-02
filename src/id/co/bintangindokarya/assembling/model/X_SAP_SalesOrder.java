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

/** Generated Model for SAP_SalesOrder
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_SalesOrder")
public class X_SAP_SalesOrder extends PO implements I_SAP_SalesOrder, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250901L;

    /** Standard Constructor */
    public X_SAP_SalesOrder (Properties ctx, int SAP_SalesOrder_ID, String trxName)
    {
      super (ctx, SAP_SalesOrder_ID, trxName);
      /** if (SAP_SalesOrder_ID == 0)
        {
			setCustomerNo (null);
			setDocumentType (null);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setRejectionStatus (false);
// N
			setSAP_SalesOrder_ID (0);
			setSalesOrderItem (null);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_SalesOrder (Properties ctx, int SAP_SalesOrder_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_SalesOrder_ID, trxName, virtualColumns);
      /** if (SAP_SalesOrder_ID == 0)
        {
			setCustomerNo (null);
			setDocumentType (null);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setRejectionStatus (false);
// N
			setSAP_SalesOrder_ID (0);
			setSalesOrderItem (null);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_SalesOrder (Properties ctx, String SAP_SalesOrder_UU, String trxName)
    {
      super (ctx, SAP_SalesOrder_UU, trxName);
      /** if (SAP_SalesOrder_UU == null)
        {
			setCustomerNo (null);
			setDocumentType (null);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setRejectionStatus (false);
// N
			setSAP_SalesOrder_ID (0);
			setSalesOrderItem (null);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_SalesOrder (Properties ctx, String SAP_SalesOrder_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_SalesOrder_UU, trxName, virtualColumns);
      /** if (SAP_SalesOrder_UU == null)
        {
			setCustomerNo (null);
			setDocumentType (null);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setRejectionStatus (false);
// N
			setSAP_SalesOrder_ID (0);
			setSalesOrderItem (null);
			setSalesOrderNumber (null);
        } */
    }

    /** Load Constructor */
    public X_SAP_SalesOrder (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_SalesOrder[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bill To Party.
		@param BillToParty Bill To Party
	*/
	public void setBillToParty (String BillToParty)
	{
		set_Value (COLUMNNAME_BillToParty, BillToParty);
	}

	/** Get Bill To Party.
		@return Bill To Party	  */
	public String getBillToParty()
	{
		return (String)get_Value(COLUMNNAME_BillToParty);
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

	/** Set Commited Delivery Creation Date.
		@param CommitedDeliveryCreationDate Commited Delivery Creation Date
	*/
	public void setCommitedDeliveryCreationDate (Timestamp CommitedDeliveryCreationDate)
	{
		set_Value (COLUMNNAME_CommitedDeliveryCreationDate, CommitedDeliveryCreationDate);
	}

	/** Get Commited Delivery Creation Date.
		@return Commited Delivery Creation Date	  */
	public Timestamp getCommitedDeliveryCreationDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_CommitedDeliveryCreationDate);
	}

	/** Set Commited Delivery Date.
		@param CommitedDeliveryDate Commited Delivery Date
	*/
	public void setCommitedDeliveryDate (Timestamp CommitedDeliveryDate)
	{
		set_Value (COLUMNNAME_CommitedDeliveryDate, CommitedDeliveryDate);
	}

	/** Get Commited Delivery Date.
		@return Commited Delivery Date	  */
	public Timestamp getCommitedDeliveryDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_CommitedDeliveryDate);
	}

	/** Set Customer No.
		@param CustomerNo EDI Identification Number 
	*/
	public void setCustomerNo (String CustomerNo)
	{
		set_Value (COLUMNNAME_CustomerNo, CustomerNo);
	}

	/** Get Customer No.
		@return EDI Identification Number 
	  */
	public String getCustomerNo()
	{
		return (String)get_Value(COLUMNNAME_CustomerNo);
	}

	/** Set Document Type.
		@param DocumentType Document Type
	*/
	public void setDocumentType (String DocumentType)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentType, DocumentType);
	}

	/** Get Document Type.
		@return Document Type
	  */
	public String getDocumentType()
	{
		return (String)get_Value(COLUMNNAME_DocumentType);
	}

	/** Set First Production Date.
		@param FirstProductionDate First Production Date
	*/
	public void setFirstProductionDate (Timestamp FirstProductionDate)
	{
		set_Value (COLUMNNAME_FirstProductionDate, FirstProductionDate);
	}

	/** Get First Production Date.
		@return First Production Date	  */
	public Timestamp getFirstProductionDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_FirstProductionDate);
	}

	/** Set Last Production Date.
		@param LastProductionDate Last Production Date
	*/
	public void setLastProductionDate (Timestamp LastProductionDate)
	{
		set_Value (COLUMNNAME_LastProductionDate, LastProductionDate);
	}

	/** Get Last Production Date.
		@return Last Production Date	  */
	public Timestamp getLastProductionDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_LastProductionDate);
	}

	/** Set Line Aggregator.
		@param LineAggregator Line Aggregator
	*/
	public void setLineAggregator (String LineAggregator)
	{
		set_Value (COLUMNNAME_LineAggregator, LineAggregator);
	}

	/** Get Line Aggregator.
		@return Line Aggregator	  */
	public String getLineAggregator()
	{
		return (String)get_Value(COLUMNNAME_LineAggregator);
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

	/** Set PODD.
		@param PODD PODD
	*/
	public void setPODD (Timestamp PODD)
	{
		set_Value (COLUMNNAME_PODD, PODD);
	}

	/** Get PODD.
		@return PODD	  */
	public Timestamp getPODD()
	{
		return (Timestamp)get_Value(COLUMNNAME_PODD);
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

	/** Set POSDD.
		@param POSDD POSDD
	*/
	public void setPOSDD (Timestamp POSDD)
	{
		set_Value (COLUMNNAME_POSDD, POSDD);
	}

	/** Get POSDD.
		@return POSDD	  */
	public Timestamp getPOSDD()
	{
		return (Timestamp)get_Value(COLUMNNAME_POSDD);
	}

	/** Set Ordered Quantity.
		@param QtyOrdered Ordered Quantity
	*/
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
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

	/** Set Rejection Status.
		@param RejectionStatus Rejection Status
	*/
	public void setRejectionStatus (boolean RejectionStatus)
	{
		set_Value (COLUMNNAME_RejectionStatus, Boolean.valueOf(RejectionStatus));
	}

	/** Get Rejection Status.
		@return Rejection Status	  */
	public boolean isRejectionStatus()
	{
		Object oo = get_Value(COLUMNNAME_RejectionStatus);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requested Delivery Date.
		@param RequestedDeliveryDate Requested Delivery Date
	*/
	public void setRequestedDeliveryDate (Timestamp RequestedDeliveryDate)
	{
		set_Value (COLUMNNAME_RequestedDeliveryDate, RequestedDeliveryDate);
	}

	/** Get Requested Delivery Date.
		@return Requested Delivery Date	  */
	public Timestamp getRequestedDeliveryDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_RequestedDeliveryDate);
	}

	/** Set SAP_SalesOrder.
		@param SAP_SalesOrder_ID SAP_SalesOrder
	*/
	public void setSAP_SalesOrder_ID (int SAP_SalesOrder_ID)
	{
		if (SAP_SalesOrder_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_SalesOrder_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_SalesOrder_ID, Integer.valueOf(SAP_SalesOrder_ID));
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

	/** Set SAP_SalesOrder_UU.
		@param SAP_SalesOrder_UU SAP_SalesOrder_UU
	*/
	public void setSAP_SalesOrder_UU (String SAP_SalesOrder_UU)
	{
		set_Value (COLUMNNAME_SAP_SalesOrder_UU, SAP_SalesOrder_UU);
	}

	/** Get SAP_SalesOrder_UU.
		@return SAP_SalesOrder_UU	  */
	public String getSAP_SalesOrder_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_SalesOrder_UU);
	}

	/** Set Sales Order Item.
		@param SalesOrderItem Sales Order Item
	*/
	public void setSalesOrderItem (String SalesOrderItem)
	{
		set_Value (COLUMNNAME_SalesOrderItem, SalesOrderItem);
	}

	/** Get Sales Order Item.
		@return Sales Order Item	  */
	public String getSalesOrderItem()
	{
		return (String)get_Value(COLUMNNAME_SalesOrderItem);
	}

	/** Set Sales Order Number.
		@param SalesOrderNumber Sales Order Number
	*/
	public void setSalesOrderNumber (String SalesOrderNumber)
	{
		set_Value (COLUMNNAME_SalesOrderNumber, SalesOrderNumber);
	}

	/** Get Sales Order Number.
		@return Sales Order Number	  */
	public String getSalesOrderNumber()
	{
		return (String)get_Value(COLUMNNAME_SalesOrderNumber);
	}

	/** Set Ship To Party.
		@param ShipToParty Ship To Party
	*/
	public void setShipToParty (String ShipToParty)
	{
		set_Value (COLUMNNAME_ShipToParty, ShipToParty);
	}

	/** Get Ship To Party.
		@return Ship To Party	  */
	public String getShipToParty()
	{
		return (String)get_Value(COLUMNNAME_ShipToParty);
	}

	/** Set Ship To Party Country.
		@param ShipToPartyCountry Ship To Party Country
	*/
	public void setShipToPartyCountry (String ShipToPartyCountry)
	{
		set_Value (COLUMNNAME_ShipToPartyCountry, ShipToPartyCountry);
	}

	/** Get Ship To Party Country.
		@return Ship To Party Country	  */
	public String getShipToPartyCountry()
	{
		return (String)get_Value(COLUMNNAME_ShipToPartyCountry);
	}

	/** Set Ship To Party Name.
		@param ShipToPartyName Ship To Party Name
	*/
	public void setShipToPartyName (String ShipToPartyName)
	{
		set_Value (COLUMNNAME_ShipToPartyName, ShipToPartyName);
	}

	/** Get Ship To Party Name.
		@return Ship To Party Name	  */
	public String getShipToPartyName()
	{
		return (String)get_Value(COLUMNNAME_ShipToPartyName);
	}

	/** Set Sold To Party.
		@param SoldToParty Sold To Party
	*/
	public void setSoldToParty (String SoldToParty)
	{
		set_Value (COLUMNNAME_SoldToParty, SoldToParty);
	}

	/** Get Sold To Party.
		@return Sold To Party	  */
	public String getSoldToParty()
	{
		return (String)get_Value(COLUMNNAME_SoldToParty);
	}
}