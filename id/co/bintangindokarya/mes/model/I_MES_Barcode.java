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

/** Generated Interface for MES_Barcode
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_MES_Barcode 
{

    /** TableName=MES_Barcode */
    public static final String Table_Name = "MES_Barcode";

    /** AD_Table_ID=1000015 */
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

    /** Column name BatchDocumentNo */
    public static final String COLUMNNAME_BatchDocumentNo = "BatchDocumentNo";

	/** Set Batch Document No.
	  * Document Number of the Batch
	  */
	public void setBatchDocumentNo (String BatchDocumentNo);

	/** Get Batch Document No.
	  * Document Number of the Batch
	  */
	public String getBatchDocumentNo();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CustomerNo */
    public static final String COLUMNNAME_CustomerNo = "CustomerNo";

	/** Set Customer No.
	  * EDI Identification Number 
	  */
	public void setCustomerNo (String CustomerNo);

	/** Get Customer No.
	  * EDI Identification Number 
	  */
	public String getCustomerNo();

    /** Column name CuttingNumber */
    public static final String COLUMNNAME_CuttingNumber = "CuttingNumber";

	/** Set Cutting Number	  */
	public void setCuttingNumber (String CuttingNumber);

	/** Get Cutting Number	  */
	public String getCuttingNumber();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name LineAggregator */
    public static final String COLUMNNAME_LineAggregator = "LineAggregator";

	/** Set Line Aggregator	  */
	public void setLineAggregator (String LineAggregator);

	/** Get Line Aggregator	  */
	public String getLineAggregator();

    /** Column name MES_BarcodeType_ID */
    public static final String COLUMNNAME_MES_BarcodeType_ID = "MES_BarcodeType_ID";

	/** Set MES Barcode Type	  */
	public void setMES_BarcodeType_ID (int MES_BarcodeType_ID);

	/** Get MES Barcode Type	  */
	public int getMES_BarcodeType_ID();

	public I_MES_BarcodeType getMES_BarcodeType() throws RuntimeException;

    /** Column name MES_Barcode_ID */
    public static final String COLUMNNAME_MES_Barcode_ID = "MES_Barcode_ID";

	/** Set ID.
	  * Production barcode header
	  */
	public void setMES_Barcode_ID (int MES_Barcode_ID);

	/** Get ID.
	  * Production barcode header
	  */
	public int getMES_Barcode_ID();

    /** Column name MES_Barcode_UU */
    public static final String COLUMNNAME_MES_Barcode_UU = "MES_Barcode_UU";

	/** Set MES_Barcode_UU	  */
	public void setMES_Barcode_UU (String MES_Barcode_UU);

	/** Get MES_Barcode_UU	  */
	public String getMES_Barcode_UU();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name QtyPack */
    public static final String COLUMNNAME_QtyPack = "QtyPack";

	/** Set Qty Pack	  */
	public void setQtyPack (BigDecimal QtyPack);

	/** Get Qty Pack	  */
	public BigDecimal getQtyPack();

    /** Column name SAP_ProdOrderMaster_ID */
    public static final String COLUMNNAME_SAP_ProdOrderMaster_ID = "SAP_ProdOrderMaster_ID";

	/** Set Production Order Master	  */
	public void setSAP_ProdOrderMaster_ID (int SAP_ProdOrderMaster_ID);

	/** Get Production Order Master	  */
	public int getSAP_ProdOrderMaster_ID();

    /** Column name SAP_Sloc_ID */
    public static final String COLUMNNAME_SAP_Sloc_ID = "SAP_Sloc_ID";

	/** Set Storage Location	  */
	public void setSAP_Sloc_ID (int SAP_Sloc_ID);

	/** Get Storage Location	  */
	public int getSAP_Sloc_ID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
