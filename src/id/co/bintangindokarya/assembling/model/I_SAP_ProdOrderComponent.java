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

/** Generated Interface for SAP_ProdOrderComponent
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_SAP_ProdOrderComponent 
{

    /** TableName=SAP_ProdOrderComponent */
    public static final String Table_Name = "SAP_ProdOrderComponent";

    /** AD_Table_ID=1000009 */
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

    /** Column name ProcurementType */
    public static final String COLUMNNAME_ProcurementType = "ProcurementType";

	/** Set Procurement Type	  */
	public void setProcurementType (String ProcurementType);

	/** Get Procurement Type	  */
	public String getProcurementType();

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

    /** Column name ReservationItem */
    public static final String COLUMNNAME_ReservationItem = "ReservationItem";

	/** Set Reservation Item	  */
	public void setReservationItem (String ReservationItem);

	/** Get Reservation Item	  */
	public String getReservationItem();

    /** Column name ReservationNumber */
    public static final String COLUMNNAME_ReservationNumber = "ReservationNumber";

	/** Set Reservation Number	  */
	public void setReservationNumber (String ReservationNumber);

	/** Get Reservation Number	  */
	public String getReservationNumber();

    /** Column name SAP_ProdOrderComponent_ID */
    public static final String COLUMNNAME_SAP_ProdOrderComponent_ID = "SAP_ProdOrderComponent_ID";

	/** Set SAP_ProdOrderComponent	  */
	public void setSAP_ProdOrderComponent_ID (int SAP_ProdOrderComponent_ID);

	/** Get SAP_ProdOrderComponent	  */
	public int getSAP_ProdOrderComponent_ID();

    /** Column name SAP_ProdOrderComponent_UU */
    public static final String COLUMNNAME_SAP_ProdOrderComponent_UU = "SAP_ProdOrderComponent_UU";

	/** Set SAP_ProdOrderComponent_UU	  */
	public void setSAP_ProdOrderComponent_UU (String SAP_ProdOrderComponent_UU);

	/** Get SAP_ProdOrderComponent_UU	  */
	public String getSAP_ProdOrderComponent_UU();

    /** Column name SAP_ProdOrder_ID */
    public static final String COLUMNNAME_SAP_ProdOrder_ID = "SAP_ProdOrder_ID";

	/** Set SAP_ProdOrder	  */
	public void setSAP_ProdOrder_ID (int SAP_ProdOrder_ID);

	/** Get SAP_ProdOrder	  */
	public int getSAP_ProdOrder_ID();

	public I_SAP_ProdOrder getSAP_ProdOrder() throws RuntimeException;

    /** Column name SpecialProcurementType */
    public static final String COLUMNNAME_SpecialProcurementType = "SpecialProcurementType";

	/** Set Special Procurement Type	  */
	public void setSpecialProcurementType (String SpecialProcurementType);

	/** Get Special Procurement Type	  */
	public String getSpecialProcurementType();

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
