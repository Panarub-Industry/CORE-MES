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

/** Generated Interface for MES_BarcodeDetail
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_MES_BarcodeDetail 
{

    /** TableName=MES_BarcodeDetail */
    public static final String Table_Name = "MES_BarcodeDetail";

    /** AD_Table_ID=1000005 */
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

    /** Column name IsComplete */
    public static final String COLUMNNAME_IsComplete = "IsComplete";

	/** Set Complete.
	  * It is complete
	  */
	public void setIsComplete (boolean IsComplete);

	/** Get Complete.
	  * It is complete
	  */
	public boolean isComplete();

    /** Column name MES_BarcodeDetail_ID */
    public static final String COLUMNNAME_MES_BarcodeDetail_ID = "MES_BarcodeDetail_ID";

	/** Set MES Production Barcode	  */
	public void setMES_BarcodeDetail_ID (int MES_BarcodeDetail_ID);

	/** Get MES Production Barcode	  */
	public int getMES_BarcodeDetail_ID();

    /** Column name MES_BarcodeDetail_UU */
    public static final String COLUMNNAME_MES_BarcodeDetail_UU = "MES_BarcodeDetail_UU";

	/** Set MES_ProdBarcode_UU	  */
	public void setMES_BarcodeDetail_UU (String MES_BarcodeDetail_UU);

	/** Get MES_ProdBarcode_UU	  */
	public String getMES_BarcodeDetail_UU();

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

	public I_MES_Barcode getMES_Barcode() throws RuntimeException;

    /** Column name OrderNumber */
    public static final String COLUMNNAME_OrderNumber = "OrderNumber";

	/** Set Order Number	  */
	public void setOrderNumber (String OrderNumber);

	/** Get Order Number	  */
	public String getOrderNumber();

    /** Column name ParentBarcode_ID */
    public static final String COLUMNNAME_ParentBarcode_ID = "ParentBarcode_ID";

	/** Set ParentBarcode 	  */
	public void setParentBarcode_ID (String ParentBarcode_ID);

	/** Get ParentBarcode 	  */
	public String getParentBarcode_ID();

    /** Column name QtyBarcode */
    public static final String COLUMNNAME_QtyBarcode = "QtyBarcode";

	/** Set Qty Barcode	  */
	public void setQtyBarcode (BigDecimal QtyBarcode);

	/** Get Qty Barcode	  */
	public BigDecimal getQtyBarcode();

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
