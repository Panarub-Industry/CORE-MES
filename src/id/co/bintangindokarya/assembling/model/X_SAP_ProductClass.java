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

/** Generated Model for SAP_ProductClass
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SAP_ProductClass")
public class X_SAP_ProductClass extends PO implements I_SAP_ProductClass, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250828L;

    /** Standard Constructor */
    public X_SAP_ProductClass (Properties ctx, int SAP_ProductClass_ID, String trxName)
    {
      super (ctx, SAP_ProductClass_ID, trxName);
      /** if (SAP_ProductClass_ID == 0)
        {
			setAttribute (null);
			setAttributeName (null);
			setAttributeValue (null);
			setM_Product_ID (0);
			setSAP_ProductClass_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProductClass (Properties ctx, int SAP_ProductClass_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProductClass_ID, trxName, virtualColumns);
      /** if (SAP_ProductClass_ID == 0)
        {
			setAttribute (null);
			setAttributeName (null);
			setAttributeValue (null);
			setM_Product_ID (0);
			setSAP_ProductClass_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProductClass (Properties ctx, String SAP_ProductClass_UU, String trxName)
    {
      super (ctx, SAP_ProductClass_UU, trxName);
      /** if (SAP_ProductClass_UU == null)
        {
			setAttribute (null);
			setAttributeName (null);
			setAttributeValue (null);
			setM_Product_ID (0);
			setSAP_ProductClass_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_SAP_ProductClass (Properties ctx, String SAP_ProductClass_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SAP_ProductClass_UU, trxName, virtualColumns);
      /** if (SAP_ProductClass_UU == null)
        {
			setAttribute (null);
			setAttributeName (null);
			setAttributeValue (null);
			setM_Product_ID (0);
			setSAP_ProductClass_ID (0);
        } */
    }

    /** Load Constructor */
    public X_SAP_ProductClass (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SAP_ProductClass[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attribute.
		@param Attribute Attribute
	*/
	public void setAttribute (String Attribute)
	{
		set_Value (COLUMNNAME_Attribute, Attribute);
	}

	/** Get Attribute.
		@return Attribute	  */
	public String getAttribute()
	{
		return (String)get_Value(COLUMNNAME_Attribute);
	}

	/** Set Attribute Name.
		@param AttributeName Name of the Attribute
	*/
	public void setAttributeName (String AttributeName)
	{
		set_Value (COLUMNNAME_AttributeName, AttributeName);
	}

	/** Get Attribute Name.
		@return Name of the Attribute
	  */
	public String getAttributeName()
	{
		return (String)get_Value(COLUMNNAME_AttributeName);
	}

	/** Set Attribute Value.
		@param AttributeValue Value of the Attribute
	*/
	public void setAttributeValue (String AttributeValue)
	{
		set_Value (COLUMNNAME_AttributeValue, AttributeValue);
	}

	/** Get Attribute Value.
		@return Value of the Attribute
	  */
	public String getAttributeValue()
	{
		return (String)get_Value(COLUMNNAME_AttributeValue);
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
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Product Classification.
		@param SAP_ProductClass_ID Product Classification
	*/
	public void setSAP_ProductClass_ID (int SAP_ProductClass_ID)
	{
		if (SAP_ProductClass_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_ProductClass_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_ProductClass_ID, Integer.valueOf(SAP_ProductClass_ID));
	}

	/** Get Product Classification.
		@return Product Classification	  */
	public int getSAP_ProductClass_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_ProductClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SAP_ProductClass_UU.
		@param SAP_ProductClass_UU SAP_ProductClass_UU
	*/
	public void setSAP_ProductClass_UU (String SAP_ProductClass_UU)
	{
		set_Value (COLUMNNAME_SAP_ProductClass_UU, SAP_ProductClass_UU);
	}

	/** Get SAP_ProductClass_UU.
		@return SAP_ProductClass_UU	  */
	public String getSAP_ProductClass_UU()
	{
		return (String)get_Value(COLUMNNAME_SAP_ProductClass_UU);
	}
}