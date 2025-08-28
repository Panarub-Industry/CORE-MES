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

/** Generated Model for SAP_WorkCenter
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_WorkCenter")
public class X_SAP_WorkCenter extends PO implements I_SAP_WorkCenter, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250828L;

    /** Standard Constructor */
    public X_SAP_WorkCenter (Properties ctx, int SAP_WorkCenter_ID, String trxName)
    {
      super (ctx, SAP_WorkCenter_ID, trxName);
      /** if (SAP_WorkCenter_ID == 0)
        {
			setName (null);
			setSAP_Sloc_ID (0);
			setSAP_WorkCenter_ID (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_WorkCenter (Properties ctx, int SAP_WorkCenter_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_WorkCenter_ID, trxName, virtualColumns);
      /** if (SAP_WorkCenter_ID == 0)
        {
			setName (null);
			setSAP_Sloc_ID (0);
			setSAP_WorkCenter_ID (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_WorkCenter (Properties ctx, String SAP_WorkCenter_UU, String trxName)
    {
      super (ctx, SAP_WorkCenter_UU, trxName);
      /** if (SAP_WorkCenter_UU == null)
        {
			setName (null);
			setSAP_Sloc_ID (0);
			setSAP_WorkCenter_ID (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_SAP_WorkCenter (Properties ctx, String SAP_WorkCenter_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_WorkCenter_UU, trxName, virtualColumns);
      /** if (SAP_WorkCenter_UU == null)
        {
			setName (null);
			setSAP_Sloc_ID (0);
			setSAP_WorkCenter_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_SAP_WorkCenter (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_WorkCenter[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
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

	/** Set SAP_WorkCenter_UU.
		@param SAP_WorkCenter_UU SAP_WorkCenter_UU
	*/
	public void setSAP_WorkCenter_UU (String SAP_WorkCenter_UU)
	{
		set_Value (COLUMNNAME_SAP_WorkCenter_UU, SAP_WorkCenter_UU);
	}

	/** Get SAP_WorkCenter_UU.
		@return SAP_WorkCenter_UU	  */
	public String getSAP_WorkCenter_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_WorkCenter_UU);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}