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
package id.co.bintangindokarya.mes.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for V_MES_ProductionInfo
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_V_MES_ProductionInfo 
{

    /** TableName=V_MES_ProductionInfo */
    public static final String Table_Name = "V_MES_ProductionInfo";

    /** AD_Table_ID=1000032 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Tenant.
	  * Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within tenant
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within tenant
	  */
	public int getAD_Org_ID();

    /** Column name Last_Trans_Created */
    public static final String COLUMNNAME_Last_Trans_Created = "Last_Trans_Created";

	/** Set Last_Trans_Created	  */
	public void setLast_Trans_Created (Timestamp Last_Trans_Created);

	/** Get Last_Trans_Created	  */
	public Timestamp getLast_Trans_Created();

    /** Column name MasterProductionOrder */
    public static final String COLUMNNAME_MasterProductionOrder = "MasterProductionOrder";

	/** Set Master Production Order	  */
	public void setMasterProductionOrder (String MasterProductionOrder);

	/** Get Master Production Order	  */
	public String getMasterProductionOrder();

    /** Column name OrderNumber */
    public static final String COLUMNNAME_OrderNumber = "OrderNumber";

	/** Set Order Number	  */
	public void setOrderNumber (String OrderNumber);

	/** Get Order Number	  */
	public String getOrderNumber();

    /** Column name POReference */
    public static final String COLUMNNAME_POReference = "POReference";

	/** Set Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference);

	/** Get Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference();

    /** Column name Product */
    public static final String COLUMNNAME_Product = "Product";

	/** Set Product	  */
	public void setProduct (String Product);

	/** Get Product	  */
	public String getProduct();

    /** Column name ProductCategory */
    public static final String COLUMNNAME_ProductCategory = "ProductCategory";

	/** Set ProductCategory	  */
	public void setProductCategory (String ProductCategory);

	/** Get ProductCategory	  */
	public String getProductCategory();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name QtyOutput */
    public static final String COLUMNNAME_QtyOutput = "QtyOutput";

	/** Set QtyOutput	  */
	public void setQtyOutput (BigDecimal QtyOutput);

	/** Get QtyOutput	  */
	public BigDecimal getQtyOutput();

    /** Column name QtyOutstanding */
    public static final String COLUMNNAME_QtyOutstanding = "QtyOutstanding";

	/** Set QtyOutstanding.
	  * Remaining quantity to be produced
	  */
	public void setQtyOutstanding (BigDecimal QtyOutstanding);

	/** Get QtyOutstanding.
	  * Remaining quantity to be produced
	  */
	public BigDecimal getQtyOutstanding();

    /** Column name QtyPlanned */
    public static final String COLUMNNAME_QtyPlanned = "QtyPlanned";

	/** Set QtyPlanned	  */
	public void setQtyPlanned (BigDecimal QtyPlanned);

	/** Get QtyPlanned	  */
	public BigDecimal getQtyPlanned();

    /** Column name SAP_WorkCenter_ID */
    public static final String COLUMNNAME_SAP_WorkCenter_ID = "SAP_WorkCenter_ID";

	/** Set SAP_WorkCenter	  */
	public void setSAP_WorkCenter_ID (int SAP_WorkCenter_ID);

	/** Get SAP_WorkCenter	  */
	public int getSAP_WorkCenter_ID();

    /** Column name SalesOrderNumber */
    public static final String COLUMNNAME_SalesOrderNumber = "SalesOrderNumber";

	/** Set Sales Order Number	  */
	public void setSalesOrderNumber (String SalesOrderNumber);

	/** Get Sales Order Number	  */
	public String getSalesOrderNumber();

    /** Column name SizeFactory */
    public static final String COLUMNNAME_SizeFactory = "SizeFactory";

	/** Set Size Factory	  */
	public void setSizeFactory (String SizeFactory);

	/** Get Size Factory	  */
	public String getSizeFactory();

    /** Column name Sloc */
    public static final String COLUMNNAME_Sloc = "Sloc";

	/** Set Sloc	  */
	public void setSloc (String Sloc);

	/** Get Sloc	  */
	public String getSloc();

    /** Column name SlocName */
    public static final String COLUMNNAME_SlocName = "SlocName";

	/** Set SlocName	  */
	public void setSlocName (String SlocName);

	/** Get SlocName	  */
	public String getSlocName();

    /** Column name SlocReceiving */
    public static final String COLUMNNAME_SlocReceiving = "SlocReceiving";

	/** Set Sloc Receiving	  */
	public void setSlocReceiving (String SlocReceiving);

	/** Get Sloc Receiving	  */
	public String getSlocReceiving();

    /** Column name V_MES_ProductionInfo_ID */
    public static final String COLUMNNAME_V_MES_ProductionInfo_ID = "V_MES_ProductionInfo_ID";

	/** Set MES Production Info	  */
	public void setV_MES_ProductionInfo_ID (int V_MES_ProductionInfo_ID);

	/** Get MES Production Info	  */
	public int getV_MES_ProductionInfo_ID();

    /** Column name Variant */
    public static final String COLUMNNAME_Variant = "Variant";

	/** Set Variant	  */
	public void setVariant (String Variant);

	/** Get Variant	  */
	public String getVariant();

    /** Column name WorkCenter */
    public static final String COLUMNNAME_WorkCenter = "WorkCenter";

	/** Set WorkCenter	  */
	public void setWorkCenter (String WorkCenter);

	/** Get WorkCenter	  */
	public String getWorkCenter();

    /** Column name WorkCenterName */
    public static final String COLUMNNAME_WorkCenterName = "WorkCenterName";

	/** Set WorkCenterName	  */
	public void setWorkCenterName (String WorkCenterName);

	/** Get WorkCenterName	  */
	public String getWorkCenterName();
}
