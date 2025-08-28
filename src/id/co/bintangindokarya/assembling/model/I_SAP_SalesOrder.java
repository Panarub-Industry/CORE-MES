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
package id.co.bintangindokarya.assembling.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for SAP_SalesOrder
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_SAP_SalesOrder 
{

    /** TableName=SAP_SalesOrder */
    public static final String Table_Name = "SAP_SalesOrder";

    /** AD_Table_ID=1000008 */
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

    /** Column name BillToParty */
    public static final String COLUMNNAME_BillToParty = "BillToParty";

	/** Set Bill To Party	  */
	public void setBillToParty (String BillToParty);

	/** Get Bill To Party	  */
	public String getBillToParty();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name CommitedDeliveryCreationDate */
    public static final String COLUMNNAME_CommitedDeliveryCreationDate = "CommitedDeliveryCreationDate";

	/** Set Commited Delivery Creation Date	  */
	public void setCommitedDeliveryCreationDate (Timestamp CommitedDeliveryCreationDate);

	/** Get Commited Delivery Creation Date	  */
	public Timestamp getCommitedDeliveryCreationDate();

    /** Column name CommitedDeliveryDate */
    public static final String COLUMNNAME_CommitedDeliveryDate = "CommitedDeliveryDate";

	/** Set Commited Delivery Date	  */
	public void setCommitedDeliveryDate (Timestamp CommitedDeliveryDate);

	/** Get Commited Delivery Date	  */
	public Timestamp getCommitedDeliveryDate();

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

    /** Column name DocumentType */
    public static final String COLUMNNAME_DocumentType = "DocumentType";

	/** Set Document Type.
	  * Document Type
	  */
	public void setDocumentType (String DocumentType);

	/** Get Document Type.
	  * Document Type
	  */
	public String getDocumentType();

    /** Column name FirstProductionDate */
    public static final String COLUMNNAME_FirstProductionDate = "FirstProductionDate";

	/** Set First Production Date	  */
	public void setFirstProductionDate (Timestamp FirstProductionDate);

	/** Get First Production Date	  */
	public Timestamp getFirstProductionDate();

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

    /** Column name LastProductionDate */
    public static final String COLUMNNAME_LastProductionDate = "LastProductionDate";

	/** Set Last Production Date	  */
	public void setLastProductionDate (Timestamp LastProductionDate);

	/** Get Last Production Date	  */
	public Timestamp getLastProductionDate();

    /** Column name LineAggregator */
    public static final String COLUMNNAME_LineAggregator = "LineAggregator";

	/** Set Line Aggregator	  */
	public void setLineAggregator (String LineAggregator);

	/** Get Line Aggregator	  */
	public String getLineAggregator();

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

    /** Column name PODD */
    public static final String COLUMNNAME_PODD = "PODD";

	/** Set PODD	  */
	public void setPODD (Timestamp PODD);

	/** Get PODD	  */
	public Timestamp getPODD();

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

    /** Column name POSDD */
    public static final String COLUMNNAME_POSDD = "POSDD";

	/** Set POSDD	  */
	public void setPOSDD (Timestamp POSDD);

	/** Get POSDD	  */
	public Timestamp getPOSDD();

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

    /** Column name RejectionStatus */
    public static final String COLUMNNAME_RejectionStatus = "RejectionStatus";

	/** Set Rejection Status	  */
	public void setRejectionStatus (boolean RejectionStatus);

	/** Get Rejection Status	  */
	public boolean isRejectionStatus();

    /** Column name RequestedDeliveryDate */
    public static final String COLUMNNAME_RequestedDeliveryDate = "RequestedDeliveryDate";

	/** Set Requested Delivery Date	  */
	public void setRequestedDeliveryDate (Timestamp RequestedDeliveryDate);

	/** Get Requested Delivery Date	  */
	public Timestamp getRequestedDeliveryDate();

    /** Column name SAP_SalesOrder_ID */
    public static final String COLUMNNAME_SAP_SalesOrder_ID = "SAP_SalesOrder_ID";

	/** Set SAP_SalesOrder	  */
	public void setSAP_SalesOrder_ID (int SAP_SalesOrder_ID);

	/** Get SAP_SalesOrder	  */
	public int getSAP_SalesOrder_ID();

    /** Column name SAP_SalesOrder_UU */
    public static final String COLUMNNAME_SAP_SalesOrder_UU = "SAP_SalesOrder_UU";

	/** Set SAP_SalesOrder_UU	  */
	public void setSAP_SalesOrder_UU (String SAP_SalesOrder_UU);

	/** Get SAP_SalesOrder_UU	  */
	public String getSAP_SalesOrder_UU();

    /** Column name SalesOrderItem */
    public static final String COLUMNNAME_SalesOrderItem = "SalesOrderItem";

	/** Set Sales Order Item	  */
	public void setSalesOrderItem (String SalesOrderItem);

	/** Get Sales Order Item	  */
	public String getSalesOrderItem();

    /** Column name SalesOrderNumber */
    public static final String COLUMNNAME_SalesOrderNumber = "SalesOrderNumber";

	/** Set Sales Order Number	  */
	public void setSalesOrderNumber (String SalesOrderNumber);

	/** Get Sales Order Number	  */
	public String getSalesOrderNumber();

    /** Column name ShipToParty */
    public static final String COLUMNNAME_ShipToParty = "ShipToParty";

	/** Set Ship To Party	  */
	public void setShipToParty (String ShipToParty);

	/** Get Ship To Party	  */
	public String getShipToParty();

    /** Column name ShipToPartyCountry */
    public static final String COLUMNNAME_ShipToPartyCountry = "ShipToPartyCountry";

	/** Set Ship To Party Country	  */
	public void setShipToPartyCountry (String ShipToPartyCountry);

	/** Get Ship To Party Country	  */
	public String getShipToPartyCountry();

    /** Column name ShipToPartyName */
    public static final String COLUMNNAME_ShipToPartyName = "ShipToPartyName";

	/** Set Ship To Party Name	  */
	public void setShipToPartyName (String ShipToPartyName);

	/** Get Ship To Party Name	  */
	public String getShipToPartyName();

    /** Column name SoldToParty */
    public static final String COLUMNNAME_SoldToParty = "SoldToParty";

	/** Set Sold To Party	  */
	public void setSoldToParty (String SoldToParty);

	/** Get Sold To Party	  */
	public String getSoldToParty();

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
