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

/** Generated Interface for SAP_Operation
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_SAP_Operation 
{

    /** TableName=SAP_Operation */
    public static final String Table_Name = "SAP_Operation";

    /** AD_Table_ID=1000010 */
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

    /** Column name OperationDesc */
    public static final String COLUMNNAME_OperationDesc = "OperationDesc";

	/** Set Operation Desc	  */
	public void setOperationDesc (String OperationDesc);

	/** Get Operation Desc	  */
	public String getOperationDesc();

    /** Column name OperationPhaseNumber */
    public static final String COLUMNNAME_OperationPhaseNumber = "OperationPhaseNumber";

	/** Set Operation Phase Number	  */
	public void setOperationPhaseNumber (String OperationPhaseNumber);

	/** Get Operation Phase Number	  */
	public String getOperationPhaseNumber();

    /** Column name SAP_Operation_ID */
    public static final String COLUMNNAME_SAP_Operation_ID = "SAP_Operation_ID";

	/** Set SAP_Operation	  */
	public void setSAP_Operation_ID (int SAP_Operation_ID);

	/** Get SAP_Operation	  */
	public int getSAP_Operation_ID();

    /** Column name SAP_Operation_UU */
    public static final String COLUMNNAME_SAP_Operation_UU = "SAP_Operation_UU";

	/** Set SAP_Operation_UU	  */
	public void setSAP_Operation_UU (String SAP_Operation_UU);

	/** Get SAP_Operation_UU	  */
	public String getSAP_Operation_UU();

    /** Column name SAP_ProdOrder_ID */
    public static final String COLUMNNAME_SAP_ProdOrder_ID = "SAP_ProdOrder_ID";

	/** Set SAP_ProdOrder	  */
	public void setSAP_ProdOrder_ID (int SAP_ProdOrder_ID);

	/** Get SAP_ProdOrder	  */
	public int getSAP_ProdOrder_ID();

	public I_SAP_ProdOrder getSAP_ProdOrder() throws RuntimeException;

    /** Column name SAP_WorkCenter_ID */
    public static final String COLUMNNAME_SAP_WorkCenter_ID = "SAP_WorkCenter_ID";

	/** Set SAP_WorkCenter	  */
	public void setSAP_WorkCenter_ID (int SAP_WorkCenter_ID);

	/** Get SAP_WorkCenter	  */
	public int getSAP_WorkCenter_ID();

	public I_SAP_WorkCenter getSAP_WorkCenter() throws RuntimeException;

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
