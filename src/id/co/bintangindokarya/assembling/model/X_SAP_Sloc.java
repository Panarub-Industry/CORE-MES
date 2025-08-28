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

/** Generated Model for SAP_Sloc
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_Sloc")
public class X_SAP_Sloc extends PO implements I_SAP_Sloc, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250828L;

    /** Standard Constructor */
    public X_SAP_Sloc (Properties ctx, int SAP_Sloc_ID, String trxName)
    {
      super (ctx, SAP_Sloc_ID, trxName);
      /** if (SAP_Sloc_ID == 0)
        {
			setName (null);
			setSAP_Sloc_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_Sloc (Properties ctx, int SAP_Sloc_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_Sloc_ID, trxName, virtualColumns);
      /** if (SAP_Sloc_ID == 0)
        {
			setName (null);
			setSAP_Sloc_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_Sloc (Properties ctx, String SAP_Sloc_UU, String trxName)
    {
      super (ctx, SAP_Sloc_UU, trxName);
      /** if (SAP_Sloc_UU == null)
        {
			setName (null);
			setSAP_Sloc_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_Sloc (Properties ctx, String SAP_Sloc_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_Sloc_UU, trxName, virtualColumns);
      /** if (SAP_Sloc_UU == null)
        {
			setName (null);
			setSAP_Sloc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_SAP_Sloc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_Sloc[")
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

	/** Set SAP_Sloc_UU.
		@param SAP_Sloc_UU SAP_Sloc_UU
	*/
	public void setSAP_Sloc_UU (String SAP_Sloc_UU)
	{
		set_Value (COLUMNNAME_SAP_Sloc_UU, SAP_Sloc_UU);
	}

	/** Get SAP_Sloc_UU.
		@return SAP_Sloc_UU	  */
	public String getSAP_Sloc_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_Sloc_UU);
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