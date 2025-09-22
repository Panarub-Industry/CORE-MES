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

/** Generated Model for MES_ProdOutputDetail
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="MES_ProdOutputDetail")
public class X_MES_ProdOutputDetail extends PO implements I_MES_ProdOutputDetail, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250919L;

    /** Standard Constructor */
    public X_MES_ProdOutputDetail (Properties ctx, int MES_ProdOutputDetail_ID, String trxName)
    {
      super (ctx, MES_ProdOutputDetail_ID, trxName);
      /** if (MES_ProdOutputDetail_ID == 0)
        {
			setArticle (null);
			setBarcode (null);
			setMES_ProdOutputDetail_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setPOReference (null);
			setPostConfirmationNo (null);
			setPostNo (null);
			setPostStatus (false);
// N
			setQtyEntered (Env.ZERO);
			setSalesOrderNumber (null);
			setSizeCustomer (null);
			setSizeFactory (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_ProdOutputDetail (Properties ctx, int MES_ProdOutputDetail_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_ProdOutputDetail_ID, trxName, virtualColumns);
      /** if (MES_ProdOutputDetail_ID == 0)
        {
			setArticle (null);
			setBarcode (null);
			setMES_ProdOutputDetail_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setPOReference (null);
			setPostConfirmationNo (null);
			setPostNo (null);
			setPostStatus (false);
// N
			setQtyEntered (Env.ZERO);
			setSalesOrderNumber (null);
			setSizeCustomer (null);
			setSizeFactory (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_ProdOutputDetail (Properties ctx, String MES_ProdOutputDetail_UU, String trxName)
    {
      super (ctx, MES_ProdOutputDetail_UU, trxName);
      /** if (MES_ProdOutputDetail_UU == null)
        {
			setArticle (null);
			setBarcode (null);
			setMES_ProdOutputDetail_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setPOReference (null);
			setPostConfirmationNo (null);
			setPostNo (null);
			setPostStatus (false);
// N
			setQtyEntered (Env.ZERO);
			setSalesOrderNumber (null);
			setSizeCustomer (null);
			setSizeFactory (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_ProdOutputDetail (Properties ctx, String MES_ProdOutputDetail_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_ProdOutputDetail_UU, trxName, virtualColumns);
      /** if (MES_ProdOutputDetail_UU == null)
        {
			setArticle (null);
			setBarcode (null);
			setMES_ProdOutputDetail_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setPOReference (null);
			setPostConfirmationNo (null);
			setPostNo (null);
			setPostStatus (false);
// N
			setQtyEntered (Env.ZERO);
			setSalesOrderNumber (null);
			setSizeCustomer (null);
			setSizeFactory (null);
        } */
    }

    /** Load Constructor */
    public X_MES_ProdOutputDetail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_MES_ProdOutputDetail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Article.
		@param Article Article
	*/
	public void setArticle (String Article)
	{
		set_Value (COLUMNNAME_Article, Article);
	}

	/** Get Article.
		@return Article	  */
	public String getArticle()
	{
		return (String)get_Value(COLUMNNAME_Article);
	}

	/** Set Barcode.
		@param Barcode Barcode
	*/
	public void setBarcode (String Barcode)
	{
		set_Value (COLUMNNAME_Barcode, Barcode);
	}

	/** Get Barcode.
		@return Barcode	  */
	public String getBarcode()
	{
		return (String)get_Value(COLUMNNAME_Barcode);
	}

	/** Set Kanban Number.
		@param KanbanNumber Kanban Number
	*/
	public void setKanbanNumber (String KanbanNumber)
	{
		set_Value (COLUMNNAME_KanbanNumber, KanbanNumber);
	}

	/** Get Kanban Number.
		@return Kanban Number	  */
	public String getKanbanNumber()
	{
		return (String)get_Value(COLUMNNAME_KanbanNumber);
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

	/** Set Production Output Detail.
		@param MES_ProdOutputDetail_ID Production Output Detail
	*/
	public void setMES_ProdOutputDetail_ID (int MES_ProdOutputDetail_ID)
	{
		if (MES_ProdOutputDetail_ID < 1)
			set_ValueNoCheck (COLUMNNAME_MES_ProdOutputDetail_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_MES_ProdOutputDetail_ID, Integer.valueOf(MES_ProdOutputDetail_ID));
	}

	/** Get Production Output Detail.
		@return Production Output Detail	  */
	public int getMES_ProdOutputDetail_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MES_ProdOutputDetail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MES_ProdOutputDetail_UU.
		@param MES_ProdOutputDetail_UU MES_ProdOutputDetail_UU
	*/
	public void setMES_ProdOutputDetail_UU (String MES_ProdOutputDetail_UU)
	{
		set_Value (COLUMNNAME_MES_ProdOutputDetail_UU, MES_ProdOutputDetail_UU);
	}

	/** Get MES_ProdOutputDetail_UU.
		@return MES_ProdOutputDetail_UU	  */
	public String getMES_ProdOutputDetail_UU()
	{
		return (String)get_Value(COLUMNNAME_MES_ProdOutputDetail_UU);
	}

	public I_MES_ProdOutput getMES_ProdOutput() throws RuntimeException
	{
		return (I_MES_ProdOutput)MTable.get(getCtx(), I_MES_ProdOutput.Table_ID)
			.getPO(getMES_ProdOutput_ID(), get_TrxName());
	}

	/** Set MES_ProdOutput.
		@param MES_ProdOutput_ID MES_ProdOutput
	*/
	public void setMES_ProdOutput_ID (int MES_ProdOutput_ID)
	{
		if (MES_ProdOutput_ID < 1)
			set_ValueNoCheck (COLUMNNAME_MES_ProdOutput_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_MES_ProdOutput_ID, Integer.valueOf(MES_ProdOutput_ID));
	}

	/** Get MES_ProdOutput.
		@return MES_ProdOutput	  */
	public int getMES_ProdOutput_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MES_ProdOutput_ID);
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

	/** MovementType AD_Reference_ID=189 */
	public static final int MOVEMENTTYPE_AD_Reference_ID=189;
	/** Customer Returns = C+ */
	public static final String MOVEMENTTYPE_CustomerReturns = "C+";
	/** Customer Shipment = C- */
	public static final String MOVEMENTTYPE_CustomerShipment = "C-";
	/** Inventory In = I+ */
	public static final String MOVEMENTTYPE_InventoryIn = "I+";
	/** Inventory Out = I- */
	public static final String MOVEMENTTYPE_InventoryOut = "I-";
	/** Movement To = M+ */
	public static final String MOVEMENTTYPE_MovementTo = "M+";
	/** Movement From = M- */
	public static final String MOVEMENTTYPE_MovementFrom = "M-";
	/** Production + = P+ */
	public static final String MOVEMENTTYPE_ProductionPlus = "P+";
	/** Production - = P- */
	public static final String MOVEMENTTYPE_Production_ = "P-";
	/** Vendor Receipts = V+ */
	public static final String MOVEMENTTYPE_VendorReceipts = "V+";
	/** Vendor Returns = V- */
	public static final String MOVEMENTTYPE_VendorReturns = "V-";
	/** Work Order + = W+ */
	public static final String MOVEMENTTYPE_WorkOrderPlus = "W+";
	/** Work Order - = W- */
	public static final String MOVEMENTTYPE_WorkOrder_ = "W-";
	/** Set Movement Type.
		@param MovementType Method of moving the inventory
	*/
	public void setMovementType (String MovementType)
	{

		set_Value (COLUMNNAME_MovementType, MovementType);
	}

	/** Get Movement Type.
		@return Method of moving the inventory
	  */
	public String getMovementType()
	{
		return (String)get_Value(COLUMNNAME_MovementType);
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

	/** Set Order Reference.
		@param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	*/
	public void setPOReference (String POReference)
	{
		set_Value (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference()
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	/** Set PostConfirmationNo.
		@param PostConfirmationNo PostConfirmationNo
	*/
	public void setPostConfirmationNo (String PostConfirmationNo)
	{
		set_Value (COLUMNNAME_PostConfirmationNo, PostConfirmationNo);
	}

	/** Get PostConfirmationNo.
		@return PostConfirmationNo	  */
	public String getPostConfirmationNo()
	{
		return (String)get_Value(COLUMNNAME_PostConfirmationNo);
	}

	/** Set PostDate.
		@param PostDate PostDate
	*/
	public void setPostDate (Timestamp PostDate)
	{
		set_Value (COLUMNNAME_PostDate, PostDate);
	}

	/** Get PostDate.
		@return PostDate	  */
	public Timestamp getPostDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_PostDate);
	}

	/** Set PostMessage.
		@param PostMessage PostMessage
	*/
	public void setPostMessage (String PostMessage)
	{
		set_Value (COLUMNNAME_PostMessage, PostMessage);
	}

	/** Get PostMessage.
		@return PostMessage	  */
	public String getPostMessage()
	{
		return (String)get_Value(COLUMNNAME_PostMessage);
	}

	/** Set PostNo.
		@param PostNo PostNo
	*/
	public void setPostNo (String PostNo)
	{
		set_Value (COLUMNNAME_PostNo, PostNo);
	}

	/** Get PostNo.
		@return PostNo	  */
	public String getPostNo()
	{
		return (String)get_Value(COLUMNNAME_PostNo);
	}

	/** Set PostStatus.
		@param PostStatus PostStatus
	*/
	public void setPostStatus (boolean PostStatus)
	{
		set_Value (COLUMNNAME_PostStatus, Boolean.valueOf(PostStatus));
	}

	/** Get PostStatus.
		@return PostStatus	  */
	public boolean isPostStatus()
	{
		Object oo = get_Value(COLUMNNAME_PostStatus);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity.
		@param QtyEntered The Quantity Entered is based on the selected UoM
	*/
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Size Customer.
		@param SizeCustomer Size Customer
	*/
	public void setSizeCustomer (String SizeCustomer)
	{
		set_Value (COLUMNNAME_SizeCustomer, SizeCustomer);
	}

	/** Get Size Customer.
		@return Size Customer	  */
	public String getSizeCustomer()
	{
		return (String)get_Value(COLUMNNAME_SizeCustomer);
	}

	/** Set Size Factory.
		@param SizeFactory Size Factory
	*/
	public void setSizeFactory (String SizeFactory)
	{
		set_Value (COLUMNNAME_SizeFactory, SizeFactory);
	}

	/** Get Size Factory.
		@return Size Factory	  */
	public String getSizeFactory()
	{
		return (String)get_Value(COLUMNNAME_SizeFactory);
	}
}