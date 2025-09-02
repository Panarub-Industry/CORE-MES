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

/** Generated Interface for MES_ProdOutput
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_MES_ProdOutput 
{

    /** TableName=MES_ProdOutput */
    public static final String Table_Name = "MES_ProdOutput";

    /** AD_Table_ID=1000013 */
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

    /** Column name Article */
    public static final String COLUMNNAME_Article = "Article";

	/** Set Article	  */
	public void setArticle (String Article);

	/** Get Article	  */
	public String getArticle();

    /** Column name Barcode */
    public static final String COLUMNNAME_Barcode = "Barcode";

	/** Set Barcode	  */
	public void setBarcode (String Barcode);

	/** Get Barcode	  */
	public String getBarcode();

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

    /** Column name KanbanNumber */
    public static final String COLUMNNAME_KanbanNumber = "KanbanNumber";

	/** Set Kanban Number	  */
	public void setKanbanNumber (String KanbanNumber);

	/** Get Kanban Number	  */
	public String getKanbanNumber();

    /** Column name LineAggregator */
    public static final String COLUMNNAME_LineAggregator = "LineAggregator";

	/** Set Line Aggregator	  */
	public void setLineAggregator (String LineAggregator);

	/** Get Line Aggregator	  */
	public String getLineAggregator();

    /** Column name MES_ProdOutput_ID */
    public static final String COLUMNNAME_MES_ProdOutput_ID = "MES_ProdOutput_ID";

	/** Set MES_ProdOutput	  */
	public void setMES_ProdOutput_ID (int MES_ProdOutput_ID);

	/** Get MES_ProdOutput	  */
	public int getMES_ProdOutput_ID();

    /** Column name MES_ProdOutput_UU */
    public static final String COLUMNNAME_MES_ProdOutput_UU = "MES_ProdOutput_UU";

	/** Set MES_ProdOutput_UU	  */
	public void setMES_ProdOutput_UU (String MES_ProdOutput_UU);

	/** Get MES_ProdOutput_UU	  */
	public String getMES_ProdOutput_UU();

    /** Column name MES_Trans_ID */
    public static final String COLUMNNAME_MES_Trans_ID = "MES_Trans_ID";

	/** Set MES_Trans_ID	  */
	public void setMES_Trans_ID (String MES_Trans_ID);

	/** Get MES_Trans_ID	  */
	public String getMES_Trans_ID();

    /** Column name MasterProductionOrder */
    public static final String COLUMNNAME_MasterProductionOrder = "MasterProductionOrder";

	/** Set Master Production Order	  */
	public void setMasterProductionOrder (String MasterProductionOrder);

	/** Get Master Production Order	  */
	public String getMasterProductionOrder();

    /** Column name MovementType */
    public static final String COLUMNNAME_MovementType = "MovementType";

	/** Set Movement Type.
	  * Method of moving the inventory
	  */
	public void setMovementType (String MovementType);

	/** Get Movement Type.
	  * Method of moving the inventory
	  */
	public String getMovementType();

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

    /** Column name PostingRemark */
    public static final String COLUMNNAME_PostingRemark = "PostingRemark";

	/** Set Posting Remark	  */
	public void setPostingRemark (String PostingRemark);

	/** Get Posting Remark	  */
	public String getPostingRemark();

    /** Column name PostingStatus */
    public static final String COLUMNNAME_PostingStatus = "PostingStatus";

	/** Set Posting Status	  */
	public void setPostingStatus (boolean PostingStatus);

	/** Get Posting Status	  */
	public boolean isPostingStatus();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

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

    /** Column name SizeCustomer */
    public static final String COLUMNNAME_SizeCustomer = "SizeCustomer";

	/** Set Size Customer	  */
	public void setSizeCustomer (String SizeCustomer);

	/** Get Size Customer	  */
	public String getSizeCustomer();

    /** Column name SizeFactory */
    public static final String COLUMNNAME_SizeFactory = "SizeFactory";

	/** Set Size Factory	  */
	public void setSizeFactory (String SizeFactory);

	/** Get Size Factory	  */
	public String getSizeFactory();

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
