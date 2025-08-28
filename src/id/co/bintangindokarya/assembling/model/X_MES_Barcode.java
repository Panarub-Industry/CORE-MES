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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for MES_Barcode
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="MES_Barcode")
public class X_MES_Barcode extends PO implements I_MES_Barcode, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250827L;

    /** Standard Constructor */
    public X_MES_Barcode (Properties ctx, int MES_Barcode_ID, String trxName)
    {
      super (ctx, MES_Barcode_ID, trxName);
      /** if (MES_Barcode_ID == 0)
        {
			setBatchDocumentNo (null);
			setCustomerNo (null);
			setMES_BarcodeType_ID (0);
			setMES_Barcode_ID (0);
			setM_Product_ID (0);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setQtyPack (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_MES_Barcode (Properties ctx, int MES_Barcode_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_Barcode_ID, trxName, virtualColumns);
      /** if (MES_Barcode_ID == 0)
        {
			setBatchDocumentNo (null);
			setCustomerNo (null);
			setMES_BarcodeType_ID (0);
			setMES_Barcode_ID (0);
			setM_Product_ID (0);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setQtyPack (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_MES_Barcode (Properties ctx, String MES_Barcode_UU, String trxName)
    {
      super (ctx, MES_Barcode_UU, trxName);
      /** if (MES_Barcode_UU == null)
        {
			setBatchDocumentNo (null);
			setCustomerNo (null);
			setMES_BarcodeType_ID (0);
			setMES_Barcode_ID (0);
			setM_Product_ID (0);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setQtyPack (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_MES_Barcode (Properties ctx, String MES_Barcode_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_Barcode_UU, trxName, virtualColumns);
      /** if (MES_Barcode_UU == null)
        {
			setBatchDocumentNo (null);
			setCustomerNo (null);
			setMES_BarcodeType_ID (0);
			setMES_Barcode_ID (0);
			setM_Product_ID (0);
			setPOReference (null);
			setQtyOrdered (Env.ZERO);
			setQtyPack (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_MES_Barcode (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_MES_Barcode[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Cutting Number.
		@param CuttingNumber Cutting Number
	*/
	public void setCuttingNumber (String CuttingNumber)
	{
		set_Value (COLUMNNAME_CuttingNumber, CuttingNumber);
	}

	/** Get Cutting Number.
		@return Cutting Number	  */
	public String getCuttingNumber()
	{
		return (String)get_Value(COLUMNNAME_CuttingNumber);
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

	public I_MES_BarcodeType getMES_BarcodeType() throws RuntimeException
	{
		return (I_MES_BarcodeType)MTable.get(getCtx(), I_MES_BarcodeType.Table_ID)
			.getPO(getMES_BarcodeType_ID(), get_TrxName());
	}

	/** Set MES Barcode Type.
		@param MES_BarcodeType_ID MES Barcode Type
	*/
	public void setMES_BarcodeType_ID (int MES_BarcodeType_ID)
	{
		if (MES_BarcodeType_ID < 1)
			set_Value (COLUMNNAME_MES_BarcodeType_ID, null);
		else
			set_Value (COLUMNNAME_MES_BarcodeType_ID, Integer.valueOf(MES_BarcodeType_ID));
	}

	/** Get MES Barcode Type.
		@return MES Barcode Type	  */
	public int getMES_BarcodeType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MES_BarcodeType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Production Barcode.
		@param MES_Barcode_ID Production barcode header
	*/
	public void setMES_Barcode_ID (int MES_Barcode_ID)
	{
		if (MES_Barcode_ID < 1)
			set_ValueNoCheck (COLUMNNAME_MES_Barcode_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_MES_Barcode_ID, Integer.valueOf(MES_Barcode_ID));
	}

	/** Get Production Barcode.
		@return Production barcode header
	  */
	public int getMES_Barcode_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MES_Barcode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MES_Barcode_UU.
		@param MES_Barcode_UU MES_Barcode_UU
	*/
	public void setMES_Barcode_UU (String MES_Barcode_UU)
	{
		set_Value (COLUMNNAME_MES_Barcode_UU, MES_Barcode_UU);
	}

	/** Get MES_Barcode_UU.
		@return MES_Barcode_UU	  */
	public String getMES_Barcode_UU()
	{
		return (String)get_Value(COLUMNNAME_MES_Barcode_UU);
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

	/** Set Qty Pack.
		@param QtyPack Qty Pack
	*/
	public void setQtyPack (BigDecimal QtyPack)
	{
		set_Value (COLUMNNAME_QtyPack, QtyPack);
	}

	/** Get Qty Pack.
		@return Qty Pack	  */
	public BigDecimal getQtyPack()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPack);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Production Order Master.
		@param SAP_ProdOrderMaster_ID Production Order Master
	*/
	public void setSAP_ProdOrderMaster_ID (int SAP_ProdOrderMaster_ID)
	{
		if (SAP_ProdOrderMaster_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_ProdOrderMaster_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_ProdOrderMaster_ID, Integer.valueOf(SAP_ProdOrderMaster_ID));
	}

	/** Get Production Order Master.
		@return Production Order Master	  */
	public int getSAP_ProdOrderMaster_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_ProdOrderMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Storage Location.
		@param SAP_Sloc_ID Storage Location
	*/
	public void setSAP_Sloc_ID (int SAP_Sloc_ID)
	{
		if (SAP_Sloc_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_Sloc_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_Sloc_ID, Integer.valueOf(SAP_Sloc_ID));
	}

	/** Get Storage Location.
		@return Storage Location	  */
	public int getSAP_Sloc_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_Sloc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Total Package.
		@param TotalPack Total Package
	*/
	public void setTotalPack (BigDecimal TotalPack)
	{
		set_Value (COLUMNNAME_TotalPack, TotalPack);
	}

	/** Get Total Package.
		@return Total Package	  */
	public BigDecimal getTotalPack()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalPack);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}