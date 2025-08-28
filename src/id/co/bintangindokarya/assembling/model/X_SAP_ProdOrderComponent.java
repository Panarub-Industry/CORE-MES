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

/** Generated Model for SAP_ProdOrderComponent
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_ProdOrderComponent")
public class X_SAP_ProdOrderComponent extends PO implements I_SAP_ProdOrderComponent, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250828L;

    /** Standard Constructor */
    public X_SAP_ProdOrderComponent (Properties ctx, int SAP_ProdOrderComponent_ID, String trxName)
    {
      super (ctx, SAP_ProdOrderComponent_ID, trxName);
      /** if (SAP_ProdOrderComponent_ID == 0)
        {
			setC_UOM_ID (0);
			setProcurementType (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrderComponent_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrderComponent (Properties ctx, int SAP_ProdOrderComponent_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProdOrderComponent_ID, trxName, virtualColumns);
      /** if (SAP_ProdOrderComponent_ID == 0)
        {
			setC_UOM_ID (0);
			setProcurementType (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrderComponent_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrderComponent (Properties ctx, String SAP_ProdOrderComponent_UU, String trxName)
    {
      super (ctx, SAP_ProdOrderComponent_UU, trxName);
      /** if (SAP_ProdOrderComponent_UU == null)
        {
			setC_UOM_ID (0);
			setProcurementType (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrderComponent_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrderComponent (Properties ctx, String SAP_ProdOrderComponent_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProdOrderComponent_UU, trxName, virtualColumns);
      /** if (SAP_ProdOrderComponent_UU == null)
        {
			setC_UOM_ID (0);
			setProcurementType (null);
			setQtyOrdered (Env.ZERO);
			setSAP_ProdOrderComponent_ID (0);
        } */
    }

    /** Load Constructor */
    public X_SAP_ProdOrderComponent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_ProdOrderComponent[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Procurement Type.
		@param ProcurementType Procurement Type
	*/
	public void setProcurementType (String ProcurementType)
	{
		set_Value (COLUMNNAME_ProcurementType, ProcurementType);
	}

	/** Get Procurement Type.
		@return Procurement Type	  */
	public String getProcurementType()
	{
		return (String)get_Value(COLUMNNAME_ProcurementType);
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

	/** Set Reservation Item.
		@param ReservationItem Reservation Item
	*/
	public void setReservationItem (String ReservationItem)
	{
		set_Value (COLUMNNAME_ReservationItem, ReservationItem);
	}

	/** Get Reservation Item.
		@return Reservation Item	  */
	public String getReservationItem()
	{
		return (String)get_Value(COLUMNNAME_ReservationItem);
	}

	/** Set Reservation Number.
		@param ReservationNumber Reservation Number
	*/
	public void setReservationNumber (String ReservationNumber)
	{
		set_Value (COLUMNNAME_ReservationNumber, ReservationNumber);
	}

	/** Get Reservation Number.
		@return Reservation Number	  */
	public String getReservationNumber()
	{
		return (String)get_Value(COLUMNNAME_ReservationNumber);
	}

	/** Set SAP_ProdOrderComponent.
		@param SAP_ProdOrderComponent_ID SAP_ProdOrderComponent
	*/
	public void setSAP_ProdOrderComponent_ID (int SAP_ProdOrderComponent_ID)
	{
		if (SAP_ProdOrderComponent_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_ProdOrderComponent_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_ProdOrderComponent_ID, Integer.valueOf(SAP_ProdOrderComponent_ID));
	}

	/** Get SAP_ProdOrderComponent.
		@return SAP_ProdOrderComponent	  */
	public int getSAP_ProdOrderComponent_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_ProdOrderComponent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SAP_ProdOrderComponent_UU.
		@param SAP_ProdOrderComponent_UU SAP_ProdOrderComponent_UU
	*/
	public void setSAP_ProdOrderComponent_UU (String SAP_ProdOrderComponent_UU)
	{
		set_Value (COLUMNNAME_SAP_ProdOrderComponent_UU, SAP_ProdOrderComponent_UU);
	}

	/** Get SAP_ProdOrderComponent_UU.
		@return SAP_ProdOrderComponent_UU	  */
	public String getSAP_ProdOrderComponent_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_ProdOrderComponent_UU);
	}

	public I_SAP_ProdOrder getSAP_ProdOrder() throws RuntimeException
	{
		return (I_SAP_ProdOrder)MTable.get(getCtx(), I_SAP_ProdOrder.Table_ID)
			.getPO(getSAP_ProdOrder_ID(), get_TrxName());
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

	/** Set Special Procurement Type.
		@param SpecialProcurementType Special Procurement Type
	*/
	public void setSpecialProcurementType (String SpecialProcurementType)
	{
		set_Value (COLUMNNAME_SpecialProcurementType, SpecialProcurementType);
	}

	/** Get Special Procurement Type.
		@return Special Procurement Type	  */
	public String getSpecialProcurementType()
	{
		return (String)get_Value(COLUMNNAME_SpecialProcurementType);
	}
}