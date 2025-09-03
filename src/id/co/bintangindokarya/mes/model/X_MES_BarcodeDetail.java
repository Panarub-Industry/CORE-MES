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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for MES_BarcodeDetail
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="MES_BarcodeDetail")
public class X_MES_BarcodeDetail extends PO implements I_MES_BarcodeDetail, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250827L;

    /** Standard Constructor */
    public X_MES_BarcodeDetail (Properties ctx, int MES_BarcodeDetail_ID, String trxName)
    {
      super (ctx, MES_BarcodeDetail_ID, trxName);
      /** if (MES_BarcodeDetail_ID == 0)
        {
			setBarcode (null);
			setIsComplete (false);
// N
			setMES_BarcodeDetail_ID (0);
			setMES_Barcode_ID (0);
			setQtyBarcode (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_MES_BarcodeDetail (Properties ctx, int MES_BarcodeDetail_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_BarcodeDetail_ID, trxName, virtualColumns);
      /** if (MES_BarcodeDetail_ID == 0)
        {
			setBarcode (null);
			setIsComplete (false);
// N
			setMES_BarcodeDetail_ID (0);
			setMES_Barcode_ID (0);
			setQtyBarcode (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_MES_BarcodeDetail (Properties ctx, String MES_BarcodeDetail_UU, String trxName)
    {
      super (ctx, MES_BarcodeDetail_UU, trxName);
      /** if (MES_BarcodeDetail_UU == null)
        {
			setBarcode (null);
			setIsComplete (false);
// N
			setMES_BarcodeDetail_ID (0);
			setMES_Barcode_ID (0);
			setQtyBarcode (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_MES_BarcodeDetail (Properties ctx, String MES_BarcodeDetail_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_BarcodeDetail_UU, trxName, virtualColumns);
      /** if (MES_BarcodeDetail_UU == null)
        {
			setBarcode (null);
			setIsComplete (false);
// N
			setMES_BarcodeDetail_ID (0);
			setMES_Barcode_ID (0);
			setQtyBarcode (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_MES_BarcodeDetail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_MES_BarcodeDetail[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Complete.
		@param IsComplete It is complete
	*/
	public void setIsComplete (boolean IsComplete)
	{
		set_Value (COLUMNNAME_IsComplete, Boolean.valueOf(IsComplete));
	}

	/** Get Complete.
		@return It is complete
	  */
	public boolean isComplete()
	{
		Object oo = get_Value(COLUMNNAME_IsComplete);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set MES Production Barcode.
		@param MES_BarcodeDetail_ID MES Production Barcode
	*/
	public void setMES_BarcodeDetail_ID (int MES_BarcodeDetail_ID)
	{
		if (MES_BarcodeDetail_ID < 1)
			set_ValueNoCheck (COLUMNNAME_MES_BarcodeDetail_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_MES_BarcodeDetail_ID, Integer.valueOf(MES_BarcodeDetail_ID));
	}

	/** Get MES Production Barcode.
		@return MES Production Barcode	  */
	public int getMES_BarcodeDetail_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MES_BarcodeDetail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MES_ProdBarcode_UU.
		@param MES_BarcodeDetail_UU MES_ProdBarcode_UU
	*/
	public void setMES_BarcodeDetail_UU (String MES_BarcodeDetail_UU)
	{
		set_Value (COLUMNNAME_MES_BarcodeDetail_UU, MES_BarcodeDetail_UU);
	}

	/** Get MES_ProdBarcode_UU.
		@return MES_ProdBarcode_UU	  */
	public String getMES_BarcodeDetail_UU()
	{
		return (String)get_Value(COLUMNNAME_MES_BarcodeDetail_UU);
	}

	public I_MES_Barcode getMES_Barcode() throws RuntimeException
	{
		return (I_MES_Barcode)MTable.get(getCtx(), I_MES_Barcode.Table_ID)
			.getPO(getMES_Barcode_ID(), get_TrxName());
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

	/** Set ParentBarcode .
		@param ParentBarcode_ID ParentBarcode 
	*/
	public void setParentBarcode_ID (String ParentBarcode_ID)
	{
		set_ValueNoCheck (COLUMNNAME_ParentBarcode_ID, ParentBarcode_ID);
	}

	/** Get ParentBarcode .
		@return ParentBarcode 	  */
	public String getParentBarcode_ID()
	{
		return (String)get_Value(COLUMNNAME_ParentBarcode_ID);
	}

	/** Set Qty Barcode.
		@param QtyBarcode Qty Barcode
	*/
	public void setQtyBarcode (BigDecimal QtyBarcode)
	{
		set_Value (COLUMNNAME_QtyBarcode, QtyBarcode);
	}

	/** Get Qty Barcode.
		@return Qty Barcode	  */
	public BigDecimal getQtyBarcode()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBarcode);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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