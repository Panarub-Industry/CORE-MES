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

/** Generated Interface for SAP_ProdOrder
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_SAP_ProdOrder 
{

    /** TableName=SAP_ProdOrder */
    public static final String Table_Name = "SAP_ProdOrder";

    /** AD_Table_ID=1000006 */
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

    /** Column name ActualRelease */
    public static final String COLUMNNAME_ActualRelease = "ActualRelease";

	/** Set Actual Release	  */
	public void setActualRelease (Timestamp ActualRelease);

	/** Get Actual Release	  */
	public Timestamp getActualRelease();

    /** Column name BasicFinish */
    public static final String COLUMNNAME_BasicFinish = "BasicFinish";

	/** Set Basic Finish	  */
	public void setBasicFinish (Timestamp BasicFinish);

	/** Get Basic Finish	  */
	public Timestamp getBasicFinish();

    /** Column name BasicStart */
    public static final String COLUMNNAME_BasicStart = "BasicStart";

	/** Set Basic Start	  */
	public void setBasicStart (Timestamp BasicStart);

	/** Get Basic Start	  */
	public Timestamp getBasicStart();

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

    /** Column name ChangeDateOrderMaster */
    public static final String COLUMNNAME_ChangeDateOrderMaster = "ChangeDateOrderMaster";

	/** Set Change Date Order Master	  */
	public void setChangeDateOrderMaster (Timestamp ChangeDateOrderMaster);

	/** Get Change Date Order Master	  */
	public Timestamp getChangeDateOrderMaster();

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

    /** Column name SAP_ProdOrder_ID */
    public static final String COLUMNNAME_SAP_ProdOrder_ID = "SAP_ProdOrder_ID";

	/** Set SAP_ProdOrder	  */
	public void setSAP_ProdOrder_ID (int SAP_ProdOrder_ID);

	/** Get SAP_ProdOrder	  */
	public int getSAP_ProdOrder_ID();

    /** Column name SAP_ProdOrder_UU */
    public static final String COLUMNNAME_SAP_ProdOrder_UU = "SAP_ProdOrder_UU";

	/** Set SAP_ProdOrder_UU	  */
	public void setSAP_ProdOrder_UU (String SAP_ProdOrder_UU);

	/** Get SAP_ProdOrder_UU	  */
	public String getSAP_ProdOrder_UU();

    /** Column name SAP_SalesOrder_ID */
    public static final String COLUMNNAME_SAP_SalesOrder_ID = "SAP_SalesOrder_ID";

	/** Set SAP_SalesOrder	  */
	public void setSAP_SalesOrder_ID (int SAP_SalesOrder_ID);

	/** Get SAP_SalesOrder	  */
	public int getSAP_SalesOrder_ID();

	public I_SAP_SalesOrder getSAP_SalesOrder() throws RuntimeException;

    /** Column name SlocIssuing */
    public static final String COLUMNNAME_SlocIssuing = "SlocIssuing";

	/** Set Sloc Issuing	  */
	public void setSlocIssuing (String SlocIssuing);

	/** Get Sloc Issuing	  */
	public String getSlocIssuing();

    /** Column name SlocReceiving */
    public static final String COLUMNNAME_SlocReceiving = "SlocReceiving";

	/** Set Sloc Receiving	  */
	public void setSlocReceiving (String SlocReceiving);

	/** Get Sloc Receiving	  */
	public String getSlocReceiving();

    /** Column name SystemStatus */
    public static final String COLUMNNAME_SystemStatus = "SystemStatus";

	/** Set System Status.
	  * Status of the system - Support priority depends on system status
	  */
	public void setSystemStatus (String SystemStatus);

	/** Get System Status.
	  * Status of the system - Support priority depends on system status
	  */
	public String getSystemStatus();

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
