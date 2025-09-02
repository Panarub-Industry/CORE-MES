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

/** Generated Model for SAP_Operation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_Operation")
public class X_SAP_Operation extends PO implements I_SAP_Operation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250901L;

    /** Standard Constructor */
    public X_SAP_Operation (Properties ctx, int SAP_Operation_ID, String trxName)
    {
      super (ctx, SAP_Operation_ID, trxName);
      /** if (SAP_Operation_ID == 0)
        {
			setOperationDesc (null);
			setOperationPhaseNumber (null);
			setSAP_Operation_ID (0);
			setSAP_ProdOrder_ID (0);
			setSAP_WorkCenter_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_Operation (Properties ctx, int SAP_Operation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_Operation_ID, trxName, virtualColumns);
      /** if (SAP_Operation_ID == 0)
        {
			setOperationDesc (null);
			setOperationPhaseNumber (null);
			setSAP_Operation_ID (0);
			setSAP_ProdOrder_ID (0);
			setSAP_WorkCenter_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_Operation (Properties ctx, String SAP_Operation_UU, String trxName)
    {
      super (ctx, SAP_Operation_UU, trxName);
      /** if (SAP_Operation_UU == null)
        {
			setOperationDesc (null);
			setOperationPhaseNumber (null);
			setSAP_Operation_ID (0);
			setSAP_ProdOrder_ID (0);
			setSAP_WorkCenter_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_Operation (Properties ctx, String SAP_Operation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_Operation_UU, trxName, virtualColumns);
      /** if (SAP_Operation_UU == null)
        {
			setOperationDesc (null);
			setOperationPhaseNumber (null);
			setSAP_Operation_ID (0);
			setSAP_ProdOrder_ID (0);
			setSAP_WorkCenter_ID (0);
        } */
    }

    /** Load Constructor */
    public X_SAP_Operation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_Operation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Operation Desc.
		@param OperationDesc Operation Desc
	*/
	public void setOperationDesc (String OperationDesc)
	{
		set_Value (COLUMNNAME_OperationDesc, OperationDesc);
	}

	/** Get Operation Desc.
		@return Operation Desc	  */
	public String getOperationDesc()
	{
		return (String)get_Value(COLUMNNAME_OperationDesc);
	}

	/** Set Operation Phase Number.
		@param OperationPhaseNumber Operation Phase Number
	*/
	public void setOperationPhaseNumber (String OperationPhaseNumber)
	{
		set_Value (COLUMNNAME_OperationPhaseNumber, OperationPhaseNumber);
	}

	/** Get Operation Phase Number.
		@return Operation Phase Number	  */
	public String getOperationPhaseNumber()
	{
		return (String)get_Value(COLUMNNAME_OperationPhaseNumber);
	}

	/** Set SAP_Operation.
		@param SAP_Operation_ID SAP_Operation
	*/
	public void setSAP_Operation_ID (int SAP_Operation_ID)
	{
		if (SAP_Operation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_Operation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_Operation_ID, Integer.valueOf(SAP_Operation_ID));
	}

	/** Get SAP_Operation.
		@return SAP_Operation	  */
	public int getSAP_Operation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_Operation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SAP_Operation_UU.
		@param SAP_Operation_UU SAP_Operation_UU
	*/
	public void setSAP_Operation_UU (String SAP_Operation_UU)
	{
		set_Value (COLUMNNAME_SAP_Operation_UU, SAP_Operation_UU);
	}

	/** Get SAP_Operation_UU.
		@return SAP_Operation_UU	  */
	public String getSAP_Operation_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_Operation_UU);
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

	public I_SAP_WorkCenter getSAP_WorkCenter() throws RuntimeException
	{
		return (I_SAP_WorkCenter)MTable.get(getCtx(), I_SAP_WorkCenter.Table_ID)
			.getPO(getSAP_WorkCenter_ID(), get_TrxName());
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
}