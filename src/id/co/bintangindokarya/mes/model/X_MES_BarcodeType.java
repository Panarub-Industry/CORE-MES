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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for MES_BarcodeType
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="MES_BarcodeType")
public class X_MES_BarcodeType extends PO implements I_MES_BarcodeType, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250827L;

    /** Standard Constructor */
    public X_MES_BarcodeType (Properties ctx, int MES_BarcodeType_ID, String trxName)
    {
      super (ctx, MES_BarcodeType_ID, trxName);
      /** if (MES_BarcodeType_ID == 0)
        {
			setMES_BarcodeType_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_BarcodeType (Properties ctx, int MES_BarcodeType_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_BarcodeType_ID, trxName, virtualColumns);
      /** if (MES_BarcodeType_ID == 0)
        {
			setMES_BarcodeType_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_BarcodeType (Properties ctx, String MES_BarcodeType_UU, String trxName)
    {
      super (ctx, MES_BarcodeType_UU, trxName);
      /** if (MES_BarcodeType_UU == null)
        {
			setMES_BarcodeType_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_BarcodeType (Properties ctx, String MES_BarcodeType_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_BarcodeType_UU, trxName, virtualColumns);
      /** if (MES_BarcodeType_UU == null)
        {
			setMES_BarcodeType_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_MES_BarcodeType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_MES_BarcodeType[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set MES Barcode Type.
		@param MES_BarcodeType_ID MES Barcode Type
	*/
	public void setMES_BarcodeType_ID (int MES_BarcodeType_ID)
	{
		if (MES_BarcodeType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_MES_BarcodeType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_MES_BarcodeType_ID, Integer.valueOf(MES_BarcodeType_ID));
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

	/** Set MES_BarcodeType_UU.
		@param MES_BarcodeType_UU MES_BarcodeType_UU
	*/
	public void setMES_BarcodeType_UU (String MES_BarcodeType_UU)
	{
		set_Value (COLUMNNAME_MES_BarcodeType_UU, MES_BarcodeType_UU);
	}

	/** Get MES_BarcodeType_UU.
		@return MES_BarcodeType_UU	  */
	public String getMES_BarcodeType_UU()
	{
		return (String)get_Value(COLUMNNAME_MES_BarcodeType_UU);
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_ValueNoCheck (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_ValueNoCheck (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}