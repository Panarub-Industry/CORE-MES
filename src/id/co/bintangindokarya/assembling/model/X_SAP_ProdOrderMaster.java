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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for SAP_ProdOrderMaster
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_ProdOrderMaster")
public class X_SAP_ProdOrderMaster extends PO implements I_SAP_ProdOrderMaster, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250828L;

    /** Standard Constructor */
    public X_SAP_ProdOrderMaster (Properties ctx, int SAP_ProdOrderMaster_ID, String trxName)
    {
      super (ctx, SAP_ProdOrderMaster_ID, trxName);
      /** if (SAP_ProdOrderMaster_ID == 0)
        {
			setMasterProductionOrder (null);
			setPOReference (null);
			setSAP_ProdOrderMaster_ID (0);
			setSAP_Sloc_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrderMaster (Properties ctx, int SAP_ProdOrderMaster_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProdOrderMaster_ID, trxName, virtualColumns);
      /** if (SAP_ProdOrderMaster_ID == 0)
        {
			setMasterProductionOrder (null);
			setPOReference (null);
			setSAP_ProdOrderMaster_ID (0);
			setSAP_Sloc_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrderMaster (Properties ctx, String SAP_ProdOrderMaster_UU, String trxName)
    {
      super (ctx, SAP_ProdOrderMaster_UU, trxName);
      /** if (SAP_ProdOrderMaster_UU == null)
        {
			setMasterProductionOrder (null);
			setPOReference (null);
			setSAP_ProdOrderMaster_ID (0);
			setSAP_Sloc_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProdOrderMaster (Properties ctx, String SAP_ProdOrderMaster_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProdOrderMaster_UU, trxName, virtualColumns);
      /** if (SAP_ProdOrderMaster_UU == null)
        {
			setMasterProductionOrder (null);
			setPOReference (null);
			setSAP_ProdOrderMaster_ID (0);
			setSAP_Sloc_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Load Constructor */
    public X_SAP_ProdOrderMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_ProdOrderMaster[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set SAP_ProdOrderMaster_UU.
		@param SAP_ProdOrderMaster_UU SAP_ProdOrderMaster_UU
	*/
	public void setSAP_ProdOrderMaster_UU (String SAP_ProdOrderMaster_UU)
	{
		set_Value (COLUMNNAME_SAP_ProdOrderMaster_UU, SAP_ProdOrderMaster_UU);
	}

	/** Get SAP_ProdOrderMaster_UU.
		@return SAP_ProdOrderMaster_UU	  */
	public String getSAP_ProdOrderMaster_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_ProdOrderMaster_UU);
	}

	public I_SAP_Sloc getSAP_Sloc() throws RuntimeException
	{
		return (I_SAP_Sloc)MTable.get(getCtx(), I_SAP_Sloc.Table_ID)
			.getPO(getSAP_Sloc_ID(), get_TrxName());
	}

	/** Set Storage Location.
		@param SAP_Sloc_ID Storage Location
	*/
	public void setSAP_Sloc_ID (int SAP_Sloc_ID)
	{
		if (SAP_Sloc_ID < 1)
			set_Value (COLUMNNAME_SAP_Sloc_ID, null);
		else
			set_Value (COLUMNNAME_SAP_Sloc_ID, Integer.valueOf(SAP_Sloc_ID));
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
}