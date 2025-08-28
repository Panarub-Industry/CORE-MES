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

/** Generated Interface for SAP_ProdOrderMaster
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_SAP_ProdOrderMaster 
{

    /** TableName=SAP_ProdOrderMaster */
    public static final String Table_Name = "SAP_ProdOrderMaster";

    /** AD_Table_ID=1000019 */
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

    /** Column name MasterProductionOrder */
    public static final String COLUMNNAME_MasterProductionOrder = "MasterProductionOrder";

	/** Set Master Production Order	  */
	public void setMasterProductionOrder (String MasterProductionOrder);

	/** Get Master Production Order	  */
	public String getMasterProductionOrder();

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

    /** Column name SAP_ProdOrderMaster_ID */
    public static final String COLUMNNAME_SAP_ProdOrderMaster_ID = "SAP_ProdOrderMaster_ID";

	/** Set Production Order Master	  */
	public void setSAP_ProdOrderMaster_ID (int SAP_ProdOrderMaster_ID);

	/** Get Production Order Master	  */
	public int getSAP_ProdOrderMaster_ID();

    /** Column name SAP_ProdOrderMaster_UU */
    public static final String COLUMNNAME_SAP_ProdOrderMaster_UU = "SAP_ProdOrderMaster_UU";

	/** Set SAP_ProdOrderMaster_UU	  */
	public void setSAP_ProdOrderMaster_UU (String SAP_ProdOrderMaster_UU);

	/** Get SAP_ProdOrderMaster_UU	  */
	public String getSAP_ProdOrderMaster_UU();

    /** Column name SAP_Sloc_ID */
    public static final String COLUMNNAME_SAP_Sloc_ID = "SAP_Sloc_ID";

	/** Set Storage Location	  */
	public void setSAP_Sloc_ID (int SAP_Sloc_ID);

	/** Get Storage Location	  */
	public int getSAP_Sloc_ID();

	public I_SAP_Sloc getSAP_Sloc() throws RuntimeException;

    /** Column name SalesOrderNumber */
    public static final String COLUMNNAME_SalesOrderNumber = "SalesOrderNumber";

	/** Set Sales Order Number	  */
	public void setSalesOrderNumber (String SalesOrderNumber);

	/** Get Sales Order Number	  */
	public String getSalesOrderNumber();

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
